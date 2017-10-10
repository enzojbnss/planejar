<%@ include file="/header.jsp"%>
<script type="text/javascript"
	src="<c:url value="/js/projeto/form.js"/>"></script>


<div class="panel">
	<div class="jumbotron">
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="form-group" style="height: 35px;">
					<input id="initiID" data-bind="text: projeto.id" type="hidden"
						value="${initiID}">
					<div class="col-md-18">
						<table class="table-striped">
							<thead>
								<tr>
									<th data-bind="text: projeto.nome"></th>
									<th data-bind="text: projeto.descricao"></th>
									<th data-bind="text: projeto.status.descricao"></th>
									<th><span data-bind="text: projeto.inicio.day"></span>/ <span
										data-bind="text: projeto.inicio.month"></span>/ <span
										data-bind="text: projeto.inicio.year"></span></th>
									<th><span data-bind="text: projeto.fim.day"></span>/ <span
										data-bind="text: projeto.fim.month"></span>/ <span
										data-bind="text: projeto.fim.year"></span></th>
									<th><button id="btnNovaTarefa" class="btn btn-success">
											Nova tarefa&nbsp;<span class="glyphicon glyphicon-plus-sign"></span>
										</button></th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-body" style="height: 650px">
				<div class="form-group">
					<div class="col-md-18">
						<%@ include file="/WEB-INF/jsp/tarefa/index.jsp"%>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


<%@ include file="/footer.jsp"%>



