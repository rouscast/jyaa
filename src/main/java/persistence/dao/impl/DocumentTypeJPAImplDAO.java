package persistence.dao.impl;
import java.util.ArrayList;

import exceptions.ErrorEnOperacionException;
import model.DocumentType;
import persistence.dao.interfaces.DocumentTypeDao;



public class DocumentTypeJPAImplDAO extends GenericJPAImplDAO<DocumentType> implements DocumentTypeDao
{

	public DocumentTypeJPAImplDAO() {
		super(DocumentType.class);
	}
	
	
	public DocumentType getDocumentTypeByDescription(String type) throws ErrorEnOperacionException {
		ArrayList<DocumentType> list = (ArrayList<DocumentType>) this.listByWhere("name = '" + type + "'");

		if (list.size() == 0) {
			return null;
		}

		return list.get(0);
	}

	@Override
	public boolean documentTypeExist(String type) throws ErrorEnOperacionException {

		return getDocumentTypeByDescription(type)!=null;
	}
}
