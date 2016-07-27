package vista;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import java.io.Serializable;
import java.util.List;

import controlador.GestionAdministrador;

import javax.annotation.PostConstruct;

import modelo.Peticion;
import modelo.Gestionador;
import vista.Listas_de_las_Solicitudes;

@ManagedBean
@ViewScoped
public class BeanSolicitud implements Serializable{
	/**
	 * 
	 */
	private String productId;
	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId){
		this.productId = productId;
	}
	private static final long serialVersionUID = 7L;

	private int i=1;
	
	private String opcion1="¿Quienes somos?";
	private String opcion2="Solicitudes";
	private String opcion3="Gestionar solicitudes";
	private String opcion4="Reportes";
	private String opcion5="Cerrar Sesión";

	public static int cedulaGestionador;
	
	List<Listas_de_las_Solicitudes> lista;
	
	public List<Listas_de_las_Solicitudes> getLista() {
		return lista;
	}

	public void setLista(List<Listas_de_las_Solicitudes> lista) {
		this.lista = lista;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public String getOpcion1() {
		return opcion1;
	}

	public void setOpcion1(String opcion1) {
		this.opcion1 = opcion1;
	}

	public String getOpcion2() {
		return opcion2;
	}

	public void setOpcion2(String opcion2) {
		this.opcion2 = opcion2;
	}

	public String getOpcion3() {
		return opcion3;
	}

	public void setOpcion3(String opcion3) {
		this.opcion3 = opcion3;
	}

	public String getOpcion4() {
		return opcion4;
	}

	public void setOpcion4(String opcion4) {
		this.opcion4 = opcion4;
	}
	public String getOpcion5() {
		return opcion5;
	}

	public void setOpcion5(String opcion5) {
		this.opcion5 = opcion5;
	}
	@PostConstruct
	public void init() {
		System.out.println(cedulaGestionador);
		if(cedulaGestionador>0){
			GestionAdministrador usuario_Administrador=new GestionAdministrador ();					
			lista=usuario_Administrador.Mostrar_las_peticiones_que_han_llegado_al_Usuario_Administrador(cedulaGestionador);
		}		
	}
	
	public String cerrarsesion(){
    	BeanMenu bm = new BeanMenu();
		bm.email=null;
		bm.direccion=null;

		BeanRegistrarSolicitud bs = new BeanRegistrarSolicitud();
		bs.direccion = null;
		bs.email = null;
		bs.nombre = null;
		bs.apellido = null;
		bs.cedula = 0;
		
		BeanConsultaCiudadana bcc= new BeanConsultaCiudadana();
		bcc.cedula = 0;
		bcc.email = null;
		bcc.direccion = null;
		
		BeanRegistrarReposicion brr = new BeanRegistrarReposicion();
		brr.cedula=0;
		brr.email = null;
		brr.direccion = null;
		brr.celular = null;
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate(); //Cierre de sesion
        }
        return null;
        //return "index.xhtml";    	
    }
}
