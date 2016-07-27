package controlador;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;






import modelo.*;

public class PruebasUnidad {
	
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("LeyTransparencia");
	private static final EntityManager em = emf.createEntityManager();
	private static int i=0;
	
	public static Departamento departamento(){
		
		Departamento dto = new Departamento();
		dto.setIdDepartamento(i+1);
		dto.setNombreDto("Cundinamarca");
		dto.setEstadoDepartamento(true);
		
		em.getTransaction().begin();
		em.persist(dto);
		em.getTransaction().commit();
		
		return dto;
	}
	
	public static Area area(Empresa emps){
		Area ar = new Area();
		ar.setIdtable(i+1);
		ar.setNombreArea("Contabilidad");
		ar.setEstadoArea(true);
		ar.setEmpresa(emps);
		
		em.getTransaction().begin();
		em.persist(ar);
		em.getTransaction().commit();
		
		return ar;
	}
	
	public static Estado estado(){
		Estado est = new Estado();
		
		est.setIdEstado(i+3);
		est.setTipoEstado("Informacion encontr");
		est.setEstadoEstado(true);
		
		em.getTransaction().begin();
		em.persist(est);
		em.getTransaction().commit();
		return est;
	}
	
	public static Gestionador gestionador(Empresa emps){
		Gestionador ges = new Gestionador();
		ges.setCedulaGestionador(1345632456);
		ges.setNombreGestionador("Herman Jose");
		ges.setApellidoGestionador("Garcia Pacheco");
		ges.setContrasenaGestionador("josele2015");
		ges.setEmailGestionador("hjgarcia38@gmail.com");
		ges.setEstadoGestionado(true);
		ges.setEmpresa(emps);
		
		
		em.getTransaction().begin();
		em.persist(ges);
		em.getTransaction().commit();
		return ges;
	}
	
	public static Tipoinformacion tipoinfo(){
		Tipoinformacion tp = new Tipoinformacion();
		tp.setIdTipoInformacion(i+2);
		tp.setNombreTipoInformacion("Contratos");
		tp.setEstadoTipoInformacion(true);
		
		em.getTransaction().begin();
		em.persist(tp);
		em.getTransaction().commit();
		
		return tp;
	}
	
	public static Usuario usuario(){
		Usuario us = new Usuario();
		BigInteger bi = new BigInteger("3103346632", 10);
		int a=1;
		us.setCedulaUsuario(a);
		us.setNombreUsuario("Jessica");
		us.setApellidoUsuario("Ortiza");
		us.setCelularUsuario(bi);
		us.setEmailUsuario("@gmail.com");
		us.setDireccionUsuario("Ciudad salitre");
		us.setContrasenaUsuario("123");
		us.setEstadoUsuario(true);
		
		em.getTransaction().begin();
		em.persist(us);
		em.getTransaction().commit();
		
		return us;		
	}
	
	public static void peticion(Empresa empresa, Usuario usuario, Estado estado, Tipoinformacion tipo) throws ParseException{
		Peticion pet = new Peticion ();
		pet.setIdPeticion(1);
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");		
		pet.setFechaPeticion(formatoDelTexto.parse("2015-05-12"));
		pet.setObservacionesPeticion("Sin comentarios");
		pet.setEstadoPeticion(true);
		pet.setEstado(estado);
		pet.setEmpresa(empresa);
		pet.setTipoinformacion(tipo);
		pet.setUsuario(usuario);
		pet.setReposicion(false);
		
		em.getTransaction().begin();
		em.persist(pet);
		em.getTransaction().commit();
	}
	
	public static Empresa empresa(Ciudad ciu){
		Empresa emp = new Empresa();
		emp.setIdEmpresa(i+2);
		emp.setEstadoEmpresa(true);
		emp.setNombreEmpresa("CODENSA");
		emp.setCiudad(ciu);
		emp.setCiudad_Departamento_idDepartamento(1);
		emp.setCiudad_idCiudad(1);
		
		em.getTransaction().begin();
		em.persist(emp);
		em.getTransaction().commit();
		return emp;
	}
	
	public static Ciudad ciudad(Departamento dep){
		Ciudad c = new Ciudad();
		c.setNombreCiudad("Bogota");
		c.setIdCiudad(i+1);
		c.setEstadoCiudad(true);
		c.setDepartamento(dep);
		
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
		System.out.println(c);
		return c;
	}
	
	public static void main(String args[]) throws ParseException{
		//Departamento d;
		//d = departamento();
		
		//Ciudad ci;
		//ci = ciudad(d);
		
		//Empresa ge;
		//ge=empresa(ci);
		
		//gestionador(ge);
		
		//area(ge);
		
		//Estado e;
		//e=estado();
		
		//Tipoinformacion ti;
		//7ti=tipoinfo();
		
		Usuario u;
		u=usuario();
		
		//peticion(ge, u, e, ti);
		/*long yournumber=23377855;
		Date date= new Date(yournumber);
		System.out.println(date);
		
		SimpleDateFormat formatofecha = new SimpleDateFormat("yyyy-MM-dd");
		Calendar fecha = Calendar.getInstance();
		int año = fecha.get(Calendar.YEAR);
		int mes = fecha.get(Calendar.MONTH)+1;
		int dia = fecha.get(Calendar.DAY_OF_MONTH);
		String añostring = Integer.toString(año);
		String messtring = Integer.toString(mes);
		String diastring = Integer.toString(dia);
		String fechafinal=añostring+"-"+messtring+"-"+diastring;
		try{
			Date dato = formatofecha.parse(fechafinal);
			System.out.println(dato);
			System.out.println(formatofecha.parse(fechafinal));
		}catch (ParseException e){
			e.printStackTrace();
		}
		System.out.println(fechafinal);*/
	}
}
