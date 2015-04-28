package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import entity.Enfermedad;
import entity.Tratamiento;

public class TratamientoDao implements DaoInterface<Tratamiento> {
	
 	private Session currentSession;
 	private Transaction currentTransaction;
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
	public void persist(Tratamiento entity) {
		// TODO Auto-generated method stub
		getCurrentSession().save(entity);
	}

	@Override
	public void update(Tratamiento entity) {
		// TODO Auto-generated method stub
		getCurrentSession().update(entity);
	}

	@Override
	public Tratamiento findById(int id) {
		// TODO Auto-generated method stub
 		Tratamiento tratamiento = (Tratamiento) getCurrentSession().get(Tratamiento.class, id);
 		return tratamiento; 
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tratamiento> findAll() {
		// TODO Auto-generated method stub
 		List<Tratamiento> tratamientos = (List<Tratamiento>) getCurrentSession().createQuery("from Tratamiento order by id ASC").list();
 		return tratamientos;
	 	
	}

}
