package com.bhl.crm.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.bhl.crm.entities.User;



@Repository
public class UserDaoImpl implements UserDao {

	// need to inject the session factory
	@Autowired
	@Qualifier("sessionFactoryForUser")
	private SessionFactory sessionFactory;

	@Override
	public User findByUserName(String userName) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// now retrieve/read from database using username
		Query<User> theQuery = currentSession.createQuery("from User u where u.userName=:name", User.class);
		theQuery.setParameter("name", userName);
		User theUser = null;
		try {
			theUser = theQuery.getSingleResult();
		} catch (Exception e) {
			/*-- if the user does not exist in DB --*/
			theUser = null;
		}

		return theUser;
	}

	@Override
	public void save(User theUser) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create the user ... finally LOL
			//currentSession.saveOrUpdate(theUser);
			currentSession.save(theUser);
	}
	
	@Override
	public void update(User theUser) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create the user ... finally LOL
			currentSession.update(theUser);
			
	}

	@Override
	public Long maxIdinTable() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<User> query = currentSession.createQuery("from User u",User.class);
		List<User> users = query.getResultList();
		Long maxId = (long) users.size();
		return maxId;
	}

	@Override
	public void delete(Long id) {
		Session session=sessionFactory.getCurrentSession();
		session.delete(session.get(User.class, id));
	}

}
