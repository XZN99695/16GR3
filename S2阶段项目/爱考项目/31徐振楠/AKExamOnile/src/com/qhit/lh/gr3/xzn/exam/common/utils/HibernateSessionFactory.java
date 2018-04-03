package com.qhit.lh.gr3.cyh.exam.common.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {
	private static SessionFactory sessionfactory;
	private static Configuration configuration;
	static {
		configuration = new Configuration().configure("hibernate.cfg.xml");
		sessionfactory = configuration.buildSessionFactory();
	}
	
	public static Session getSession() {
		return sessionfactory.openSession();
	}
}
