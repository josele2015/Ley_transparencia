package vista;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.model.chart.PieChartModel;

import controlador.GestionAdministrador;

@ManagedBean
public class BeanEstadistica implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private PieChartModel pieModel1;	
	public PieChartModel pieModel;
	public PieChartModel pieModel22;
	public PieChartModel pieModel33;
	private String opcion1="¿Quienes somos?";
	private String opcion2="Solicitudes";
	private String opcion3="Gestionar solicitudes";
	private String opcion4="Reportes";
	private String opcion5="Cerrar Sesión";
	private PieChartModel pieModel2;
	private PieChartModel pieModel3;

	public PieChartModel getPieModel2() {
		pieModel2=pieModel22;
		return pieModel2;
	}

	public void setPieModel2(PieChartModel pieModel2) {
		this.pieModel2 = pieModel2;
		this.pieModel2 = pieModel22;
	}

	public PieChartModel getPieModel3() {
		pieModel3=pieModel33;
		return pieModel3;
	}

	public void setPieModel3(PieChartModel pieModel3) {
		this.pieModel3 = pieModel3;
		this.pieModel3 = pieModel33;
	}
	
	public PieChartModel getPieModel1() {
		pieModel1=pieModel;
        return pieModel1;
    }
    
    public void setPieModel1(PieChartModel pieModel1) {
		this.pieModel1 = pieModel1;
		this.pieModel1 = pieModel;
	}

	private int i=3;
	
	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public String getOpcion1() {
		return opcion1;
	}

	public void setOpcion1(String opcion1) {
		this.opcion1 = opcion1;
	}

	public String getOpcion2() {
		return opcion2;
	}

	public void setOpcion2(String opcion2) {
		this.opcion2 = opcion2;
	}

	public String getOpcion3() {
		return opcion3;
	}

	public void setOpcion3(String opcion3) {
		this.opcion3 = opcion3;
	}

	public String getOpcion4() {
		return opcion4;
	}

	public void setOpcion4(String opcion4) {
		this.opcion4 = opcion4;
	}
	
	public String getOpcion5() {
		return opcion5;
	}

	public void setOpcion5(String opcion5) {
		this.opcion5 = opcion5;
	}
    
    @PostConstruct
    public void cambio() {    	
		GestionAdministrador ga = new GestionAdministrador();
		pieModel=(PieChartModel) ga.crearmodelo();
		pieModel22=(PieChartModel) ga.crearmodelo2();
		pieModel33=(PieChartModel) ga.crearmodelo3();
    }
    
    public String cerrarsesion(){
    	BeanMenu bm = new BeanMenu();
		bm.email=null;
		bm.direccion=null;

		BeanRegistrarSolicitud bs = new BeanRegistrarSolicitud();
		bs.direccion = null;
		bs.email = null;
		bs.nombre = null;
		bs.apellido = null;
		bs.cedula = 0;
		
		BeanConsultaCiudadana bcc= new BeanConsultaCiudadana();
		bcc.cedula = 0;
		bcc.email = null;
		bcc.direccion = null;
		
		BeanRegistrarReposicion brr = new BeanRegistrarReposicion();
		brr.cedula=0;
		brr.email = null;
		brr.direccion = null;
		brr.celular = null;
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate(); //Cierre de sesion
        }
        return null;
        //return "index.xhtml";    	
    }
}