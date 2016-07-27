package controlador;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.primefaces.model.chart.PieChartModel;
import controlador.GestionUsuario.autentificadorSMTP;

import javax.mail.*;
import javax.mail.internet.*;

import java.util.*;

import vista.BeanEstadistica;
import vista.BeanGestionarSoli;
import vista.BeanRegistrarSolicitud;
import modelo.Empresa;
import modelo.Estado;
import modelo.Gestionador;
import modelo.Peticion;
import modelo.Tipoinformacion;
import modelo.Usuario;

//@autor Henry Joe Wong Urquiza

//La clase que tiene la tarea debe de implementar de la clase Job de Quartz
//ya que el programador de la tarea va a buscar una clase que implemente de ella
//y buscara el metodo execute para ejecutar la tarea
public class TareaInvocar implements Job {
	private static final EntityManagerFactory entitymanagerfactory = Persistence.createEntityManagerFactory("LeyTransparencia");
	private static final EntityManager entitymanager = entitymanagerfactory.createEntityManager();
	
	final String miCorreo = "respuestadesolicitudes@gmail.com";
    final String miContraseña = "respuestadesolicitudes&&";
    final String servidorSMTP = "smtp.gmail.com";
    final String puertoEnvio = "465";
    String mailReceptor = "respuestadesolicitudes@gmail.com";
    
	//Metodo que se ejecutara cada cierto tiempo que lo programemos despues
	public void execute(JobExecutionContext jec) throws JobExecutionException {
		//Aca pueden poner la tarea o el job que desean automatizar
		//Por ejemplo enviar correo, revisar ciertos datos, etc
		Query consultapeticion = entitymanager.createQuery("SELECT p FROM Peticion p");
		List<Peticion> listaconpe=consultapeticion.getResultList();
		Usuario usuario;
		Peticion peticion;// = (Peticion) consultapeticion.getSingleResult();
		System.out.println(listaconpe.size()+"cantidad");
		for(int i=0;i<listaconpe.size();i++){			
			peticion=listaconpe.get(i);
			System.out.println(peticion.getEstado_idEstado()+"Que imprime");
			if(peticion.getEstado_idEstado() ==4){				
				try{
					String asunto = "Advertencia";
				    String cuerpo = "La solicitud nÃºmero "+Integer.toString(peticion.getIdPeticion())+". No ha recibido respuesta";
				    
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
			            msg.addRecipient(Message.RecipientType.TO, new InternetAddress("respuestadesolicitudes@gmail.com"));
			            Transport t = session.getTransport("smtp");
			            Transport.send(msg);
			        } catch (Exception mex) {
			            mex.printStackTrace();
			        }
				}catch(NoResultException es){
					
				}
			       
			}
		}
	    SimpleDateFormat formato = new SimpleDateFormat("hh:mm:ss");
	    System.out.println( "Tarea invocada a la hora: " + formato.format(new Date()));	
	}
}