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

import model.Format;

import org.apache.log4j.Logger;

import persistence.dao.interfaces.FormatDao;
import application.manager.DaoManager;
import exceptions.ErrorEnOperacionException;
import exceptions.ValorRequeridoException;

/**
 * Define el bean que maneja los formatos
 * 
 */
@ManagedBean(name = "formatBean")
@ApplicationScoped
public class FormatBean extends AbstractBaseBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(FormatBean.class);

	private String name;

	private Format format;

	public List<Format> list;

	private HtmlDataTable formatTable;

	private static FormatDao formatDao = DaoManager.getFormatDao();

	/**
	 * Crea un nuevo formato
	 *
	 * @return String
	 */
	public String create() {
		Format newFormat = new Format(this.getName());
		try {
			this.getFormatDao().insertElemento(newFormat);
			return "listformat?faces-redirect=true";
		} catch (ErrorEnOperacionException e) {
			logger.error(e.getMessage(), e);
			this.error("No se pudo guardar el formato, error con la Base de datos");
			return "";
		}

	}

	/**
	 * Modifica un formato
	 * 
	 * @return String
	 */
	public String update() {
		try {
			this.getFormatDao().update(this.getFormat());
			return "listformat?faces-redirect=true";
		} catch (ErrorEnOperacionException e) {
			logger.error(e.getMessage(), e);
			this.error("No se pudo actualizar el formato, error con la Base de datos");
			return "";
		}

	}

	/**
	 * Verifica si existe un formato con el mismo nombre
	 * 
	 * @param context
	 * @param component
	 * @param value
	 * @throws ValidatorException
	 */
	public void validateFormatName(FacesContext context, UIComponent component,
			Object value) {
		String item = (String) value;
		try {
			Object format = formatDao.getElementoByName(item);
			if (format != null) {
				Format form = (Format) format;
				if (form.getId() != this.getFormat().getId())
					throw new ValidatorException(new FacesMessage(
							"Ya existe un formato con ese nombre"));
			}

		} catch (ErrorEnOperacionException | ValorRequeridoException e) {
			logger.error(e.getMessage(), e);
			this.error("Error validando el formato");
		}

	}

	/**
	 * Recupera el objeto Format, a ser editado
	 * 
	 * @return String
	 * 
	 */
	public String searchFormat() {
		/*
		 * HttpServletRequest request = (HttpServletRequest) FacesContext
		 * .getCurrentInstance().getExternalContext().getRequest(); String id =
		 * request.getParameter("id"); try {
		 * this.setFormat(formatDao.getElemento(Integer.parseInt(id))); } catch
		 * (NumberFormatException | ErrorEnOperacionException |
		 * ValorRequeridoException e) { logger.error(e.getMessage(), e);
		 * this.error
		 * ("No se pudo seleccionaar la actividad, error con la Base de datos");
		 * return ""; } return "editformat?faces-redirect=true";
		 */
		this.setFormat((Format) formatTable.getRowData());
		return "editformat?faces-redirect=true";
	}

	/************************** Seters and Getters**************************** */

	public FormatDao getFormatDao() {
		return formatDao;
	}

	public static void setFormatDao(FormatDao formatDao) {
		FormatBean.formatDao = formatDao;
	}

	public Format getFormat() {
		return format;
	}

	public void setFormat(Format format) {
		this.format = format;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Format> getList() {
		return this.getFormatDao().list();
	}

	public void setList(List<Format> list) {
		this.list = list;
	}

	public HtmlDataTable getFormatTable() {
		return formatTable;
	}

	public void setFormatTable(HtmlDataTable formatTable) {
		this.formatTable = formatTable;
	}

}
