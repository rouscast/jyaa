package controller.bean;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import application.manager.DaoManager;
import exceptions.ErrorEnOperacionException;
import model.AdminProfile;
import model.Profile;
import model.UserProfile;
import persistence.dao.interfaces.ProfileDao;

@ManagedBean(name="profileBean")
@ApplicationScoped
public class ProfileBean extends AbstractBaseBean  {
	
	public List<Profile> list;
	
	public List<Profile> getList() {
		return list();
	}
	private static ProfileDao profileDao = DaoManager.getProfileDao();
	public ProfileDao getProfileDao() {
		return profileDao;
	}
	public List<Profile> list(){
		return getProfileDao().list();
	}
	
	public void generateDefault() throws ErrorEnOperacionException{
		if(!getProfileDao().profileExist(new UserProfile())){
			getProfileDao().insertElemento(new UserProfile());
		}
		if(!getProfileDao().profileExist(new AdminProfile())){
			getProfileDao().insertElemento(new AdminProfile());
		}
	}
	
	public Profile getProfile(int id) throws ErrorEnOperacionException{
		return getProfileDao().getElemento(id);
	}
	public Profile getProfile(Profile profile) throws ErrorEnOperacionException {
		return getProfileDao().getProfile(profile);
	}
}
