package pruebas;

import service.EnfermedadService;
import service.TratamientoService;
import service.UsuarioService;

public class conexionBD {
	
	
	
	public static void main(String[]args){
		UsuarioService usuario=new UsuarioService();
		System.out.println("USUARIOS:"+usuario.findAll().size());
		EnfermedadService enfermedad= new EnfermedadService();
		System.out.println("ENFERMEDAD:"+enfermedad.findAll().size());
		TratamientoService tratemiento=new TratamientoService();
		System.out.println("TRATAMIENTO:"+tratemiento.findAll().size());
	
	}

}
