package net.ddns.enzojbnss.planejar.controller;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import net.ddns.enzojbnss.planejar.dao.ProjetoDao;
import net.ddns.enzojbnss.planejar.dao.TarefaDao;
import net.ddns.enzojbnss.planejar.model.Projeto;
import net.ddns.enzojbnss.planejar.model.Tarefa;
import net.ddns.enzojbnss.planejar.util.TesteExecute;

@Controller
public class TarefaController {

	@Inject
	private Result result;
	@Inject
	private TarefaDao dao;
	@Inject
	private ProjetoDao projetoDao;

	@Path("/tarefa")
	public void index() {

	}

	@Path("/tarefa/")
	public void tarefaVazio() {
		this.result.redirectTo(ProjetoController.class).index();
	}

	@Path("/tarefa/{idTarefa}")
	public void form(Long idTarefa) {
		Boolean mudar = (dao.existe(idTarefa) == false);
		if (idTarefa == null || idTarefa == 0 || mudar)
			this.result.redirectTo(ProjetoController.class).index();
		else
			this.result.include("initiID", idTarefa);
	}

	@Path("/teste/{idProjeto}")
	public void teste(Long idProjeto) {
		List<Tarefa> tarefas = dao.teste(idProjeto);
		this.result.use(Results.json()).withoutRoot().from(tarefas).include("status", "inicio", "fim").serialize();
	}

	public void get(Long idTarefa) {
		Tarefa tarefa = dao.getTarefa(idTarefa);
		this.result.use(Results.json()).withoutRoot().from(tarefa).recursive().serialize();
	}

	public void lista(Long idProjeto) {
		List<Tarefa> tarefas = dao.getTarefas(idProjeto);
		this.result.use(Results.json()).withoutRoot().from(tarefas).include("status", "inicio", "fim").serialize();
	}

	public void salvar(Tarefa tarefa, String inicio, String fim, Long idProjeto) {
		TesteExecute teste;
		try {
			tarefa.setInicio(LocalDate.parse(inicio));
			tarefa.setFim(LocalDate.parse(fim));
			if (tarefa.getId() == 0) {
				tarefa.setId(null);
				teste = dao.add(tarefa);
			} else {
				teste = dao.edit(tarefa);
			}
			Projeto projeto = this.projetoDao.getProjeto(idProjeto);
			projeto.getTarefas().add(tarefa);
			this.projetoDao.edit(projeto);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			teste = new TesteExecute(false, e.getMessage());
		}
		this.result.use(Results.json()).withoutRoot().from(teste).serialize();
	}
	
	public void excluir(Long idTarefa){ 
		TesteExecute teste;
		try {
			Tarefa tarefa = dao.getTarefa(idTarefa);
			tarefa.setAtivo(false);
			teste = dao.edit(tarefa);	
		} catch (Exception e) {
			System.out.println(e.getMessage());
			teste = new TesteExecute(false, e.getMessage());
		}
		if(teste.isStatus() == true)teste.setMensagem("Tarefa excluidacom sucesso");
		this.result.use(Results.json()).withoutRoot().from(teste).serialize();
	}
}
