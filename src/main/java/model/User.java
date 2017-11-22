package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.codec.digest.DigestUtils;


@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	private String userName;
	private String password;
	private String names;
	private String lastName;
	private Date birthdate;
	private String mail;
	private String sex;
	private String domicile;
	
	@OneToOne(optional = true, cascade = CascadeType.ALL)
	private Document document;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Route> myRoutes;
	
	@ManyToOne(optional = false)
	private Profile profile;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
		this.password = toMd5(password);
	}
	private String toMd5(String password) {
		return DigestUtils.md5Hex(password);
	}
	public boolean isYourPassword(String password) {
		return this.getPassword().equals(this.toMd5(password));		
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
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
	public User() {
	}
	public User(String userName, String password, String names, String lastName, Date birthdate, String mail,
			String sex, String domicile, Document document,Profile profile) {
		setBirthdate(birthdate);
		setDocument(document);
		setDomicile(domicile);
		setLastName(lastName);
		setMail(mail);
		setNames(names);
		setPassword(password);
		setSex(sex);
		setUserName(userName);	
		setProfile(profile);
		setMyRoutes(new ArrayList<Route>());
	}
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", names=" + names
				+ ", profile=" + profile + "]";
	}
	
	public boolean isUser() {	
		return getProfile().isUser();
	}
	
	public boolean isAdmin() {	
		return getProfile().isAdmin();
	}
	public List<Route> getMyRoutes() {
		return myRoutes;
	}
	private void setMyRoutes(List<Route> myRoutes) {
		this.myRoutes = myRoutes;
	}
	public void addRoute(Route route){
		if(!getMyRoutes().contains(route)){
			getMyRoutes().add(route);
		}
	}
}