package test.persistence;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import model.Activity;
import model.AdminProfile;
import model.Difficulty;
import model.Document;
import model.DocumentType;
import model.Format;
import model.Point;
import model.Privacy;
import model.Route;
import model.TraceRoute;
import model.User;
import model.UserProfile;
import model.Visit;
import model.utils.Gender;

import org.apache.log4j.Logger;

import persistence.dao.impl.ActivityJPAImplDAO;
import persistence.dao.impl.AdminProfileJPAImplDAO;
import persistence.dao.impl.DifficultyJPAImplDAO;
import persistence.dao.impl.DocumentTypeJPAImplDAO;
import persistence.dao.impl.FormatJPAImplDAO;
import persistence.dao.impl.PrivacyJPAImplDAO;
import persistence.dao.impl.UserJPAImplDAO;
import persistence.dao.impl.ProfileJPAImplDAO;
import persistence.dao.interfaces.ActivityDao;
import persistence.dao.interfaces.AdminProfileDao;
import persistence.dao.interfaces.DifficultyDao;
import persistence.dao.interfaces.DocumentTypeDao;
import persistence.dao.interfaces.FormatDao;
import persistence.dao.interfaces.PrivacyDao;
import persistence.dao.interfaces.RouteDao;
import persistence.dao.interfaces.UserDao;
import persistence.dao.interfaces.ProfileDao;
import application.manager.DaoManager;
import exceptions.ErrorEnOperacionException;
import exceptions.ValorRequeridoException;

public class TestEntityManager {

	private static final Logger logger = Logger.getLogger(TestEntityManager.class);

	public TestEntityManager() {

	}

	public static void main(String[] args) {
		logger.info("Start Main  ");
		try {
			crear_datos();
			listar();
			logger.info("END Main  ");
		} catch (ErrorEnOperacionException | ValorRequeridoException e) {
			e.printStackTrace();
		}

	}

	public static void crear_datos() throws ErrorEnOperacionException, ValorRequeridoException {
		createDifficulties();
		createPrivacy();
		creatFormat();
		createDocumentsType();
		createProfiles();
		createUser();
		createUser2();
		createRoute();
		createRoute2();
		createVisits();
		createActivity();
	}

	public static void listar() {
		listUsers();
		listFormats();
		listActividades();
		listDifficulties();
		listPrivacities();
		listRoutes();

	}

	private static void modificarRoutes() throws ErrorEnOperacionException, ValorRequeridoException {
		DaoManager.getRouteDao().eliminar(1);
		DaoManager.getRouteDao().eliminar(2);
		DaoManager.getActivityDao().eliminar(1);
	}

	private static void listUsers() {
		List<User> users = DaoManager.getUserDao().list();
		logger.info("Users count " + users.size());
	}

	private static void listActividades() {
		List<Activity> activities = DaoManager.getActivityDao().list();
		logger.info("activities count " + activities);
	}

	private static void listFormats() {
		List<Format> formats = DaoManager.getFormatDao().list();
		logger.info("Formatrs count " + formats.size());
	}

	private static void listDifficulties() {
		List<Difficulty> difficulties = DaoManager.getDifficultyDao().list();
		logger.info("difficulties count " + difficulties.size());
	}

	private static void listPrivacities() {
		List<Privacy> privacities = DaoManager.getPrivacyDao().list();
		logger.info("privacities count " + privacities.size());
	}

	private static void listRoutes() {
		List<Route> routes = DaoManager.getRouteDao().list();
		logger.info("Routes count " + routes.size() + " routes " + routes);
	}

	private static void createVisits() throws ErrorEnOperacionException {
		List<User> users = DaoManager.getUserDao().list();
		List<Route> routes = DaoManager.getRouteDao().list();
		logger.info("Users " + users.size());
		logger.info(" Routes " + routes.size());
		if (users.size() > 1 && routes.size() > 1) {
			User user1 = users.get(0);
			Route route1 = routes.get(0);
			User user2 = users.get(1);
			Route route2 = routes.get(1);
			Visit visit = new Visit(user1, route1);
			Visit visit2 = new Visit(user2, route2);
			logger.info("Visit1: " + visit);
			logger.info("visit2: " + visit2);
			DaoManager.getVisitDao().insertElemento(visit);
			DaoManager.getVisitDao().insertElemento(visit2);
			logger.info("lista de visitas: " + DaoManager.getVisitDao().list().size());
		} else {
			logger.error("Faltan fatos para guardar las visitas");
		}

	}

