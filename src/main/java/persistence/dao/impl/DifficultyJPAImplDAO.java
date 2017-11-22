package persistence.dao.impl;
import model.Difficulty;
import persistence.dao.interfaces.DifficultyDao;



public class DifficultyJPAImplDAO extends GenericJPAImplDAO<Difficulty> implements DifficultyDao
{

	public DifficultyJPAImplDAO(
			 ) {
		super(Difficulty.class);
		// TODO Auto-generated constructor stub
	}

}
