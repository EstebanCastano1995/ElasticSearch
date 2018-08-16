/**
 * CrudContactoImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.koralat.com.ws;

public interface CrudContactoImpl extends java.rmi.Remote {
    public void registrarContacto(co.koralat.com.ws.Contacto arg0, java.lang.String arg1, java.lang.String arg2) throws java.rmi.RemoteException;
    public java.lang.String obtenerIndexContacto(java.lang.String arg0) throws java.rmi.RemoteException;
    public void actualizarContacto(co.koralat.com.ws.Contacto arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3) throws java.rmi.RemoteException;
    public co.koralat.com.ws.Contacto[] listarContacto() throws java.rmi.RemoteException;
    public void eliminarContacto(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) throws java.rmi.RemoteException;
    public boolean existeContacto(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) throws java.rmi.RemoteException;
}
