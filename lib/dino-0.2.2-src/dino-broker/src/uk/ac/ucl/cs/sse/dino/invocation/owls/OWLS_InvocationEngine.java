package uk.ac.ucl.cs.sse.dino.invocation.owls;

import java.io.FileNotFoundException;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.mindswap.owl.OWLClass;
import org.mindswap.owl.OWLDataProperty;
import org.mindswap.owl.OWLDataValue;
import org.mindswap.owl.OWLEntity;
import org.mindswap.owl.OWLFactory;
import org.mindswap.owl.OWLIndividual;
import org.mindswap.owl.OWLKnowledgeBase;
import org.mindswap.owl.OWLObjectProperty;
import org.mindswap.owl.OWLOntology;
import org.mindswap.owl.OWLValue;
import org.mindswap.owls.OWLSFactory;
import org.mindswap.owls.process.Input;
import org.mindswap.owls.process.Process;
import org.mindswap.owls.process.execution.ProcessExecutionEngine;
import org.mindswap.owls.service.Service;
import org.mindswap.query.ValueMap;
import org.mindswap.swrl.Variable;

import uk.ac.ucl.cs.sse.dino.Param;
import uk.ac.ucl.cs.sse.dino.doc.ServiceDescription;
import uk.ac.ucl.cs.sse.dino.doc.ServiceImplementation;
import uk.ac.ucl.cs.sse.dino.invocation.AbstractServiceInvocationEngine;
import uk.ac.ucl.cs.sse.dino.invocation.InvocationMonitorManager;
import uk.ac.ucl.cs.sse.dino.invocation.ServiceExecutionException;
import uk.ac.ucl.cs.sse.dino.invocation.ServiceInvocationException;

/**
 * Implementation of the ServiceInvocationEngine service for OWL-S services.
 * 
 */
public class OWLS_InvocationEngine extends AbstractServiceInvocationEngine {
	private OWLKnowledgeBase kb = OWLFactory.createKB();

	/**
	 * Creates an invocation engine for executing OWL-S services.
	 * 
	 * @param monitorManager
	 *            the monitor manager to send invocation events to.
	 * 
	 */
	public OWLS_InvocationEngine(final InvocationMonitorManager monitorManager) {
		super(monitorManager);
		// WSDLOperation.DEBUG = false;
		// Utils.DEBUG = false;
	}

	private ServiceDescription buildServiceDescription(
			final ServiceImplementation serviceImplementation)
			throws ServiceExecutionException {
		if (serviceImplementation == null) {
			throw new NullPointerException(
					"Null parameter: serviceImplementation");
		}

		ProcessExecutionEngine exec = OWLSFactory.createExecutionEngine();

		try {
			OWLOntology ont = kb.read(serviceImplementation
					.getlocalServiceDescription().toURI());
			Service service = ont.getService();

			if (service == null) {
				throw new ServiceExecutionException(
						"Could not find OWL-S service element. Make sure OWL-S file is version 1.1");
			}
			Process process = service.getProcess();
			if (process == null) {
				throw new ServiceExecutionException(
						"Could not find service process.");
			}

			OWLS_ServiceDescription description = new OWLS_ServiceDescription(
					exec, process, serviceImplementation
							.getServiceDescriptionURI());
			return description;

		} catch (FileNotFoundException e) {
			throw new ServiceExecutionException(
					"Couldn't find service description "
							+ serviceImplementation.getServiceDescriptionURI()
							+ ".");
		}
	}

	@Override
	public Param[] tryInvokeService(final ServiceImplementation impl,
			final Param[] params) throws ServiceInvocationException,
			ServiceExecutionException {
		// Preconditions
		if (impl == null) {
			throw new NullPointerException("Null parameter: descrption");
		}
		if (params == null) {
			throw new NullPointerException("Null parameter: params");
		}

		// Method logic
		ServiceDescription description = impl.getServiceDescription();
		if (description == null) {
			description = buildServiceDescription(impl);
			impl.setServiceDescription(description);
		}

		if (!(description instanceof OWLS_ServiceDescription)) {
			throw new ServiceInvocationException(
					"Service description is not an OWL-S service descrption");
		}
		OWLS_ServiceDescription owlsDescrption = (OWLS_ServiceDescription) description;

		ValueMap values = paramsToValueMap(params, owlsDescrption.process);

		// Execute the service.
		try {
			values = owlsDescrption.exec
					.execute(owlsDescrption.process, values);
		} catch (Exception e) {
			throw new ServiceExecutionException("Error executing service.", e);
		}
		Param[] outputParams = valueMapToParams(values);

		// Postconditions
		assert outputParams != null;
		return outputParams;

	}

	private static class OWLS_ServiceDescription extends ServiceDescription {
		private ProcessExecutionEngine exec;

		private Process process;

