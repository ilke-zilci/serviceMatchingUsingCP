/**
 * DinoBroker.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package uk.ac.ucl.cs.sse.dino.axis;

public interface DinoBroker extends java.rmi.Remote {
    public java.lang.String startSession() throws java.rmi.RemoteException;
    public void quitSession(java.lang.String sessionId) throws java.rmi.RemoteException;
    public void registerReqDoc(java.lang.String sessionId, java.lang.String reqDocURL) throws java.rmi.RemoteException, uk.ac.ucl.cs.sse.dino.axis.DinoDocReadException, uk.ac.ucl.cs.sse.dino.axis.DinoIdException;
    public void registerCapDoc(java.lang.String sessionId, java.lang.String capDocURL) throws java.rmi.RemoteException, uk.ac.ucl.cs.sse.dino.axis.DinoDocReadException, uk.ac.ucl.cs.sse.dino.axis.DinoIdException;
    public uk.ac.ucl.cs.sse.dino.axis.SelectModeResponse selectMode(java.lang.String sessionId, java.lang.String[] requestedModes) throws java.rmi.RemoteException, uk.ac.ucl.cs.sse.dino.axis.DinoIdException;
    public uk.ac.ucl.cs.sse.dino.axis.InvocationResponse invokeService(java.lang.String sessionId, java.lang.String serviceName, uk.ac.ucl.cs.sse.dino.axis.Param[] params) throws java.rmi.RemoteException, uk.ac.ucl.cs.sse.dino.axis.ServiceInvocationException, uk.ac.ucl.cs.sse.dino.axis.DinoIdException, uk.ac.ucl.cs.sse.dino.axis.UnsupportedServiceException;
    public void updateAttributeForInvocation(java.lang.String sessionId, java.lang.String serviceURI, java.lang.String attributeName, double value) throws java.rmi.RemoteException, uk.ac.ucl.cs.sse.dino.axis.DinoIdException;
}
