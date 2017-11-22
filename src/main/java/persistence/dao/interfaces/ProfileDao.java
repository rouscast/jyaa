package persistence.dao.interfaces;

import exceptions.ErrorEnOperacionException;
import model.Profile;

public interface ProfileDao extends GenericDao<Profile> {
	
	@Override
	public Profile getElemento(Integer id) throws ErrorEnOperacionException;

	public Profile getProfile(Profile profile) throws ErrorEnOperacionException;

	public boolean profileExist(Profile profile) throws ErrorEnOperacionException;
}
