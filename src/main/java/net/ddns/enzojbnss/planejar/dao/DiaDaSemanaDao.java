package net.ddns.enzojbnss.planejar.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import net.ddns.enzojbnss.planejar.model.DiaDaSemana;

public class DiaDaSemanaDao {
	
	@Inject
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	public List<DiaDaSemana> getLista() {
		List<DiaDaSemana> diasDASemana;
		try {
			System.out.println(this.manager.toString());
			Query query = this.manager.createQuery("from DiaDaSemana");
			diasDASemana = (List<DiaDaSemana>) query.getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage() + " | " + e.toString());
			diasDASemana = new ArrayList<DiaDaSemana>();
		}
		return diasDASemana;
	}

}