	@SuppressWarnings("static-access")
	public static void createRoute() throws ErrorEnOperacionException, ValorRequeridoException {

		Route route = new Route();

		route.setTraceRoute(new TraceRoute());
		route.addPoint(new Point(4, 6));
		route.addPoint(new Point(8, 12));

		route.setDateRealization(new Date());
		route.setDescription("Ruta expoAuto 2016");
		route.setEstimatedTime(123);
		route.setName("Firs route");
		route.setDistance(2.5);
		route.setFormat((Format) DaoManager.getFormatDao().getElemento(1));
		route.setDifficulty((Difficulty) DaoManager.getDifficultyDao().getElemento(1));
		route.setActivity((Activity) DaoManager.getActivityDao().getElemento(1));
		route.setPrivacy((Privacy) DaoManager.getPrivacyDao().getElemento(1));
		RouteDao daoRoute = DaoManager.getInstance().getRouteDao();
		daoRoute.insertElemento(route);
		logger.info("--- Route creado --");

	}

	@SuppressWarnings("static-access")
	public static void createRoute2() throws ErrorEnOperacionException, ValorRequeridoException {

		Route route = new Route();

		route.setTraceRoute(new TraceRoute());
		route.addPoint(new Point(4, 6));
		route.addPoint(new Point(3, 12));

		route.setDateRealization(new Date());
		route.setDescription("Ruta expoAuto 2016");
		route.setEstimatedTime(223);
		route.setName("Second route");
		route.setDistance(2.5);
		route.setFormat((Format) DaoManager.getFormatDao().getElemento(1));
		route.setDifficulty((Difficulty) DaoManager.getDifficultyDao().getElemento(1));
		route.setActivity((Activity) DaoManager.getActivityDao().getElemento(4));
		route.setPrivacy((Privacy) DaoManager.getPrivacyDao().getElemento(2));
		RouteDao daoRoute = DaoManager.getInstance().getRouteDao();
		daoRoute.insertElemento(route);
		logger.info("--- Route creado 2 --");

	}

	public static void createActivity() throws ErrorEnOperacionException {

		ActivityDao dao = DaoManager.getActivityDao();
		List<String> data = Arrays.asList("Mountain bike", "Ciclismo", "Cicloturismo", "Senderismo",
				"Carrera por montaña", "Alpinismo", "Motociclismo", "Cuatriciclo", "Esquí", "Kayac", "Vela",
				"A caballo");
		Activity entity;
		for (int i = 0; i < data.size(); i++) {
			entity = new Activity();
			entity.setName(data.get(i));
			dao.insertElemento(entity);
		}

		logger.info("datos guardados: " + dao.count());
	}

	public static void createProfiles() throws ErrorEnOperacionException {

		ProfileDao daoUser = new ProfileJPAImplDAO();
		UserProfile entity = new UserProfile();
		daoUser.insertElemento(entity);
		logger.info("User guardado: " + daoUser.count());

		AdminProfileDao daoAdmin = new AdminProfileJPAImplDAO();
		AdminProfile entityAdmin = new AdminProfile();
		daoAdmin.insertElemento(entityAdmin);
		logger.info("Admin guardado: " + daoAdmin.count());
	}

	public static void creatFormat() throws ErrorEnOperacionException {

		FormatDao dao = new FormatJPAImplDAO();
		List<String> data = Arrays.asList("Sólo ida", "Circular");
		Format entity;
		for (int i = 0; i < data.size(); i++) {
			entity = new Format();
			entity.setName(data.get(i));
			dao.insertElemento(entity);
		}

		logger.info("datos guardados: " + dao.count() + " Formats");
	}

