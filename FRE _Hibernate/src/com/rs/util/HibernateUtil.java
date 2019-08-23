package com.rs.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static Session session = null;
	private static SessionFactory factory;

	static {
		factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

	}

	public static Session getSession() {

		session = factory.openSession();
		return session;
	}

	public static void closeSession() {

		session.close();

	}

	public static void closeSessionFactory() {
		factory.close();

	}
}
