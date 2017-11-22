package persistence.dao.impl;
import model.Activity;
import persistence.dao.interfaces.ActivityDao;



public class ActivityJPAImplDAO extends GenericJPAImplDAO<Activity> implements ActivityDao
{

	public ActivityJPAImplDAO( ) {
		super(Activity.class);
	
	}
}
