package modelo;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the gestionador database table.
 * 
 */
@Entity
@Table(name="GESTIONADOR")
@NamedQueries({
@NamedQuery(name="Gestionador.findAll", query="SELECT g FROM Gestionador g"),
@NamedQuery(name = "Gestionador.findByCedulaGestionador", query = "SELECT g FROM Gestionador g WHERE g.cedulaGestionador = :cedulaGestionador"),
@NamedQuery(name = "Gestionador.findByCedulaPasswordGestionador", query = "SELECT g FROM Gestionador g WHERE g.cedulaGestionador = :cedulaGestionador and g.contrasenaGestionador=:contrasenaGestionador")})
public class Gestionador implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int cedulaGestionador;

	private String apellidoGestionador;

	private String contrasenaGestionador;

	private String emailGestionador;

	private boolean estadoGestionado;

	private String nombreGestionador;

	//bi-directional many-to-one association to Empresa
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="Empresa_Ciudad_Departamento_idDepartamento", referencedColumnName="Ciudad_Departamento_idDepartamento"),
		@JoinColumn(name="Empresa_Ciudad_idCiudad", referencedColumnName="Ciudad_idCiudad"),
		@JoinColumn(name="Empresa_idEmpresa", referencedColumnName="idEmpresa")
		})
	private Empresa empresa;

	public Gestionador() {
	}

	public String getApellidoGestionador() {
		return this.apellidoGestionador;
	}

	public void setApellidoGestionador(String apellidoGestionador) {
		this.apellidoGestionador = apellidoGestionador;
	}

	public String getContrasenaGestionador() {
		return this.contrasenaGestionador;
	}

	public void setContrasenaGestionador(String contrasenaGestionador) {
		this.contrasenaGestionador = contrasenaGestionador;
	}

	public String getEmailGestionador() {
		return this.emailGestionador;
	}

	public void setEmailGestionador(String emailGestionador) {
		this.emailGestionador = emailGestionador;
	}

	public boolean getEstadoGestionado() {
		return this.estadoGestionado;
	}

	public void setEstadoGestionado(boolean estadoGestionado) {
		this.estadoGestionado = estadoGestionado;
	}

	public String getNombreGestionador() {
		return this.nombreGestionador;
	}

	public void setNombreGestionador(String nombreGestionador) {
		this.nombreGestionador = nombreGestionador;
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Column(insertable=false, updatable=false)
	private int empresa_idEmpresa;

	@Column(insertable=false, updatable=false)
	private int empresa_Ciudad_idCiudad;

	@Column(insertable=false, updatable=false)
	private int empresa_Ciudad_Departamento_idDepartamento;

	public int getCedulaGestionador() {
		return this.cedulaGestionador;
	}
	
	public void setCedulaGestionador(int cedulaGestionador) {
		this.cedulaGestionador = cedulaGestionador;
	}
	
	public int getEmpresa_idEmpresa() {
		return this.empresa_idEmpresa;
	}
	
	public void setEmpresa_idEmpresa(int empresa_idEmpresa) {
		this.empresa_idEmpresa = empresa_idEmpresa;
	}
	
	public int getEmpresa_Ciudad_idCiudad() {
		return this.empresa_Ciudad_idCiudad;
	}
	
	public void setEmpresa_Ciudad_idCiudad(int empresa_Ciudad_idCiudad) {
		this.empresa_Ciudad_idCiudad = empresa_Ciudad_idCiudad;
	}
	
	public int getEmpresa_Ciudad_Departamento_idDepartamento() {
		return this.empresa_Ciudad_Departamento_idDepartamento;
	}
	
	public void setEmpresa_Ciudad_Departamento_idDepartamento(int empresa_Ciudad_Departamento_idDepartamento) {
		this.empresa_Ciudad_Departamento_idDepartamento = empresa_Ciudad_Departamento_idDepartamento;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Gestionador)) {
			return false;
		}
		Gestionador castOther = (Gestionador)other;
		return 
			(this.cedulaGestionador == castOther.cedulaGestionador)
			&& (this.empresa_idEmpresa == castOther.empresa_idEmpresa)
			&& (this.empresa_Ciudad_idCiudad == castOther.empresa_Ciudad_idCiudad)
			&& (this.empresa_Ciudad_Departamento_idDepartamento == castOther.empresa_Ciudad_Departamento_idDepartamento);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cedulaGestionador;
		hash = hash * prime + this.empresa_idEmpresa;
		hash = hash * prime + this.empresa_Ciudad_idCiudad;
		hash = hash * prime + this.empresa_Ciudad_Departamento_idDepartamento;
		
		return hash;
	}
}