package persistence.dao.impl;
import model.Format;
import persistence.dao.interfaces.FormatDao;



public class FormatJPAImplDAO extends GenericJPAImplDAO<Format> implements FormatDao
{

	public FormatJPAImplDAO() {
		super(Format.class);
		// TODO Auto-generated constructor stub
	}


}
