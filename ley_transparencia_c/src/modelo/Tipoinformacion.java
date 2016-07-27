package modelo;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the tipoinformacion database table.
 * 
 */
@Entity
@Table(name="TIPOINFORMACION")
@NamedQueries({
    @NamedQuery(name = "Tipoinformacion.findAll", query = "SELECT t FROM Tipoinformacion t"),
    @NamedQuery(name = "Tipoinformacion.findByIdTipoInformacion", query = "SELECT t FROM Tipoinformacion t WHERE t.idTipoInformacion = :idTipoInformacion"),
    @NamedQuery(name = "Tipoinformacion.findByNombreTipoInformacion", query = "SELECT t FROM Tipoinformacion t WHERE t.nombreTipoInformacion = :nombreTipoInformacion"),
    @NamedQuery(name = "Tipoinformacion.findByEstadoTipoInformacion", query = "SELECT t FROM Tipoinformacion t WHERE t.estadoTipoInformacion = :estadoTipoInformacion")})
public class Tipoinformacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idTipoInformacion;

	private boolean estadoTipoInformacion;

	private String nombreTipoInformacion;

	//bi-directional many-to-one association to Peticion
	@OneToMany(mappedBy="tipoinformacion")
	private List<Peticion> peticions;

	public Tipoinformacion() {
	}

	public int getIdTipoInformacion() {
		return this.idTipoInformacion;
	}

	public void setIdTipoInformacion(int idTipoInformacion) {
		this.idTipoInformacion = idTipoInformacion;
	}

	public boolean getEstadoTipoInformacion() {
		return this.estadoTipoInformacion;
	}

	public void setEstadoTipoInformacion(boolean estadoTipoInformacion) {
		this.estadoTipoInformacion = estadoTipoInformacion;
	}

	public String getNombreTipoInformacion() {
		return this.nombreTipoInformacion;
	}

	public void setNombreTipoInformacion(String nombreTipoInformacion) {
		this.nombreTipoInformacion = nombreTipoInformacion;
	}

	public List<Peticion> getPeticions() {
		return this.peticions;
	}

	public void setPeticions(List<Peticion> peticions) {
		this.peticions = peticions;
	}

	public Peticion addPeticion(Peticion peticion) {
		getPeticions().add(peticion);
		peticion.setTipoinformacion(this);

		return peticion;
	}

	public Peticion removePeticion(Peticion peticion) {
		getPeticions().remove(peticion);
		peticion.setTipoinformacion(null);

		return peticion;
	}

}