package modelo;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the area database table.
 * 
 */
@Entity
@Table(name="AREA")
@NamedQuery(name="Area.findAll", query="SELECT a FROM Area a")
public class Area implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idtable;

	private boolean estadoArea;

	private String nombreArea;

	//bi-directional many-to-one association to Empresa
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="Empresa_Ciudad_Departamento_idDepartamento", referencedColumnName="Ciudad_Departamento_idDepartamento"),
		@JoinColumn(name="Empresa_Ciudad_idCiudad", referencedColumnName="Ciudad_idCiudad"),
		@JoinColumn(name="Empresa_idEmpresa", referencedColumnName="idEmpresa")
		})
	private Empresa empresa;

	public Area() {
	}

	public boolean getEstadoArea() {
		return this.estadoArea;
	}

	public void setEstadoArea(boolean estadoArea) {
		this.estadoArea = estadoArea;
	}

	public String getNombreArea() {
		return this.nombreArea;
	}

	public void setNombreArea(String nombreArea) {
		this.nombreArea = nombreArea;
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

	public int getIdtable() {
		return this.idtable;
	}
	public void setIdtable(int idtable) {
		this.idtable = idtable;
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
		if (!(other instanceof Area)) {
			return false;
		}
		Area castOther = (Area)other;
		return 
			(this.idtable == castOther.idtable)
			&& (this.empresa_idEmpresa == castOther.empresa_idEmpresa)
			&& (this.empresa_Ciudad_idCiudad == castOther.empresa_Ciudad_idCiudad)
			&& (this.empresa_Ciudad_Departamento_idDepartamento == castOther.empresa_Ciudad_Departamento_idDepartamento);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idtable;
		hash = hash * prime + this.empresa_idEmpresa;
		hash = hash * prime + this.empresa_Ciudad_idCiudad;
		hash = hash * prime + this.empresa_Ciudad_Departamento_idDepartamento;
		
		return hash;
	}

}