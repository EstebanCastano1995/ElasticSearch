package co.koralat.com.ws;

public class CrudContactoImplProxy implements co.koralat.com.ws.CrudContactoImpl {
  private String _endpoint = null;
  private co.koralat.com.ws.CrudContactoImpl crudContactoImpl = null;
  
  public CrudContactoImplProxy() {
    _initCrudContactoImplProxy();
  }
  
  public CrudContactoImplProxy(String endpoint) {
    _endpoint = endpoint;
    _initCrudContactoImplProxy();
  }
  
  private void _initCrudContactoImplProxy() {
    try {
      crudContactoImpl = (new co.koralat.com.ws.CrudContactoImplServiceLocator()).getCrudContactoImplPort();
      if (crudContactoImpl != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)crudContactoImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)crudContactoImpl)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (crudContactoImpl != null)
      ((javax.xml.rpc.Stub)crudContactoImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public co.koralat.com.ws.CrudContactoImpl getCrudContactoImpl() {
    if (crudContactoImpl == null)
      _initCrudContactoImplProxy();
    return crudContactoImpl;
  }
  
  public void registrarContacto(co.koralat.com.ws.Contacto arg0, java.lang.String arg1, java.lang.String arg2) throws java.rmi.RemoteException{
    if (crudContactoImpl == null)
      _initCrudContactoImplProxy();
    crudContactoImpl.registrarContacto(arg0, arg1, arg2);
  }
  
  public java.lang.String obtenerIndexContacto(java.lang.String arg0) throws java.rmi.RemoteException{
    if (crudContactoImpl == null)
      _initCrudContactoImplProxy();
    return crudContactoImpl.obtenerIndexContacto(arg0);
  }
  
  public void actualizarContacto(co.koralat.com.ws.Contacto arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3) throws java.rmi.RemoteException{
    if (crudContactoImpl == null)
      _initCrudContactoImplProxy();
    crudContactoImpl.actualizarContacto(arg0, arg1, arg2, arg3);
  }
  
  public co.koralat.com.ws.Contacto[] listarContacto() throws java.rmi.RemoteException{
    if (crudContactoImpl == null)
      _initCrudContactoImplProxy();
    return crudContactoImpl.listarContacto();
  }
  
  public void eliminarContacto(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) throws java.rmi.RemoteException{
    if (crudContactoImpl == null)
      _initCrudContactoImplProxy();
    crudContactoImpl.eliminarContacto(arg0, arg1, arg2);
  }
  
  public boolean existeContacto(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) throws java.rmi.RemoteException{
    if (crudContactoImpl == null)
      _initCrudContactoImplProxy();
    return crudContactoImpl.existeContacto(arg0, arg1, arg2);
  }
  
  
}