/**
 * 
 */
var app;

var AppViewModel = {
	Class : function() {
		this.txtID = ko.observable(0);
		this.txtNome = ko.observable("");
		this.txtDescricao = ko.observable("");
		this.txtInicio = ko.observable(new Date());
		this.txtFim = ko.observable(new Date());
		this.projeto = new Object();
		this.projetos = ko.observableArray([]);
		this.listaStatusProjeto = ko.observableArray([]);
		this.statusProjetoValue = ko.observable("");
		this.statusSelecionado = new Object();

		this.carregar = function(lista) {
			this.projetos(lista);
		};

		this.carregarListaStatusProjeto = function(lista) {
			this.listaStatusProjeto(lista);
		};

		this.statusProjetoValue.subscribe(function(newValue) {
			try {
				parent.app.statusSelecionado = newValue;
			} catch (e) {
				// TODO: handle exception
			}
		});

		this.detalhar = function(projeto) {
			var caminho = '/planejar/projeto/' + projeto.id;
			$(location).attr('href', caminho);
		};

		this.editar = function(projeto) {
			statusProjeto = projeto.status;
			try {
				parent.app.txtID(projeto.id);
				parent.app.txtNome(projeto.nome);
				parent.app.txtDescricao(projeto.descricao);
				parent.app.txtInicio(getDataTratada(projeto.inicio.year,
						projeto.inicio.month, projeto.inicio.day));
				parent.app.txtFim(getDataTratada(projeto.fim.year,
						projeto.fim.month, projeto.fim.day));
				$('#cboStatus option').each(function(index, opcao) {
					if (statusProjeto.id == index)
						opcao.selected = true;
					else
						opcao.selected = false;
				});
				parent.app.statusSelecionado = statusProjeto;
			} catch (e) {
				// TODO: handle exception
				alert(e);
			}
			addProjeto();
		};

		this.salvar = function(gravar) {
			if (gravar) {
				parent.app.projeto.id = parent.app.txtID();
				parent.app.projeto.nome = parent.app.txtNome();
				parent.app.projeto.descricao = parent.app.txtDescricao();
				parent.app.projeto.inicio = parent.app.txtInicio();
				parent.app.projeto.fim = parent.app.txtFim();
				parent.app.projeto.status = new Object();
				parent.app.projeto.status.id = parent.app.statusSelecionado.id;
				parent.app.projeto.status.descricao = parent.app.statusSelecionado.descricao;
				parent.app.projeto.status.ativo = parent.app.statusSelecionado.ativo;

			}
			parent.app.txtNome("");
			parent.app.txtDescricao("");
			parent.app.txtInicio("");
			parent.app.txtFim("");
			salvarProjeto(parent.app.projeto);
		}

	}
}

$(function() {
	app = new AppViewModel.Class();
	ko.applyBindings(app);
	getProjetos();
	getStatus();
	$("#dvForm").hide();
	$("#btnNovoProjeto").click(function() {
		addProjeto();
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

function getDataTratada(ano, mes, dia) {
	if (typeof ano == "undefined" && typeof mes == "undefined"
			&& typeof dia == "undefined")
		return new Date().toISOString().substr(0, 10);
	else
		return new Date(ano, mes, dia).toISOString().substr(0, 10);
}

function addProjeto() {
	$("#dvForm").dialog({
		modal : true,
		height : 550,
		width : 1000
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
		app.carregarListaStatusProjeto(retorno);
	}).error(function(retorno) {
		return [];
	});

}

function getProjetos() {
	$.ajax({
		method : 'POST',
		url : "/planejar/projeto/lista",
		xhrFields : {
			withCredentials : true
		},
		data : {}
	}).success(function(retorno) {
		app.carregar(retorno);
	}).error(function(retorno) {
		return [];
	});

}

function salvarProjeto(projeto) {
	$.ajax({
		method : 'POST',
		url : "/planejar/projeto/salvar",
		xhrFields : {
			withCredentials : true
		},
		data : {
			"projeto.id" : projeto.id,
			"projeto.nome" : projeto.nome,
			"projeto.descricao" : projeto.descricao,
			"projeto.status.id" : projeto.status.id,
			"projeto.status.descricao" : projeto.status.descricao,
			"projeto.status.ativo" : projeto.status.ativo,
			"inicio" : projeto.inicio,
			"fim" : projeto.fim
		}
	}).success(function(retorno) {
		alert(retorno.mensagem);
		getProjetos();
	}).error(function(retorno) {
		alert(retorno);
		return [];
	});
}
/*
 * "projeto.id" : 3 , "projeto.nome" : projeto.nome , "projeto.descricao" :
 * projeto.descricao , "projeto.inicio" :{ "day" : 10, "month" : 6 , "year" :
 * 2017 } , "projeto.fim" :{ "day" : 10, "month" : 10 , "year" : 2017 },
 * 
 */