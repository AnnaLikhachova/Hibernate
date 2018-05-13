package com.hibernate;

import org.hibernate.SessionFactory;
import java.io.File;
import org.hibernate.Session;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class FilmManager {
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		// code to load Hibernate Session factory

		String hibernatePropsFilePath = "src/main/resources/hibernate.cfg.xml";
		File hibernatePropsFile = new File(hibernatePropsFilePath);
		Configuration configuration = new Configuration();
		configuration.configure(hibernatePropsFile);
		StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		return sessionFactory;

	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	protected void exit() {
		// code to close Hibernate Session factory
		getSessionFactory().close();
	}

	protected void create() {
		// code to save a film
		Film film = new Film();
		film.setTitle("Effective Java");
		film.setDescription("Joshua Bloch");
		film.setCast(32000000);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(film);
		session.getTransaction().commit();
		session.close();
	}

	protected void read() {
		// code to get a film
		Session session = sessionFactory.openSession();
		long filmId = 1;
		Film film = (Film) session.get(Film.class, filmId);
		System.out.println("Title: " + film.getTitle());
		System.out.println("Description: " + film.getDescription());
		System.out.println("Cast: " + film.getCast());

		session.close();
	}

	protected void update() {
		// code to modify a film
		Film film = new Film();
		film.setId(20);
		film.setTitle("Ultimate Programming");
		film.setDescription("Nam Ha Minh");
		film.setCast(16000000);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(film);
		session.getTransaction().commit();
		session.close();
	}

	protected void delete() {
		// code to remove a film
		Film film = new Film();
		film.setId(2);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(film);
		session.getTransaction().commit();
		session.close();
	}

	public static void main(String[] args) {
		FilmManager manager = new FilmManager();
		manager.buildSessionFactory();
		manager.create();
		manager.exit();

	}

}
