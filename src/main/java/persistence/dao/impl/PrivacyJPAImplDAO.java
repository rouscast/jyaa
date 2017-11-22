package persistence.dao.impl;
import model.Privacy;
import persistence.dao.interfaces.PrivacyDao;



public class PrivacyJPAImplDAO extends GenericJPAImplDAO<Privacy> implements PrivacyDao
{

	public PrivacyJPAImplDAO() {
		super(Privacy.class);
		// TODO Auto-generated constructor stub
	}


}
