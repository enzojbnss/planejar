/**
 * 
 */
var app;

var Projeto = {
	Class : function(data) {
		if (typeof data == "undefined") {
			this.id = ko.observable("");
			this.nome = ko.observable("");
			this.descricao = ko.observable("");
			this.status = new Object();
			this.status.descricao = ko.observable("");
			this.inicio = new Object();
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
			this.inicio = ko.observable(data.inicio);
			this.fim = ko.observable(data.fim);
		}

	}
}

var AppViewModel = {
	Class : function() {
		this.txtID = ko.observable(0);
		this.txtNome = ko.observable("");
		this.txtDescricao = ko.observable("");
		this.txtInicio = ko.observable("");
		this.txtFim = ko.observable("");
		this.projeto = new Projeto.Class();
		this.tarefas = ko.observableArray([]);
		this.tarefa = new Object();
		this.listaStatusTarefa = ko.observableArray([]);
		this.statusTarefaValue = ko.observable("");
		this.statusSelecionado = new Object();

		this.carregar = function(lista) {
			this.projeto.id(lista.id);
			this.projeto.nome(lista.nome);
			this.projeto.descricao(lista.descricao);
			this.projeto.status.descricao(lista.status.descricao);
			this.projeto.inicio.day(getValorTexto(lista.inicio.day));
			this.projeto.inicio.month(getValorTexto(lista.inicio.month));
			this.projeto.inicio.year(getValorTexto(lista.inicio.year));
			this.projeto.fim.day(getValorTexto(lista.fim.day));
			this.projeto.fim.month(getValorTexto(lista.fim.month));
			this.projeto.fim.year(getValorTexto(lista.fim.year));
		};

		this.carregarTarefas = function(lista) {
			this.tarefas(lista);
		};

		this.carregarListaStatusTarefa = function(lista) {
			this.listaStatusTarefa(lista);
		};

		this.statusTarefaValue.subscribe(function(newValue) {
			try {
				parent.app.statusSelecionado = newValue;
			} catch (e) {
				// TODO: handle exception
			}
		});

		this.detalhar = function(tarefa) {
			var caminho = '/planejar/tarefa/' + tarefa.id
			$(location).attr('href', caminho);
		};

		this.editar = function(tarefa) {
			statusTarefa = tarefa.status;
			try {
				parent.app.txtID(tarefa.id);
				parent.app.txtNome(tarefa.nome);
				parent.app.txtDescricao(tarefa.descricao);
				parent.app.txtInicio(getDataTratada(tarefa.inicio.year,
						tarefa.inicio.month, tarefa.inicio.day));
				parent.app.txtFim(getDataTratada(tarefa.fim.year,
						tarefa.fim.month, tarefa.fim.day));
				$('#cboStatus option').each(function(index, opcao) {
					if (statusTarefa.id == index)
						opcao.selected = true;
					else
						opcao.selected = false;
				});
				parent.app.statusSelecionado = statusTarefa;
			} catch (e) {
				// TODO: handle exception
				alert(e);
			}
			addTarefa()
		};

		this.salvar = function(gravar) {
			if (gravar) {
				parent.app.tarefa.id = parent.app.txtID();
				parent.app.tarefa.nome = parent.app.txtNome();
				parent.app.tarefa.descricao = parent.app.txtDescricao();
				parent.app.tarefa.inicio = parent.app.txtInicio();
				parent.app.tarefa.fim = parent.app.txtFim();
				parent.app.tarefa.status = new Object();
				parent.app.tarefa.status.id = parent.app.statusSelecionado.id;
				parent.app.tarefa.status.descricao = parent.app.statusSelecionado.descricao;
				parent.app.tarefa.status.ativo = parent.app.statusSelecionado.ativo;

			}
			parent.app.txtNome("");
			parent.app.txtDescricao("");
			parent.app.txtInicio("");
			parent.app.txtFim("");
			salvarTarfea(parent.app.tarefa, parent.app.projeto.id);
		}

	}
}

$(function() {
	app = new AppViewModel.Class();
	ko.applyBindings(app);
	getStatus();
	getProjeto();
	$("#dvForm").hide();
	$("#btnNovaTarefa").click(function() {
		addTarefa();
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

function getValorTexto(valor) {
	if (valor < 10)
		return "0" + valor;
	else
		return "" + valor;
}

function getDataTratada(ano, mes, dia) {
	if (typeof ano == "undefined" && typeof mes == "undefined"
			&& typeof dia == "undefined")
		return new Date().toISOString().substr(0, 10);
	else
		return new Date(ano, mes, dia).toISOString().substr(0, 10);
}

function addTarefa() {
	$("#dvForm").dialog({
		modal : true,
		height : 550,
		width : 1000
	});
}

function getProjeto() {
	$.ajax({
		method : 'POST',
		url : "/planejar/projeto/get",
		xhrFields : {
			withCredentials : true
		},
		data : {
			"idProjeto" : $("#initiID").val()
		}
	}).success(function(retorno) {
		app.carregar(retorno);
		getTarefas(retorno.id);
	}).error(function(retorno) {
		return [];
	});

}
function getTarefas(idProjeto) {
	$.ajax({
		method : 'POST',
		url : "/planejar/tarefa/lista",
		xhrFields : {
			withCredentials : true
		},
		data : {
			"idProjeto" : idProjeto
		}
	}).success(function(retorno) {
		app.carregarTarefas(retorno);
	}).error(function(retorno) {
		return [];
	});
}
function getStatus() {
	$.ajax({
		method : 'POST',
		url : "/planejar/status/lista",
		xhrFields : {
			withCredentials : true
		},
		data : {}
	}).success(function(retorno) {
		app.carregarListaStatusTarefa(retorno);
	}).error(function(retorno) {
		return [];
	});

}

function salvarTarfea(tarefa, idProjeto) {
	$.ajax({
		method : 'POST',
		url : "/planejar/tarefa/salvar",
		xhrFields : {
			withCredentials : true
		},
		data : {
			"tarefa.id" : tarefa.id,
			"tarefa.nome" : tarefa.nome,
			"tarefa.descricao" : tarefa.descricao,
			"tarefa.status.id" : tarefa.status.id,
			"tarefa.status.descricao" : tarefa.status.descricao,
			"tarefa.status.ativo" : tarefa.status.ativo,
			"inicio" : tarefa.inicio,
			"fim" : tarefa.fim,
			"idProjeto" : idProjeto
		}
	}).success(function(retorno) {
		alert(retorno.mensagem);
		getTarefas(idProjeto);
	}).error(function(retorno) {
		alert(retorno);
		return [];
	});
}
