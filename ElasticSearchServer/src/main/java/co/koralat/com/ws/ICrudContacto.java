package co.koralat.com.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import co.koralat.com.vo.Contacto;
@WebService
public interface ICrudContacto 
{
	@WebMethod
	public void registrarContacto(Contacto contacto,String bd,String nombreDoc);
	@WebMethod
	public void eliminarContacto(String bd,String nombreDoc,String index);
	@WebMethod
	public void actualizarContacto(Contacto contacto,String bd,String nombreDoc,String index);
	@WebMethod
	public List<Contacto> listarContacto();
	
	public boolean existeContacto(String bd,String nombreDoc,String index);
	public String obtenerIndexContacto(String identificacion);
}
