package uk.ac.ucl.cs.sse.dino;


/**
 * Represents the response from a service invocation. Stores the output
 * parameters from the invocation and other information about the invocation such
 * as the URI of the service sued.
 * 
 */
public final class InvocationResponse {
	private Param[] outputParameters;
	private String invokedService;
	
	/**
	 * Creates an empty invocation response.
	 *
	 */
	public InvocationResponse() {
		
	}

	/**
	 * Creates a new invocation response with the given output parameters.
	 * @param outputParameters the output parameters of the service invocation.
	 * @param invokedService the URI of the service impelmentation which was invoked.
	 */
	public InvocationResponse(Param[] outputParameters, String invokedService) {
		this.outputParameters = outputParameters;
		this.invokedService = invokedService;
	}

	/**
	 * Gets the URI of the service which was invoked.
	 * @return the URI of the invoked service.
	 */
	public String getInvokedService() {
		return invokedService;
	}
	
	/**
	 * Sets the URI of the service implemetnation which was invoked.
	 * @param invokedService
	 */
	public void setInvokedService(String invokedService) {
		this.invokedService = invokedService;
	}

	/**
	 * Gets the output parameters of the invoked service.
	 * @return an array of output parmeters.
	 */
	public Param[] getOutputParameters() {
		return outputParameters;
	}
	
	/**
	 * sets the output parameters of the invoked service.
	 * @param outputParameters
	 */
	public void setOutputParameters(Param[] outputParameters) {
		this.outputParameters = outputParameters;
	}

}
