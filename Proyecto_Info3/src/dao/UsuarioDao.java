package dao;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import entity.Usuario;

public class UsuarioDao implements DaoInterface<Usuario> {
	 	private Session currentSession;
	 	
	 	private Transaction currentTransaction;

	 	public UsuarioDao() {
	 	}

	 	public Session openCurrentSession() {
	 		currentSession = getSessionFactory().openSession();
	 		return currentSession;
	 	}

	 	public Session openCurrentSessionwithTransaction() {
	 		currentSession = getSessionFactory().openSession();
	 		currentTransaction = currentSession.beginTransaction();
	 		return currentSession;
	 	}
	 	
	 	public void closeCurrentSession() {
	 		currentSession.close();
	 	}
	 	
	 	public void closeCurrentSessionwithTransaction() {
	 		currentTransaction.commit();
	 		currentSession.close();
	 	}
	 	
	 	private static SessionFactory getSessionFactory() {
	 		Configuration configuration = new Configuration().configure();
	 		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
	 				.applySettings(configuration.getProperties());
	 		SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
	 		return sessionFactory;
	 	}

	 	public Session getCurrentSession() {
	 		return currentSession;
	 	}

	 	public void setCurrentSession(Session currentSession) {
	 		this.currentSession = currentSession;
	 	}

	 	public Transaction getCurrentTransaction() {
	 		return currentTransaction;
	 	}

	 	public void setCurrentTransaction(Transaction currentTransaction) {
	 		this.currentTransaction = currentTransaction;
	 	}
	 	@Override
	 	public void persist(Usuario entity) {
	 		getCurrentSession().save(entity);
	 	}
	 	@Override
	 	public void update(Usuario entity) {
	 		getCurrentSession().update(entity);
	 	}
	 	@Override
	 	public Usuario findById(int id) {
	 		Usuario usuario = (Usuario) getCurrentSession().get(Usuario.class, id);
	 		return usuario; 
	 	}
	 	@Override
	 	@SuppressWarnings("unchecked")
	 	public List<Usuario> findAll() {
	 		List<Usuario> usuarios = (List<Usuario>) getCurrentSession().createQuery("from Usuario order by id ASC").list();
	 		return usuarios;
	 	}
	 	
	 	
	 	@SuppressWarnings("unchecked")
		public Usuario logIn(String login) {
	 		List<Usuario> usuarios = (List<Usuario>) getCurrentSession().createQuery("from Usuario where login=?").setParameter(0, login).list();
	 		
	 		if(null==usuarios || usuarios.size()==0){
	 			return null;
	 		}else{
	 			return usuarios.get(0);
	 		}
	 		
	 	}

}
