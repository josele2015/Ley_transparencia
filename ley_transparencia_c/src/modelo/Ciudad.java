package modelo;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the ciudad database table.
 * 
 */
@Entity
@Table(name="CIUDAD")
@NamedQuery(name="Ciudad.findAll", query="SELECT c FROM Ciudad c")
public class Ciudad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idCiudad;

	private boolean estadoCiudad;

	private String nombreCiudad;

	//bi-directional many-to-one association to Departamento
	@ManyToOne
	private Departamento departamento;

	//bi-directional many-to-one association to Empresa
	@OneToMany(mappedBy="ciudad")
	private List<Empresa> empresas;

	public Ciudad() {
	}

	public boolean getEstadoCiudad() {
		return this.estadoCiudad;
	}

	public void setEstadoCiudad(boolean estadoCiudad) {
		this.estadoCiudad = estadoCiudad;
	}

	public String getNombreCiudad() {
		return this.nombreCiudad;
	}

	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}

	public Departamento getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public List<Empresa> getEmpresas() {
		return this.empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public Empresa addEmpresa(Empresa empresa) {
		getEmpresas().add(empresa);
		empresa.setCiudad(this);

		return empresa;
	}

	public Empresa removeEmpresa(Empresa empresa) {
		getEmpresas().remove(empresa);
		empresa.setCiudad(null);

		return empresa;
	}

	@Column(insertable=false, updatable=false)
	private int departamento_idDepartamento;

	public int getIdCiudad() {
		return this.idCiudad;
	}
	public void setIdCiudad(int idCiudad) {
		this.idCiudad = idCiudad;
	}
	public int getDepartamento_idDepartamento() {
		return this.departamento_idDepartamento;
	}
	public void setDepartamento_idDepartamento(int departamento_idDepartamento) {
		this.departamento_idDepartamento = departamento_idDepartamento;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Ciudad)) {
			return false;
		}
	Ciudad castOther = (Ciudad)other;
		return 
			(this.idCiudad == castOther.idCiudad)
			&& (this.departamento_idDepartamento == castOther.departamento_idDepartamento);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idCiudad;
		hash = hash * prime + this.departamento_idDepartamento;
		
		return hash;
	}

}