package persistence.dao.impl;
import model.Route;
import persistence.dao.interfaces.RouteDao;



public class RouteJPAImplDAO extends GenericJPAImplDAO<Route> implements RouteDao
{

	public RouteJPAImplDAO() {
		super(Route.class);
		// TODO Auto-generated constructor stub
	}


}
