package net.ddns.enzojbnss.planejar.util;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ProdutorEntityManager {
	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("planejamentoDS");

	@Produces
	@RequestScoped
	public EntityManager criaEntityManager() {
		
		try {
			return factory.createEntityManager();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return factory.createEntityManager();
		}	
	}

	public void finaliza(@Disposes EntityManager manager) {
		System.out.println("encerrou o manager");
		manager.close();
	}


}
