package application.manager;

import persistence.dao.impl.ActivityJPAImplDAO;
import persistence.dao.impl.DifficultyJPAImplDAO;
import persistence.dao.impl.DocumentTypeJPAImplDAO;
import persistence.dao.impl.FormatJPAImplDAO;
import persistence.dao.impl.PrivacyJPAImplDAO;
import persistence.dao.impl.RouteJPAImplDAO;
import persistence.dao.impl.UserJPAImplDAO;
import persistence.dao.impl.ProfileJPAImplDAO;
import persistence.dao.impl.VisitJPAImplDAO;
import persistence.dao.interfaces.ActivityDao;
import persistence.dao.interfaces.DifficultyDao;
import persistence.dao.interfaces.DocumentTypeDao;
import persistence.dao.interfaces.FormatDao;
import persistence.dao.interfaces.PrivacyDao;
import persistence.dao.interfaces.RouteDao;
import persistence.dao.interfaces.UserDao;
import persistence.dao.interfaces.ProfileDao;
import persistence.dao.interfaces.VisitDao;


public class DaoManager {
	
	private static DaoManager instance_manager;
	private static UserJPAImplDAO instance_userDao;
	private static ProfileJPAImplDAO instance_userProfileDao;
	private static ActivityJPAImplDAO instance_activityDao;
	private static PrivacyJPAImplDAO instance_privacyDao;
	private static DocumentTypeJPAImplDAO instance_documentTypeDao;
	private static DifficultyJPAImplDAO instance_difficultyDao;
	private static RouteJPAImplDAO instance_routeDao;
	private static FormatJPAImplDAO instance_formatDao;
	private static VisitJPAImplDAO instance_visitDao;
	
	/**
	 * <p>Retorna la única instancia Manager.</p>
	 * @return la instancia.
	 */
	public static DaoManager getInstance() {
		if (instance_manager == null) {
			instance_manager = new DaoManager();
		}
		return instance_manager;
	}
	
	public static ActivityDao getActivityDao(){
		if (instance_activityDao==null)
			instance_activityDao=new ActivityJPAImplDAO();		
		return instance_activityDao;
	}
	public static UserDao getUserDao(){
		if (instance_userDao==null)
			instance_userDao=new UserJPAImplDAO();		
		return instance_userDao;
	}
	public static PrivacyDao getPrivacyDao(){
		if (instance_privacyDao==null)
			instance_privacyDao=new PrivacyJPAImplDAO();		
		return instance_privacyDao;
	}
	
	public static DifficultyDao getDifficultyDao(){
		if (instance_difficultyDao==null)
			instance_difficultyDao=new DifficultyJPAImplDAO();		
		return instance_difficultyDao;
	}
	public static RouteDao getRouteDao(){
		if (instance_routeDao==null)
			instance_routeDao=new RouteJPAImplDAO();		
		return instance_routeDao;
	}
	public static FormatDao getFormatDao(){
		if (instance_formatDao==null)
			instance_formatDao=new FormatJPAImplDAO();		
		return instance_formatDao;
	}
	
	public static VisitDao getVisitDao(){
		if (instance_visitDao==null)
			instance_visitDao=new VisitJPAImplDAO();		
		return instance_visitDao;
	}
	public DaoManager() {
		// TODO Auto-generated constructor stub
	}

	public static ProfileDao getProfileDao() {
		if (instance_userProfileDao==null)
			instance_userProfileDao=new ProfileJPAImplDAO();		
		return instance_userProfileDao;
	}
	public static DocumentTypeDao getDocumentTypeDao() {
		if (instance_documentTypeDao==null)
			instance_documentTypeDao=new DocumentTypeJPAImplDAO();		
		return instance_documentTypeDao;
	}

}
