package net.ddns.enzojbnss.planejar.controller;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import net.ddns.enzojbnss.planejar.dao.HorarioDao;
import net.ddns.enzojbnss.planejar.dao.PeriodoDao;
import net.ddns.enzojbnss.planejar.dao.TarefaDao;
import net.ddns.enzojbnss.planejar.model.Horario;
import net.ddns.enzojbnss.planejar.model.Periodo;
import net.ddns.enzojbnss.planejar.model.Tarefa;
import net.ddns.enzojbnss.planejar.util.TesteExecute;

@Controller
public class HorarioController {

	@Inject
	private Result result;

	@Inject
	private HorarioDao dao;

	@Inject
	private TarefaDao tarefaDao;

	@Inject
	private PeriodoDao periodoDao;

	public void lista() {
		List<Horario> horarios = dao.getLista();
		this.result.use(Results.json()).withoutRoot().from(horarios).include("diaDaSemana").serialize();
	}

	public void salvar(Horario horario, Long idTarefa) {
		TesteExecute execute;
		try {
			if (horario.getId() == 0) {
				execute = tratarHorarioADD(horario,idTarefa);
			} else {
				execute = tratarHorarioEdit(horario,idTarefa);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			execute = new TesteExecute(false, e.getMessage());
		}
		this.result.use(Results.json()).withoutRoot().from(execute).serialize();
	}

	private TesteExecute tratarHorarioADD(Horario horario, Long idTarefa) {
		TesteExecute execute;
		try {
			if (this.dao.existe(horario) == true) {
				horario.setId(this.dao.getID(horario));
				execute = new TesteExecute(true, "Horario adicionado com Sucesso!");
			} else {
				horario.setId(null);
				execute = this.dao.add(horario);
			}
			Tarefa tarefa = tarefaDao.getTarefa(idTarefa);
			List<Periodo> periodos = tarefa.getPeriodos();
			Periodo periodo = new Periodo(horario);
			periodoDao.add(periodo);
			periodos.add(periodo);
			tarefa.setPeriodos(periodos);
			tarefaDao.edit(tarefa);	
		} catch (Exception e) {
			System.out.println(e.getMessage());
			execute = new TesteExecute(false, e.getMessage());
		}
		return execute;
	}
	
	private TesteExecute tratarHorarioEdit(Horario horario, Long idTarefa) {
		TesteExecute execute;
		try {
			if(this.periodoDao.travado(horario.getId())==true){
				if (this.dao.existe(horario) == true) {
					horario.setId(this.dao.getID(horario));
					execute = new TesteExecute(true, "Horario Atualizado com Sucesso!");
				} else {
					horario.setId(null);
					execute = this.dao.add(horario);
					execute.setMensagem("Horario Atualizado com Sucesso!");
					Tarefa tarefa = tarefaDao.getTarefa(idTarefa);
					List<Periodo> periodos = tarefa.getPeriodos();
					Periodo periodo = new Periodo(horario);
					periodoDao.add(periodo);
					periodos.add(periodo);
					tarefa.setPeriodos(periodos);
					tarefaDao.edit(tarefa);
				}	
			}else{
				execute = this.dao.edit(horario);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			execute = new TesteExecute(false, e.getMessage());
		}
		return execute;
	}
}
