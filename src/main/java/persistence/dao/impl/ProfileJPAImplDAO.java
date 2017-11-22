package persistence.dao.impl;
import exceptions.ErrorEnOperacionException;
import model.Profile;
import persistence.dao.interfaces.ProfileDao;



public class ProfileJPAImplDAO extends GenericJPAImplDAO<Profile> implements ProfileDao
{

	public ProfileJPAImplDAO() {
		super(Profile.class);
	}
	
	@Override
	public Profile getElemento(Integer id) throws ErrorEnOperacionException{
		
		Object obj=super.getElemento(id);
		
		return (obj!=null)? (Profile)obj:null;
	}
	
	@Override
	public Profile getProfile(Profile profile) throws ErrorEnOperacionException{
		for (Profile thisProfile : list()) {
			if (thisProfile.equals(profile))
				return thisProfile;
		}
		return null;
	}
	
	@Override
	public boolean profileExist(Profile profile) throws ErrorEnOperacionException{
		return getProfile(profile)!=null;
	}

}
