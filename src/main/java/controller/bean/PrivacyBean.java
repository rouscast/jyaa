package controller.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import model.Privacy;

import org.apache.log4j.Logger;

import persistence.dao.interfaces.PrivacyDao;
import application.manager.DaoManager;
import exceptions.ErrorEnOperacionException;
import exceptions.ValorRequeridoException;

/**
 * Define el bean que maneja las privacidades
 * 
 */
@ManagedBean(name = "privacyBean")
@ApplicationScoped
public class PrivacyBean extends AbstractBaseBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(PrivacyBean.class);

	private String name;

	private Privacy privacy;

	public List<Privacy> list;

	private HtmlDataTable privacyTable;

	private static PrivacyDao privacyDao = DaoManager.getPrivacyDao();

	/**
	 * Crea una nueva privacidad
	 *
	 * @return String
	 */
	public String create() {
		Privacy newPrivacy = new Privacy(this.getName());
		try {
			this.getPrivacyDao().insertElemento(newPrivacy);
			return "listprivacy?faces-redirect=true";
		} catch (ErrorEnOperacionException e) {
			logger.error(e.getMessage(), e);
			this.error("No se pudo guardar la actividad, error con la Base de datos");
			return "";
		}

	}

	/**
	 * Modifica una privacidad
	 * 
	 * @return String
	 */
	public String update() {
		try {
			this.getPrivacyDao().update(this.getPrivacy());
			return "listprivacy?faces-redirect=true";
		} catch (ErrorEnOperacionException e) {
			logger.error(e.getMessage(), e);
			this.error("No se pudo actualizar la privacidad, error con la Base de datos");
			return "";
		}

	}

	/**
	 * Verifica si existe una privacidad con el mismo nombre
	 * 
	 * @param context
	 * @param component
	 * @param value
	 * @throws ValidatorException
	 */
	public void validatePrivacyName(FacesContext context,
			UIComponent component, Object value) {
		String item = (String) value;
		try {
			Object privacy = privacyDao.getElementoByName(item);
			if (privacy != null) {
				Privacy priv = (Privacy) privacy;
				if (priv.getId() != this.getPrivacy().getId())
					throw new ValidatorException(new FacesMessage(
							"Ya existe una privacidad con ese nombre"));
			}
		} catch (ErrorEnOperacionException | ValorRequeridoException e) {
			this.error("Error validando el formato");
		}

	}

	/**
	 * Busca una privacidad dado un id
	 * 
	 * @return String
	 * 
	 */
	public String searchPrivacy() {
		/*
		 * HttpServletRequest request = (HttpServletRequest) FacesContext
		 * .getCurrentInstance().getExternalContext().getRequest(); String id =
		 * request.getParameter("id"); try {
		 * this.setPrivacy(privacyDao.getElemento(Integer.parseInt(id))); return
		 * "editprivacy?faces-redirect=true"; } catch (NumberFormatException |
		 * ErrorEnOperacionException | ValorRequeridoException e) {
		 * logger.error(e.getMessage(), e); this.error(
		 * "No se pudo seleccionar la privacidad, error con la Base de datos");
		 * return ""; }
		 */
		this.setPrivacy((Privacy) privacyTable.getRowData());
		return "editprivacy?faces-redirect=true";

	}

	/************************** Seters and Getters**************************** */

	public PrivacyDao getPrivacyDao() {
		return privacyDao;
	}

	public static void setPrivacyDao(PrivacyDao privacyDao) {
		PrivacyBean.privacyDao = privacyDao;
	}

	public Privacy getPrivacy() {
		return privacy;
	}

	public void setPrivacy(Privacy privacy) {
		this.privacy = privacy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Privacy> getList() {
		return this.getPrivacyDao().list();
	}

	public void setList(List<Privacy> list) {
		this.list = list;
	}

	public HtmlDataTable getPrivacyTable() {
		return privacyTable;
	}

	public void setPrivacyTable(HtmlDataTable privacyTable) {
		this.privacyTable = privacyTable;
	}

}
