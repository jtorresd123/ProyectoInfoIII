package bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import service.UsuarioService;
import util.Cifrador;
import entity.Usuario;

@ManagedBean(name = "usuarioMB")
@ViewScoped
public class UsuarioManagedBean {
	private static final char estadoDef = 'A';
	private static final char tipoUsuarioDef = 'M';
	private static final Integer idProyectoDef = 2;

	private int id;
	private String login;
	private String password;
	private String apellidosNombres;
	private Timestamp fechaCreacion;
	private char estado;
	private String correo;
	private Timestamp fechaClave;
	private char tipoUsuario;
	private Integer idProyecto;
	private boolean actualizacion;

	private UsuarioService usuarioService;
	private Cifrador cifrador;
	private List<Usuario> usuarioList;
	private Usuario usuarioSeleccionado;

	public void agregarUsuario() {
		FacesMessage message = null;
		if (password.matches(".*\\d.*")
				&& !password.equals(password.toLowerCase())
				&& !password.equals(password.toUpperCase())) {
			if(login.length() <=8 && !loginRepetido()){
			fechaCreacion = new Timestamp(
					new GregorianCalendar().getTimeInMillis());
			fechaClave = fechaCreacion;
//			String ipComputador = "";
//			try {
//				ipComputador = InetAddress.getLocalHost().toString().substring(1);
//			} catch (UnknownHostException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			String mensaje = "<html><br />Datos del registro:.<br />"
//					+ "<b>Usuario: </b>"
//					+ login
//					+ "<br/>"
//					+ "<b>Clave: </b>"
//					+ password
//					+ "<br/>"
//					+ "<b>Apellidos Y Nombres: </b>"
//					+ apellidosNombres
//					+ "<br/>"
//					+ "<b>Correo: </b>"
//					+ correo
//					+ "<br/>"
//					+ "<b>Estado: </b>"
//					+ estadoDef
//					+ "<br/>"
//					+ "<b>Tipo de Usuario: </b>"
//					+ tipoUsuario
//					+ "<br/>"
//					+ "<b>Fecha de creacion: </b>"
//					+ fechaCreacion.toString()
//					+ "<br/>"
//					+ "<br/>"
//					+ "<b>Link de ingreso: </b>"
//					+ "http://"
//					+ ipComputador
//					+ ":8080/Proyecto_Info3/index.xhtml</html>";

			// Correo correoObj=new Correo();
			// if(correoObj.sendEmailMessage(correo, mensaje)){

			cifrador = new Cifrador();
			password = cifrador.cifrar(password);
			Usuario usuarioNuevo = new Usuario(id, login, password,apellidosNombres, fechaCreacion, estadoDef, correo,fechaClave, tipoUsuarioDef, idProyectoDef);
			usuarioService = new UsuarioService();
			usuarioService.persist(usuarioNuevo);
			reset();
			message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Registro agregado exitosamente!", "");
			// message = new FacesMessage(FacesMessage.SEVERITY_INFO,
			// "Registro agregado exitosamente, en instantes llegará un mensaje al correo "+correo+" con la información","");

			// }else{
			// message = new FacesMessage(FacesMessage.SEVERITY_WARN,
			// "No se pudo enviar el mensaje de confirmación al correo especificado","");
			// }
			}else{
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"El login ya se encuentra registrado", "");
			}
		} else {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"La clave no está en el formato especificado", "");
		}
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void modificarUsuario() {
		FacesMessage message = null;
		try {
			usuarioService = new UsuarioService();
			usuarioService.update(usuarioSeleccionado);
			actualizacion = true;
			message = new FacesMessage(FacesMessage.SEVERITY_INFO,"El usuario fue actualizado!", "");

		} catch (Exception e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"No fue posible actualizar el usuario", "");
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().addMessage(null, message);
		RequestContext.getCurrentInstance().execute("PF('dialogoModificar').hide();");
	}
	
	public void modificarClaveUsuario() {
		FacesMessage message = null;
		try {
			String passwordUsuario = usuarioSeleccionado.getPassword();
			if (passwordUsuario.matches(".*\\d.*") && !passwordUsuario.equals(passwordUsuario.toLowerCase()) && !passwordUsuario.equals(passwordUsuario.toUpperCase())) {
			cifrador = new Cifrador();
			usuarioSeleccionado.setPassword(cifrador.cifrar(passwordUsuario));
			usuarioSeleccionado.setFechaClave(new Timestamp(
					new GregorianCalendar().getTimeInMillis()));
			usuarioService = new UsuarioService();
			usuarioService.update(usuarioSeleccionado);
			actualizacion = true;
			message = new FacesMessage(FacesMessage.SEVERITY_INFO,"El contraseña fue actualizada!", "");
			RequestContext.getCurrentInstance().execute("PF('dialogoClave').hide();");
			}else{
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"La contraseña no está en el formato especificado", "");
			}
		} catch (Exception e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"No fue posible actualizar el usuario", "");
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().addMessage(null, message);
		
	}
	
	public void eliminarUsuario(){
		FacesMessage message = null;
		try {
			usuarioService=new UsuarioService();
			usuarioSeleccionado.setEstado('I');
			usuarioService.update(usuarioSeleccionado);
			actualizacion = true;
			message = new FacesMessage(FacesMessage.SEVERITY_INFO,"El usuario fue desactivado!", "");
		} catch (Exception e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"No fue posible desactivar el usuario", "");
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().addMessage(null, message);
		RequestContext.getCurrentInstance().execute("PF('dialogoEliminar').hide();");
	}
	
	public void reset(){
		id=0;
		login=null;
		password=null;
		apellidosNombres=null;
		fechaCreacion=null;
		estado=0;
		correo=null;
		fechaClave=null;
		tipoUsuario=0;
		idProyecto=0;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getApellidosNombres() {
		return apellidosNombres;
	}

	public void setApellidosNombres(String apellidosNombres) {
		this.apellidosNombres = apellidosNombres;
	}

	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Timestamp getFechaClave() {
		return fechaClave;
	}

	public void setFechaClave(Timestamp fechaClave) {
		this.fechaClave = fechaClave;
	}

	public char getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(char tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public Integer getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(Integer idProyecto) {
		this.idProyecto = idProyecto;
	}

	public List<Usuario> getUsuarioList() {
		if (null == usuarioList || actualizacion == true) {
			usuarioService = new UsuarioService();
			usuarioList = new ArrayList<Usuario>();
			usuarioList.addAll(usuarioService.findAll());
			actualizacion = false;
		}

		return usuarioList;
	}

	public void setUsuarioList(List<Usuario> usuarioList) {
		this.usuarioList = usuarioList;
	}

	public Usuario getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}

	public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}
	
	public boolean loginRepetido(){
		getUsuarioList();
		boolean loginRepetido=false;
		for(int i=0;i<usuarioList.size();i++){
			if(login.equals(usuarioList.get(i).getLogin())){
				loginRepetido=true;
				break;
			}
			
		}
		
		return loginRepetido;
	}


}
