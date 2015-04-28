package service;

import java.util.List;

import dao.EnfermedadDao;
import entity.Enfermedad;

public class EnfermedadService implements ServiceInterface<Enfermedad>{

	private static EnfermedadDao enfermedadDao;
	public EnfermedadService() {
		enfermedadDao = new EnfermedadDao();
	}
	@Override
	public void persist(Enfermedad entity) {
		enfermedadDao.openCurrentSessionwithTransaction();
		enfermedadDao.persist(entity);
		enfermedadDao.closeCurrentSessionwithTransaction();
	}
	@Override
	public void update(Enfermedad entity) {
		enfermedadDao.openCurrentSessionwithTransaction();
		enfermedadDao.update(entity);
		enfermedadDao.closeCurrentSessionwithTransaction();
	}
	@Override
	public Enfermedad findById(int id) {
		enfermedadDao.openCurrentSession();
		Enfermedad enfermedad = enfermedadDao.findById(id);
		enfermedadDao.closeCurrentSession();
		return enfermedad;
	}
	@Override
	public List<Enfermedad> findAll() {
		enfermedadDao.openCurrentSession();
		List<Enfermedad> enfermedades = enfermedadDao.findAll();
		enfermedadDao.closeCurrentSession();
		return enfermedades;
	}

	public EnfermedadDao enfermedadDao() {
		return enfermedadDao;
	}

}
