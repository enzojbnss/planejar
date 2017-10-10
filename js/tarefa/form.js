/**
 * 
 */
var app;
var diaSelecionado;
var Tarefa = {
	Class : function(data) {
		if (typeof data == "undefined") {
			this.id = ko.observable("");
			this.nome = ko.observable("");
			this.descricao = ko.observable("");
			this.status = new Object();
			this.status.descricao = ko.observable("");
			this.inicio =  new Object();
			this.inicio.day = ko.observable("");
			this.inicio.month = ko.observable("");
			this.inicio.year = ko.observable("");
			this.fim = new Object();
			this.fim.day = ko.observable("");
			this.fim.month = ko.observable("");
			this.fim.year = ko.observable("");
		} else {
			this.id = ko.observable(data.id);
			this.descricao = ko.observable(data.descricao);
			this.status = ko.observable(data.status);
		}

	}
}

var AppViewModel = {
	Class : function() {
		this.tarefa = new Tarefa.Class();
		this.txtID = ko.observable(0);
		this.txtInicio = ko.observable("");
		this.txtFim = ko.observable("");
		this.diaValue = ko.observable("");
		this.diaSelecionado = new Object();
		this.periodos = ko.observableArray([]);
		this.horario = new Object();
		this.diasDASemana = ko.observableArray([]);


		this.carregar = function(lista) {
			this.tarefa.id(lista.id);
			this.tarefa.nome(lista.nome);
			this.tarefa.descricao(lista.descricao);
			this.tarefa.status.descricao(lista.status.descricao);
			this.tarefa.inicio.day(getValorTexto(lista.inicio.day));
			this.tarefa.inicio.month(getValorTexto(lista.inicio.month));
			this.tarefa.inicio.year(getValorTexto(lista.inicio.year));
			this.tarefa.fim.day(getValorTexto(lista.fim.day));
			this.tarefa.fim.month(getValorTexto(lista.fim.month));
			this.tarefa.fim.year(getValorTexto(lista.fim.year));
		};

		this.editar = function(periodo) {
			horario = periodo.horario;
			var dia = horario.diaDaSemana;
			try {
				parent.app.txtID(horario.id);
				parent.app.txtInicio(horario.inicio);
				parent.app.txtFim(horario.fim);
				$('#cboDia option').each(function(index, opcao) {
					if (dia.id == index)
						opcao.selected = true;
					else
						opcao.selected = false;

				});
				parent.app.diaSelecionado = dia;
			} catch (e) {
				alert(e);
			}
			addHorario();
		};

		this.salvar = function(gravar) {
			if (gravar) {
				parent.app.horario.id = parent.app.txtID();
				parent.app.horario.inicio = parent.app.txtInicio();
				parent.app.horario.fim = parent.app.txtFim();
				parent.app.horario.diaDaSemana = new Object();
				parent.app.horario.diaDaSemana.id = parent.app.diaSelecionado.id;
				parent.app.horario.diaDaSemana.descricao = parent.app.diaSelecionado.descricao;
			}
			parent.app.txtInicio("");
			parent.app.txtFim("");
			salvarHorario(parent.app.horario,parent.app.tarefa.id());
		}
		
		this.carregarPeriodos = function(lista) {
			this.periodos(lista);
		};

		this.carregarDiasDASemana = function(lista) {
			this.diasDASemana(lista);
		};
		
		this.diaValue.subscribe(function(newValue) {
			try {
				parent.app.diaSelecionado = newValue;
			} catch (e) {
				// TODO: handle exception
			}
		});
	}
}

function getValorTexto(valor){
	if(valor<10) return "0" + valor;
	else return "" + valor;
}


$(function() {
	app = new AppViewModel.Class();
	ko.applyBindings(app);
	getDiasDaSemana();
	getTarefa();
	$("#dvForm").hide();
	$("#btnNovoHorario").click(function() {
		addHorario();
	}).trigger('change');

	$("#btnSalvar").click(function() {
		app.salvar(true);
		$("#dvForm").dialog("close");
	}).trigger('change');

	$("#btnCancelar").click(function() {
		app.salvar(false);
		$("#dvForm").dialog("close");
	}).trigger('change');
});

function addHorario() {
	$("#dvForm").dialog({
		modal : true,
		height : 400,
		width : 900
	});
}




function getTarefa(){
	$.ajax({
		method : 'POST',
		url : "/planejar/tarefa/get",
		xhrFields : {
			withCredentials : true
		},
		data : {
			"idTarefa" : $("#initiID").val()
		}
	}).success(function(retorno) {
		app.carregar(retorno);
		getPeriodos(retorno.id);
	}).error(function(retorno) {
		return [];
	});
}

function getPeriodos(idTarefa){
	$.ajax({
		method : 'POST',
		url : "/planejar/periodo/lista",
		xhrFields : {
			withCredentials : true
		},
		data : {
			"idTarefa" : idTarefa
		}
	}).success(function(retorno) {
		app.carregarPeriodos(retorno);
	}).error(function(retorno) {
		return [];
	});
}

function getDiasDaSemana(){
	$.ajax({
		method : 'POST',
		url : "/planejar/diaDASemana/lista",
		xhrFields : {
			withCredentials : true
		},
		data : {}
	}).success(function(retorno) {
		app.carregarDiasDASemana(retorno);
	}).error(function(retorno) {
		return [];
	});
}

function salvarHorario(horario , idTarefa) {
	$.ajax({
		method : 'POST',
		url : "/planejar/horario/salvar",
		xhrFields : {
			withCredentials : true
		},
		data : {
			"horario.id" : horario.id,
			"horario.inicio" : horario.inicio,
			"horario.fim" : horario.fim,
			"horario.diaDaSemana.id" : horario.diaDaSemana.id,
			"horario.diaDaSemana.descricao" : horario.diaDaSemana.descricao,
			"idTarefa" : idTarefa
		}
	}).success(function(retorno) {
		alert(retorno.mensagem);
		getPeriodos(idTarefa);
	}).error(function(retorno) {
		alert(retorno);
		return [];
	});
}
