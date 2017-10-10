package net.ddns.enzojbnss.planejar.controller;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import net.ddns.enzojbnss.planejar.dao.ProjetoDao;
import net.ddns.enzojbnss.planejar.model.Projeto;
import net.ddns.enzojbnss.planejar.util.TesteExecute;

@Controller
public class ProjetoController {

	@Inject
	private Result result;
	@Inject
	private ProjetoDao dao;

	@Path("/projeto")
	public void index() {

	}

	@Path("/projeto/{idProjeto}")
	public void form(Long idProjeto) {
		Boolean mudar = (dao.existe(idProjeto) == false);
		System.out.println(mudar);
		if (idProjeto == null || idProjeto == 0 || mudar)
			this.result.redirectTo(ProjetoController.class).index();
		else
			this.result.include("initiID", idProjeto);
	}

	public void get(Long idProjeto) {
		Projeto projeto = dao.getProjeto(idProjeto);
		this.result.use(Results.json()).withoutRoot().from(projeto).include("status", "inicio", "fim").serialize();
	}

	public void lista() {
		List<Projeto> projetos = dao.getLista();
		this.result.use(Results.json()).withoutRoot().from(projetos).include("status", "inicio", "fim").serialize();
	}

	public void salvar(Projeto projeto, String inicio, String fim) {
		TesteExecute teste;
		try {
			System.out.println(projeto.getDescricao());
			projeto.setInicio(LocalDate.parse(inicio));
			projeto.setFim(LocalDate.parse(fim));
			if (projeto.getId() == 0) {
				projeto.setId(null);
				teste = this.dao.add(projeto);
			} else {
				teste = this.dao.edit(projeto);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			teste = new TesteExecute(false, e.getMessage());
		}
		this.result.use(Results.json()).withoutRoot().from(teste).serialize();
	}

	public void excluir(Long idProjeto){ 
		TesteExecute teste;
		try {
			Projeto projeto = dao.getProjeto(idProjeto);
			projeto.setAtivo(false);
			teste = dao.edit(projeto);	
		} catch (Exception e) {
			System.out.println(e.getMessage());
			teste = new TesteExecute(false, e.getMessage());
		}
		if(teste.isStatus() == true)teste.setMensagem("Projeto excluido com sucesso");
		this.result.use(Results.json()).withoutRoot().from(teste).serialize();
	}
}
