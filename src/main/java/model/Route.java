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
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "route")
public class Route {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	private String name;
	private String description;
	
	@ManyToOne(optional = true)
	private Privacy privacy;
	
	@ManyToOne(optional = true)
	private Format format;
	
	private Double distance;
	
	@ManyToOne(optional = true)
	private Difficulty difficulty;
	
	@ManyToOne(optional = true)
	private Activity activity;
	
	@Column(name = "estimated_time")
	private Integer estimatedTime;
	
	@Column(name = "date_realization")
	private Date dateRealization;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL )
	@JoinTable(name = "route_multimedia")
	private List<Multimedia> multimedia= new ArrayList<Multimedia>();
	
	@OneToOne(optional = true, cascade = CascadeType.ALL)
	private TraceRoute traceRoute;
	
	public TraceRoute getTraceRoute() {
		return traceRoute;
	}
	public void setTraceRoute(TraceRoute traceRoute) {
		this.traceRoute = traceRoute;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Privacy getPrivacy() {
		return privacy;
	}
	public void setPrivacy(Privacy privacy) {
		this.privacy = privacy;
	}
	public Format getFormat() {
		return format;
	}
	public void setFormat(Format format) {
		this.format = format;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public Difficulty getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	public Integer getEstimatedTime() {
		return estimatedTime;
	}
	public void setEstimatedTime(Integer estimatedTime) {
		this.estimatedTime = estimatedTime;
	}
	public Date getDateRealization() {
		return dateRealization;
	}
	public void setDateRealization(Date dateRealization) {
		this.dateRealization = dateRealization;
	}
	public List<Multimedia> getMultimedia() {
		return multimedia;
	}
	public void setMultimedia(List<Multimedia> multimedia) {
		getMultimedia().clear();
		for (Multimedia elem : multimedia) {
			addMultimedia(elem);
		}
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Route(String name, String description, Privacy privacy, Format format, Double distance,
			Difficulty difficulty, Activity activity, Integer estimatedTime, Date dateRealization, TraceRoute traceRoute){
		setActivity(activity);
		setDateRealization(dateRealization);
		setDescription(description);
		setDifficulty(difficulty);
		setDistance(distance);
		setEstimatedTime(estimatedTime);
		setFormat(format);
		setName(name);
		setPrivacy(privacy);
		setTraceRoute(traceRoute);
	}	
	public Route() {	
	}
	public void addMultimedia(Multimedia multimedia){
		if(!getMultimedia().contains(multimedia)){
			getMultimedia().add(multimedia);
		}
	}
	public void addPoint(Point point){
		getTraceRoute().addPoint(point);
	}
	
	@Override
	public String toString() {
		return "Route [id=" + id + ", name=" + name + ", description="
				+ description + ", privacy=" + privacy + "]";
	}
	
}