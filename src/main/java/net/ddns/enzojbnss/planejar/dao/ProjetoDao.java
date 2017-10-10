package net.ddns.enzojbnss.planejar.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import net.ddns.enzojbnss.planejar.model.Projeto;
import net.ddns.enzojbnss.planejar.util.TesteExecute;

public class ProjetoDao {

	@Inject
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	public List<Projeto> getLista() {
		List<Projeto> projetos;
		try {
			System.out.println(this.manager.toString());
			Query query = this.manager.createQuery("from Projeto p where p.ativo = 1");
			projetos = (List<Projeto>) query.getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage() + " | " + e.toString());
			projetos = new ArrayList<Projeto>();
		}
		return projetos;

	}

	public Projeto getProjeto(Long idProjeto) {
		Projeto projeto;
		try {
			System.out.println(this.manager.toString());
			projeto = this.manager.find(Projeto.class, idProjeto);
		} catch (Exception e) {
			System.out.println(e.getMessage() + " | " + e.toString());
			projeto = new Projeto();
		}
		return projeto;
	}

	public Boolean existe(Long idProjeto) {
		Boolean teste = false;
		try {
			System.out.println(this.manager.toString());
			Query query = this.manager.createQuery("select Count(*) from Projeto p where p.id = ?");
			query.setParameter(1, idProjeto);
			Long qtd = (Long) query.getSingleResult();
			teste = (qtd > 0);
		} catch (Exception e) {
			System.out.println(e.getMessage() + " | " + e.toString());
		}
		return teste;
	}

	public TesteExecute add(Projeto projeto) {
		TesteExecute execute;
		try {
			this.manager.getTransaction().begin();
			this.manager.persist(projeto);
			this.manager.getTransaction().commit();
			execute = new TesteExecute(true, "Projeto adicionado com sucesso!");
		} catch (Exception e) {
			System.out.println(e.getMessage() + " | " + e.toString());
			execute = new TesteExecute(false, e.getMessage() + " | " + e.toString());
		}
		return execute;
	}

	public TesteExecute edit(Projeto projeto) {
		TesteExecute execute;
		try {
			this.manager.getTransaction().begin();
			this.manager.merge(projeto);
			this.manager.getTransaction().commit();
			execute = new TesteExecute(true, "Projeto atualizado com sucesso!");
		} catch (Exception e) {
			System.out.println(e.getMessage() + " | " + e.toString());
			execute = new TesteExecute(false, e.getMessage() + " | " + e.toString());
		}
		return execute;
	}
}
