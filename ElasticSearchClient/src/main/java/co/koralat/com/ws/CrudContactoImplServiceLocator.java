/**
 * CrudContactoImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.koralat.com.ws;

public class CrudContactoImplServiceLocator extends org.apache.axis.client.Service implements co.koralat.com.ws.CrudContactoImplService {

    public CrudContactoImplServiceLocator() {
    }


    public CrudContactoImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CrudContactoImplServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CrudContactoImplPort
    private java.lang.String CrudContactoImplPort_address = "http://localhost:8086/ws/CrudContactoImpl";

    public java.lang.String getCrudContactoImplPortAddress() {
        return CrudContactoImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CrudContactoImplPortWSDDServiceName = "CrudContactoImplPort";

    public java.lang.String getCrudContactoImplPortWSDDServiceName() {
        return CrudContactoImplPortWSDDServiceName;
    }

    public void setCrudContactoImplPortWSDDServiceName(java.lang.String name) {
        CrudContactoImplPortWSDDServiceName = name;
    }

    public co.koralat.com.ws.CrudContactoImpl getCrudContactoImplPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CrudContactoImplPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCrudContactoImplPort(endpoint);
    }

    public co.koralat.com.ws.CrudContactoImpl getCrudContactoImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            co.koralat.com.ws.CrudContactoImplPortBindingStub _stub = new co.koralat.com.ws.CrudContactoImplPortBindingStub(portAddress, this);
            _stub.setPortName(getCrudContactoImplPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCrudContactoImplPortEndpointAddress(java.lang.String address) {
        CrudContactoImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (co.koralat.com.ws.CrudContactoImpl.class.isAssignableFrom(serviceEndpointInterface)) {
                co.koralat.com.ws.CrudContactoImplPortBindingStub _stub = new co.koralat.com.ws.CrudContactoImplPortBindingStub(new java.net.URL(CrudContactoImplPort_address), this);
                _stub.setPortName(getCrudContactoImplPortWSDDServiceName());
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
        if ("CrudContactoImplPort".equals(inputPortName)) {
            return getCrudContactoImplPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://ws.com.koralat.co/", "CrudContactoImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://ws.com.koralat.co/", "CrudContactoImplPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CrudContactoImplPort".equals(portName)) {
            setCrudContactoImplPortEndpointAddress(address);
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
