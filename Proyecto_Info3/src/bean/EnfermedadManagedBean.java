package bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import entity.Enfermedad;
import service.EnfermedadService;

@ManagedBean(name = "enfermedadMB")
@ViewScoped
public class EnfermedadManagedBean {
	private static final char estadoDef = 'A';
	private int id;
	private String descripcion;
	private char estado;
	private boolean actualizacion;
	
	private EnfermedadService enfermedadService;
	private List<Enfermedad> enfermedadList;
	private Enfermedad enfermedadSeleccionada;	
	
	public void agregarEnfermedad(){
		FacesMessage message = null;
		try{
		Enfermedad enfermedadNueva=new Enfermedad(id, descripcion, estadoDef);
		enfermedadService= new EnfermedadService();
		enfermedadService.persist(enfermedadNueva);
		reset();
		message = new FacesMessage(FacesMessage.SEVERITY_INFO,"La enfermedad fue agregada exitosamente!", "");

		}catch(Exception e){
			e.printStackTrace();
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"No fue posible agregar la enfermedad.", "");

		}
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public void modificarEnfermedad(){
		FacesMessage message = null;
		try{
		enfermedadService= new EnfermedadService();	
		enfermedadService.update(enfermedadSeleccionada);
		actualizacion=true;
		message = new FacesMessage(FacesMessage.SEVERITY_INFO,"La enfermedad fue actualizada!", "");
		}catch(Exception e){
			e.printStackTrace();
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"No fue posible actualizar la enfermedad.", "");

		}
		FacesContext.getCurrentInstance().addMessage(null, message);
		RequestContext.getCurrentInstance().execute("PF('dialogoModificar').hide();");
	}
	
	public void eliminarEnfermedad(){
		FacesMessage message = null;
		try{
		enfermedadService= new EnfermedadService();	
		enfermedadSeleccionada.setEstado('I');
		enfermedadService.update(enfermedadSeleccionada);
		actualizacion=true;
		message = new FacesMessage(FacesMessage.SEVERITY_INFO,"La enfermedad fue desactivada exitosamente!", "");
		}catch(Exception e){
			e.printStackTrace();
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"No fue posible desactivar la enfermedad.", "");

		}
		FacesContext.getCurrentInstance().addMessage(null, message);
		RequestContext.getCurrentInstance().execute("PF('dialogoEliminar').hide();");		
	}
	
	public void reset(){
		id=0;
		descripcion=null;
		estado=0;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public char getEstado() {
		return estado;
	}
	public void setEstado(char estado) {
		this.estado = estado;
	}

	public EnfermedadService getEnfermedadService() {
		return enfermedadService;
	}

	public void setEnfermedadService(EnfermedadService enfermedadService) {
		this.enfermedadService = enfermedadService;
	}

	public List<Enfermedad> getEnfermedadList() {
		if(null==enfermedadList || actualizacion==true){
			enfermedadService = new EnfermedadService();
			enfermedadList=new ArrayList<Enfermedad>();
			enfermedadList.addAll(enfermedadService.findAll());
			actualizacion=false;
		}
		return enfermedadList;
	}

	public void setEnfermedadList(List<Enfermedad> enfermedadList) {
		this.enfermedadList = enfermedadList;
	}

	public Enfermedad getEnfermedadSeleccionada() {
		return enfermedadSeleccionada;
	}

	public void setEnfermedadSeleccionada(Enfermedad enfermedadSeleccionada) {
		this.enfermedadSeleccionada = enfermedadSeleccionada;
	}
	
	
	

}
