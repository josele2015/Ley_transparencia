package vista;


import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import modelo.Usuario;
import controlador.GestionUsuario;

import java.io.Serializable;
 
@ManagedBean
public class BeanIndex implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String textobotoniniciarsesion="Login";
	private String textobotonregistraradministradorn="Registro admin";
	private String textobotonregistrarusuario="Registrar usuario";
	private String textocajausuario="Cédula";
	private String textocajacontrasena="Contraseña";
	private String textolink="¿No tienes cuenta?";
	private static Usuario usuario;
	
	private String login;
	private String password;
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTextolink() {
		return textolink;
	}

	public void setTextolink(String textolink) {
		this.textolink = textolink;
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
	
	public String getTextobotoniniciarsesion() {
		return textobotoniniciarsesion;
	}
	
	public void setTextobotoniniciarsesion(String textobotoniniciarsesion) {
		this.textobotoniniciarsesion = textobotoniniciarsesion;
	}
	
	public String getTextobotonregistraradministradorn() {
		return textobotonregistraradministradorn;
	}
	
	public void setTextobotonregistraradministradorn(String textobotonregistraradministradorn) {
		this.textobotonregistraradministradorn = textobotonregistraradministradorn;
	}
	
	public String getTextobotonregistrarusuario() {
		return textobotonregistrarusuario;
	}
	
	public void setTextobotonregistrarusuario(String textobotonregistrarusuario) {
		this.textobotonregistrarusuario = textobotonregistrarusuario;
	}
	
	public String iniciarsesion(){
		
		//RequestContext context = RequestContext.getCurrentInstance();
		//FacesMessage msg = null;
		GestionUsuario gestionarsolicitante = new GestionUsuario();
		//aqui busca si existe un usuario 
		if(gestionarsolicitante.AutenticarSolicitante(login, password).getClass().getName().equals("modelo.Usuario")){
			//BeanMenu bn = new BeanMenu();
			//bn.email = gestionarsolicitante.getClass().getName(
			//bn.direccion
			//bn.celular
			//System.out.println(gestionarsolicitante.AutenticarSolicitante(login, password).getClass().getName());
			//FacesContext facesContext = FacesContext.getCurrentInstance();			
			return "menu.xhtml";
		}
		else{
			//aqui busca si existe un Gestionador
			if(gestionarsolicitante.AutenticarSolicitante(login, password).getClass().getName().equals("modelo.Gestionador")){
				BeanMenuGestionador bmg=new BeanMenuGestionador(); 
				bmg.login=login;
				BeanSolicitud bs=new BeanSolicitud();
				bs.cedulaGestionador=Integer.parseInt(login);
				BeanGestionarSoli bgs=new BeanGestionarSoli();
				bgs.login=login;
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
}