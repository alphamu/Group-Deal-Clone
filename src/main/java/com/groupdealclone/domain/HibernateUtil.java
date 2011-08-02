package com.groupdealclone.domain;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.Interceptor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateUtil {
	private static final Logger logger = LoggerFactory
			.getLogger(HibernateUtil.class);

	private static final SessionFactory sessionFactory;
	private static List<Class<?>> entityClasses;
	private static final String[] PROPERTY_FILES = { "entities.properties",
			"entities.properties" };
	private static Interceptor interceptor;

	static {
		try {
			Configuration anno = new Configuration();
			entityClasses = new ArrayList<Class<?>>();

			for (String propertyFile : PROPERTY_FILES) {
				logger.debug("Reading entities list from '{}' -----",propertyFile);
				Properties entities = loadProperties(propertyFile);
				if (entities == null)
					continue;
				
				List<String> keys = new ArrayList<String>(entities.stringPropertyNames());
				Collections.sort(keys);

				for (String key : keys) {
					String z = entities.getProperty(key, null);
					if (logger.isDebugEnabled())
						logger.debug("{}={}",key, z);
					if (z != null) {
						try {
							Class<?> zz = Class.forName(z);
							if (!entityClasses.contains(zz)) {
								anno.addAnnotatedClass(zz);
								entityClasses.add(zz);
							}
						} catch (Exception x) {
							logger.error("Exception loading class {}\n{}",z,x);
						}
					}
				}
			}

			anno.configure();

			sessionFactory = anno.buildSessionFactory();
		} catch (Throwable ex) {
			ex.printStackTrace();
			logger.error("Exception in HibernateUtils {}", ex);
			// Log exception!
			throw new ExceptionInInitializerError(ex);
		}

	}

	/**
	 * Loads and returns a properties file from the WEB-INF/classes dir
	 */
	private static Properties loadProperties(String resourceName) {
		InputStream is = null;
		try {
			is = HibernateUtil.class.getClassLoader().getResourceAsStream(
					resourceName);
		} catch (Exception x) {
			logger.error("Cannot load '{}' properties:\n{}", resourceName, x);
			return null;
		}

		if (is != null) {
			try {
				Properties props = new Properties();
				props.load(is);
				return (props);
			} catch (IOException e) {
				logger.error("Error reading properties '{}'\n{} ", resourceName, e);
			}
		}

		return (null);

	}

	public static SessionFactory getSessionFactory() {
		return (sessionFactory);
	}

	public static Session getSession() throws HibernateException {
		if (interceptor == null)
			return sessionFactory.openSession();
		else {
			Session session = sessionFactory.openSession(interceptor);
			return session;
		}
	}

	public static void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}

}