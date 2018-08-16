package co.koralat.co.bean;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import co.koralat.com.ws.Contacto;
import co.koralat.com.ws.CrudContactoImpl;


/**
 * Esta clase sirve para consultar los contactos en la BD e implementa la
 * interface serializable para ser un managedBean
 * 
 * @author Martin y Mariana
 *
 */
@ManagedBean(name = "tablaContactoBean")
@ApplicationScoped
public class tablaContactoBean implements Serializable {

	private static final long serialVersionUID = 8995118759276351827L;
	private Contacto[] contactos;

	/**
	 * Este metodo sirve para seleccionar todos los contactos de la BD recibe
	 * como parametro un objeto de tipo ContactoDAO la cual tiene la conexion y
	 * puede hacer CRUD en la BD.
	 * 
	 * @param personDAO
	 */
	public void cargarContactos(CrudContactoImpl contactoImpl) {
		try {
			contactos=contactoImpl.listarContacto();
		} catch (RemoteException e) 
		{
			e.printStackTrace();
		}
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





}
