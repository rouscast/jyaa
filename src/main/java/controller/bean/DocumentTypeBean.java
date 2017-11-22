package controller.bean;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import application.manager.DaoManager;
import exceptions.ErrorEnOperacionException;
import exceptions.ValorRequeridoException;
import model.DocumentType;
import persistence.dao.interfaces.DocumentTypeDao;

@ManagedBean(name="documentTypeBean")
@ApplicationScoped
public class DocumentTypeBean {

	private static DocumentTypeDao documentTypeDao= DaoManager.getDocumentTypeDao();
	
	public List<DocumentType> list;
	
	public DocumentTypeDao getDocumentTypeDao() {
		return documentTypeDao;
	}

	public List<DocumentType> getList(){
		return list();
	}
	
	public List<DocumentType> list(){
		return getDocumentTypeDao().list();
	}
	
	public void generateDefault() throws ErrorEnOperacionException{
		if(!getDocumentTypeDao().documentTypeExist("DNI")){
			getDocumentTypeDao().insertElemento(new DocumentType("DNI"));
		}
		if(!getDocumentTypeDao().documentTypeExist("PASAPORTE")){
			getDocumentTypeDao().insertElemento(new DocumentType("PASAPORTE"));
		}	
	}
	
	public DocumentType getDocumentType(String type) throws ErrorEnOperacionException{
		return getDocumentTypeDao().getDocumentTypeByDescription(type);
	}
	public DocumentType getDocumentType(int	 id) throws ErrorEnOperacionException, ValorRequeridoException{
		return getDocumentTypeDao().getElemento(id);
	}
}
