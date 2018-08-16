package co.koralat.co.bean;

import java.rmi.RemoteException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.swing.JOptionPane;
import javax.xml.rpc.ServiceException;

import co.koralat.com.ws.Contacto;
import co.koralat.com.ws.CrudContactoImpl;
import co.koralat.com.ws.CrudContactoImplServiceLocator;

/**
 * Esta clase sirve para administrar la interfaz index.xhtml , en esta se hace
 * uso de la clase tablaContactoBean para cargar todos los datos de los
 * contactos consultados en la BD en un DataTable, tambien , sirve para llamar
 * segun una opcion seleccionada en la interfaz si se quiere registrar ,
 * actualizar o eliminar un contacto.
 * 
 * @author Martin y Mariana
 *
 */
@ManagedBean(name = "contactoBean")
@RequestScoped
public class ContactoBean {

	private int identificador;
	private String nombre;
	private String apellido;
	private String numero;
	private String mensaje;
	private String ejecutarOperacion;
	private Contacto[] contactos;
	private CrudContactoImpl contactoImpl;

	@ManagedProperty("#{tablaContactoBean}")
	private tablaContactoBean tcb ;
	/**
	 * Constructor de la clase donde se inicializa un objeto de tipo
	 * tablaContactoBean para cargar la data table con los contactos de la BD.
	 * @throws ServiceException 
	 */
	public ContactoBean()
	{			
		try 
		{
			tcb=new tablaContactoBean();
			CrudContactoImplServiceLocator impl=new CrudContactoImplServiceLocator();
			contactoImpl= impl.getCrudContactoImplPort();
			cargarContactos();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que sirve para saber que operacion ejecutar segun una lista
	 * desplegable de opciones y saber que me todo llamar , ya sea registrar ,
	 * actualizar o eliminar
	 */
	public void ejecutar() 
	{	
			Contacto contacto=new Contacto();		
			switch (ejecutarOperacion) 
			{
			case "Registrar":		
				contacto.setIdentificador(this.identificador);
				contacto.setNombre(this.nombre);
				contacto.setApellido(this.apellido);
	     		contacto.setNumero(this.numero);	
	     		try {
	     		
					contactoImpl.registrarContacto(contacto, "posts","doc");
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				break;
			case "Eliminar":
				contacto = new Contacto();
				contacto.setIdentificador(this.identificador);
				contacto.setNombre(this.nombre);
				contacto.setApellido(this.apellido);
				contacto.setNumero(this.numero);	
				try {
					String index=contactoImpl.obtenerIndexContacto(String.valueOf(this.identificador));
					contactoImpl.eliminarContacto("posts","doc",index);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				break;
			case "Actualizar":
				contacto.setIdentificador(this.identificador);
				contacto.setNombre(this.nombre);
				contacto.setApellido(this.apellido);
				contacto.setNumero(this.numero);	
				try {
					String index=contactoImpl.obtenerIndexContacto(String.valueOf(this.identificador));
					contactoImpl.actualizarContacto(contacto, "posts","doc",index);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				break;
			}
			
			cargarContactos();
			
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Successful", "Your message: " + "Operacion satisfactorio"));
			context.addMessage(null, new FacesMessage("", ""));		
	}
	
	public void cargarContactos()
	{
		tcb.cargarContactos(contactoImpl);
		contactos=tcb.getContactos();
	}

	/**
	 * @return the identificador
	 */
	public int getIdentificador() {
		return identificador;
	}

	/**
	 * @param identificador
	 *            the identificador to set
	 */
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @return the contactos
	 */
	public Contacto[] getContactos() {
		return contactos;
	}

	/**
	 * @param contactos the contactos to set
	 */
	public void setContactos(Contacto[] contactos) {
		this.contactos = contactos;
	}

	/**
	 * @param apellido
	 *            the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero
	 *            the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje
	 *            the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * @return the ejecutarOperacion
	 */
	public String getEjecutarOperacion() {
		return ejecutarOperacion;
	}

	/**
	 * @param ejecutarOperacion
	 *            the ejecutarOperacion to set
	 */
	public void setEjecutarOperacion(String ejecutarOperacion) {
		this.ejecutarOperacion = ejecutarOperacion;
	}

	/**
	 * @return the tcb
	 */
	public tablaContactoBean getTcb() {
		return tcb;
	}

	/**
	 * @param tcb
	 *            the tcb to set
	 */
	public void setTcb(tablaContactoBean tcb) {
		this.tcb = tcb;
	}

}
