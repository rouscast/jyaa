package persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import persistence.dao.interfaces.GenericDao;
import persistence.entityManager.FactoryEntityManager;
import exceptions.ErrorEnOperacionException;

public class GenericJPAImplDAO<T> implements GenericDao<T> {
	private Class<T> persistentClass;
	private static Logger logger = Logger.getLogger(GenericJPAImplDAO.class);

	/**
	 * Crea un entitymanager
	 */
	protected EntityManager getEntityManager() {
		return FactoryEntityManager.getEmf().createEntityManager();
	}

	public GenericJPAImplDAO(Class<T> c) {
		persistentClass = c;
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	public void setPersistentClass(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	/**
	 * metodo para el alta de un elemento
	 */
	@Override
	public void insertElemento(T elemento) throws ErrorEnOperacionException {
		EntityManager em = this.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(elemento);
			em.getTransaction().commit();
		} catch (Exception e) {
			throw new ErrorEnOperacionException("Error en la Base de Datos", e);
		} finally {
			em.close();
		}
	}

	/**
	 * Metodo para la modificacion de un elemento
	 * 
	 * @throws ErrorEnOperacionException
	 * 
	 */
	@Override
	public void update(T entity) throws ErrorEnOperacionException {
		EntityManager em = this.getEntityManager();
		try {
			em.getTransaction().begin();
			@SuppressWarnings("unused")
			T entity1 = em.merge(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			throw new ErrorEnOperacionException("Error en la Base de Datos", e);
		} finally {
			em.close();
		}
	}

	/**
	 * Ejecuta la consulta para listar
	 */
	private List<T> basicList(String query) throws ErrorEnOperacionException {
		EntityManager em = this.getEntityManager();
		try {
			em.getTransaction().begin();
			Query consulta = em.createQuery(query);
			@SuppressWarnings("unchecked")
			List<T> resultado = (List<T>) consulta.getResultList();
			return resultado;
		} catch (Exception e) {
			throw new ErrorEnOperacionException("Error en la Base de Datos", e);
		} finally {
			em.close();
		}
	}

	/**
	 * Metodo para listar todos los elementos, ordenadas por un atributo
	 * 
	 * @throws ErrorEnOperacionException
	 */
	@Override
	public List<T> list(String columOrder) throws ErrorEnOperacionException {
		return this.basicList("select e from "
				+ getPersistentClass().getSimpleName() + " e order by e."
				+ columOrder);
	}

	/**
	 * Metodo para listar todos los elementos
	 *
	 */
	@Override
	public List<T> list() {
		try {
			return this.basicList("select e from "
					+ getPersistentClass().getSimpleName() + " e");
		} catch (ErrorEnOperacionException e) {
			logger.error("Error en la Base de Datos", e);
			return new ArrayList<T>();
		}
	}

	/**
	 * Metodo para listar elementos aplicando un filtro discriminador
	 * 
	 * @throws ErrorEnOperacionException
	 *
	 */
	public List<T> listWithDiscriminator(String dis)
			throws ErrorEnOperacionException {
		return this.basicList("select e from "
				+ getPersistentClass().getSimpleName() + " e"
				+ " where e.persontype=" + dis);
	}

	/**
	 * Metodo para listar todos los elementos, con un where por parametro
	 * 
	 * @throws ErrorEnOperacionException
	 *
	 */
	public List<T> listByWhere(String where) throws ErrorEnOperacionException {
		return this.basicList("select e from "
				+ getPersistentClass().getSimpleName() + " e where " + where);
	}

	/**
	 * Metodo para eliminar un elemento
	 *
	 */
	@Override
	public void eliminar(Integer id) {
		EntityManager em = this.getEntityManager();
		em.getTransaction().begin();
		T searchedItem = (T) em.find(this.getPersistentClass(), id);
		if (searchedItem != null) {
			em.remove(searchedItem);
			em.getTransaction().commit();
		}

		em.close();
	}

	/**
	 * Retorna un elemento buscado
	 * 
	 * @throws ErrorEnOperacionException
	 * 
	 */
	@Override
	public T getElemento(Integer id) throws ErrorEnOperacionException {
		EntityManager em = this.getEntityManager();
		try {
			em.getTransaction().begin();
			T elementoBuscado = (T) em.find(this.getPersistentClass(), id);
			return elementoBuscado;
		} catch (Exception e) {
			throw new ErrorEnOperacionException("Error en la Base de Datos", e);
		} finally {
			em.close();
		}

	}

	/**
	 * Busca un elemento por nombre y lo retorna
	 * 
	 * @throws ErrorEnOperacionException
	 */
	public Object getElementoByName(String nombre)
			throws ErrorEnOperacionException {
		Object resultado = null;
		EntityManager em = this.getEntityManager();
		try {
			em.getTransaction().begin();
			Query consulta = em.createQuery("select e from "
					+ this.getPersistentClass().getSimpleName()
					+ " e where e.name=:nombre ");
			consulta.setParameter("nombre", nombre);
			resultado = consulta.getSingleResult();
			return resultado;
		} catch (NoResultException nre) {
			return null;
			// System.out.println();

		} catch (Exception e) {
			throw new ErrorEnOperacionException("Error en la Base de Datos", e);
		} finally {
			em.close();
		}
	}

	/**
	 * Retorna el total de elementos
	 * 
	 * @throws ErrorEnOperacionException
	 * 
	 */
	public Integer count() throws ErrorEnOperacionException {
		return this.list().size();
	}

}
