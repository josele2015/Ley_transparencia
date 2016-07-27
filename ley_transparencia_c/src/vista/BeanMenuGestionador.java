package vista;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;




//import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import controlador.GestionAdministrador;
//import controlador.Programacion;
import modelo.Estado;

@ManagedBean
@ViewScoped
public class BeanMenuGestionador implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String opcion1="¿Quienes somos?";
	private String opcion2="Solicitudes";
	private String opcion3="Gestionar solicitudes";
	private String opcion4="Reportes";
	private String opcion5="Cerrar Sesión";

	private int i=0;

	public static String login;
	private List<Estado> lista2;
	private String cedula="";
	
	final String miCorreo = "respuestadesolicitudes@gmail.com";
    final String miContraseña = "respuestadesolicitudes&&";
    final String servidorSMTP = "smtp.gmail.com";
    final String puertoEnvio = "465";
    
    private String descripcion1="En este proyecto se desea desarrollar un sistema de información, en el cual está involucrada la facultad de ingeniería, el programa de sistemas y la facultad de derecho. Con el fin de tener control y llevar un seguimiento sobre la ley de transparencia y acceso a la información. Este software es para municipios de sexta categoría, principalmente para el municipio de San Francisco de Sales.\n";
	private String descripcion2="Este proyecto lo están realizando los estudiantes Herman José García Pacheco y Juan Carlos Sánchez Fajardo como opción de grado, el director es el ingeniero John Velandia.";
	private String descripcion3="Esta página tiene como fin facilitar a los usuario encontrar cualquier tipo de información que necesiten de una entidad, y de esta misma manera poder controlar la ley 1712 también conocida como ley de transparencia y acceso a la información, la cual consiste en permitir el acceso a cualquierto tipo de información siempre y cuando sea de caracter público. Para más información de esta ley pueden visitar\n";
	
	public String getDescripcion1() {
		return descripcion1;
	}

	public void setDescripcion1(String descripcion1) {
		this.descripcion1 = descripcion1;
	}

	public String getDescripcion2() {
		return descripcion2;
	}

	public void setDescripcion2(String descripcion2) {
		this.descripcion2 = descripcion2;
	}

	public String getDescripcion3() {
		return descripcion3;
	}

	public void setDescripcion3(String descripcion3) {
		this.descripcion3 = descripcion3;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}
	
	public String getCedula() {
		cedula=login;
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
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
	
/*	public void trabajo(){
		try {
            new Programacion().iniciarTarea();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
	}*/
}
