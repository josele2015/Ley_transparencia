package vista;


import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import java.io.Serializable;
import java.math.BigInteger;
 
@ManagedBean
public class BeanMenu implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String opcion1="Quienes somos?";
	private String opcion2="Realizar petición";
	private String opcion3="Consulta ciudadana";
	private String opcion4="Reposición";
	private String opcion5="Cerra Sesión";
	private String emailsolicitante="";
	private String direccionsolicitante="";
	private BigInteger celularsolicitante;
	
	public static String email;
	public static String direccion;
	public static BigInteger celular;
	public static String nombre;
	public static String apellido;
	private String nombresolicitante;
	private String apellidosolicitante;
	private String bienvenida="Bienvenido";
	
	private String descripcion1="En este proyecto se desea desarrollar un sistema de información, en el cual está involucrada la facultad de ingeniería, el programa de sistemas y la facultad de derecho. Con el fin de tener control y llevar un seguimiento sobre la ley de transparencia y acceso a la información. Este software es para municipios de sexta categoría, principalmente para el municipio de San Francisco de Sales.\n";
	private String descripcion2="Este proyecto lo están realizando los estudiantes Herman José García Pacheco y Juan Carlos Sánchez Fajardo como opción de grado, el director es el ingeniero John Velandia.";
	private String descripcion3="Esta página tiene como fin facilitar a los usuario encontrar cualquier tipo de información que necesiten de una entidad, y de esta misma manera poder controlar la ley 1712 también conocida como ley de transparencia y acceso a la información, la cual consiste en permitir el acceso a cualquierto tipo de información siempre y cuando sea de caracter público. Para más información de esta ley pueden visitar\n";
	
	public String getDescripcion3() {
		return descripcion3;
	}

	public void setDescripcion3(String descripcion3) {
		this.descripcion3 = descripcion3;
	}

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

	public String getNombresolicitante() {
		this.nombresolicitante=nombre;
		return nombresolicitante;
	}

	public void setNombresolicitante(String nombresolicitante) {		
		this.nombresolicitante = nombresolicitante;
		this.nombresolicitante=nombre;
	}

	public String getApellidosolicitante() {
		this.apellidosolicitante=apellido;
		return apellidosolicitante;
	}

	public void setApellidosolicitante(String apellidosolicitante) {
		this.apellidosolicitante = apellidosolicitante;
		this.apellidosolicitante=apellido;
	}

	public String getBienvenida() {
		return bienvenida;
	}

	public void setBienvenida(String bienvenida) {
		this.bienvenida = bienvenida;
	}

	public static String getNombre() {
		return nombre;
	}

	public static void setNombre(String nombre) {
		BeanMenu.nombre = nombre;
	}

	public static String getApellido() {
		return apellido;
	}

	public static void setApellido(String apellido) {
		BeanMenu.apellido = apellido;
	}

	public String getEmailsolicitante() {
		this.emailsolicitante=email;
		return emailsolicitante;
	}

	public void setEmailsolicitante(String emailsolicitante) {
		this.emailsolicitante=email;
		this.emailsolicitante = emailsolicitante;
	}

	public String getDireccionsolicitante() {
		this.direccionsolicitante=direccion;
		return direccionsolicitante;
	}

	public void setDireccionsolicitante(String direccionsolicitante) {
		this.direccionsolicitante = direccionsolicitante;
		this.direccionsolicitante=direccion;
	}

	public BigInteger getCelularsolicitante() {
		this.celularsolicitante=celular;
		return celularsolicitante;
	}

	public void setCelularsolicitante(BigInteger celularsolicitante) {
		this.celularsolicitante=celular;
		this.celularsolicitante = celularsolicitante;
	}

	public String getOpcion5() {
		return opcion5;
	}

	public void setOpcion5(String opcion5) {
		this.opcion5 = opcion5;
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
