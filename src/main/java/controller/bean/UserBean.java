package controller.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.apache.log4j.Logger;

import application.manager.DaoManager;
import controller.validators.GenericValidator;
import exceptions.ErrorEnOperacionException;
import exceptions.ValorRequeridoException;
import model.User;
import persistence.dao.interfaces.UserDao;

@ManagedBean(name="userBean")
@ApplicationScoped
public class UserBean extends AbstractBaseBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(UserBean.class);
	
	public List<User> users;

	public List<User> getUsers() {
		return list();
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}

	private static UserDao userDao= DaoManager.getUserDao();
	public UserDao getUserDao() {
		return userDao;
	}
	public void create(User user) throws ErrorEnOperacionException{
		if(getUserDao().userNameAvailble(user.getUserName())){
			getUserDao().insertElemento(user);
		}		
	}
	public List<User> list(){
		return getUserDao().list();
	}

	public void validateUserName(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		String item =(String) value;
		if(GenericValidator.isNotEmpty(item)){
			if (GenericValidator.noWhite(item)) {
				try {
					if(!getUserDao().userNameAvailble(item)){					
						throw new ValidatorException(new FacesMessage("No disponible"));
					}
				} catch (ErrorEnOperacionException e) {
					throw new ValidatorException(new FacesMessage("Error en la validacion, error de Base de datos"));
				}
			}else{
				throw new ValidatorException(new FacesMessage("No puede contener espacios"));
			}
		}else{
			throw new ValidatorException(new FacesMessage("Ingrese un nombre"));
		}

	}

	public void validateMail(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		String item =(String) value;
		if (!GenericValidator.isEmail(item)) {								
			throw new ValidatorException(new FacesMessage("Ingrese un email correcto"));
		}

	}
	public void validateBirthdate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {		
		Date today= new Date();
		if(today.compareTo((Date)value)<0){
			throw new ValidatorException(new FacesMessage("Ingrese un dato real"));
		}
	}
	public User getUser(String userName) throws ErrorEnOperacionException{		
		return getUserDao().getUser(userName);
	}
	public void update(User user) {
		try {
			getUserDao().update(user);
		} catch (ErrorEnOperacionException e) {
			logger.error(e.getMessage(),e);
			this.error("No se pudo actualizar el usuario, error con la Base de datos");

		}

	}
	public User getUser(int id) throws ErrorEnOperacionException, ValorRequeridoException {
		return getUserDao().getElemento(id);
	}
}
