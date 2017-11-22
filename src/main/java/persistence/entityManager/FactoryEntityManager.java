package persistence.entityManager;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;

import org.hibernate.jpa.HibernatePersistenceProvider;

public class FactoryEntityManager {
	static HibernatePersistenceProvider provider = new HibernatePersistenceProvider();
	static Map<String, Object> configuration = new HashMap<>();
	private static EntityManagerFactory emf =  provider.createEntityManagerFactory("jyaa2016",configuration);

	public static EntityManagerFactory getEmf() {
		if (emf == null) {
			emf =  provider.createEntityManagerFactory("jyaa2016",configuration);
		}
		return emf;
	}

	public static void setEmf(EntityManagerFactory emf) {
		FactoryEntityManager.emf = emf;
	}

}
