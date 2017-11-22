package persistence.dao.interfaces;

import exceptions.ErrorEnOperacionException;
import model.User;

public interface UserDao extends GenericDao<User> {

	public boolean userNameAvailble(String name) throws ErrorEnOperacionException;

	public User getUser(String userName) throws ErrorEnOperacionException;

}
