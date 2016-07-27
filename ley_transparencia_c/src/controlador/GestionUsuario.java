
package controlador;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
 
import java.net.*;
import java.io.*;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.InputStream;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import vista.BeanConsultaCiudadana;
import vista.BeanGestionarSoli;
import vista.BeanIndex;
import vista.BeanMenu;
import vista.BeanMenuGestionador;
import vista.BeanOrganigrama;
import vista.BeanRegistrarReposicion;
import vista.BeanRegistrarSolicitud;
import vista.BeanSolicitud;
import modelo.Area;
import modelo.Ciudad;
import modelo.Departamento;
import modelo.Empresa;
import modelo.Estado;
import modelo.Gestionador;
import modelo.Peticion;
import modelo.Tipoinformacion;
import modelo.Usuario;

import javax.mail.*;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.*;

public class GestionUsuario {	
	private static final String buscarusuario="SELECT u FROM Usuario u";
	private static final String buscarpeticion="SELECT p FROM Peticion p";

	private FacesMessage facesMessage;
	private static Usuario usuario;
	private static Gestionador Gestionador;
	private static Peticion peticion;
	private static Empresa empresa;
	private static Tipoinformacion tipoinformacion;
	int idtipo=0, idempresa=0,idempresaciudad=0,idempresadepartamento=0, idpeticion=0;
	String nombre, apellido;
	StreamedContent file;
	
	BigInteger celularsolicitante=new BigInteger("3102132231", 10);
	
	final String miCorreo = "respuestadesolicitudes@gmail.com";
    final String miContraseña = "respuestadesolicitudes&&";
    final String servidorSMTP = "smtp.gmail.com";
    final String puertoEnvio = "465";
    private Map<String,Map<String,String>> data = new HashMap<String, Map<String,String>>();
	
	public Map<String, Map<String, String>> getData() {
		return data;
	}	

	public Usuario RegistrarSolicitante (int cedula, String nombre, String apellido, BigInteger celular, String email, String direccion, String contrasena){
		EntityManagerFactory entitymanagerfactory = Persistence.createEntityManagerFactory("LeyTransparencia");
		EntityManager entitymanager = entitymanagerfactory.createEntityManager();
		entitymanager.getEntityManagerFactory().getCache().evictAll();
		String cedulaso=Integer.toString(cedula);
		usuario = new Usuario();
		try{	
			Query consulta =entitymanager.createQuery("Select u From Usuario u Where u.cedulaUsuario="+cedula);
			usuario = (Usuario) consulta.getSingleResult();
			System.out.println(consulta);
		}catch(NoResultException e){			
			BeanRegistrarSolicitud bs = new BeanRegistrarSolicitud();			
			usuario.setCedulaUsuario(cedula);
			usuario.setNombreUsuario(nombre);
			usuario.setApellidoUsuario(apellido);
			usuario.setCelularUsuario(celular);			
			usuario.setEmailUsuario(email);			
			usuario.setDireccionUsuario(direccion);			
			usuario.setContrasenaUsuario(contrasena);
			usuario.setEstadoUsuario(true);
			
			bs.nombre=usuario.getNombreUsuario();
			bs.apellido=usuario.getApellidoUsuario();
			
			BeanMenu bm = new BeanMenu();
			bm.nombre=usuario.getNombreUsuario();
			bm.apellido=usuario.getApellidoUsuario();
			
			BeanOrganigrama bo = new BeanOrganigrama();
			bo.nombre=usuario.getNombreUsuario();
			bo.apellido=usuario.getApellidoUsuario();
			
			bs.direccion = usuario.getDireccionUsuario();
			bs.email = usuario.getEmailUsuario();
			bs.celular = usuario.getCelularUsuario();
			bs.nombre = usuario.getNombreUsuario();
			bs.apellido = usuario.getApellidoUsuario();
			bs.cedula = usuario.getCedulaUsuario();
			
			BeanConsultaCiudadana bcc= new BeanConsultaCiudadana();
			bcc.nombre=usuario.getNombreUsuario();
			bcc.apellido = usuario.getApellidoUsuario();
			bcc.direccion = usuario.getDireccionUsuario();
			bcc.celular = usuario.getCelularUsuario();
			bcc.cedula = usuario.getCedulaUsuario();
			
			BeanRegistrarReposicion brr = new BeanRegistrarReposicion();
			brr.cedula=usuario.getCedulaUsuario();
			brr.email = usuario.getEmailUsuario();
			brr.direccion = usuario.getDireccionUsuario();
			brr.celular = usuario.getCelularUsuario();
			brr.nombre = usuario.getNombreUsuario();
			brr.apellido = usuario.getApellidoUsuario();
			
			entitymanager.getTransaction().begin();
			entitymanager.persist(usuario);
			entitymanager.getTransaction().commit();			
			return usuario;
		}				
		return null;
	}
	
