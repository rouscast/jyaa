package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "point")
public class Point {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	private long x;
	private long y;
	
	public Point() {
	}
	
	public Point(long x, long y) {
		setX(x);
		setY(y);
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public long getX() {
		return x;
	}
	public void setX(long x) {
		this.x = x;
	}
	public long getY() {
		return y;
	}
	public void setY(long y) {
		this.y = y;
	}
	
	
	@Override
	public boolean equals(Object obj) {	
		return (((Point)obj).getX()==getX())&&
				(((Point)obj).getY()==getY());
	}
}
