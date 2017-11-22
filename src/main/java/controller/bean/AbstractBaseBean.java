package controller.bean;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class AbstractBaseBean {
	
	
	/**
	 * Este metodo busca borrar un bean de memoria
	 *
	 */
	@SuppressWarnings("rawtypes")
	public void deleteSessionBean(Object key){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Map sessionMap = facesContext.getExternalContext().getSessionMap();
		sessionMap.remove(key);
	}
	
	/**
	 * Este metodo guarda en la session un bean de memoria
	 *
	 */
	public void saveSessionBean(String beanName,Object beanObject){
		//agrego o piso el bean de session pasado como parametro
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.getExternalContext().getSessionMap().put(beanName,beanObject);
	}
	
	public void errorForId(String id, String mensaje){
		FacesContext.getCurrentInstance().addMessage(id, new FacesMessage(FacesMessage.SEVERITY_ERROR,mensaje,mensaje));	
	}
	public void infoForId(String id, String mensaje){
		FacesContext.getCurrentInstance().addMessage(id, new FacesMessage(FacesMessage.SEVERITY_INFO,mensaje,mensaje));	
	}
	
	public void error(String mensaje){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,mensaje,mensaje));	
	}
	
	public void info(String mensaje){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,mensaje,mensaje));	
	}

	public void warn(String mensaje){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,mensaje,mensaje));	
	}

}
