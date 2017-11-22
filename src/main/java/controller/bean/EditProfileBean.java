package controller.bean;



import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.apache.log4j.Logger;

import exceptions.ErrorEnOperacionException;
import exceptions.ValorRequeridoException;
import model.User;

@ManagedBean(name="editProfileBean")
@SessionScoped
public class EditProfileBean extends AbstractBaseBean {
	private static Logger logger = Logger.getLogger(EditProfileBean.class);
	private User user;
	@ManagedProperty("#{loginBean}")
	private LoginBean loginBean;
	@ManagedProperty("#{userBean}")
	private UserBean userBean;
	private String newUserName;
	private String oldPassword;
	private String newPassword;
	private String reNewPassword;
	
	public String getNewUserName() {
		return newUserName;
	}
	public void setNewUserName(String newUserName) {
		this.newUserName = newUserName;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getReNewPassword() {
		return reNewPassword;
	}
	public void setReNewPassword(String reNewPassword) {
		this.reNewPassword = reNewPassword;
	}	
	public LoginBean getLoginBean() {
		return loginBean;
	}
	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}	
	public UserBean getUserBean() {
		return userBean;
	}
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}	
	public String update(){
		User user= getLoginBean().getUser();
		getUserBean().update(user);
		return null;
	}
	public String updatePassword(){
		User user= getLoginBean().getUser();
		user.setPassword(newPassword);
		getUserBean().update(user);
		return null;
	}
	public void validateOldPassword(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		String item =(String) value;
		if (!getLoginBean().getUser().isYourPassword(item)) {								
				throw new ValidatorException(new FacesMessage("Ingrese la contrase�a actual"));
		}

	}
	
	public String editAdmin(int id){
		try {
			setUser(getUserBean().getUser(id));
			return "editprofile?faces-redirect=true";
		} catch (ErrorEnOperacionException | ValorRequeridoException e) {
			logger.error(e.getMessage(), e);
			this.error("No se pudo seleccionar el usuario, error con la Base de datos");
			return "";
		}
		
	}
	public String updateAdmin(){
		if(!getNewUserName().isEmpty()){
			getUser().setUserName(getNewUserName());
			setNewUserName("");
		}
		getUserBean().update(getUser());
		return null;
	}
	
	public String changePassword(){
		getUser().setPassword(getNewPassword());
		getUserBean().update(getUser());
		return null;
	}
}
