package vista;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class Lista_consulta_ciudadana  implements Serializable{
	private String id;
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaPeticion;
	
	private String nombreestado;

	private String descarga;
	
	public Lista_consulta_ciudadana(){
	}
	
	public Lista_consulta_ciudadana(String id,Date fechapeticion,String nombreestado,String descarga){
		this.id=id;
		this.fechaPeticion=fechapeticion;
		this.nombreestado=nombreestado;
		this.descarga=descarga;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getFechaPeticion() {
		return fechaPeticion;
	}

	public void setFechaPeticion(Date fechaPeticion) {
		this.fechaPeticion = fechaPeticion;
	}

	public String getNombreestado() {
		return nombreestado;
	}

	public void setNombreestado(String nombreestado) {
		this.nombreestado = nombreestado;
	}
	public String getDescarga() {
		return descarga;
	}

	public void setDescarga(String descargas) {
		descarga = descargas;
	}
	
}
