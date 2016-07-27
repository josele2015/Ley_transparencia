package vista;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import modelo.Gestionador;
import modelo.Usuario;
import controlador.GestionAdministrador;
import controlador.GestionUsuario;

import java.io.Serializable;
import java.math.BigInteger;
 
@ManagedBean
@SessionScoped
public class BeanRegistrarUsuario implements Serializable{
	private static final long serialVersionUID = 1L;
	private String textlabelnombre="Nombres:";
	private String textlabecedula="Cedula:";
	private String textlabelapellido="Apellidos:";
	private String textlabelcelular="Celular:";
	private String textlabelcontrasena="Contraseña:";
	private String textlabelemail="E-mail:";
	private String textlabelrepetircontra="Repetir contraseña:";
	private String textlabeldireccion="Dirección:";
	private String textlabelentidad="Entidad:";
	private String textlabellicencia="Licencia:";
	
	private String textcajanombre="Nombres ...";
	private String textcajacedula="Cedula ...";
	private String textcajaapellido="Apellidos ...";
	private String textcajacelular="Celular ...";
	private String textcajacontrasena="Contraseña ...";
	private String textcajaemail="E-mail ...";
	private String textcajarepetircontra="Repetir contraseña ...";
	private String textcajadireccion="Dirección ...";
	private String textcajaentidad="Entidad:*";
	private String textcajalicencia="Licencia:*	";
	
	private String textbotonregistrarse="Registrarse";
	private Usuario usuario;
	private Gestionador Gestionador;
	
	private int cedula;
	private BigInteger celular;
	private String nombre="", apellido="",email="",direccion="",contrasena="", repetircontrasena="";
	
	public String getRepetircontrasena() {
		return repetircontrasena;
	}

	public void setRepetircontrasena(String repetircontrasena) {
		this.repetircontrasena = repetircontrasena;
	}
	
	public int getCedula() {
		return cedula;
	}

	public void setCedula(int cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public BigInteger getCelular() {
		return celular;
	}

	public void setCelular(BigInteger celular) {
		this.celular = celular;
	}

	public String getTextlabelentidad() {
		return textlabelentidad;
	}

	public void setTextlabelentidad(String textlabelentidad) {
		this.textlabelentidad = textlabelentidad;
	}

	public String getTextlabellicencia() {
		return textlabellicencia;
	}

	public void setTextlabellicencia(String textlabellicencia) {
		this.textlabellicencia = textlabellicencia;
	}

	public String getTextcajaentidad() {
		return textcajaentidad;
	}

	public void setTextcajaentidad(String textcajaentidad) {
		this.textcajaentidad = textcajaentidad;
	}

	public String getTextcajalicencia() {
		System.out.println(textcajalicencia);
		return textcajalicencia;
	}

	public void setTextcajalicencia(String textcajalicencia) {
		System.out.println(textcajalicencia);
		this.textcajalicencia = textcajalicencia;
	}

	public String getTextbotonregistrarse() {
		return textbotonregistrarse;
	}

	public void setTextbotonregistrarse(String textbotonregistrarse) {
		this.textbotonregistrarse = textbotonregistrarse;
	}

	public String getTextcajanombre() {
		return textcajanombre;
	}

	public void setTextcajanombre(String textcajanombre) {
		this.textcajanombre = textcajanombre;
	}

	public String getTextcajacedula() {
		return textcajacedula;
	}

	public void setTextcajacedula(String textcajacedula) {
		this.textcajacedula = textcajacedula;
	}

	public String getTextcajaapellido() {
		return textcajaapellido;
	}

	public void setTextcajaapellido(String textcajaapellido) {
		this.textcajaapellido = textcajaapellido;
	}

	public String getTextcajacelular() {
		return textcajacelular;
	}

	public void setTextcajacelular(String textcajacelular) {
		this.textcajacelular = textcajacelular;
	}

	public String getTextcajacontrasena() {
		return textcajacontrasena;
	}

	public void setTextcajacontrasena(String textcajacontrasena) {
		this.textcajacontrasena = textcajacontrasena;
	}

	public String getTextcajaemail() {
		return textcajaemail;
	}

	public void setTextcajaemail(String textcajaemail) {
		this.textcajaemail = textcajaemail;
	}

	public String getTextcajarepetircontra() {
		return textcajarepetircontra;
	}

	public void setTextcajarepetircontra(String textcajarepetircontra) {
		this.textcajarepetircontra = textcajarepetircontra;
	}

	public String getTextcajadireccion() {
		return textcajadireccion;
	}

	public void setTextcajadireccion(String textcajadireccion) {
		this.textcajadireccion = textcajadireccion;
	}

	public String getTextlabelnombre() {
		return textlabelnombre;
	}
	
	public void setTextlabelnombre(String textlabelnombre) {
		this.textlabelnombre = textlabelnombre;
	}
	
	public String getTextlabecedula() {
		return textlabecedula;
	}
	
	public void setTextlabecedula(String textlabecedula) {
		this.textlabecedula = textlabecedula;
	}
	
	public String getTextlabelapellido() {
		return textlabelapellido;
	}
	
	public void setTextlabelapellido(String textlabelapellido) {
		this.textlabelapellido = textlabelapellido;
	}
	
	public String getTextlabelcelular() {
		return textlabelcelular;
	}
	
	public void setTextlabelcelular(String textlabelcelular) {
		this.textlabelcelular = textlabelcelular;
	}
	
	public String getTextlabelcontrasena() {
		return textlabelcontrasena;
	}
	
	public void setTextlabelcontrasena(String textlabelcontrasena) {
		this.textlabelcontrasena = textlabelcontrasena;
	}
	
	public String getTextlabelemail() {
		return textlabelemail;
	}
	
	public void setTextlabelemail(String textlabelemail) {
		this.textlabelemail = textlabelemail;
	}
	
	public String getTextlabelrepetircontra() {
		return textlabelrepetircontra;
	}
	
	public void setTextlabelrepetircontra(String textlabelrepetircontra) {
		this.textlabelrepetircontra = textlabelrepetircontra;
	}
	
	public String getTextlabeldireccion() {
		return textlabeldireccion;
	}
	
	public void setTextlabeldireccion(String textlabeldireccion) {
		this.textlabeldireccion = textlabeldireccion;
	}
	
	public String registrarusuario(){
		GestionUsuario gessolicitante = new GestionUsuario();
		if(contrasena.equals(repetircontrasena)){
			usuario=gessolicitante.RegistrarSolicitante(cedula, nombre, apellido, celular, email, direccion, contrasena);
			if(usuario!=null){				
				gessolicitante.enviarconfirmacion(email,contrasena,cedula);
				return "menu.xhtml";	
			}							
		}		
		return null;
	}	
}
