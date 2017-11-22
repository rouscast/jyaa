package persistence.dao.interfaces;

import exceptions.ErrorEnOperacionException;
import model.DocumentType;

public interface DocumentTypeDao  extends GenericDao<DocumentType>{
	
	public DocumentType getDocumentTypeByDescription(String type) throws ErrorEnOperacionException;
	public boolean documentTypeExist(String type) throws ErrorEnOperacionException;

}
