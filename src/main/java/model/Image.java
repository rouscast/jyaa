package model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="multimedia_image")
public class Image extends Multimedia {
	
	private String render;
	public String getRender() {
		return render;
	}
	public void setRender(String render) {
		this.render = render;
	}
	public Image() {
		super();
	}
	public Image(String render) {
		super();
		setRender(render);
	}
	
}
