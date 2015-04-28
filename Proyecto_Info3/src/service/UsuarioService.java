package service;

import java.util.List;

import dao.UsuarioDao;
import entity.Usuario;

public class UsuarioService implements ServiceInterface<Usuario> {
	private static UsuarioDao usuarioDao;

	public UsuarioService() {
		usuarioDao = new UsuarioDao();
	}
	@Override
	public void persist(Usuario entity) {
		usuarioDao.openCurrentSessionwithTransaction();
		usuarioDao.persist(entity);
		usuarioDao.closeCurrentSessionwithTransaction();
	}
	@Override
	public void update(Usuario entity) {
		usuarioDao.openCurrentSessionwithTransaction();
		usuarioDao.update(entity);
		usuarioDao.closeCurrentSessionwithTransaction();
	}
	@Override
	public Usuario findById(int id) {
		usuarioDao.openCurrentSession();
		Usuario usuario = usuarioDao.findById(id);
		usuarioDao.closeCurrentSession();
		return usuario;
	}


	@Override
	public List<Usuario> findAll() {
		usuarioDao.openCurrentSession();
		List<Usuario> usuarios = usuarioDao.findAll();
		usuarioDao.closeCurrentSession();
		return usuarios;
	}
	
	public Usuario logIn(String login){
		usuarioDao.openCurrentSession();
		Usuario usuario = usuarioDao.logIn(login);
		usuarioDao.closeCurrentSession();
		return usuario;
	}

	public UsuarioDao usuarioDao() {
		return usuarioDao;
	}


}