		/**
		 * Creates an OWL-S service description.
		 * 
		 * @param exec
		 *            the process execution engine for executing this service.
		 * @param process
		 *            the OWL-S process which represents the service to be
		 *            executed.
		 * @param serviceDescrptionURI
		 *            the URI of the service description.
		 */
		public OWLS_ServiceDescription(ProcessExecutionEngine exec,
				Process process, URI serviceDescrptionURI) {
			super(serviceDescrptionURI);
			this.exec = exec;
			this.process = process;
		}
	}

	private ValueMap paramsToValueMap(final Param[] params, Process process)
			throws ServiceInvocationException {
		// Preconditions
		assert params != null;

		ValueMap values = new ValueMap();
		for (Param p : params) {
			String name = p.getName();
			OWLValue value = paramToValue(p, kb);
			Input var = process.getInput(name);
			if (var == null) {
				throw new InvalidInvocationParameterException(name
						+ " is not a valid parameter for this service.");
			}
			values.setValue(var, value);
		}

		// Postconditions
		assert values != null;
		assert values.size() == params.length;
		return values;
	}

	private OWLValue paramToValue(final Param p, final OWLKnowledgeBase kb)
			throws ServiceInvocationException {
		// Preconditions
		assert p != null;
		assert kb != null;

		String type = p.getType();
		if (type.equals("string")) {
			return kb.createDataValue(p.getValue());
		} else if (type.equals("class")) {
			OWLClass indClass = kb.getClass(URI.create(p.getValue()));
			OWLIndividual ind = kb.createInstance(indClass);

			// If the type is a class, its properties will be explicitly
			// provided in the message.
			addProperties(ind, p, kb);

			// Postconditions
			assert ind != null;
			return ind;
		} else if (type.equals("individual")) {
			return kb.getIndividual(URI.create(p.getValue()));
		} else {
			throw new ServiceInvocationException("Unknown input type: " + type);
		}
	}

	private void addProperties(final OWLIndividual ind, final Param p,
			final OWLKnowledgeBase kb) throws ServiceInvocationException {
		// Preconditions
		assert ind != null;
		assert p != null;
		assert kb != null;

		Param[] propertyArray = p.getProperties();
		if (propertyArray != null) {
			for (Param property : propertyArray) {
				OWLValue value = paramToValue(property, kb);

				if (value.isIndividual()) {
					OWLObjectProperty id = kb.getObjectProperty(URI
							.create(property.getName()));
					ind.addProperty(id, (OWLIndividual) value);
				} else if (value.isDataValue()) {
					OWLDataProperty id = kb.createDataProperty(URI
							.create(property.getName()));
					ind.addProperty(id, (OWLDataValue) value);
				}
			}
		}
	}

	private Param[] valueMapToParams(final ValueMap values) {
		// Preconditions
		assert values != null;

		Param[] params = new Param[values.size()];
		int count = 0;
		for (Object o : values.getVariables()) {
			Variable var = (Variable) o;
			OWLValue value = values.getValue(var);
			params[count] = valueToParam(var, value, false);

			count++;
		}
		// Postconditions
		assert params != null;
		assert values.size() == params.length;
		return params;
	}

	private Param valueToParam(final OWLEntity var, final OWLValue value,
			final boolean isProperty) {
		// Preconditions
		assert var != null;
		assert value != null;

		Param p = new Param();

		if (isProperty) {
			p.setName(var.getURI().toString());
		} else {
			p.setName(var.getLocalName());
		}

		if (value.isIndividual()) {
			OWLIndividual ind = (OWLIndividual) value;
			if (ind.isAnon()) {
				p.setType("class");
				p.setValue(ind.getType().getURI().toString());

				// In this case there should be properties
				addPropertiesToParam(p, ind);

			} else {
				p.setType("individual");
				p.setValue(ind.getURI().toString());
			}
		} else if (value.isDataValue()) {
			p.setType("string");
			p.setValue(((OWLDataValue) value).getLexicalValue());
		}

		// Postconditions
		assert p != null;
		return p;
	}

	private void addPropertiesToParam(final Param p, final OWLIndividual ind) {
		// Preconditions
		assert p != null;
		assert ind != null;

		Map properties = ind.getProperties();

		List<Param> outProperties = new LinkedList<Param>();

		for (Object property : properties.keySet()) {
			List propertyList;
			if (property instanceof OWLDataProperty) {
				propertyList = ind.getProperties((OWLDataProperty) property);
			} else {
				propertyList = ind.getProperties((OWLObjectProperty) property);
			}

			for (Object value : propertyList) {
				OWLValue owlValue = (OWLValue) value;
				Param outProp = valueToParam((OWLEntity) property, owlValue,
						true);
				outProperties.add(outProp);
			}
		}
		p.setProperties(outProperties.toArray(new Param[0]));
	}

}
