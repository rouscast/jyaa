package model;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Multimedia {
	 
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;
	 
	@Column(nullable = true)
	private Blob content; 
	
	private String content_type; 
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Blob getContent() {
		return content;
	}
	public void setContent(Blob content) {
		this.content = content;
	}
	public String getContent_type() {
		return content_type;
	}
	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}
	public Multimedia() {
	}
}
