package controller.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
/**
 * LA idea es generar los datos por defecto del sistema 
 * @author NATA
 *
 */
@ManagedBean(name="generateDefaultBean")
@RequestScoped
public class GenerateDefaultBean {
	@ManagedProperty("#{documentTypeBean}")
	DocumentTypeBean documentTypeBean;
	@ManagedProperty("#{profileBean}")
	ProfileBean profileBean;
		
	public DocumentTypeBean getDocumentTypeBean() {
		return documentTypeBean;
	}
	public void setDocumentTypeBean(DocumentTypeBean documentTypeBean) {
		this.documentTypeBean = documentTypeBean;
	}
	public ProfileBean getProfileBean() {
		return profileBean;
	}
	public void setProfileBean(ProfileBean profileBean) {
		this.profileBean = profileBean;
	}
	public void generate() {
		//getDocumentTypeBean().generateDefault();
		//getProfileBean().generateDefault();
	}
}
