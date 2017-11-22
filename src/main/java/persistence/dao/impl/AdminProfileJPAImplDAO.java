package persistence.dao.impl;
import model.AdminProfile;
import persistence.dao.interfaces.AdminProfileDao;



public class AdminProfileJPAImplDAO extends GenericJPAImplDAO<AdminProfile> implements AdminProfileDao
{

	public AdminProfileJPAImplDAO() {
		super(AdminProfile.class);
		// TODO Auto-generated constructor stub
	}


}
