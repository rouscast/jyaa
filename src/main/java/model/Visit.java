package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "visit")
public class Visit extends Mark {
	public static String VISIT="VISIT";
	@Override
	public String toString() {	
		return VISIT+" by user: "+this.getUser()+" route:"+this.getRoute();
	}
	public Visit() {
	}
	public Visit(User user, Route route) {
		setRoute(route);
		setUser(user);
	}
}
