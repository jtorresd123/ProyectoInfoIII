package service;

import java.util.List;

import dao.TratamientoDao;
import entity.Tratamiento;

public class TratamientoService implements ServiceInterface<Tratamiento>{

	private static TratamientoDao tratamientoDao;
	public TratamientoService() {
		tratamientoDao = new TratamientoDao();
	}
	@Override
	public void persist(Tratamiento entity) {
		tratamientoDao.openCurrentSessionwithTransaction();
		tratamientoDao.persist(entity);
		tratamientoDao.closeCurrentSessionwithTransaction();
	}
	@Override
	public void update(Tratamiento entity) {
		tratamientoDao.openCurrentSessionwithTransaction();
		tratamientoDao.update(entity);
		tratamientoDao.closeCurrentSessionwithTransaction();
	}
	@Override
	public Tratamiento findById(int id) {
		tratamientoDao.openCurrentSession();
		Tratamiento tratamiento = tratamientoDao.findById(id);
		tratamientoDao.closeCurrentSession();
		return tratamiento;
	}
	@Override
	public List<Tratamiento> findAll() {
		tratamientoDao.openCurrentSession();
		List<Tratamiento> tratamientos = tratamientoDao.findAll();
		tratamientoDao.closeCurrentSession();
		return tratamientos;
	}

	public TratamientoDao tratamientoDao() {
		return tratamientoDao;
	}
}
