package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the peticion database table.
 * 
 */
@Entity
@Table(name="PETICION")
@NamedQuery(name="Peticion.findAll", query="SELECT p FROM Peticion p")
@NamedQueries({
@NamedQuery(name="Peticion.findAll", query="SELECT p FROM Peticion p"),
@NamedQuery(name = "Peticion.findByIdPeticion", query = "SELECT p FROM Peticion p WHERE p.idPeticion = :idPeticion"),
@NamedQuery(name = "Peticion.findByUsuariocedulaUsuario", query = "SELECT p FROM Peticion p WHERE p.usuario_cedulaUsuario = :usuario_cedulaUsuario")})
public class Peticion implements Serializable {
private static final long serialVersionUID = 1L;

@Id
private int idPeticion;

private boolean estadoPeticion;

@Temporal(TemporalType.TIMESTAMP)
private Date fechaPeticion;

private String observacionesPeticion;

private boolean reposicion;

@Column(insertable=false, updatable=false)
private int estado_idEstado;

@Column(insertable=false, updatable=false)
private int empresa_idEmpresa;

@Column(insertable=false, updatable=false)
private int empresa_Ciudad_idCiudad;

@Column(insertable=false, updatable=false)
private int empresa_Ciudad_Departamento_idDepartamento;

@Column(insertable=false, updatable=false)
private int usuario_cedulaUsuario;

@Column(insertable=false, updatable=false)
private int tipoInformacion_idTipoInformacion;

//bi-directional many-to-one association to Empresa
@ManyToOne
@JoinColumns({
	@JoinColumn(name="Empresa_Ciudad_Departamento_idDepartamento", referencedColumnName="Ciudad_Departamento_idDepartamento"),
	@JoinColumn(name="Empresa_Ciudad_idCiudad", referencedColumnName="Ciudad_idCiudad"),
	@JoinColumn(name="Empresa_idEmpresa", referencedColumnName="idEmpresa")
	})
private Empresa empresa;

//bi-directional many-to-one association to Estado
@ManyToOne
private Estado estado;

//bi-directional many-to-one association to Tipoinformacion
@ManyToOne
private Tipoinformacion tipoinformacion;

//bi-directional many-to-one association to Usuario
@ManyToOne
private Usuario usuario;

public Peticion() {
}

public boolean getReposicion() {
	return this.reposicion;
}

public void setReposicion(boolean reposicion) {
	this.reposicion = reposicion;
}

public boolean getEstadoPeticion() {
	return this.estadoPeticion;
}

public void setEstadoPeticion(boolean estadoPeticion) {
	this.estadoPeticion = estadoPeticion;
}

public Date getFechaPeticion() {
	return this.fechaPeticion;
}

public void setFechaPeticion(Date fechaPeticion) {
	this.fechaPeticion = fechaPeticion;
}

public String getObservacionesPeticion() {
	return this.observacionesPeticion;
}

public void setObservacionesPeticion(String observacionesPeticion) {
	this.observacionesPeticion = observacionesPeticion;
}

public Empresa getEmpresa() {
	return this.empresa;
}

public void setEmpresa(Empresa empresa) {
	this.empresa = empresa;
}

public Estado getEstado() {
	return this.estado;
}

public void setEstado(Estado estado) {
	this.estado = estado;
}

public Tipoinformacion getTipoinformacion() {
	return this.tipoinformacion;
}

public void setTipoinformacion(Tipoinformacion tipoinformacion) {
	this.tipoinformacion = tipoinformacion;
}

public Usuario getUsuario() {
	return this.usuario;
}

public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
}

public int getIdPeticion() {
	return this.idPeticion;
}

public void setIdPeticion(int idPeticion) {
	this.idPeticion = idPeticion;
}

public int getEstado_idEstado() {
	return this.estado_idEstado;
}

public void setEstado_idEstado(int estado_idEstado) {
	this.estado_idEstado = estado_idEstado;
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

public int getUsuario_cedulaUsuario() {
	return this.usuario_cedulaUsuario;
}

public void setUsuario_cedulaUsuario(int usuario_cedulaUsuario) {
	this.usuario_cedulaUsuario = usuario_cedulaUsuario;
}

public int getTipoInformacion_idTipoInformacion() {
	return this.tipoInformacion_idTipoInformacion;
}

public void setTipoInformacion_idTipoInformacion(int tipoInformacion_idTipoInformacion) {
	this.tipoInformacion_idTipoInformacion = tipoInformacion_idTipoInformacion;
}

public boolean equals(Object other) {
	if (this == other) {
		return true;
	}
	if (!(other instanceof Peticion)) {
		return false;
	}
	Peticion castOther = (Peticion)other;
	return 
		(this.idPeticion == castOther.idPeticion)
		&& (this.estado_idEstado == castOther.estado_idEstado)
		&& (this.empresa_idEmpresa == castOther.empresa_idEmpresa)
		&& (this.empresa_Ciudad_idCiudad == castOther.empresa_Ciudad_idCiudad)
		&& (this.empresa_Ciudad_Departamento_idDepartamento == castOther.empresa_Ciudad_Departamento_idDepartamento)
		&& (this.usuario_cedulaUsuario == castOther.usuario_cedulaUsuario)
		&& (this.tipoInformacion_idTipoInformacion == castOther.tipoInformacion_idTipoInformacion);
}

public int hashCode() {
	final int prime = 31;
	int hash = 17;
	hash = hash * prime + this.idPeticion;
	hash = hash * prime + this.estado_idEstado;
	hash = hash * prime + this.empresa_idEmpresa;
	hash = hash * prime + this.empresa_Ciudad_idCiudad;
	hash = hash * prime + this.empresa_Ciudad_Departamento_idDepartamento;
	hash = hash * prime + this.usuario_cedulaUsuario;
	hash = hash * prime + this.tipoInformacion_idTipoInformacion;
	
	return hash;
}
}
