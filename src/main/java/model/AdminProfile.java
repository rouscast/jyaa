package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue(value = "admin")
public class AdminProfile extends Profile {
	public static String ADMIN="ADMIN";
	@Override
	public String toString() {
		return ADMIN;
	}
	public AdminProfile() {
	}
	
	public boolean isAdmin() {	
		return true;
	}
}