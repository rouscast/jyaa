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

import model.Activity;

import org.apache.log4j.Logger;

import persistence.dao.interfaces.ActivityDao;
import application.manager.DaoManager;
import exceptions.ErrorEnOperacionException;
import exceptions.ValorRequeridoException;

/**
 * Define el bean que maneja las actividades
 * 
 */
@ManagedBean(name = "activityBean")
@ApplicationScoped
public class ActivityBean extends AbstractBaseBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ActivityBean.class);

	private String name;

	private Activity activity;

	public List<Activity> list;

	private HtmlDataTable activityTable;

	private static ActivityDao activityDao = DaoManager.getActivityDao();

	/**
	 * Crea una nueva actividad
	 *
	 * @return String
	 */
	public String create() {
		Activity newActivity = new Activity(this.getName());
		try {
			this.getActivityDao().insertElemento(newActivity);
			return "listactivity?faces-redirect=true";
		} catch (ErrorEnOperacionException e) {
			logger.error(e.getMessage(), e);
			this.error("No se pudo guardar la actividad, error con la Base de datos");
			return "";
		}

	}

	/**
	 * Modifica una actividad
	 * 
	 * @return String
	 */
	public String update() {
		try {
			this.getActivityDao().update(this.getActivity());
			return "listactivity?faces-redirect=true";
		} catch (ErrorEnOperacionException e) {
			logger.error(e.getMessage(), e);
			this.error("No se pudo actualizar la actividad, error con la Base de datos");
			return "";
		}

	}

	/**
	 * Verifica si existe una Actividad con el mismo nombre
	 * 
	 * @param context
	 * @param component
	 * @param value
	 * @throws ValidatorException
	 */
	public void validateActivityName(FacesContext context,
			UIComponent component, Object value) throws ValidatorException {
		String item = (String) value;
		try {
			Object activity = activityDao.getElementoByName(item);
			if (activity != null) {
				Activity actv = (Activity) activity;
				if (actv.getId() != this.getActivity().getId())
					throw new ValidatorException(new FacesMessage(
							"Ya existe una actividad con ese nombre"));
			}
		} catch (ErrorEnOperacionException | ValorRequeridoException e) {
			logger.error(e.getMessage(), e);
			this.error("Error validando la actividad");
		}

	}

	/**
	 * Recupera el objeto activity, a ser editado
	 * 
	 * @return String
	 * 
	 */
	public String searchActivity() {
		/*
		 * HttpServletRequest request = (HttpServletRequest) FacesContext
		 * .getCurrentInstance().getExternalContext().getRequest(); String id =
		 * request.getParameter("id"); try {
		 * this.setactivity(activityDao.getElemento(Integer.parseInt(id)));
		 * return "editactivity?faces-redirect=true"; } catch
		 * (NumberFormatException | ErrorEnOperacionException |
		 * ValorRequeridoException e) { logger.error(e.getMessage(), e);
		 * this.error
		 * ("No se pudo seleccionar la actividad, error con la Base de datos");
		 * return ""; }
		 */
		this.setactivity((Activity) activityTable.getRowData());
		return "editactivity?faces-redirect=true";
	}

	/************************** Seters and Getters**************************** */

	public ActivityDao getActivityDao() {
		return activityDao;
	}

	public static void setActivityDao(ActivityDao activityDao) {
		ActivityBean.activityDao = activityDao;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setactivity(Activity activity) {
		this.activity = activity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Activity> getList() {
		return getActivityDao().list();
	}

	public void setList(List<Activity> list) {
		this.list = list;
	}

	public HtmlDataTable getActivityTable() {
		return activityTable;
	}

	public void setActivityTable(HtmlDataTable activityTable) {
		this.activityTable = activityTable;
	}

}
