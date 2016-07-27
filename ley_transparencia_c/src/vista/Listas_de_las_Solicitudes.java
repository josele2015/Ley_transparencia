package vista;


import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.io.Serializable;
import java.util.Date;

public class Listas_de_las_Solicitudes implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaPeticion;
	
	private String nombreestado;
	
	private String area;
	public Listas_de_las_Solicitudes(){
		
	}
	public Listas_de_las_Solicitudes(String id,Date fechapeticion,String nombreestado,String area){
		this.id=id;
		this.fechaPeticion=fechapeticion;
		this.area=area;
		this.nombreestado=nombreestado;
		this.area=area;
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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
}
