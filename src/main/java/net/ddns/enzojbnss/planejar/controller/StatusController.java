package net.ddns.enzojbnss.planejar.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import net.ddns.enzojbnss.planejar.dao.StatusDao;

@Controller
public class StatusController {
	@Inject
	private Result result;
	
	@Inject
	private StatusDao dao;
   
	public void lista(){
	    this.result.use(Results.json()).withoutRoot().from(dao.getLista()).serialize();	
	}
}
