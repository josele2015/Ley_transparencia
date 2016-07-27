package vista;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import controlador.GestionUsuario;
import modelo.Usuario;

@ManagedBean
public class BeanCerrarSesion implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String opcion1="¿Quienes somos?";
	private String opcion2="Realizar petición";
	private String opcion3="Consulta ciudadana";
	private String opcion4="Reposición";
	private String opcion5="Cerrar Sesión";
	private String textobotoniniciarsesion="Login";
	private String textobotonregistraradministradorn="Registro admin";
	private String textobotonregistrarusuario="Registrar usuario";
	private String textocajausuario="Cédula";
	private String textocajacontrasena="Contraseña";
	private String textolink="¿No tienes cuenta?";
	private static Usuario usuario;
	
	/*BeanRegistrarSolicitud brs = new BeanRegistrarSolicitud();
	brs.cerrarsesion();*/
	
	public String getTextobotoniniciarsesion() {
		/*BeanRegistrarSolicitud brs = new BeanRegistrarSolicitud();
		brs.cerrarsesion();*/
		return textobotoniniciarsesion;
	}
	
	public void setTextobotoniniciarsesion(String textobotoniniciarsesion) {
		this.textobotoniniciarsesion = textobotoniniciarsesion;
	}
	
	public String getTextobotonregistraradministradorn() {
		return textobotonregistraradministradorn;
	}
	
	public void setTextobotonregistraradministradorn(
			String textobotonregistraradministradorn) {
		this.textobotonregistraradministradorn = textobotonregistraradministradorn;
	}
	
	public String getTextobotonregistrarusuario() {
		
		return textobotonregistrarusuario;
	}
	
	public void setTextobotonregistrarusuario(String textobotonregistrarusuario) {
		this.textobotonregistrarusuario = textobotonregistrarusuario;
	}
	
	public String getTextocajausuario() {
		return textocajausuario;
	}
	
	public void setTextocajausuario(String textocajausuario) {
		this.textocajausuario = textocajausuario;
	}
	
	public String getTextocajacontrasena() {
		return textocajacontrasena;
	}
	
	public void setTextocajacontrasena(String textocajacontrasena) {
		this.textocajacontrasena = textocajacontrasena;
	}
	
	public String getTextolink() {
		return textolink;
	}
	
	public void setTextolink(String textolink) {
		this.textolink = textolink;
	}
	
	public static Usuario getUsuario() {
		return usuario;
	}
	
	public static void setUsuario(Usuario usuario) {
		BeanCerrarSesion.usuario = usuario;
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
	
	public String iniciarsesion(){
		
		//RequestContext context = RequestContext.getCurrentInstance();
		//FacesMessage msg = null;
		GestionUsuario gestionarsolicitante = new GestionUsuario();
		//aqui busca si existe un usuario 
		if(gestionarsolicitante.AutenticarSolicitante(textocajausuario, textocajacontrasena).getClass().getName().equals("modelo.Usuario")){
			BeanMenu bn = new BeanMenu();
			//bn.email = gestionarsolicitante.getClass().getName(
			//bn.direccion
			//bn.celular
			//System.out.println(gestionarsolicitante.AutenticarSolicitante(login, password).getClass().getName());
			//FacesContext facesContext = FacesContext.getCurrentInstance();			
			return "menu.xhtml";
		}
		else{
			//aqui busca si existe un gestionador
			if(gestionarsolicitante.AutenticarSolicitante(textocajausuario, textocajacontrasena).getClass().getName().equals("modelo.Gestionador")){
				BeanMenuGestionador bmg=new BeanMenuGestionador(); 
				bmg.login=textocajausuario;
				BeanSolicitud bs=new BeanSolicitud();
				bs.cedulaGestionador=Integer.parseInt(textocajausuario);
				BeanGestionarSoli bgs=new BeanGestionarSoli();
				bgs.login=textocajausuario;
				//System.out.println(gestionarsolicitante.AutenticarSolicitante(login, password).getClass().getName());
				return "menugestionador.xhtml";
			}
			else{
				return "nnaa";
			}
		}
	}
	

	public Object cambio_registrarse(){
		System.out.println("Esta entrando.");
		return "registrarusuario.xhtml";		
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
        //if (session != null) 
        session.invalidate(); //Cierre de sesion
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return null;
        //return "cerrarsesion.xhtml?faces-rdirect=true";    	
    }
}
