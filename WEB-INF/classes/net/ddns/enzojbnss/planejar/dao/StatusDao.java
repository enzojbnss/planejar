package net.ddns.enzojbnss.planejar.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import net.ddns.enzojbnss.planejar.model.Status;

public class StatusDao {

	@Inject
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	public List<Status> getLista(){
		List<Status> listaStatus;
		try {
			System.out.println(this.manager.toString());
			Query query = this.manager.createQuery("from Status");
			listaStatus = (List<Status>) query.getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage() + " | " + e.toString());
			listaStatus = new ArrayList<Status>();
		}
		return listaStatus;
	}
}
