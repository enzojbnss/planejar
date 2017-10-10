package net.ddns.enzojbnss.planejar.controller;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import net.ddns.enzojbnss.planejar.dao.DiaDaSemanaDao;
import net.ddns.enzojbnss.planejar.model.DiaDaSemana;

@Controller
public class DiaDASemanaController {
    
	@Inject  
	private Result result;
	
	@Inject
	private DiaDaSemanaDao dao;
	
	public void lista(){
		List<DiaDaSemana> diasDaSemana = dao.getLista();
		this.result.use(Results.json()).withoutRoot().from(diasDaSemana).serialize();
	}
	
}
