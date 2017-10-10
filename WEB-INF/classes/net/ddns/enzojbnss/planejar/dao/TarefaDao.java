package net.ddns.enzojbnss.planejar.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import net.ddns.enzojbnss.planejar.model.Tarefa;
import net.ddns.enzojbnss.planejar.util.TesteExecute;

public class TarefaDao {

	@Inject
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	public List<Tarefa> getTarefas(Long idProjeto) {
		List<Tarefa> tarefas = new ArrayList<Tarefa>();
		try {
			System.out.println(this.manager.toString());
			Query query = this.manager.createQuery("select p.tarefas from Projeto p where p.id = ?");
			query.setParameter(1, idProjeto);
			tarefas = (List<Tarefa>) query.getResultList();
			List<Tarefa> nova = new ArrayList<Tarefa>() ;
			for (Tarefa tarefa : tarefas) {
				if (tarefa.getAtivo() == true) {
					nova.add(tarefa);
				}
			}
			tarefas = nova;

		} catch (Exception e) {
			System.out.println(e.getMessage() + " | " + e.toString());
			tarefas = new ArrayList<Tarefa>();
		}
		return tarefas;
	}


	public Tarefa getTarefa(Long idTarefa) {
		Tarefa tarefa;
		try {
			System.out.println(this.manager.toString());
			tarefa = this.manager.find(Tarefa.class, idTarefa);
		} catch (Exception e) {
			System.out.println(e.getMessage() + " | " + e.toString());
			tarefa = new Tarefa();
		}
		return tarefa;
	}

	public Boolean existe(Long idTarefa) {
		Boolean teste = false;
		try {
			System.out.println(this.manager.toString());
			Query query = this.manager.createQuery("select Count(*) from Tarefa t where t.id = ?");
			query.setParameter(1, idTarefa);
			Long qtd = (Long) query.getSingleResult();
			teste = (qtd > 0);
		} catch (Exception e) {
			System.out.println(e.getMessage() + " | " + e.toString());
		}
		return teste;
	}

	public TesteExecute add(Tarefa tarefa) {
		TesteExecute execute;
		try {
			this.manager.getTransaction().begin();
			this.manager.persist(tarefa);
			this.manager.getTransaction().commit();
			execute = new TesteExecute(true, "Tarefa adicionada com sucesso!");
		} catch (Exception e) {
			System.out.println(e.getMessage() + " | " + e.toString());
			execute = new TesteExecute(false, e.getMessage() + " | " + e.toString());
		}
		return execute;
	}

	@SuppressWarnings("unchecked")
	public List<Tarefa> teste(Long idProjeto) {
		System.out.println("idProjeto : "+ idProjeto);
		List<Tarefa> tarefas = new ArrayList<Tarefa>();
		try {
			System.out.println(this.manager.toString());
			Query query = this.manager.createQuery("select p.tarefas from Projeto p where p.id = ? and p.tarefas in(from Tarefa) ");
			query.setParameter(1, idProjeto);
			tarefas = (List<Tarefa>) query.getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage() + " | " + e.toString());
			tarefas = new ArrayList<Tarefa>();
		}
		return tarefas;

	}

	public TesteExecute edit(Tarefa tarefa) {
		TesteExecute execute;
		try {
			this.manager.getTransaction().begin();
			this.manager.merge(tarefa);
			this.manager.getTransaction().commit();
			execute = new TesteExecute(true, "Tarefa atualizada com sucesso!");
		} catch (Exception e) {
			System.out.println(e.getMessage() + " | " + e.toString());
			execute = new TesteExecute(false, e.getMessage() + " | " + e.toString());
		}
		return execute;
	}

}
