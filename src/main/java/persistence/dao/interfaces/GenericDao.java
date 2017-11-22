package persistence.dao.interfaces;

import java.util.List;

import exceptions.*;

public interface GenericDao<T> {
	public void insertElemento(T elemento) throws ErrorEnOperacionException ;

	public List<T> list(String columOrder) throws ErrorEnOperacionException ;

	public void update(T entity)throws ErrorEnOperacionException ;

	public void eliminar(Integer id) throws ErrorEnOperacionException,ValorRequeridoException ;

	public T getElemento(Integer id) throws ErrorEnOperacionException,ValorRequeridoException ;

	public List<T> list();

	public Integer count() throws ErrorEnOperacionException;

	public Object getElementoByName(String nombre) throws ErrorEnOperacionException,ValorRequeridoException ;
}
