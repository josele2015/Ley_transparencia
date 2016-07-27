package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the estado database table.
 * 
 */
@Entity
@Table(name="ESTADO")
@NamedQuery(name="Estado.findAll", query="SELECT e FROM Estado e")
@NamedQueries({
    @NamedQuery(name = "Estado.findAll", query = "SELECT e FROM Estado e"),
    @NamedQuery(name = "Estado.findByIdEstado", query = "SELECT e FROM Estado e WHERE e.idEstado = :idEstado"),
    @NamedQuery(name = "Estado.findByTipoEstado", query = "SELECT e FROM Estado e WHERE e.tipoEstado = :tipoEstado"),
    @NamedQuery(name = "Estado.findByEstadoEstado", query = "SELECT e FROM Estado e WHERE e.estadoEstado = :estadoEstado")})
public class Estado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idEstado;

	private boolean estadoEstado;

	private String tipoEstado;

	//bi-directional many-to-one association to Peticion
	@OneToMany(mappedBy="estado")
	private List<Peticion> peticions;

	public Estado() {
	}

	public int getIdEstado() {
		return this.idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public boolean getEstadoEstado() {
		return this.estadoEstado;
	}

	public void setEstadoEstado(boolean estadoEstado) {
		this.estadoEstado = estadoEstado;
	}

	public String getTipoEstado() {
		return this.tipoEstado;
	}

	public void setTipoEstado(String tipoEstado) {
		this.tipoEstado = tipoEstado;
	}

	public List<Peticion> getPeticions() {
		return this.peticions;
	}

	public void setPeticions(List<Peticion> peticions) {
		this.peticions = peticions;
	}

	public Peticion addPeticion(Peticion peticion) {
		getPeticions().add(peticion);
		peticion.setEstado(this);

		return peticion;
	}

	public Peticion removePeticion(Peticion peticion) {
		getPeticions().remove(peticion);
		peticion.setEstado(null);

		return peticion;
	}

}