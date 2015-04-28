package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import entity.Enfermedad;

public class EnfermedadDao implements DaoInterface<Enfermedad> {
 	private Session currentSession;
 	
 	private Transaction currentTransaction;
 	public EnfermedadDao() {
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
 	public void persist(Enfermedad entity) {
 		getCurrentSession().save(entity);
 	}
 	@Override
 	public void update(Enfermedad entity) {
 		getCurrentSession().update(entity);
 	}
 	@Override
 	public Enfermedad findById(int id) {
 		Enfermedad enfermedad = (Enfermedad) getCurrentSession().get(Enfermedad.class, id);
 		return enfermedad; 
 	}

 	@Override
 	@SuppressWarnings("unchecked")
 	public List<Enfermedad> findAll() {
 		List<Enfermedad> enfermedades = (List<Enfermedad>) getCurrentSession().createQuery("from Enfermedad order by id ASC").list();
 		return enfermedades;
 	}

 	
 	
}
