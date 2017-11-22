package persistence.dao.impl;
import persistence.dao.interfaces.VisitDao;
import model.Visit;



public class VisitJPAImplDAO extends GenericJPAImplDAO<Visit> implements VisitDao
{

	public VisitJPAImplDAO() {
		super(Visit.class);
		// TODO Auto-generated constructor stub
	}


}