	public static void createDocumentsType() throws ErrorEnOperacionException {
		DocumentTypeDao dao = new DocumentTypeJPAImplDAO();
		List<String> data = Arrays.asList("DNI", "Pasaporte", "CI");
		DocumentType entity;
		for (int i = 0; i < data.size(); i++) {
			entity = new DocumentType();
			entity.setName(data.get(i));
			dao.insertElemento(entity);
		}

		logger.info("datos guardados: " + dao.count() + " documentType");

	}

	public static void createPrivacy() throws ErrorEnOperacionException {
		PrivacyDao privacyDao = DaoManager.getPrivacyDao();
		if (privacyDao.count() == 0) {
			List<String> data = Arrays.asList("Privado", "Público");
			Privacy privacy;
			for (int i = 0; i < data.size(); i++) {
				privacy = new Privacy();
				privacy.setName(data.get(i));
				privacyDao.insertElemento(privacy);
			}
			logger.info("datos guardados: " + privacyDao.count() + " privacy");
		}
	}

	public static void createDifficulties() throws ErrorEnOperacionException {
		DifficultyDao difficultyDao = DaoManager.getDifficultyDao();
		List<String> difficulties = Arrays.asList("Fácil", "Moderado", "Dificil", "Muy Dificil", "Sólo Expertos");
		Difficulty difficulty;
		for (int i = 0; i < difficulties.size(); i++) {
			difficulty = new Difficulty();
			difficulty.setName(difficulties.get(i));
			difficultyDao.insertElemento(difficulty);
		}

		logger.info("Dificultades para guardar: " + difficulties.size());
		logger.info("Dificultades guardados: " + difficultyDao.count());

	}

	public static void createUser() throws ErrorEnOperacionException {

		UserDao userDao = DaoManager.getUserDao();
		ProfileDao userProfileDao = DaoManager.getProfileDao();
		DocumentTypeDao documentTypeDao = DaoManager.getDocumentTypeDao();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		User user = new User();
		user.setNames("Marcus");
		try {
			user.setBirthdate((Date) sdf.parse("21/12/1990"));
		} catch (ParseException e) {
			logger.error("Error parseo de fecha.");
		}
		user.setDomicile("12 nro 123 La Plata");
		user.setLastName("Darles");
		user.setPassword("myPassword");
		user.setSex(Gender.MALE.getDescripcion());
		user.setMail("toemail@web.ar");
		user.setUserName("marcus");
		user.setProfile(userProfileDao.getElemento(1));
		Random ran = new Random();
		int x = ran.nextInt(6) + 15;
		user.setUserName("marcus" + x);
		Document doc = new Document(34542100 + x, documentTypeDao.getDocumentTypeByDescription("DNI"));
		user.setDocument(doc);

		userDao.insertElemento(user);

		logger.info("usuario registrado ");

	}

	@SuppressWarnings("static-access")
	public static void createUser2() throws ErrorEnOperacionException {

		UserDao userDao = DaoManager.getUserDao();
		ProfileDao userProfileDao = DaoManager.getProfileDao();
		DocumentTypeDao documentTypeDao = DaoManager.getDocumentTypeDao();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		User user = new User();
		user.setNames("Paolo2");
		try {
			user.setBirthdate((Date) sdf.parse("11/02/1920"));
		} catch (ParseException e) {
			logger.error("Error parseo de fecha.");
		}
		user.setDomicile("15 nro 123 La Plata");
		user.setLastName("Paolo2s");
		user.setPassword("myPassword2");
		user.setSex(Gender.MALE.getDescripcion());
		user.setMail("paoemail@web.ar");
		user.setUserName("paolus2");
		user.setProfile(userProfileDao.getElemento(1));
		Document doc = new Document(12266623, documentTypeDao.getDocumentTypeByDescription("DNI"));
		user.setDocument(doc);

		DaoManager.getInstance().getUserDao().insertElemento(user);

		logger.info("usuario registrado " + userDao.list().size());

	}

}
