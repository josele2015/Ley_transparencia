package controlador;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import junit.framework.TestCase;
import modelo.Area;
import modelo.Ciudad;
import modelo.Departamento;
import modelo.Empresa;
import modelo.Estado;
import modelo.Gestionador;
import modelo.Peticion;
import modelo.Tipoinformacion;
import modelo.Usuario;

import org.junit.Test;

public class PruebasUnidadTest extends TestCase{

	Departamento departamento;
	Ciudad ciudad;
	Empresa empresa;
	Gestionador gestionador;
	Area area;
	Estado estado;
	Tipoinformacion tipoinfo;
	Usuario usuario;
	Peticion peticion;
	
	@Test
	public void setUp() throws Exception{
		departamento = new Departamento ();
		ciudad = new Ciudad ();
		empresa = new Empresa();
		gestionador = new Gestionador();
		area = new Area();
		estado = new Estado();
		tipoinfo = new Tipoinformacion();
		usuario = new Usuario();
		peticion = new Peticion();
	}
	
	@Test
	public void testDepartamento() throws SQLException{
		try{
			departamento.setIdDepartamento(1);
			departamento.setNombreDto("Cundinamarca");
			departamento.setEstadoDepartamento(true);
			System.out.println("funciona departamento");
		}catch(Exception e){
					
		}
	}
	
	@Test
	public void testCiudad() throws SQLException{
		try{
			ciudad.setIdCiudad(1);
			ciudad.setNombreCiudad("Bogota D.C.");
			ciudad.setEstadoCiudad(false);
			ciudad.setDepartamento(departamento);
			System.out.println("funciona ciudad");
		}catch(Exception e1){
			fail("Not yet implemented");			
		}
	}
	
	@Test
	public void testEmpresa() throws SQLException{
		try{
			empresa.setIdEmpresa(1);
			empresa.setNombreEmpresa("Herman y asociados");
			empresa.setEstadoEmpresa(true);
			empresa.setCiudad(ciudad);		
			System.out.println("funciona empresa");
		}catch(Exception e1){
			fail("Not yet implemented");
		}
	}
	
	@Test
	public void testGestionador() throws SQLException{
		try{
			gestionador.setCedulaGestionador(1019234543);
			gestionador.setNombreGestionador("José");
			gestionador.setApellidoGestionador("Ramirez");
			gestionador.setContrasenaGestionador("Futbol");
			gestionador.setEmailGestionador("registrarsolicitudes@gmail");
			gestionador.setEstadoGestionado(true);
			gestionador.setEmpresa(empresa);		
			System.out.println("funciona gestionador");
		}catch(Exception e1){
			fail("Not yet implemented");
		}
	}

	@Test
	public void testArea() {
		try{
			area.setIdtable(1);
			area.setNombreArea("Recursos Humanos");
			area.setEstadoArea(true);
			area.setEmpresa(empresa);
			System.out.println("funciona area");
		}catch(Exception e){
			fail("Not yet implemented");
		}
	}
	
	@Test
	public void testEstado() {
		try{
			estado.setIdEstado(1);
			estado.setTipoEstado("informacion encontrada");
			estado.setEstadoEstado(true);
			System.out.println("funciona estado");
		}catch(Exception e){
			fail("Not yet implemented");
		}
	}
	
	@Test
	public void testTipoinfo() throws SQLException{
		try{
			tipoinfo.setIdTipoInformacion(1);
			tipoinfo.setNombreTipoInformacion("Cnotratos");
			tipoinfo.setEstadoTipoInformacion(true);
			System.out.println("funciona tipoinformacion");
		}catch(Exception e){
			fail("Not yet implemented");
		}	
	}
	
	@Test
	public void testUsuario() throws SQLException{
		try{
			BigInteger bi = new BigInteger("3104563389", 10);
			usuario.setCedulaUsuario(1045321785);
			usuario.setNombreUsuario("Lorena");
			usuario.setApellidoUsuario("Vera");
			usuario.setCelularUsuario(bi);
			usuario.setEmailUsuario("lorena34@gmail.com");
			usuario.setDireccionUsuario("Calla 60 B sur n0 74-40");
			usuario.setContrasenaUsuario("123");
			usuario.setEstadoUsuario(true);
			System.out.println("funciona usuario");
		}catch(Exception e){
			fail("Not yet implemented");
		}	
	}
	
	@Test
	public void testPeticion() throws SQLException{
		try{
			SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");		
						
			peticion.setIdPeticion(1);
			peticion.setFechaPeticion(formatoDelTexto.parse("2015-05-12"));
			peticion.setObservacionesPeticion("Contratos del 2011");
			peticion.setEstadoPeticion(true);
			peticion.setEstado(estado);
			peticion.setEmpresa(empresa);
			peticion.setUsuario(usuario);
			peticion.setTipoinformacion(tipoinfo);
			System.out.println("funciona peticion");
		}catch(Exception e){
			fail("Not yet implemented");
		}
	}

	/*@Test
	public void testEstado() {
		fail("Not yet implemented");
	}

	@Test
	public void testGestionador() {
		fail("Not yet implemented");
	}

	

	@Test
	public void testUsuario() {
		fail("Not yet implemented");
	}

	@Test
	public void testPeticion() {
		fail("Not yet implemented");
	}

	@Test
	public void testEmpresa() {
		fail("Not yet implemented");
	}

	@Test
	public void testCiudad() {
		fail("Not yet implemented");
	}*/

	/*@Test
	public void testMain() {
		fail("Not yet implemented");
	}*/

	/*@Test
	public void testObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetClass() {
		fail("Not yet implemented");
	}

	@Test
	public void testHashCode() {
		fail("Not yet implemented");
	}

	@Test
	public void testEquals() {
		fail("Not yet implemented");
	}

	@Test
	public void testClone() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testNotify() {
		fail("Not yet implemented");
	}

	@Test
	public void testNotifyAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testWaitLong() {
		fail("Not yet implemented");
	}

	@Test
	public void testWaitLongInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testWait() {
		fail("Not yet implemented");
	}

	@Test
	public void testFinalize() {
		fail("Not yet implemented");
	}*/

}
