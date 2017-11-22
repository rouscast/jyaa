package controller.bean;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.apache.log4j.Logger;

import exceptions.ErrorEnOperacionException;
import exceptions.ValorRequeridoException;
import model.Document;
import model.User;
import model.UserProfile;


@ManagedBean(name = "registBean")
@RequestScoped
public class RegistBean  extends AbstractBaseBean {
	@ManagedProperty("#{profileBean}")
	private ProfileBean profileBean;
	@ManagedProperty("#{documentTypeBean}")
	private DocumentTypeBean documentTypeBean;
	@ManagedProperty("#{userBean}")
	private UserBean userBean;
	
	private static Logger logger = Logger.getLogger(RegistBean.class);
	private String userName="";
	private String password="";
	private String repassword="";
	private String names="";
	private String lastName="";
	private Date birthdate;
	private String mail="";
	private String sex="";
	private String domicile="";
	private int nroDocument=0;
	private int documentType;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getDomicile() {
		return domicile;
	}
	public void setDomicile(String domicile) {
		this.domicile = domicile;
	}
	public int getNroDocument() {
		return nroDocument;
	}
	public void setNroDocument(int nroDocument) {
		this.nroDocument = nroDocument;
	}
	public int getDocumentType() {
		return documentType;
	}
	public void setDocumentType(int documentType) {
		this.documentType = documentType;
	}

	public String register(){
		User newUser;
		try {
			newUser = new User(getUserName(),getPassword(),getNames(),getLastName(),getBirthdate(),getMail(),getSex(),
					getDomicile(),new Document(nroDocument, getDocumentTypeBean().getDocumentType(getDocumentType())), getProfileBean().getProfile(new UserProfile()));
			getUserBean().create(newUser);
			return "index?faces-redirect=true";
		} catch (ErrorEnOperacionException | ValorRequeridoException e) {
			logger.error(e.getMessage(),e);
			this.error("No se pudo guardar la actividad, error con la Base de datos");
			return "";
		}
		
	}
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	public ProfileBean getProfileBean() {
		return profileBean;
	}
	public void setProfileBean(ProfileBean profileBean) {
		this.profileBean = profileBean;
	}
	public DocumentTypeBean getDocumentTypeBean() {
		return documentTypeBean;
	}
	public void setDocumentTypeBean(DocumentTypeBean documentTypeBean) {
		this.documentTypeBean = documentTypeBean;
	}
	public UserBean getUserBean() {
		return userBean;
	}
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
}