	public Object AutenticarSolicitante(String cedula, String contrasena){
		EntityManagerFactory entitymanagerfactory = Persistence.createEntityManagerFactory("LeyTransparencia");
		EntityManager entitymanager = entitymanagerfactory.createEntityManager();
		entitymanager.getEntityManagerFactory().getCache().evictAll();
		try{
			Query consultausuario = entitymanager.createQuery("SELECT u FROM Usuario u WHERE u.cedulaUsuario ="+cedula+" and u.contrasenaUsuario ="+"\""+contrasena+"\"");
			usuario =(Usuario) consultausuario.getSingleResult();
			
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Usuario", usuario);
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			session.setAttribute("usuarioic", usuario);
			
			BeanRegistrarSolicitud bs = new BeanRegistrarSolicitud();
			
			BeanMenu bm = new BeanMenu();
			bm.nombre=usuario.getNombreUsuario();
			bm.apellido=usuario.getApellidoUsuario();
			//bm=(BeanMenu) session.getAttribute("usuarioic");
			BeanOrganigrama bo = new BeanOrganigrama();
			bo.nombre=usuario.getNombreUsuario();
			bo.apellido=usuario.getApellidoUsuario();
			
			bs.direccion = usuario.getDireccionUsuario();
			bs.email = usuario.getEmailUsuario();
			bs.celular = usuario.getCelularUsuario();
			bs.nombre=usuario.getNombreUsuario();
			bs.apellido=usuario.getApellidoUsuario();
			bs.cedula = usuario.getCedulaUsuario();
			//bs= (BeanRegistrarSolicitud) session.getAttribute(null);
			
			BeanConsultaCiudadana bcc= new BeanConsultaCiudadana();
			bcc.cedula=usuario.getCedulaUsuario();
			bcc.email = usuario.getEmailUsuario();
			bcc.direccion = usuario.getDireccionUsuario();
			bcc.celular = usuario.getCelularUsuario();
			bcc.nombre=usuario.getNombreUsuario();
			bcc.apellido=usuario.getApellidoUsuario();
			
			BeanRegistrarReposicion brr = new BeanRegistrarReposicion();
			brr.cedula=usuario.getCedulaUsuario();
			brr.email = usuario.getEmailUsuario();
			brr.direccion = usuario.getDireccionUsuario();
			brr.celular = usuario.getCelularUsuario();
			brr.nombre=usuario.getNombreUsuario();
			brr.apellido=usuario.getApellidoUsuario();
			
			//.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Has iniciado Sesiï¿½n",""));
			return usuario;
		}catch(NoResultException e){
			try{
				Query consultauGestionador = entitymanager.createQuery("SELECT g FROM Gestionador g WHERE g.cedulaGestionador ="+cedula+" and g.contrasenaGestionador="+"\""+contrasena+"\"");
				Gestionador = (Gestionador) consultauGestionador.getSingleResult();
				
				/*BeanMenuGestionador bmg = new BeanMenuGestionador();
				bmg.trabajo();*/
				
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Has iniciado Sesión",""));
				return Gestionador; 
			}catch(NoResultException ew){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La cedula o contraseña es incorrecta"));
				return "mal";
			}				
		}
	}
	
	
	public Peticion registrarpeticion(Date fecha, String observaciones, String area, String entidad, String tipoinfo, String año, int cedula) {
		EntityManagerFactory entitymanagerfactory = Persistence.createEntityManagerFactory("LeyTransparencia");
		EntityManager entitymanager = entitymanagerfactory.createEntityManager();
		entitymanager.getEntityManagerFactory().getCache().evictAll();
		Query querypeticion=entitymanager.createQuery(buscarpeticion);
		List<Peticion> listapeticiones = querypeticion.getResultList();
		idpeticion=listapeticiones.size()+1;
		System.out.println(listapeticiones.size());
		System.out.println(idpeticion);
		try{
			Query consultapeticion = entitymanager.createQuery("SELECT p FROM Peticion p WHERE p.idPeticion ="+Integer.toString(idpeticion));
			List<Peticion> listaconpe=consultapeticion.getResultList();
			peticion=(Peticion) consultapeticion.getSingleResult();
			System.out.println("Ya existe el id. /n Vuelva intentarlo.");
			
			
		}catch(NoResultException e){
			try{
				Query consultaestado=entitymanager.createQuery("SELECT e FROM Estado e WHERE e.tipoEstado = :tipoEstado");
				consultaestado.setParameter("tipoEstado", "Buscando informacion");
				Estado listaestados=(Estado) consultaestado.getSingleResult();
				System.out.println("Estado encontrado.");
				try{
					Query consultausuario=entitymanager.createQuery("SELECT u FROM Usuario u WHERE u.cedulaUsuario = "+cedula);
					System.out.println(cedula);
					//consultausuario.setParameter("cedulaUsuario", cedula);
					Usuario listausuario=(Usuario) consultausuario.getSingleResult();
					System.out.println("Usuario encontrado.");
					try{
						Query consultatipo=entitymanager.createQuery("SELECT t FROM Tipoinformacion t WHERE t.nombreTipoInformacion = :nombreTipoInformacion");
						consultatipo.setParameter("nombreTipoInformacion", tipoinfo);
						System.out.println(tipoinfo+"*************");
						Tipoinformacion listatipo=(Tipoinformacion) consultatipo.getSingleResult();
						System.out.println("Tipo enconrado.");
						
						Query q=entitymanager.createQuery("SELECT t FROM Tipoinformacion t");
						List<Tipoinformacion> resultados=q.getResultList();
						tipoinformacion = new Tipoinformacion();
						for(int i=0;i<resultados.size();i++){
							tipoinformacion=resultados.get(i);
				        	
				        	String tipo=tipoinformacion.getNombreTipoInformacion();
				        	if(tipo.equals(tipoinfo)){
				        		idtipo=tipoinformacion.getIdTipoInformacion();				        		
				        	}
						}
						
						try{
							Query consultaempresa=entitymanager.createQuery("SELECT e FROM Empresa e WHERE e.nombreEmpresa = :nombreEmpresa");
							consultaempresa.setParameter("nombreEmpresa", entidad);
							Empresa listaempresa=(Empresa) consultaempresa.getSingleResult();
							
							System.out.println("Empresa enconrada.");							
			        		idempresa=listaempresa.getIdEmpresa();
			        		idempresaciudad=listaempresa.getCiudad_idCiudad();
			        		idempresadepartamento=listaempresa.getCiudad_Departamento_idDepartamento();       

			        		java.util.Date utilDate = new java.util.Date();
			        		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			        		
							peticion = new Peticion();
							peticion.setIdPeticion(idpeticion);
							peticion.setFechaPeticion(sqlDate);
							peticion.setObservacionesPeticion(observaciones);
							peticion.setEstadoPeticion(true);
							peticion.setReposicion(false);
							peticion.setEstado(listaestados);
							peticion.setEmpresa(listaempresa);
							peticion.setUsuario(listausuario);
							peticion.setTipoinformacion(listatipo);			
							usuario.addPeticion(peticion);
							entitymanager.getTransaction().begin();
							entitymanager.persist(peticion);
							entitymanager.getTransaction().commit();
							
							
							guardar_area(area,idpeticion);
							return peticion;
						}catch(NoResultException e4){
							System.out.println("Empresa no enconrada.");
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}catch(NoResultException e3){
						System.out.println("No existe este tipo de informacion. /n Vuelva intentarlo.");
					}
				}catch(NoResultException e2){
					System.out.println("Usuario no encontrado.");
				}
			}catch(NoResultException e1){
				System.out.println("Ya estado no existe. /n Vuelva intentarlo.");
			}
		}
		return null;
	}
	
	public void registrarreposicion(Date fecha, String observaciones, String area, String entidad, String tipoinfo, String año, int cedula){
		EntityManagerFactory entitymanagerfactory = Persistence.createEntityManagerFactory("LeyTransparencia");
		EntityManager entitymanager = entitymanagerfactory.createEntityManager();
		entitymanager.getEntityManagerFactory().getCache().evictAll();
		Query querypeticion=entitymanager.createQuery(buscarpeticion);
		List<Peticion> listapeticiones = querypeticion.getResultList();
		idpeticion=listapeticiones.size()+1;
		System.out.println(listapeticiones.size());
		System.out.println(idpeticion);
		try{
			Query consultapeticion = entitymanager.createQuery("SELECT p FROM Peticion p WHERE p.idPeticion ="+Integer.toString(idpeticion));
			List<Peticion> listaconpe=consultapeticion.getResultList();
			peticion=(Peticion) consultapeticion.getSingleResult();
		}catch(NoResultException e){
			//if (peticion.getReposicion()==false){
			
				try{
					Query consultaestado=entitymanager.createQuery("SELECT e FROM Estado e WHERE e.tipoEstado = :tipoEstado");
					consultaestado.setParameter("tipoEstado", "Derecho de reposicion");
					Estado listaestados=(Estado) consultaestado.getSingleResult();
					System.out.println("Estado encontrado.");
					try{
						Query consultausuario=entitymanager.createQuery("SELECT u FROM Usuario u WHERE u.cedulaUsuario = "+cedula);
						System.out.println(cedula);
						//consultausuario.setParameter("cedulaUsuario", cedula);
						Usuario listausuario=(Usuario) consultausuario.getSingleResult();
						System.out.println("Usuario encontrado.");
						try{
							Query consultatipo=entitymanager.createQuery("SELECT t FROM Tipoinformacion t WHERE t.nombreTipoInformacion = :nombreTipoInformacion");
							consultatipo.setParameter("nombreTipoInformacion", tipoinfo);
							Tipoinformacion listatipo=(Tipoinformacion) consultatipo.getSingleResult();
							System.out.println("Tipo enconrado.");
							
							Query q=entitymanager.createQuery("SELECT t FROM Tipoinformacion t");
							List<Tipoinformacion> resultados=q.getResultList();
							tipoinformacion = new Tipoinformacion();
							for(int i=0;i<resultados.size();i++){
								tipoinformacion=resultados.get(i);
				        	
								String tipo=tipoinformacion.getNombreTipoInformacion();
								if(tipo.equals(tipoinfo)){
									idtipo=tipoinformacion.getIdTipoInformacion();				        		
								}
							}						
							try{
								Query consultaempresa=entitymanager.createQuery("SELECT e FROM Empresa e WHERE e.nombreEmpresa = :nombreEmpresa");
								consultaempresa.setParameter("nombreEmpresa", entidad);
								Empresa listaempresa=(Empresa) consultaempresa.getSingleResult();
							
								System.out.println("Empresa enconrada.");							
								idempresa=listaempresa.getIdEmpresa();
								idempresaciudad=listaempresa.getCiudad_idCiudad();
								idempresadepartamento=listaempresa.getCiudad_Departamento_idDepartamento();       

								java.util.Date utilDate = new java.util.Date();
								java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
								
								peticion = new Peticion();
								peticion.setIdPeticion(idpeticion);
								peticion.setFechaPeticion(sqlDate);
								peticion.setObservacionesPeticion(observaciones);
								peticion.setEstadoPeticion(true);
								peticion.setReposicion(true);
								peticion.setEstado(listaestados);
								peticion.setEmpresa(listaempresa);
								peticion.setUsuario(listausuario);
								peticion.setTipoinformacion(listatipo);			
								usuario.addPeticion(peticion);
								entitymanager.getTransaction().begin();
								entitymanager.persist(peticion);
								entitymanager.getTransaction().commit();
								
								guardar_area(area,idpeticion);
							}catch(NoResultException e4){
							System.out.println("Empresa no enconrada.");
						}
					}catch(NoResultException e3){
						System.out.println("No existe este tipo de informacion. /n Vuelva intentarlo.");
					}
				}catch(NoResultException e2){
					System.out.println("Usuario no encontrado.");
				}
				
				}catch(NoResultException e1){
				System.out.println("Ya estado no existe. /n Vuelva intentarlo.");
			//}
			}
		}
	}
	
	public Object previsual(Date fecha, String entidad, String ciudad, String observacion, String correo, String direccion, int cedula, String nombre, String apellido) throws FileNotFoundException{
		EntityManagerFactory entitymanagerfactory = Persistence.createEntityManagerFactory("LeyTransparencia");
		EntityManager entitymanager = entitymanagerfactory.createEntityManager();
		entitymanager.getEntityManagerFactory().getCache().evictAll();
		Date date = new Date();
		Query querypeticion=entitymanager.createQuery(buscarpeticion);
		List<Peticion> listapeticiones = querypeticion.getResultList();
		idpeticion=listapeticiones.size()+1;
		DateFormat fechas = new SimpleDateFormat("yyyy-MM-dd");
		String convertido = fechas.format(date);
		String encabezado="Bogota D.C., "+convertido;
		String referencia="Solicitud de información";
		String dirigido="Respetado señor/a:";
		String parrafo1="En ejercicio del derecho fundamental de petición, consagrado en el artículo 23 de la Consitución Nacional, y del derecho de acceso a la infomación pública, consagrados en los artículos 20 y 74 de la misma, desarrollado por la ley 1712 de 2014; de manera respetuosa le solicito la siguiente información:";		
		String parrafo2=observacion;		
		String parrafo3="La respuesta a la presente solicitud la recibirá en la dirección de correo electronico "+direccion+" o dirección fisica "+correo+".";
		String cordialmente="Cordialmente.";
		String nombres=nombre+" "+apellido;
		try{
			FileOutputStream archivo = new FileOutputStream("C:/tmp/tmp"+cedula+"_"+idpeticion+".pdf");
			Document documento = new Document();
		    PdfWriter.getInstance(documento, archivo);
		    documento.open();
		    documento.add(new Paragraph(encabezado));
		    documento.add(new Paragraph("\n\n\n\n"));
		    documento.add(new Paragraph(referencia));
		    documento.add(new Paragraph("\n\n\n\n"));
		    documento.add(new Paragraph(dirigido));
		    documento.add(new Paragraph("\n\n"));
		    documento.add(new Paragraph(parrafo1));
		    documento.add(new Paragraph("\n\n"));
		    documento.add(new Paragraph(parrafo2));
		    documento.add(new Paragraph("\n\n\n\n"));
		    documento.add(new Paragraph(parrafo3));
		    documento.add(new Paragraph("\n\n\n\n\n\n"));
		    documento.add(new Paragraph(cordialmente));
		    documento.add(new Paragraph("\n\n\n\n"));
		    documento.add(new Paragraph(nombres));
		    documento.add(new Paragraph("\n"));
		    documento.add(new Paragraph(Integer.toString(cedula)));
		    documento.close();
		    
		    try{
		    	File path = new File ("C:/tmp/tmp"+cedula+"_"+idpeticion+".pdf");
		    	Desktop d = java.awt.Desktop.getDesktop ();
		    	d.open (new java.io.File ("C:/tmp/tmp"+cedula+"_"+idpeticion+".pdf"));
		    	//Desktop.getDesktop().browse(new URI("http://www.sun.com"));
		    	//Desktop.getDesktop().open(path);
		    }
		    catch(IOException es){
		    	es.printStackTrace();
		    }
		    //Process p = Runtime.getRuntime().exec (new String[]{"/home/josele2015/Documentos/tmp/tmp123_4.pdf"});
		    
		    //Runtime.getRuntime().exec("cmd /c start "+"C:/tmp/tmp"+cedula+"_"+idpeticion+".pdf");
		    //Desktop.getDesktop().open(myFile);
		    
		    /*InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("C:/Users/JuanJose/Documents/Peticiones/"+cedula+"_"+idpeticion+".pdf");
	        file = new DefaultStreamedContent(stream, "application/pdf", "solicitud.pdf");*/
		    
		}
		catch (Exception e){
				e.printStackTrace();
		}
		return file;
	}
	
	public Object previsual_reposicion(Date fecha, String entidad, String ciudad, String observacion, String correo, String direccion, int cedula, String nombre, String apellido) throws FileNotFoundException{
		EntityManagerFactory entitymanagerfactory = Persistence.createEntityManagerFactory("LeyTransparencia");
		EntityManager entitymanager = entitymanagerfactory.createEntityManager();
		entitymanager.getEntityManagerFactory().getCache().evictAll();
		Date date = new Date();
		Query querypeticion=entitymanager.createQuery(buscarpeticion);
		List<Peticion> listapeticiones = querypeticion.getResultList();
		idpeticion=listapeticiones.size()+1;
		DateFormat fechas = new SimpleDateFormat("yyyy-MM-dd");
		String convertido = fechas.format(date);
		String encabezado="Bogota D.C., "+convertido;
		String referencia="Referencia: Recurso de reposición";
		String dirigido="Respetado señor/a:";
		String parrafo1="En atención a su respuesta a la solicitud del, mediante el cual solicite me permito presentar recurso de reposición de acuerdo al artículo 27 de la ley 1712 de 2014.";
		String parrafo2="La información solicitada es pública, ya que ";
		String parrafo3="Por lo tanto y en el ejercicio del derecho fundamental de petición consagrado en el artículo 23 de la Constitución Nacional, consagrado en los artículos 20 y 74 de la misma, desarrollado por la ley 1712 de 2014:";
		String parrafo4=observacion;
		String parrafo5="En caso de que la entidad decida volver a negar la información, solicito  que se envie la información al Tribunal de lo Contencioso Administrativo o a los Juzgados Administrativos de acuerdo al citado artículo 27 de la ley 1712 de 2014:";
		String parrafo6="'Negado este recurso corresponderá al Tribunal administrativo con jurisdicción en el lugar donde se encuentren los documentos, si se trata de autoridades nacionales, departamentales o del Distrito Capital de Bogotá, o al juez administrativo si se trata de autoridades distritales y municipales, decidir en única instancia si se niega o se adopta, total o parcialmente, la petición formulada.";
		String parrafo7="Para ello el funcionarios respectivo enviará la documentación correspondiente al tribunal o al juez administrativo en un plazo no superior a tres (3) días (...)'";
		String parrafo8="Es importante recordar que el funcionario público debe, en virtud del cuplimiento de la ley, darle el trámite correspondiente al derecho de insistencia. De acuerdo con el artículo 79 de la ley 190 de 1995 'será causal de mala conducta el hecho de que un funcionario público obstaculice, retarde o niegue inmotivadamente el acceso de la ciudadania, en general, y de los medios de comunicación, en particular, a los documentos que reposen en la dependencia a su cargo y cuya solicitud se haya presentado con el cumplimiento de los requisitos exigidos por la ley'.";
		String parrafo9="La respuesta al presente recurso de reposición la recibirá en la dirección de correo electronico "+direccion+" o dirección fisica "+correo+".";
		String parrafo10="Agradezco de antemano su colaboración,";
		String Atentamente="Atentamente.";
		String firma=nombre+" "+apellido;
		try{
			FileOutputStream archivo = new FileOutputStream("C:/tmp/tmp"+cedula+"_reposicion_"+idpeticion+".pdf");
			Document documento = new Document();
		    PdfWriter.getInstance(documento, archivo);
		    documento.open();
		    documento.add(new Paragraph(encabezado));
		    documento.add(new Paragraph("\n\n\n\n"));
		    documento.add(new Paragraph(referencia));
		    documento.add(new Paragraph("\n\n\n\n"));
		    documento.add(new Paragraph(dirigido));
		    documento.add(new Paragraph("\n\n"));
		    documento.add(new Paragraph(parrafo1));
		    documento.add(new Paragraph("\n"));
		    documento.add(new Paragraph(parrafo2));
		    documento.add(new Paragraph("\n"));
		    documento.add(new Paragraph(parrafo3));
		    documento.add(new Paragraph("\n"));
		    documento.add(new Paragraph(parrafo4));
		    documento.add(new Paragraph("\n"));
		    documento.add(new Paragraph(parrafo5));
		    documento.add(new Paragraph("\n"));
		    documento.add(new Paragraph(parrafo6));
		    documento.add(new Paragraph("\n"));
		    documento.add(new Paragraph(parrafo7));
		    documento.add(new Paragraph("\n"));
		    documento.add(new Paragraph(parrafo8));
		    documento.add(new Paragraph("\n"));
		    documento.add(new Paragraph(parrafo9));
		    documento.add(new Paragraph("\n"));
		    documento.add(new Paragraph(parrafo10));
		    documento.add(new Paragraph(Atentamente));
		    documento.add(new Paragraph("\n\n"));
		    documento.add(new Paragraph(firma));
		    documento.add(new Paragraph(Integer.toString(cedula)));
		    documento.close();
		    
		    try{
		    	File path = new File ("C:/tmp/tmp"+cedula+"_reposicion_"+idpeticion+".pdf");
		    	Desktop d = java.awt.Desktop.getDesktop ();
		    	d.open (new java.io.File ("C:/tmp/tmp"+cedula+"_reposicion_"+idpeticion+".pdf"));
		    	//Desktop.getDesktop().browse(new URI("http://www.sun.com"));
		    	//Desktop.getDesktop().open(path);
		    }
		    catch(IOException es){
		    	es.printStackTrace();
		    }
		    
		    //Process p = Runtime.getRuntime().exec ("rundll32 SHELL32.DLL,ShellExec_RunDLL "+"C:/Users/JuanJose/Documents/Peticiones/"+cedula+"_reposicion_"+idpeticion+".pdf");
		    
		    //Runtime.getRuntime().exec("cmd /c start "+"C:/tmp/tmp"+cedula+"_reposicion_"+idpeticion+".pdf");
		    
		    /*InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("C:/Users/JuanJose/Documents/Peticiones/"+cedula+"_reposicion_"+idpeticion+".pdf");
	        file = new DefaultStreamedContent(stream, "image/pdf", "solicitud.pdf");*/
		    
		}
		catch (Exception e){
				e.printStackTrace();
		}
		return file;
	}
	
	public List<Peticion> Mostrar_las_peticiones_que_ha_realizado_un_solicitante(int cedula){
		EntityManagerFactory entitymanagerfactory = Persistence.createEntityManagerFactory("LeyTransparencia");
		EntityManager entitymanager = entitymanagerfactory.createEntityManager();
		entitymanager.getEntityManagerFactory().getCache().evictAll();
		Query consultausuariopeticion=entitymanager.createQuery("SELECT u FROM Usuario u WHERE u.cedulaUsuario = "+cedula);
		Usuario usuario=(Usuario) consultausuariopeticion.getSingleResult();

		List<Peticion> listausuariopeticion=usuario.getPeticions();
		System.out.println("el usuario existe");
		return listausuariopeticion;
	}

	public List<String> peticiones(int cedula){		
		EntityManagerFactory entitymanagerfactory = Persistence.createEntityManagerFactory("LeyTransparencia");
		EntityManager entitymanager = entitymanagerfactory.createEntityManager();List<Peticion> peticiones;
		peticiones=new ArrayList();
		List<String> peticioness;
		Query consultausuario=entitymanager.createQuery("SELECT u FROM Usuario u WHERE u.cedulaUsuario = "+cedula);
		System.out.println(cedula);
		//consultausuario.setParameter("cedulaUsuario", cedula);
		Usuario listausuario=(Usuario) consultausuario.getSingleResult();
		System.out.println("Usuario encontrado.");	
		
		List<Peticion>listaPeticion=listausuario.getPeticions();
        
        peticioness=new ArrayList();
        
        for(int i=0;i<listaPeticion.size();i++){
        	//System.out.println("i"+i);
        	peticioness.add(Integer.toString(listaPeticion.get(i).getIdPeticion()));
        	
        	//return peticioness;
        }
		return peticioness;
	}
	
	public void enviarconfirmacion(String mailReceptor, String contrasena, int cedula){
		EntityManagerFactory entitymanagerfactory = Persistence.createEntityManagerFactory("LeyTransparencia");
		EntityManager entitymanager = entitymanagerfactory.createEntityManager();
		entitymanager.getEntityManagerFactory().getCache().evictAll();
		String asunto = "Respuesta de solicitud";
	    String cuerpo = "Usted se acaba de registrar en la pagina de solicitud de informacion pública."+
	    "\n Su usuario es: "+cedula+"\n Su contraseña es: "+contrasena;
	    
	    Properties props = new Properties();
        props.put("mail.smtp.mail.sender",miCorreo);
	    props.put("mail.smtp.user", miCorreo);
        props.put("mail.smtp.host", servidorSMTP);
        props.put("mail.smtp.port", puertoEnvio);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", puertoEnvio);
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.starttls.enable","false" );
        
        SecurityManager security = System.getSecurityManager();
        try {
            Authenticator auth = new autentificadorSMTP();            
            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(miCorreo, miContraseña);
                }
            });
            //session = Session.getDefaultInstance(props);
            // session.setDebug(true);

