package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "documentType")
public class DocumentType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable=false, unique=true)
	private String name;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public DocumentType() {
	}
	public DocumentType(String name) {
		setName(name);
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj.hashCode()==hashCode();
	}
	
	@Override
	public int hashCode() {		
		return 	getName().hashCode();
	}
}