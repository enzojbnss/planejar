<%@ include file="/header.jsp"%>
<script type="text/javascript"
	src="<c:url value="/js/projeto/index.js"/>"></script>

<div class="panel">
	<div class="jumbotron">
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="form-group" style="height: 35px;">
					<div class="col-md-12">
						<table class="table-striped">
							<tr>
								<th>Antonio Marcos de Campos</th>
								<th>enzojbnss</th>
								<th><button id="btnNovoProjeto" class="btn btn-success">
										Novo projeto&nbsp;<span class="glyphicon glyphicon-plus-sign"></span>
									</button></th>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="form-group">
					<div class="col-md-12">
						<table class="table-striped">
							<thead>
								<tr>
									<th>Nome</th>
									<th>Descrição</th>
									<th>Status</th>
									<th>Inicio</th>
									<th>Fim</th>
									<th></th>
									<th></th>
									<th></th>
								</tr>
							</thead>
							<tbody data-bind="foreach: projetos">
								<tr>
									<td data-bind="text: nome"></td>
									<td data-bind="text: descricao"></td>
									<td data-bind="text: status.descricao"></td>
									<td
										data-bind="text: inicio.day +'/'+ inicio.month +'/' + inicio.year  "></td>
									<td data-bind="text: fim.day +'/'+ fim.month +'/' + fim.year  "></td>
									<td><button data-bind="click : $parent.editar"
											class="btn btn-success ">
											Editar&nbsp;<span class="glyphicon glyphicon-edit"></span>
										</button></td>
									<td><button class="btn btn-danger">
											Excluir&nbsp;<span class="glyphicon glyphicon-trash"></span>
										</button></td>
									<td><button data-bind="click : $parent.detalhar"
											class="btn btn-info">
											Detalhe&nbsp;<span class="glyphicon glyphicon-info-sign"></span>
										</button></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div id="dvForm" class="panel panel-default">
			<div class="panel-body">
				<div class="form-group" style="height: 35px;">
					<div class="col-md-12">
						<input data-bind="value: txtID" type="hidden">
						<table class="table">
							<tr>
								<td colspan="2"><label style="width: 80%;" for="txtNome">nome:
										<input type="text" data-bind="value : txtNome"
										class="form-control">
								</label></td>
							</tr>
							<tr>
								<td colspan="2"><label for="txtDescricao">Descrição:
										<textarea cols="80" rows="3" data-bind="value  : txtDescricao"
											class="form-control"></textarea>
								</label></td>
							</tr>
							<tr>
								<td><label for="txtInicio">Data de inicio: <input
										data-bind="value : txtInicio" type="date">
								</label></td>
								<td><label for="txtFim">Data final: <input
										data-bind="value : txtFim" type="date">
								</label></td>
							</tr>
							<tr>
								<td colspan="2"><label style="width: 80%;" for="cboStatus">Status:
										<select id="cboStatus" class="form-control"
										data-bind="options: listaStatusProjeto,
							                   optionsText: 'descricao',
							                   value: statusProjetoValue,
							                   optionsCaption: 'Choose...'"></select>
								</label></td>
							</tr>
							<tr>
								<td><button id="btnSalvar" class="btn btn-success">Salvar</button></td>
								<td><button id="btnCancelar" class="btn btn-danger">Cancelar</button></td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="/footer.jsp"%>



