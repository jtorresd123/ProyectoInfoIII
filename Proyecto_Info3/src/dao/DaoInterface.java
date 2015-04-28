package dao;

import java.util.List;

import entity.Usuario;

public interface DaoInterface<T> {
	
	public void persist(T entity);
	
	public void update(T entity);
	
	public T findById(int id);

	public List<T> findAll();

}
