<%@ include file="/header.jsp"%>
<script type="text/javascript"
	src="<c:url value="/js/tarefa/form.js"/>"></script>


<div class="panel">
	<div class="jumbotron" >
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="form-group" style="height: 35px;">
					<input id="initiID" data-bind="text: tarefa.id" type="hidden" value="${initiID}">
					<div class="col-md-18">
						<table class="table-striped">
							<thead>
								<tr>
									<th data-bind="text: tarefa.nome"></th>
									<th data-bind="text: tarefa.descricao"></th>
									<th data-bind="text: tarefa.status.descricao"></th>
									<th><span data-bind="text: tarefa.inicio.day"></span>/
									    <span data-bind="text: tarefa.inicio.month"></span>/
									    <span data-bind="text: tarefa.inicio.year"></span>
									</th>
									<th><span data-bind="text: tarefa.fim.day"></span>/
									    <span data-bind="text: tarefa.fim.month"></span>/
									    <span data-bind="text: tarefa.fim.year"></span>
									</th>
									<th><button id="btnNovoHorario" class="btn btn-success">Novo
											horario&nbsp;<span class="glyphicon glyphicon-plus-sign"></span></button></th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="panel panel-default" >
			<div class="panel-body" style="height: 650px" >
				<div class="form-group" >
					<div class="col-md-18" >
						<%@ include file="/WEB-INF/jsp/horario/index.jsp"%>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


<%@ include file="/footer.jsp"%>



