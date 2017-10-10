package net.ddns.enzojbnss.planejar.controller;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import net.ddns.enzojbnss.planejar.dao.PeriodoDao;
import net.ddns.enzojbnss.planejar.model.Periodo;

@Controller
public class PeriodoController {

	
	@Inject
	private Result result;
	@Inject
	private PeriodoDao dao;
	
	public void lista(Long idTarefa){
		List<Periodo> periodos = dao.getLista(idTarefa);
		this.result.use(Results.json()).withoutRoot().from(periodos).recursive().serialize();
	}
}
