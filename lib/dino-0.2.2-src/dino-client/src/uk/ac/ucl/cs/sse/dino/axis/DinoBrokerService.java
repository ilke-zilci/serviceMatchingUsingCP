/**
 * DinoBrokerService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package uk.ac.ucl.cs.sse.dino.axis;

public interface DinoBrokerService extends javax.xml.rpc.Service {
    public java.lang.String getDinoBrokerServiceAddress();

    public uk.ac.ucl.cs.sse.dino.axis.DinoBroker getDinoBrokerService() throws javax.xml.rpc.ServiceException;

    public uk.ac.ucl.cs.sse.dino.axis.DinoBroker getDinoBrokerService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
