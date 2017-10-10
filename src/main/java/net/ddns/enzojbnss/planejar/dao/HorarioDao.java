package net.ddns.enzojbnss.planejar.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import net.ddns.enzojbnss.planejar.model.Horario;
import net.ddns.enzojbnss.planejar.util.TesteExecute;

public class HorarioDao {
	
	@Inject
	private EntityManager manager;
	
	@SuppressWarnings("unchecked")
	public List<Horario> getLista(){
		List<Horario> horarios;
		try {
			System.out.println(this.manager.toString());
			Query query = this.manager.createQuery("from Horario");
			horarios = (List<Horario>) query.getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage() + " | " + e.toString());
			horarios = new ArrayList<Horario>();
		}
		return horarios;
	}
	
	public Boolean existe(Horario horario){
		Boolean teste = false;
		try {
			System.out.println(this.manager.toString());
			Query query = this.manager.createQuery("select Count(*) from Horario h where "
					+ "h.inicio = ? and h.fim = ? and  h.diaDaSemana.id = ?");
			query.setParameter(1, horario.getInicio());
			query.setParameter(2, horario.getFim());
			query.setParameter(3, horario.getDiaDaSemana().getId());
			Long qtd = (Long) query.getSingleResult();
			teste = qtd > 0;
		} catch (Exception e) {
			System.out.println(e.getMessage() + " | " + e.toString());
		}
		return teste;
	}
	
	public Long getID(Horario horario){
		Long idHorario = 0l;
		try {
			System.out.println(this.manager.toString());
			Query query = this.manager.createQuery("select h.id from Horario h where "
					+ "h.inicio = ? and h.fim = ? and  h.diaDaSemana.id = ?");
			query.setParameter(1, horario.getInicio());
			query.setParameter(2, horario.getFim());
			query.setParameter(3, horario.getDiaDaSemana().getId());
			idHorario = (Long) query.getSingleResult();
		} catch (Exception e) {
			System.out.println(e.getMessage() + " | " + e.toString());
		}
		return idHorario;
	}

	public TesteExecute add(Horario horario){
		TesteExecute execute;
		try {
			this.manager.getTransaction().begin();
			this.manager.persist(horario);
			this.manager.getTransaction().commit();
			execute = new TesteExecute(true, "Horario adicionado com sucesso!");
		} catch (Exception e) {
			System.out.println(e.getMessage() + " | " + e.toString());
			execute = new TesteExecute(false, e.getMessage() + " | " + e.toString());
		}
		return execute;
	}
	
	public TesteExecute edit(Horario horario){
		TesteExecute execute;
		try {
			this.manager.getTransaction().begin();
			this.manager.merge(horario);
			this.manager.getTransaction().commit();
			execute = new TesteExecute(true, "Horario atualizado com sucesso!");
		} catch (Exception e) {
			System.out.println(e.getMessage() + " | " + e.toString());
			execute = new TesteExecute(false, e.getMessage() + " | " + e.toString());
		}
		return execute;
	}
}
