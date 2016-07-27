package vista;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import java.util.*;

import modelo.Gestionador;
import modelo.Peticion;
import controlador.ControladorArchivos;
import controlador.GestionAdministrador;
@ManagedBean
@RequestScoped
public class BeanGestionarSoli implements Serializable{
	private String destination="C:/tmp/tmp";
	private static final long serialVersionUID = 8L;
	String nombrearchivo;
	
	private int i=2;
	
	private static final int tamaño_archivo = 6124;   
	private String cargarcarpeta;
	private static String nuevalinea ="\r";
	
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("LeyTransparencia");
	private static final EntityManager em = emf.createEntityManager();	
	private GestionAdministrador gestionAdministrador;
	private String opcion1="¿Quienes somos?";
	private String opcion2="Solicitudes";
	private String opcion3="Gestionar solicitudes";
	private String opcion4="Reportes";
	private String opcion5="Cerrar Sesión";
	
	public static String login;	

	private String textlabelnumerosolicitud="N° de registro de la solicitud:";
	private String textlabelsubirinfo="Subir información:";
	private String textlabelestadosolu="Estado de solicitud:";
	
	private String textbotonenviar="Enviar";
	
	private String estado;
	private List<String> estadoss;
	//private Map<String,Integer> estados;
	
	private String peticion;
	private List<String> peticioness;

	//private Map<String,Integer> peticiones;

	private vista.BeanIndex beanIndex;

	private UploadedFile file;
	
////////////get set
	
	    public UploadedFile getFile() {
	        return file;
	    }
	 
	    public void setFile(UploadedFile file) {
	        this.file = file;
	    }   	    
	    
	public static String getLogin() {
		return login;
	}

	public static void setLogin(String login) {
		BeanGestionarSoli.login = login;
	}
	
	public List<String> getPeticioness() {
		return peticioness;
	}

	public void setPeticioness(List<String> peticioness) {
		this.peticioness = peticioness;
	}	
	public List<String> getEstadoss() {
		return estadoss;
	}

	public void setEstadoss(List<String> estadoss) {
		this.estadoss = estadoss;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}
	public String getPeticion() {
		return peticion;
	}

