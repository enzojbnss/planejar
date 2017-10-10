package net.ddns.enzojbnss.planejar.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import net.ddns.enzojbnss.planejar.model.Periodo;

public class PeriodoDao {

	@Inject
	private EntityManager manager;

	public Boolean add(Periodo periodo) {
		Boolean execute;
		try {
			this.manager.getTransaction().begin();
			this.manager.persist(periodo);
			this.manager.getTransaction().commit();
			execute = true;
		} catch (Exception e) {
			System.out.println(e.getMessage() + " | " + e.toString());
			execute = false;
		}
		return execute;
	}

	@SuppressWarnings("unchecked")
	public List<Periodo> getLista(Long idTarefa) {
		List<Periodo> periodos;
		try {
			System.out.println(this.manager.toString());
			Query query = this.manager.createQuery("select t.periodos from Tarefa t where t.id = ?");
			query.setParameter(1, idTarefa);
			periodos = (List<Periodo>) query.getResultList();
			List<Periodo> nova = new ArrayList<Periodo>() ;
			for (Periodo periodo : periodos) {
				if (periodo.getAtivo() == true) {
					nova.add(periodo);
				}
			}
			periodos = nova;
		} catch (Exception e) {
			System.out.println(e.getMessage() + " | " + e.toString());
			periodos = new ArrayList<Periodo>();
		}
		return periodos;
	}

	public Periodo getPeriodo(Long idPeriodo) {
		Periodo periodo;
		try {
			System.out.println(this.manager.toString());
			periodo = this.manager.find(Periodo.class, idPeriodo);
		} catch (Exception e) {
			System.out.println(e.getMessage() + " | " + e.toString());
			periodo = new Periodo();
		}
		return periodo;
	}

	public Boolean travado(Long idHorario) {
		Boolean teste = false;
		try {
			System.out.println(this.manager.toString());
			Query query = this.manager.createQuery("select Count(*) from periodo p where p.horario.id != ?");
			query.setParameter(1, idHorario);
			Long qtd = (Long) query.getSingleResult();
			teste = (qtd > 0);
		} catch (Exception e) {
			System.out.println(e.getMessage() + " | " + e.toString());
		}
		return teste;
	}
}
