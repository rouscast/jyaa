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

import model.Difficulty;

import org.apache.log4j.Logger;

import persistence.dao.interfaces.DifficultyDao;
import application.manager.DaoManager;
import exceptions.ErrorEnOperacionException;
import exceptions.ValorRequeridoException;

@ManagedBean(name = "difficultyBean")
@ApplicationScoped
public class DifficultyBean extends AbstractBaseBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(DifficultyBean.class);
	private String name;

	private Difficulty difficulty;

	public List<Difficulty> list;
	private HtmlDataTable difficultyTable;

	private static DifficultyDao difficultyDao = DaoManager.getDifficultyDao();

	// crea una nueva dificultad
	public String create() {
		Difficulty newDifficulty = new Difficulty(this.getName());
		try {
			getDifficultyDao().insertElemento(newDifficulty);
			return "listdifficulty?faces-redirect=true";
		} catch (ErrorEnOperacionException e) {
			logger.error(e.getMessage(), e);
			this.error("No se pudo guardando la actividad, error con la Base de datos");
			return "";
		}

	}

	/**
	 * Modifica una dificultad
	 * 
	 * @return String
	 */
	public String update() {
		try {
			getDifficultyDao().update(this.getDifficulty());
			return "listdifficulty?faces-redirect=true";
		} catch (ErrorEnOperacionException e) {
			logger.error(e.getMessage(), e);
			this.error("No se pudo actualizar la dificultad, error con la Base de datos");
			return "";
		}

	}

	/**
	 * Verifica si existe una dificultad con el mismo nombre
	 * 
	 * @param context
	 * @param component
	 * @param value
	 * @throws ValidatorException
	 */
	public void validateDifficultyName(FacesContext context,
			UIComponent component, Object value) {
		String item = (String) value;
		try {
			Object difficulty = difficultyDao.getElementoByName(item);
			if (difficulty != null) {
				Difficulty diff = (Difficulty) difficulty;
				if (diff.getId() != this.getDifficulty().getId())
					throw new ValidatorException(new FacesMessage(
							"Ya existe una dificultad con ese nombre"));
			}

		} catch (ErrorEnOperacionException | ValorRequeridoException e) {
			logger.error(e.getMessage(), e);
			this.error("Error validando la actividad");
		}

	}

	/**
	 * Recupera el objeto dificultad, a ser editado
	 * 
	 * @return
	 */
	public String searchDifficulty() {
		/*
		 * HttpServletRequest request = (HttpServletRequest) FacesContext
		 * .getCurrentInstance().getExternalContext().getRequest(); String id =
		 * request.getParameter("id"); try {
		 * this.setDifficulty(difficultyDao.getElemento(Integer.parseInt(id)));
		 * return "editdifficulty?faces-redirect=true"; } catch
		 * (NumberFormatException | ErrorEnOperacionException |
		 * ValorRequeridoException e) { logger.error(e.getMessage(), e);
		 * this.error
		 * ("No se pudo seleccionaar la actividad, error con la Base de datos");
		 * return ""; }
		 */

		this.setDifficulty((Difficulty) difficultyTable.getRowData());
		return "editdifficulty?faces-redirect=true";

	}

	/************************** Seters and Getters**************************** */

	public static DifficultyDao getDifficultyDao() {
		return difficultyDao;
	}

	public static void setDifficultyDao(DifficultyDao difficultyDao) {
		DifficultyBean.difficultyDao = difficultyDao;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Difficulty> getList() {
		return getDifficultyDao().list();
	}

	public void setList(List<Difficulty> list) {
		this.list = list;
	}

	public HtmlDataTable getDifficultyTable() {
		return difficultyTable;
	}

	public void setDifficultyTable(HtmlDataTable difficultyTable) {
		this.difficultyTable = difficultyTable;
	}

}
