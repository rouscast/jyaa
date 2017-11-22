package persistence.dao.impl;
import java.util.ArrayList;

import exceptions.ErrorEnOperacionException;
import model.User;
import persistence.dao.interfaces.UserDao;



public class UserJPAImplDAO extends GenericJPAImplDAO<User> implements UserDao
{

	public UserJPAImplDAO() {
		super(User.class);
		
	}

	@Override
	public boolean userNameAvailble(String name) throws ErrorEnOperacionException {
		ArrayList<User> list = (ArrayList<User>) this.listByWhere("userName = '" + name + "'");
		if (list.size() == 0) {
			return true;
		}
		return false;
	}
	
	@Override
	public User getUser(String userName) throws ErrorEnOperacionException {
		ArrayList<User> list = (ArrayList<User>) this.listByWhere("userName = '" + userName + "'");
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

}
