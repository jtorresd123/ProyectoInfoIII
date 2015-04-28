package bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import entity.Tratamiento;
import service.TratamientoService;
@ManagedBean(name = "tratamientoMB")
@ViewScoped
public class TratamientoManagedBean {
	private static final char estadoDef = 'A';
	private int id;
	private String descripcion;
	private char estado;
	private boolean actualizacion;
	
	private TratamientoService tratamientoService;
	private List<Tratamiento> tratamientoList;
	private Tratamiento tratamientoSeleccionado;	
	
	public void agregarTratamiento(){
		FacesMessage message = null;
		try{
		Tratamiento tratamientoNuevo=new Tratamiento(id, descripcion, estadoDef);
		tratamientoService= new TratamientoService();
		tratamientoService.persist(tratamientoNuevo);
		message = new FacesMessage(FacesMessage.SEVERITY_INFO,"El tratamiento fue agregado exitosamente!", "");

		}catch(Exception e){
			e.printStackTrace();
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"No fue posible agregar el tratamiento.", "");

		}
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public void modificarTratamiento(){
		FacesMessage message = null;
		try{
		tratamientoService= new TratamientoService();	
		tratamientoService.update(tratamientoSeleccionado);
		actualizacion=true;
		message = new FacesMessage(FacesMessage.SEVERITY_INFO,"El tratamiento fue actualizada!", "");
		}catch(Exception e){
			e.printStackTrace();
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"No fue posible actualizar el tratamiento.", "");

		}
		FacesContext.getCurrentInstance().addMessage(null, message);
		RequestContext.getCurrentInstance().execute("PF('dialogoModificar').hide();");
	}
	
	public void eliminarTratamiento(){
		FacesMessage message = null;
		try{
		tratamientoService= new TratamientoService();	
		tratamientoSeleccionado.setEstado('I');
		tratamientoService.update(tratamientoSeleccionado);
		actualizacion=true;
		message = new FacesMessage(FacesMessage.SEVERITY_INFO,"El tratamiento fue desactivado exitosamente!", "");
		}catch(Exception e){
			e.printStackTrace();
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"No fue posible desactivar el tratamiento.", "");

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

	public TratamientoService getTratamientoService() {
		return tratamientoService;
	}

	public void setTratamientoService(TratamientoService tratamientoService) {
		this.tratamientoService = tratamientoService;
	}

	public List<Tratamiento> getTratamientoList() {
		if(null==tratamientoList || actualizacion==true){
			tratamientoService = new TratamientoService();
			tratamientoList=new ArrayList<Tratamiento>();
			tratamientoList.addAll(tratamientoService.findAll());
			actualizacion=false;
		}
		return tratamientoList;
	}

	public void setTratamientoList(List<Tratamiento> tratamientoList) {
		this.tratamientoList = tratamientoList;
	}

	public Tratamiento getTratamientoSeleccionado() {
		return tratamientoSeleccionado;
	}

	public void setTratamientoSeleccionado(Tratamiento tratamientoSeleccionado) {
		this.tratamientoSeleccionado = tratamientoSeleccionado;
	}
	
}
