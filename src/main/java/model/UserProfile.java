package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue(value = "user")
public class UserProfile extends Profile {
	public static String USER="USER";
	@Override
	public String toString() {
		return USER;
	}
	public UserProfile() {
	}	
}