            MimeMessage msg = new MimeMessage(session);
            msg.setText(cuerpo);
            msg.setSubject(asunto);
            msg.setFrom(new InternetAddress(((String)props.get("mail.smtp.mail.sender"))));            
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(mailReceptor));
            Transport t = session.getTransport("smtp");
            t.send(msg);
        } catch (Exception mex) {
            mex.printStackTrace();
        }
	}
	
	public class autentificadorSMTP extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(miCorreo, miContraseña);
        }
    }
	
	public Object cargartiposinformacion(){
		EntityManagerFactory entitymanagerfactory = Persistence.createEntityManagerFactory("LeyTransparencia");
		EntityManager entitymanager = entitymanagerfactory.createEntityManager();
		entitymanager.getEntityManagerFactory().getCache().evictAll();
		Map<String,String> tipos;
		tipos  = new HashMap<String, String>();
		
		Query buscartipos = entitymanager.createQuery("SELECT t FROM Tipoinformacion t");
        
        List<Tipoinformacion> listatipos = buscartipos.getResultList();
        Peticion peticion = new Peticion();
        Estado estado = new Estado();
        Tipoinformacion ti = new Tipoinformacion();
        
        int idtipo=0;
        int c=0;
        
        for(int i=0;i<listatipos.size();i++){
        	ti=listatipos.get(i);
        	idtipo=ti.getIdTipoInformacion();        	
        	tipos.put(ti.getNombreTipoInformacion(), ti.getNombreTipoInformacion());            
        }
		return tipos;
	}
	
	public Object cargarempresa(){
		EntityManagerFactory entitymanagerfactory = Persistence.createEntityManagerFactory("LeyTransparencia");
		EntityManager entitymanager = entitymanagerfactory.createEntityManager();
		entitymanager.getEntityManagerFactory().getCache().evictAll();
		Map<String,String> empresas;
		empresas  = new HashMap<String, String>();
		
		Query buscarempresas = entitymanager.createQuery("SELECT e FROM Empresa e");
        
        List<Empresa> listaempresa = buscarempresas.getResultList();
        Empresa em = new Empresa();
        
        int idempresa=0;
        int c=0;
        
        for(int i=0;i<listaempresa.size();i++){
        	em=listaempresa.get(i);
        	idempresa=em.getIdEmpresa();
        	empresas.put(em.getNombreEmpresa(), em.getNombreEmpresa());            
        }
        //cargarareas(empresas);
		return empresas;
	}
	
	public Object cargarareas(String empresa){
		EntityManagerFactory entitymanagerfactory = Persistence.createEntityManagerFactory("LeyTransparencia");
		EntityManager entitymanager = entitymanagerfactory.createEntityManager();
		entitymanager.getEntityManagerFactory().getCache().evictAll();
		BeanRegistrarSolicitud br = new BeanRegistrarSolicitud();
		System.out.println(empresa+" edffdgdgdgd 1");
		empresa=br.getEmpresa();
		Map<String,String> areas;
		areas  = new HashMap<String, String>();
		
		try{
			Query buscarareas = entitymanager.createQuery("SELECT a FROM Area a");
			Query consultaempresa=entitymanager.createQuery("SELECT e FROM Empresa e WHERE e.nombreEmpresa = :nombreEmpresa");
			System.out.println(empresa+" edffdgdgdgd 2");
			consultaempresa.setParameter("nombreEmpresa", empresa);
			Empresa listaempresa=(Empresa) consultaempresa.getSingleResult();
			List<Area> listaarea = buscarareas.getResultList();
	        Peticion peticion = new Peticion();
	        Estado estado = new Estado();
	        Area ar = new Area();
	        
	        int idarea=0;
	        int c=0;
	        
	        for(int i=0;i<listaarea.size();i++){
	        	ar=listaarea.get(i);
        		idarea=ar.getEmpresa_idEmpresa();
	        	if(listaempresa.getIdEmpresa()==ar.getEmpresa_idEmpresa()){
	        		ar=listaarea.get(i);
	        		idarea=ar.getIdtable();
	        		areas.put(ar.getNombreArea(), ar.getNombreArea());
	        		data.put("Germany", areas);
	        	}
	        }			
		}catch(NoResultException e1){
			
		}
		//"SELECT a FROM Area a WHERE a.empresa = :empresa"
		/*Query buscarareas = entitymanager.createQuery("SELECT a FROM Area a WHERE a.empresa = :empresa");
		consultaempresa.setParameter("empresa", "0");*/
		return areas;
	}
	
	public Object cargar(String idpeticion){
		EntityManagerFactory entitymanagerfactory = Persistence.createEntityManagerFactory("LeyTransparencia");
		EntityManager entitymanager = entitymanagerfactory.createEntityManager();
		entitymanager.getEntityManagerFactory().getCache().evictAll();
		int idarea=0;
		int identidad=0;
		int idtipo=0;
		int idreposicion=0;
		
		Query querypeticion=entitymanager.createQuery(buscarpeticion);
		List<Peticion> listapeticiones = querypeticion.getResultList();
		idreposicion=listapeticiones.size()+1;
		
		Peticion peticion = new Peticion();
		Query consultapeticion = entitymanager.createQuery("SELECT p FROM Peticion p WHERE p.idPeticion ="+Integer.parseInt(idpeticion));
		List<Peticion> listaconpe=consultapeticion.getResultList();
		
		peticion=(Peticion) consultapeticion.getSingleResult();
		
		identidad=peticion.getEmpresa_idEmpresa();
		idtipo=peticion.getTipoInformacion_idTipoInformacion();
		//String tipo=
		int idpeticion1=0;
		idpeticion1=peticion.getIdPeticion();
		
		try{
			Query consultatipo=entitymanager.createQuery("SELECT t FROM Tipoinformacion t WHERE t.idTipoInformacion = :idTipoInformacion");
			consultatipo.setParameter("idTipoInformacion", idtipo);
			Tipoinformacion listatipo=(Tipoinformacion) consultatipo.getSingleResult();
			System.out.println("tipo de información encontrado");
			try{
				Query consultaempresa=entitymanager.createQuery("SELECT e FROM Empresa e WHERE e.idEmpresa = :idEmpresa");
				consultaempresa.setParameter("idEmpresa", identidad);
				Empresa listaempresa=(Empresa) consultaempresa.getSingleResult();
				System.out.println("empresa encontrada");
				try{
					String ruta="C:/area/area"+idpeticion+".txt";
					File fichero = new File(ruta);						
					if(fichero.exists()){							
						//File archivo = new File(ruta);
						//BufferedWriter bw;
						try {
							FileReader lector = new FileReader("C:/area/area"+idpeticion+".txt");
							BufferedReader contenido = new BufferedReader(lector);
							String area=contenido.readLine();
							BeanRegistrarReposicion brr = new BeanRegistrarReposicion();
							brr.setNombreentidad(listaempresa.getNombreEmpresa());
							brr.entidad=listaempresa.getNombreEmpresa();
							brr.setTipoinformacion(listatipo.getNombreTipoInformacion());
							brr.tipoinfo=listatipo.getNombreTipoInformacion();
							brr.setAreaentidad(area);
							brr.entidadarea=area; 
						} catch (IOException e) {
							e.printStackTrace();
							System.out.println("no sale nada");
						}
					}		
				}catch(NoResultException e3){
					System.out.println("no sale nada");
				}
				
			}catch(NoResultException e2){
				System.out.println("no sale nada");
			}
			
		}catch(NoResultException e1){
			System.out.println("no sale nada");
		}		
		return null;
	}
	
	public void guardar_area(String nombrearea, int id) {
		EntityManagerFactory entitymanagerfactory = Persistence.createEntityManagerFactory("LeyTransparencia");
		EntityManager entitymanager = entitymanagerfactory.createEntityManager();
		entitymanager.getEntityManagerFactory().getCache().evictAll();
		String ruta="C:/area/area"+id+".txt";
		File archivo = new File(ruta);
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(archivo));
			bw.write(nombrearea);
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
//http://localhost:8080/Ley/consultaciudadana.xhtml