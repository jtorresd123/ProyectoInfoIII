package service;

import java.util.List;


public interface ServiceInterface<T> {
	
	public void persist(T entity);
	
	public void update(T entity);
	
	public T findById(int id);

	public List<T> findAll();

}
