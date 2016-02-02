/**
 * DinoBrokerServiceSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package uk.ac.ucl.cs.sse.dino.axis;

public class DinoBrokerServiceSoapBindingStub extends org.apache.axis.client.Stub implements uk.ac.ucl.cs.sse.dino.axis.DinoBroker {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[7];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("startSession");
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "startSessionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("quitSession");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "sessionId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("registerReqDoc");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "sessionId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "reqDocURL"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.cs.ucl.ac.uk/sensoria/dino", "fault"),
                      "uk.ac.ucl.cs.sse.dino.axis.DinoDocReadException",
                      new javax.xml.namespace.QName("http://doc.dino.sse.cs.ucl.ac.uk", "DinoDocReadException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.cs.ucl.ac.uk/sensoria/dino", "fault"),
                      "uk.ac.ucl.cs.sse.dino.axis.DinoIdException",
                      new javax.xml.namespace.QName("http://dino.sse.cs.ucl.ac.uk", "DinoIdException"), 
                      true
                     ));
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("registerCapDoc");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "sessionId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "capDocURL"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.cs.ucl.ac.uk/sensoria/dino", "fault"),
                      "uk.ac.ucl.cs.sse.dino.axis.DinoDocReadException",
                      new javax.xml.namespace.QName("http://doc.dino.sse.cs.ucl.ac.uk", "DinoDocReadException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.cs.ucl.ac.uk/sensoria/dino", "fault"),
                      "uk.ac.ucl.cs.sse.dino.axis.DinoIdException",
                      new javax.xml.namespace.QName("http://dino.sse.cs.ucl.ac.uk", "DinoIdException"), 
                      true
                     ));
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("selectMode");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "sessionId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "requestedModes"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.cs.ucl.ac.uk/sensoria/dino", "ArrayOf_soapenc_string"), java.lang.String[].class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://dino.sse.cs.ucl.ac.uk", "SelectModeResponse"));
        oper.setReturnClass(uk.ac.ucl.cs.sse.dino.axis.SelectModeResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "selectModeReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.cs.ucl.ac.uk/sensoria/dino", "fault"),
                      "uk.ac.ucl.cs.sse.dino.axis.DinoIdException",
                      new javax.xml.namespace.QName("http://dino.sse.cs.ucl.ac.uk", "DinoIdException"), 
                      true
                     ));
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("invokeService");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "sessionId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "serviceName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "params"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.cs.ucl.ac.uk/sensoria/dino", "ArrayOf_tns2_Param"), uk.ac.ucl.cs.sse.dino.axis.Param[].class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://dino.sse.cs.ucl.ac.uk", "InvocationResponse"));
        oper.setReturnClass(uk.ac.ucl.cs.sse.dino.axis.InvocationResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "invokeServiceReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.cs.ucl.ac.uk/sensoria/dino", "fault"),
                      "uk.ac.ucl.cs.sse.dino.axis.ServiceInvocationException",
                      new javax.xml.namespace.QName("http://invocation.dino.sse.cs.ucl.ac.uk", "ServiceInvocationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.cs.ucl.ac.uk/sensoria/dino", "fault"),
                      "uk.ac.ucl.cs.sse.dino.axis.DinoIdException",
                      new javax.xml.namespace.QName("http://dino.sse.cs.ucl.ac.uk", "DinoIdException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.cs.ucl.ac.uk/sensoria/dino", "fault"),
                      "uk.ac.ucl.cs.sse.dino.axis.UnsupportedServiceException",
                      new javax.xml.namespace.QName("http://dino.sse.cs.ucl.ac.uk", "UnsupportedServiceException"), 
                      true
                     ));
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateAttributeForInvocation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "sessionId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "serviceURI"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "attributeName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "value"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"), double.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.cs.ucl.ac.uk/sensoria/dino", "fault"),
                      "uk.ac.ucl.cs.sse.dino.axis.DinoIdException",
                      new javax.xml.namespace.QName("http://dino.sse.cs.ucl.ac.uk", "DinoIdException"), 
                      true
                     ));
        _operations[6] = oper;

    }

    public DinoBrokerServiceSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public DinoBrokerServiceSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public DinoBrokerServiceSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://dino.sse.cs.ucl.ac.uk", "DinoIdException");
            cachedSerQNames.add(qName);
            cls = uk.ac.ucl.cs.sse.dino.axis.DinoIdException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dino.sse.cs.ucl.ac.uk", "InvocationResponse");
            cachedSerQNames.add(qName);
            cls = uk.ac.ucl.cs.sse.dino.axis.InvocationResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dino.sse.cs.ucl.ac.uk", "Param");
            cachedSerQNames.add(qName);
            cls = uk.ac.ucl.cs.sse.dino.axis.Param.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dino.sse.cs.ucl.ac.uk", "SelectModeResponse");
            cachedSerQNames.add(qName);
            cls = uk.ac.ucl.cs.sse.dino.axis.SelectModeResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dino.sse.cs.ucl.ac.uk", "UnsupportedServiceException");
            cachedSerQNames.add(qName);
            cls = uk.ac.ucl.cs.sse.dino.axis.UnsupportedServiceException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://doc.dino.sse.cs.ucl.ac.uk", "DinoDocReadException");
            cachedSerQNames.add(qName);
            cls = uk.ac.ucl.cs.sse.dino.axis.DinoDocReadException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://invocation.dino.sse.cs.ucl.ac.uk", "ServiceInvocationException");
            cachedSerQNames.add(qName);
            cls = uk.ac.ucl.cs.sse.dino.axis.ServiceInvocationException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.cs.ucl.ac.uk/sensoria/dino", "ArrayOf_soapenc_string");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.cs.ucl.ac.uk/sensoria/dino", "ArrayOf_tns2_Param");
            cachedSerQNames.add(qName);
            cls = uk.ac.ucl.cs.sse.dino.axis.Param[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://dino.sse.cs.ucl.ac.uk", "Param");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
                    _call.setEncodingStyle(org.apache.axis.Constants.URI_SOAP11_ENC);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public java.lang.String startSession() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.cs.ucl.ac.uk/sensoria/dino", "startSession"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void quitSession(java.lang.String sessionId) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.cs.ucl.ac.uk/sensoria/dino", "quitSession"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {sessionId});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void registerReqDoc(java.lang.String sessionId, java.lang.String reqDocURL) throws java.rmi.RemoteException, uk.ac.ucl.cs.sse.dino.axis.DinoDocReadException, uk.ac.ucl.cs.sse.dino.axis.DinoIdException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.cs.ucl.ac.uk/sensoria/dino", "registerReqDoc"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {sessionId, reqDocURL});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof uk.ac.ucl.cs.sse.dino.axis.DinoDocReadException) {
              throw (uk.ac.ucl.cs.sse.dino.axis.DinoDocReadException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof uk.ac.ucl.cs.sse.dino.axis.DinoIdException) {
              throw (uk.ac.ucl.cs.sse.dino.axis.DinoIdException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void registerCapDoc(java.lang.String sessionId, java.lang.String capDocURL) throws java.rmi.RemoteException, uk.ac.ucl.cs.sse.dino.axis.DinoDocReadException, uk.ac.ucl.cs.sse.dino.axis.DinoIdException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.cs.ucl.ac.uk/sensoria/dino", "registerCapDoc"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {sessionId, capDocURL});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof uk.ac.ucl.cs.sse.dino.axis.DinoDocReadException) {
              throw (uk.ac.ucl.cs.sse.dino.axis.DinoDocReadException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof uk.ac.ucl.cs.sse.dino.axis.DinoIdException) {
              throw (uk.ac.ucl.cs.sse.dino.axis.DinoIdException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public uk.ac.ucl.cs.sse.dino.axis.SelectModeResponse selectMode(java.lang.String sessionId, java.lang.String[] requestedModes) throws java.rmi.RemoteException, uk.ac.ucl.cs.sse.dino.axis.DinoIdException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.cs.ucl.ac.uk/sensoria/dino", "selectMode"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {sessionId, requestedModes});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (uk.ac.ucl.cs.sse.dino.axis.SelectModeResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (uk.ac.ucl.cs.sse.dino.axis.SelectModeResponse) org.apache.axis.utils.JavaUtils.convert(_resp, uk.ac.ucl.cs.sse.dino.axis.SelectModeResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof uk.ac.ucl.cs.sse.dino.axis.DinoIdException) {
              throw (uk.ac.ucl.cs.sse.dino.axis.DinoIdException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public uk.ac.ucl.cs.sse.dino.axis.InvocationResponse invokeService(java.lang.String sessionId, java.lang.String serviceName, uk.ac.ucl.cs.sse.dino.axis.Param[] params) throws java.rmi.RemoteException, uk.ac.ucl.cs.sse.dino.axis.ServiceInvocationException, uk.ac.ucl.cs.sse.dino.axis.DinoIdException, uk.ac.ucl.cs.sse.dino.axis.UnsupportedServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.cs.ucl.ac.uk/sensoria/dino", "invokeService"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {sessionId, serviceName, params});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (uk.ac.ucl.cs.sse.dino.axis.InvocationResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (uk.ac.ucl.cs.sse.dino.axis.InvocationResponse) org.apache.axis.utils.JavaUtils.convert(_resp, uk.ac.ucl.cs.sse.dino.axis.InvocationResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof uk.ac.ucl.cs.sse.dino.axis.ServiceInvocationException) {
              throw (uk.ac.ucl.cs.sse.dino.axis.ServiceInvocationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof uk.ac.ucl.cs.sse.dino.axis.DinoIdException) {
              throw (uk.ac.ucl.cs.sse.dino.axis.DinoIdException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof uk.ac.ucl.cs.sse.dino.axis.UnsupportedServiceException) {
              throw (uk.ac.ucl.cs.sse.dino.axis.UnsupportedServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void updateAttributeForInvocation(java.lang.String sessionId, java.lang.String serviceURI, java.lang.String attributeName, double value) throws java.rmi.RemoteException, uk.ac.ucl.cs.sse.dino.axis.DinoIdException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.cs.ucl.ac.uk/sensoria/dino", "updateAttributeForInvocation"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {sessionId, serviceURI, attributeName, new java.lang.Double(value)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof uk.ac.ucl.cs.sse.dino.axis.DinoIdException) {
              throw (uk.ac.ucl.cs.sse.dino.axis.DinoIdException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

}
