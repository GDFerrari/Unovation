package br.com.gabriel.cadastro.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("paciente");
	
	public EntityManager getEntityManager() {
		
		return emf.createEntityManager();
		
	}
}
