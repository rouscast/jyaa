package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "traceRoute")
public class TraceRoute {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	

	
	 @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Point> trace= new  ArrayList<Point>();
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<Point> getTrace() {
		return trace;
	}
	public void addPoint(Point point){
		if(!getTrace().contains(point)){
			getTrace().add(point);
		}
	}
	public TraceRoute(List<Point> trace) {
		setTrace(trace);
	}
	public void setTrace(List<Point> trace) {
		getTrace().clear();
		for (Point point : trace) {
			addPoint(point);
		}
	}
	public TraceRoute() {
	}
	
	
}
