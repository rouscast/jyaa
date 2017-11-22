package controller.bean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;

import org.apache.log4j.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import application.manager.DaoManager;
import exceptions.ErrorEnOperacionException;
import model.Image;
import model.Multimedia;
import model.Route;
import model.User;
import persistence.dao.interfaces.RouteDao;


/**
 * @author Rejas T. Wilber 
 *
 */
@ManagedBean(name="routeBean")
@SessionScoped
public class RouteBean extends AbstractBaseBean implements Serializable {
	
	private static final long serialVersionUID = 31L;
	private static Logger logger = Logger.getLogger(RouteBean.class);
	private static RouteDao routeDao = DaoManager.getRouteDao();
	
	 private HtmlDataTable routesTable;
	
	private User user;

	@ManagedProperty("#{userBean}")
	private UserBean userBean;
	
	private Route route=new Route();
	
	private Part file;
	
	Map<Integer, Multimedia> images = new HashMap<Integer, Multimedia>();
	
	private boolean saved=false;
	 
	public RouteBean() {
		super();
		//this.route=this.getRouteDao().getElemento(3);
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Route getRoute() {
		return route;
	}
	
	public void setRoute(Route route) {
		this.route = route;
	}
	
	public UserBean getUserBean() {
		return userBean;
	}
	
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}	
	
	public Part getFile() {
		return file;
	}
	
	public void setFile(Part file) {
		this.file = file;
	}
	
	public HtmlDataTable getRoutesTable() {
		return routesTable;
	}

	public void setRoutesTable(HtmlDataTable routesTable) {
		this.routesTable = routesTable;
	}

	public static RouteDao getRouteDao() {
		return routeDao;
	}
	
	public static void setRouteDao(RouteDao routeDao) {
		RouteBean.routeDao = routeDao;
	}
	
	public void processFile(Route route2){
        try{
            InputStream input=file.getInputStream();
            logger.info(" size "+ file.getSize());
         
            String mimeType =file.getContentType();
           
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int count;
            while ((count = input.read(buffer)) != -1){
                output.write(buffer, 0, count);
            }
            Image image =new Image();
            Blob blob = new SerialBlob(output.toByteArray());
            image.setContent(blob);
            image.setContent_type(mimeType);
            route2.addMultimedia(image);
            
            output.close();
            
            input.close();
        }catch(Exception e){
            logger.error(e.getMessage(), e);
        }
	}
	
	
	public void validateFile(FacesContext ctx, UIComponent comp, Object value) {
		List<FacesMessage> msgs = new ArrayList<FacesMessage>();
		Part file = (Part)value;
		if (file.getSize() > 1024) {
			msgs.add(new FacesMessage("file too big"));
		}
		if (!"text/plain".equals(file.getContentType())) {
			msgs.add(new FacesMessage("not a text file"));
		}
		if (!msgs.isEmpty()) {
			throw new ValidatorException(msgs);
		}
	}
	
	public StreamedContent getImageFromDB() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		} else {

			// Reading image from database assuming that product image (bytes)
			// of product id I1 which is already stored in the database.

			byte[] imageByte = null;
			try {
				 Image image =new Image();
				 String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("item_id");
				  
				//Integer fileId = (Integer)context.getExternalContext().getRequestMap().get("item_id");
//				DaoManager.getInstance().getRouteDao();
				//image= (Image)this.getRoute().getMultimedia().get(0);
				image= (Image)this.getImages().get(Integer.valueOf(id));
				if (image!= null) {
					 imageByte=image.getContent().getBytes(1,(int)  image.getContent().length());				}
				return new DefaultStreamedContent(new ByteArrayInputStream(imageByte),image.getContent_type());
			} catch (Exception e) { 
				logger.error(e.getMessage(), e);
				return new DefaultStreamedContent();
			}


		}
	}
	private StreamedContent convertStreaming(Multimedia image){
		
			byte[] imageByte = null;
			try {
				if (image!= null) {
					 imageByte=image.getContent().getBytes(1,(int)  image.getContent().length());
				}
				return new DefaultStreamedContent(new ByteArrayInputStream(imageByte),image.getContent_type());
			} catch (Exception e) { 
				logger.error(e.getMessage(), e);
				return new DefaultStreamedContent();
			}
		
	}
	@SuppressWarnings("static-access")
	public List<Route> getList() {
		return this.getRouteDao().list();
	}
	public Map<Integer, Multimedia> getImages() {
		return images;
	}

	public void setImages(Map<Integer, Multimedia> images) {
		this.images = images;
	}
	
	public Set<Integer> getImagesIds() {
		return this.getImages().keySet();
	}

	/**
	 * Retorna la ruta para guardar temporalmente un archivo
	 * @return
	 */
	protected  String getPathDeDescarga() {
		return FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources/images/upload");
	}
	
	
	/**
	 * Busca una ruta dado un id
	 * 
	 * @return String
	 * 
	 */
	public String selectRoute() {
		//FacesContext context = FacesContext.getCurrentInstance();
		//String id = (String)context.getExternalContext().getRequestMap().get("id");
		
		this.setRoute((Route) routesTable.getRowData());
		setearImagenes();
		
		return "route_view?faces-redirect=true";
	}

	private void setearImagenes() {
		this.getImages().clear();
		for(Multimedia image:this.getRoute().getMultimedia()){
			this.getImages().put(image.getId(), image);
		}
	}
	
	public List<StreamedContent> getImagenes() {
		FacesContext context = FacesContext.getCurrentInstance();

		
		List<StreamedContent> list=new ArrayList<StreamedContent>();
		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return list;
		} 
		for(Multimedia image:this.getRoute().getMultimedia()){
			list.add(convertStreaming(image));
		}
		return list;
	}
	
	@SuppressWarnings("static-access")
	public String saveRoute(){
		Route route= this.getRoute();
		processFile(route);
		try {
			this.getRouteDao().update(route);
		} catch (ErrorEnOperacionException e) {

			e.printStackTrace();
		}
		System.out.println(route);
		this.setSaved(true);
		this.info("La ruta fue guardada con éxito.");
		return null;
	}
	public boolean isSaved() {
		return saved;
	}
	public void setSaved(boolean saved) {
		this.saved = saved;
	}
	
}
