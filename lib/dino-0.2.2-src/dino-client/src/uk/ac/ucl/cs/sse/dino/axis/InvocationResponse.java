/**
 * InvocationResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package uk.ac.ucl.cs.sse.dino.axis;

public class InvocationResponse  implements java.io.Serializable {
    private java.lang.String invokedService;

    private uk.ac.ucl.cs.sse.dino.axis.Param[] outputParameters;

    public InvocationResponse() {
    }

    public InvocationResponse(
           java.lang.String invokedService,
           uk.ac.ucl.cs.sse.dino.axis.Param[] outputParameters) {
           this.invokedService = invokedService;
           this.outputParameters = outputParameters;
    }


    /**
     * Gets the invokedService value for this InvocationResponse.
     * 
     * @return invokedService
     */
    public java.lang.String getInvokedService() {
        return invokedService;
    }


    /**
     * Sets the invokedService value for this InvocationResponse.
     * 
     * @param invokedService
     */
    public void setInvokedService(java.lang.String invokedService) {
        this.invokedService = invokedService;
    }


    /**
     * Gets the outputParameters value for this InvocationResponse.
     * 
     * @return outputParameters
     */
    public uk.ac.ucl.cs.sse.dino.axis.Param[] getOutputParameters() {
        return outputParameters;
    }


    /**
     * Sets the outputParameters value for this InvocationResponse.
     * 
     * @param outputParameters
     */
    public void setOutputParameters(uk.ac.ucl.cs.sse.dino.axis.Param[] outputParameters) {
        this.outputParameters = outputParameters;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InvocationResponse)) return false;
        InvocationResponse other = (InvocationResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.invokedService==null && other.getInvokedService()==null) || 
             (this.invokedService!=null &&
              this.invokedService.equals(other.getInvokedService()))) &&
            ((this.outputParameters==null && other.getOutputParameters()==null) || 
             (this.outputParameters!=null &&
              java.util.Arrays.equals(this.outputParameters, other.getOutputParameters())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getInvokedService() != null) {
            _hashCode += getInvokedService().hashCode();
        }
        if (getOutputParameters() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOutputParameters());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getOutputParameters(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InvocationResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dino.sse.cs.ucl.ac.uk", "InvocationResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("invokedService");
        elemField.setXmlName(new javax.xml.namespace.QName("", "invokedService"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("outputParameters");
        elemField.setXmlName(new javax.xml.namespace.QName("", "outputParameters"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dino.sse.cs.ucl.ac.uk", "Param"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
