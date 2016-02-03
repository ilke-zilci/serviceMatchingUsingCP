/**
 * DinoBrokerServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package uk.ac.ucl.cs.sse.dino.axis;

public class DinoBrokerServiceLocator extends org.apache.axis.client.Service implements uk.ac.ucl.cs.sse.dino.axis.DinoBrokerService {

    public DinoBrokerServiceLocator() {
    }


    public DinoBrokerServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public DinoBrokerServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for DinoBrokerService
    private java.lang.String DinoBrokerService_address = "http://localhost:8080/axis/services/DinoBrokerService";

    public java.lang.String getDinoBrokerServiceAddress() {
        return DinoBrokerService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String DinoBrokerServiceWSDDServiceName = "DinoBrokerService";

    public java.lang.String getDinoBrokerServiceWSDDServiceName() {
        return DinoBrokerServiceWSDDServiceName;
    }

    public void setDinoBrokerServiceWSDDServiceName(java.lang.String name) {
        DinoBrokerServiceWSDDServiceName = name;
    }

    public uk.ac.ucl.cs.sse.dino.axis.DinoBroker getDinoBrokerService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(DinoBrokerService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getDinoBrokerService(endpoint);
    }

    public uk.ac.ucl.cs.sse.dino.axis.DinoBroker getDinoBrokerService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            uk.ac.ucl.cs.sse.dino.axis.DinoBrokerServiceSoapBindingStub _stub = new uk.ac.ucl.cs.sse.dino.axis.DinoBrokerServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getDinoBrokerServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setDinoBrokerServiceEndpointAddress(java.lang.String address) {
        DinoBrokerService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (uk.ac.ucl.cs.sse.dino.axis.DinoBroker.class.isAssignableFrom(serviceEndpointInterface)) {
                uk.ac.ucl.cs.sse.dino.axis.DinoBrokerServiceSoapBindingStub _stub = new uk.ac.ucl.cs.sse.dino.axis.DinoBrokerServiceSoapBindingStub(new java.net.URL(DinoBrokerService_address), this);
                _stub.setPortName(getDinoBrokerServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("DinoBrokerService".equals(inputPortName)) {
            return getDinoBrokerService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.cs.ucl.ac.uk/sensoria/dino", "DinoBrokerService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.cs.ucl.ac.uk/sensoria/dino", "DinoBrokerService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("DinoBrokerService".equals(portName)) {
            setDinoBrokerServiceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
