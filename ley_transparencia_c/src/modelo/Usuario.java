package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name="USUARIO")
@NamedQueries({@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByCedulaUsuario", query = "SELECT u FROM Usuario u WHERE u.cedulaUsuario = :cedulaUsuario")})
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int cedulaUsuario;
	
	private String apellidoUsuario;
	
	private BigInteger celularUsuario;
	
	private String contrasenaUsuario;
	
	private String direccionUsuario;
	
	private String emailUsuario;
	
	private boolean estadoUsuario;
	
	private String nombreUsuario;

	//bi-directional many-to-one association to Peticion
	@OneToMany(mappedBy="usuario")
	private List<Peticion> peticions;

	public Usuario() {
	}

	public int getCedulaUsuario() {
		return this.cedulaUsuario;
	}

	public void setCedulaUsuario(int cedulaUsuario) {
		this.cedulaUsuario = cedulaUsuario;
	}

	public String getApellidoUsuario() {
		return this.apellidoUsuario;
	}

	public void setApellidoUsuario(String apellidoUsuario) {
		this.apellidoUsuario = apellidoUsuario;
	}

	public BigInteger getCelularUsuario() {
		return this.celularUsuario;
	}

	public void setCelularUsuario(BigInteger celularUsuario) {
		this.celularUsuario = celularUsuario;
	}

	public String getContrasenaUsuario() {
		return this.contrasenaUsuario;
	}

	public void setContrasenaUsuario(String contrasenaUsuario) {
		this.contrasenaUsuario = contrasenaUsuario;
	}

	public String getDireccionUsuario() {
		return this.direccionUsuario;
	}

	public void setDireccionUsuario(String direccionUsuario) {
		this.direccionUsuario = direccionUsuario;
	}

	public String getEmailUsuario() {
		return this.emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public boolean getEstadoUsuario() {
		return this.estadoUsuario;
	}

	public void setEstadoUsuario(boolean estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}

	public String getNombreUsuario() {
		return this.nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public List<Peticion> getPeticions() {
		return this.peticions;
	}

	public void setPeticions(List<Peticion> peticions) {
		this.peticions = peticions;
	}

	public Peticion addPeticion(Peticion peticion) {
		getPeticions().add(peticion);
		peticion.setUsuario(this);

		return peticion;
	}

	public Peticion removePeticion(Peticion peticion) {
		getPeticions().remove(peticion);
		peticion.setUsuario(null);

		return peticion;
	}
}