package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the empresa database table.
 * 
 */
@Entity
@Table(name="EMPRESA")
@NamedQuery(name="Empresa.findAll", query="SELECT e FROM Empresa e")
@NamedQueries({
@NamedQuery(name="Empresa.findAll", query="SELECT e FROM Empresa e"),
@NamedQuery(name="Empresa.findByNombreEmpresa", query = "SELECT e FROM Empresa e WHERE e.nombreEmpresa = :nombreEmpresa")})
public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idEmpresa;

	private boolean estadoEmpresa;

	private String nombreEmpresa;
	
	@Column(insertable=false, updatable=false)
	private int ciudad_idCiudad;

	@Column(insertable=false, updatable=false)
	private int ciudad_Departamento_idDepartamento;

	//bi-directional many-to-one association to Area
	@OneToMany(mappedBy="empresa")
	private List<Area> areas;

	//bi-directional many-to-one association to Ciudad
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="Ciudad_Departamento_idDepartamento", referencedColumnName="Departamento_idDepartamento"),
		@JoinColumn(name="Ciudad_idCiudad", referencedColumnName="idCiudad")
		})
	private Ciudad ciudad;

	//bi-directional many-to-one association to Gestionador
	@OneToMany(mappedBy="empresa")
	private List<Gestionador> gestionadors;

	//bi-directional many-to-one association to Peticion
	@OneToMany(mappedBy="empresa")
	private List<Peticion> peticions;

	public Empresa() {
	}

	public boolean getEstadoEmpresa() {
		return this.estadoEmpresa;
	}

	public void setEstadoEmpresa(boolean estadoEmpresa) {
		this.estadoEmpresa = estadoEmpresa;
	}

	public String getNombreEmpresa() {
		return this.nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public List<Area> getAreas() {
		return this.areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}

	public Area addArea(Area area) {
		getAreas().add(area);
		area.setEmpresa(this);

		return area;
	}

	public Area removeArea(Area area) {
		getAreas().remove(area);
		area.setEmpresa(null);

		return area;
	}

	public Ciudad getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public List<Gestionador> getGestionadors() {
		return this.gestionadors;
	}

	public void setGestionadors(List<Gestionador> gestionadors) {
		this.gestionadors = gestionadors;
	}

	public Gestionador addGestionador(Gestionador gestionador) {
		getGestionadors().add(gestionador);
		gestionador.setEmpresa(this);

		return gestionador;
	}

	public Gestionador removeGestionador(Gestionador gestionador) {
		getGestionadors().remove(gestionador);
		gestionador.setEmpresa(null);

		return gestionador;
	}

	public List<Peticion> getPeticions() {
		return this.peticions;
	}

	public void setPeticions(List<Peticion> peticions) {
		this.peticions = peticions;
	}

	public Peticion addPeticion(Peticion peticion) {
		getPeticions().add(peticion);
		peticion.setEmpresa(this);

		return peticion;
	}

	public Peticion removePeticion(Peticion peticion) {
		getPeticions().remove(peticion);
		peticion.setEmpresa(null);

		return peticion;
	}
	
	public int getIdEmpresa() {
		return this.idEmpresa;
	}
	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public int getCiudad_idCiudad() {
		return this.ciudad_idCiudad;
	}
	public void setCiudad_idCiudad(int ciudad_idCiudad) {
		this.ciudad_idCiudad = ciudad_idCiudad;
	}
	public int getCiudad_Departamento_idDepartamento() {
		return this.ciudad_Departamento_idDepartamento;
	}
	public void setCiudad_Departamento_idDepartamento(int ciudad_Departamento_idDepartamento) {
		this.ciudad_Departamento_idDepartamento = ciudad_Departamento_idDepartamento;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Empresa)) {
			return false;
		}
	
		Empresa castOther = (Empresa)other;
		return 
			(this.idEmpresa == castOther.idEmpresa)
			&& (this.ciudad_idCiudad == castOther.ciudad_idCiudad)
			&& (this.ciudad_Departamento_idDepartamento == castOther.ciudad_Departamento_idDepartamento);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idEmpresa;
		hash = hash * prime + this.ciudad_idCiudad;
		hash = hash * prime + this.ciudad_Departamento_idDepartamento;
		
		return hash;
	}

}