package controller.bean;


import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import exceptions.ErrorEnOperacionException;
import model.User;

@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean extends AbstractBaseBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static final Logger logger = Logger.getLogger(LoginBean.class);
	
	@ManagedProperty("#{userBean}")
	private UserBean userBean;
	private String userName;
	private String password;
	private User user;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	
	public UserBean getUserBean() {
		return userBean;
	}
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String login(){
		User user;
		try {
			user = getUserBean().getUser(getUserName());
		} catch (ErrorEnOperacionException e) {
			logger.error(e.getMessage(), e);
			this.error("No se pudo obtener el usuario, error con la Base de datos");
			return "";
		}
		
		if(user!=null){
			if(user.isYourPassword(getPassword())){
				this.setUser(user);
				logger.debug("Usuario logueado: "+user.getUserName());
				return "index?faces-redirect=true";
			}
		}else{
			this.error("Usuario o contraseña incorrectos");
		}
		return "";
	}
	
	public String logout(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "index?faces-redirect=true";
	}
}
