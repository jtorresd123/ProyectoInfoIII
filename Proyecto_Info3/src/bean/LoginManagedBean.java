package bean;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import entity.Usuario;
import service.UsuarioService;
import util.Cifrador;

@ManagedBean(name = "loginMB")
@SessionScoped
public class LoginManagedBean {
	private static final int maxIntentos=3;
	private boolean loggedIn;
	private int idUsuario;
	private String login;
	private String loginEvaluado;
	private String password;
	private char tipoUsuario;
	private String moduloActual;
	private short contadorIntentos=0;

	public void iniciarSesion(){
		try {
			FacesMessage message = null;
			UsuarioService usuarioService=new UsuarioService();
			Usuario usuario=usuarioService.logIn(login);
			if(usuario==null){
				message = new FacesMessage(FacesMessage.SEVERITY_WARN,"Datos incorrectos", "");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}else{
				if(usuario.getPassword().equals(new Cifrador().cifrar(password))){
					if(usuario.getEstado()=='A'){
						contadorIntentos=0;
						loginEvaluado=null;
						loggedIn=true;
						idUsuario=usuario.getId();
						tipoUsuario=usuario.getTipoUsuario();
						if(tipoUsuario=='A'){
	
							FacesContext.getCurrentInstance().getExternalContext().redirect("/Proyecto_Info3/administrador/indexAdmin.xhtml");
						}else{
							FacesContext.getCurrentInstance().getExternalContext().redirect("/Proyecto_Info3/medico/indexMedico.xhtml");
						}
						
					}else{
						message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Su cuenta se encuentra inactiva", "");
						FacesContext.getCurrentInstance().addMessage(null, message);	
					}
				}else{
					if(null != loginEvaluado && loginEvaluado.equals(login)){
						
						contadorIntentos++;
						if(contadorIntentos>=maxIntentos){
							if(usuario.getEstado()=='A'){
								usuario.setEstado('I');
								usuarioService.update(usuario);
								message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"La cuenta "+login+" ha sido desactivada por  exceso de intentos, contactese con el administrador para activarla.", "");
							}else{
								message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"La cuenta "+login+" se encuentra desactivada, contactese con el administrador para desactivarla.", "");
							}

						}else{
							message = new FacesMessage(FacesMessage.SEVERITY_WARN,"Datos incorrectos, intentos fallidos: "+contadorIntentos, "");
						}
						
					}else{
						loginEvaluado=login;
						contadorIntentos=1;
						message = new FacesMessage(FacesMessage.SEVERITY_WARN,"Datos incorrectos", "");
					}
					FacesContext.getCurrentInstance().addMessage(null, message);
				}
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void cerrarSesion(){
		idUsuario=0;
		login=null;
		password=null;
		loggedIn=false;
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/Proyecto_Info3/index.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean isLoggedIn() {
		return loggedIn;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
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
	public char getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(char tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public String getModuloActual() {
		moduloActual=FacesContext.getCurrentInstance().getViewRoot().getViewId();
		if(moduloActual.contains("administrador")){
			moduloActual="administrador";
			
		}else if(moduloActual.contains("medico")){
			moduloActual="medico";
			
		}else{
			moduloActual="inicial";
		}
		return moduloActual;
	}
	
	
	
	
}
