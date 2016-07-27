package vista;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.activation.MimetypesFileTypeMap;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import controlador.GestionUsuario;
import modelo.Peticion;

@ManagedBean
@ViewScoped
public class BeanConsultaCiudadana implements Serializable{
	
	transient BeanConsultaCiudadana BeanConsultaCiudadan;
	private static final long serialVersionUID = 1L;
	private String opcion1="¿Quienes somos?";
	private String opcion2="Realizar petición";
	private String opcion3="Consulta ciudadana";
	private String opcion4="Reposición";
	private String opcion5="Cerrar Sesión";
	private String emailsolicitante="";
	private String direccionsolicitante="";
	private BigInteger celularsolicitante;
	List<Peticion> lista;
	List<Lista_consulta_ciudadana> mostrar;	
	List<Lista_consulta_ciudadana> mostrar2;
	//List<Peticion> lista;

	public static String email;
	public static String direccion;
	public static BigInteger celular;
	public static String nombre;
	public static String apellido;
	public static int cedula;
	String[] arrglos={"zip","rar","tgz","png","jpg","gif","xls","dot","pdf"};
	
	private String nombresolicitante;
	private String apellidosolicitante;
	private String bienvenida="Bienvenido";
	private String condicion="aparece_el_boton_descarga";
	private String condicion2="bien";
	

	public List<Lista_consulta_ciudadana> getMostrar2() {
		return mostrar2;
	}

	public void setMostrar2(List<Lista_consulta_ciudadana> mostrar2) {
		this.mostrar2 = mostrar2;
	}
	
	public List<Lista_consulta_ciudadana> getMostrar() {
		return mostrar;
	}

	public void setMostrar(List<Lista_consulta_ciudadana> mostrar) {
		this.mostrar = mostrar;
	}
	
	public String getCondicion2() {
		return condicion2;
	}

	public void setCondicion2(String condicion2) {
		this.condicion2 = condicion2;
	}

	public String getCondicion() {		
		return condicion;
	}

	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}
	
	public String getNombresolicitante() {
		this.nombresolicitante=nombre;
		return nombresolicitante;
	}

	public void setNombresolicitante(String nombresolicitante) {		
		this.nombresolicitante = nombresolicitante;
		this.nombresolicitante=nombre;
	}

	public String getApellidosolicitante() {
		this.apellidosolicitante=apellido;
		return apellidosolicitante;
	}

	public void setApellidosolicitante(String apellidosolicitante) {
		this.apellidosolicitante = apellidosolicitante;
		this.apellidosolicitante=apellido;
	}

	public String getBienvenida() {
		return bienvenida;
	}

	public void setBienvenida(String bienvenida) {
		this.bienvenida = bienvenida;
	}
	
	
	public String getOpcion5() {
		return opcion5;
	}

	public void setOpcion5(String opcion5) {
		this.opcion5 = opcion5;
	}

	public List<Peticion> getLista() {
		return lista;
	}

	public void setLista(List<Peticion> lista) {
		this.lista = lista;
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
	
	public String getEmailsolicitante() {
		emailsolicitante=email;
		return emailsolicitante;
	}
	
	public void setEmailsolicitante(String emailsolicitante) {
		this.emailsolicitante = emailsolicitante;
	}
	
	public String getDireccionsolicitante() {
		direccionsolicitante=direccion;
		return direccionsolicitante;
	}
	
	public void setDireccionsolicitante(String direccionsolicitante) {
		this.direccionsolicitante = direccionsolicitante;
	}
	
	public BigInteger getCelularsolicitante() {
		celularsolicitante=celular;
		return celularsolicitante;
	}
	
	public void setCelularsolicitante(BigInteger celularsolicitante) {
		this.celularsolicitante = celularsolicitante;
	}
	
	@PostConstruct
	public void init() {
		GestionUsuario gu = new GestionUsuario();
		lista=gu.Mostrar_las_peticiones_que_ha_realizado_un_solicitante(cedula);
		mostrar=new ArrayList();
		Lista_consulta_ciudadana boton;
		for(int i=0;i<lista.size();i++){
			if(lista.get(i).getEstado().getTipoEstado().equals("Derecho de reposicion")||lista.get(i).getEstado().getTipoEstado().equals("Buscando informacion")){
				boton=new Lista_consulta_ciudadana(Integer.toString(lista.get(i).getIdPeticion()),lista.get(i).getFechaPeticion(),lista.get(i).getEstado().getTipoEstado(),"aparece_el_boton_descarga");
				mostrar.add(boton);
			}
				
			else{
				boton=new Lista_consulta_ciudadana(Integer.toString(lista.get(i).getIdPeticion()),lista.get(i).getFechaPeticion(),lista.get(i).getEstado().getTipoEstado(),"desaparece_el_boton_descarga");
				mostrar.add(boton);
			}
		}
		mostrar2=mostrar;
		
	}
	
	private StreamedContent file, file1;
    private String contentType;
    
    public  StreamedContent FileDownloadView(String idformulario) throws IOException{        
        System.out.println(idformulario);
        InputStream stream1 = null;
    	// Aquï¿½ la carpeta donde queremos buscar    	
    	String nombre;
    	for(int i=0;i<=8;i++){    		
			nombre=idformulario+"peticion."+arrglos[i];
			String Fichero = "C:/tmp/tmp"+nombre;
	    	File fichero = new File(Fichero);
	    	//stream1 = new FileInputStream(fichero);
    		if (fichero.exists()){
    			System.out.println("El fichero " + Fichero + " existe");
    			try {
    				stream1 = new FileInputStream(fichero);    				    				
					String pathName="C:/tmp/tmp"+nombre;
					file = new DefaultStreamedContent(stream1, Files.probeContentType(Paths.get(pathName)), nombre);					
					System.out.println("El fichero " + Fichero + " se descargo");
					
					//
					return file;
    			} catch (FileNotFoundException e){
    				System.out.println("Frfregtrgtrt");
    				// TODO Auto-generated catch block
    				e.printStackTrace();    				
    				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error","El archivo no se encuentra."));
    				return null;
    			}
    	        	
    		}
    		else{
    			//stream1.close();
    			System.out.println("El fichero " + Fichero + " no existe");
    		}
        }
    	//stream1.close();
    	return null;
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
        //return "index.xhtml";
        return null;
    }
    
/*    public Object FileDownloadViewPrueba() {        
        InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("C:/Users/JuanJose/Downloads/footer.png");        
        file = new DefaultStreamedContent(stream, "image/png", "downloaded_optimus.png");
        return file;
    }*/
 
    public StreamedContent getFile() {
        return file;
    }
}