	public void setPeticion(String peticion) {
		this.peticion = peticion;
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

	public String getTextlabelnumerosolicitud() {
		return textlabelnumerosolicitud;
	}

	public void setTextlabelnumerosolicitud(String textlabelnumerosolicitud) {
		this.textlabelnumerosolicitud = textlabelnumerosolicitud;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}


	public String getTextlabelsubirinfo() {
		return textlabelsubirinfo;
	}

	public void setTextlabelsubirinfo(String textlabelsubirinfo) {
		this.textlabelsubirinfo = textlabelsubirinfo;
	}

	public String getTextlabelestadosolu() {
		return textlabelestadosolu;
	}

	public void setTextlabelestadosolu(String textlabelestadosolu) {
		this.textlabelestadosolu = textlabelestadosolu;
	}

	public String getTextbotonenviar() {
		return textbotonenviar;
	}

	public void setTextbotonenviar(String textbotonenviar) {
		this.textbotonenviar = textbotonenviar;
	}
	
	public vista.BeanIndex getBeanIndex() {
		return beanIndex;
	}

	public void setBeanIndex(vista.BeanIndex beanIndex) {
		this.beanIndex = beanIndex;
	}
///////////metodos modificables	
	@PostConstruct
    public void init() {
        estadoss=new ArrayList();
        
        estadoss.add("Información Encontrada");
        estadoss.add("Información Confidencial");
        
        Query buscarGestionador = em.createQuery("SELECT g FROM Gestionador g WHERE g.cedulaGestionador ="+login);
        Gestionador Gestionador=(Gestionador)buscarGestionador.getSingleResult();
        List<Peticion>listaPeticion=Gestionador.getEmpresa().getPeticions();
        Query buscarpeticion = em.createQuery("SELECT p FROM Peticion p WHERE p.estado_idEstado =4 OR p.estado_idEstado =3 ");
        List<Peticion>listaPeticion_buscarinformacion=(List<Peticion>)buscarpeticion.getResultList();
        
        peticioness=new ArrayList();
        
        for(int i=0;i<listaPeticion.size();i++){
        	//System.out.println("i"+i);
        	int j=0;
        	while(listaPeticion_buscarinformacion.size()>j){
        		if(listaPeticion.get(i).equals(listaPeticion_buscarinformacion.get(j))){
        			peticioness.add(Integer.toString(listaPeticion.get(i).getIdPeticion()));
        		}
        		j++;
        	}
        }
    }
	
	public String manejararchivos(String peticion) {
		//ExternalContext extContext ="C:/tmp";		
		int tamano=file.getFileName().length();
		nombrearchivo = peticion+"peticion"+file.getFileName().subSequence(tamano-4, tamano);
		File resultado = new File("C:/tmp/tmp"+peticion+"peticion"+file.getFileName().subSequence(tamano-4, tamano));
		//System.out.println(resultado);
		GestionAdministrador ga = new GestionAdministrador();
		ga.ruta2 = peticion+"peticion"+file.getFileName().subSequence(tamano-4, tamano);
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(resultado);
			byte[] tamaño = new byte[tamaño_archivo];
			int espacio;
			InputStream inputStream = file.getInputstream();
			while (true) {
				espacio = inputStream.read(tamaño);
				if (espacio < 0) {
					break;
				}
				fileOutputStream.write(tamaño, 0, espacio);
				fileOutputStream.flush();
			}

			fileOutputStream.close();
			inputStream.close();			
			//System.out.println(file.getSize()+" tamaï¿½o"+file.getContentType()+"tipo");
			FacesMessage msg =new FacesMessage("Descripcion del archivo", "Nombre del archivo: " +
			file.getFileName() +"\n Tamaño del archivo: " +
			file.getSize() / 1024 +"\n Tipo: " + file.getContentType() +
			"\\n EL ARCHIVO SE GUARDO CON EXITO!!!!!");

			//FacesContext.getCurrentInstance().addMessage(null, msg);
			//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "PrimeFaces Rocks."));
			
		} catch (IOException e) {
			e.printStackTrace();
			FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_ERROR,"EL ARCHIVO NO SE SUBIO!!!!", "");
			FacesContext.getCurrentInstance().addMessage(null, error);
		}
		
		return nombrearchivo;
	}
	
	public String RealizarCambio(){
		gestionAdministrador=new GestionAdministrador();
		ControladorArchivos controladorArchivos=new ControladorArchivos();
		manejararchivos(peticion);
		//return "solicitudes.xhtml";
		if(estado.equals("Información Encontrada")&& file!=null){
			if(controladorArchivos.tamanoArchivo(file) && controladorArchivos.Reconocer_formato(file)){
				System.out.println(estado);
				manejararchivos(peticion);
				System.out.println(peticion);
				gestionAdministrador.Cambiar_estado_de_la_petición_en_caso_de_recibir_documento(peticion, nombrearchivo);			
				//peticioness.remove(peticion);		
				return "solicitudes.xhtml";
			}
			else{
				return "gestionarsolicitud.xhtml";
			}
		}
		else{
			if(estado.equals("Información Confidencial"))
			{
				if(controladorArchivos.tamanoArchivo(file) && controladorArchivos.Reconocer_formato(file)){
					manejararchivos(peticion);
					System.out.println(peticion);
					gestionAdministrador.Cambiar_estado_de_la_petición_en_caso_de_rechazo(peticion);
					//peticioness.remove(peticion);
					return "solicitudes.xhtml";
				}
				else{
					return "gestionarsolicitud.xhtml";
				}
			}	
			else
				{return "gestionarsolicitud.xhtml";}			
		}
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
		
		/*BeanConsultaCiudadana bcc= new BeanConsultaCiudadana();
		bcc.cedula = 0;
		bcc.email = null;
		bcc.direccion = null;*/
		
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
