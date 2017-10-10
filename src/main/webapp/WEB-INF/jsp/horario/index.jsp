


<div class="panel">

	<div class="panel panel-default" style="height: 600px;">
		<div class="panel-body">
			<div class="form-group">
				<div class="col-md-12">
					<table class="table-striped">
						<thead>
							<tr>
								<th>Dia</th>
								<th>Inicio</th>
								<th>Fim</th>
								<th></th>
								<th></th>
							</tr>
						</thead>
						<tbody data-bind="foreach: periodos">
							<tr>
								<td data-bind="text: horario.diaDaSemana.descricao"></td>
								<td data-bind="text: horario.inicio"></td>
								<td data-bind="text: horario.fim"></td>
								<td><button class="btn btn-success" data-bind="click : $parent.editar" >Editar&nbsp;<span class="glyphicon glyphicon-edit"></span></button></td>
								<td><button class="btn btn-danger">Excluir&nbsp;<span class="glyphicon glyphicon-trash"></span></button></td>
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
				    <input data-bind="value: txtID" type="hidden" >
					<table class="table">
						<tr>
							<td colspan="2"><label style="width: 80%;" for="cboDia">Dia:
									<select id="cboDia" class="form-control"
									data-bind="options: diasDASemana,
							                   optionsText: 'descricao',
							                   value: diaValue,
							                   optionsCaption: 'Choose...'"></select>
							</label></td>
						</tr>
						<tr>
							<td><label for="txtInicio">Hora de inicio: <input
								data-bind="value : txtInicio"	type="time" >
							</label></td>
							<td><label for="txtFim">Hora final: <input
								data-bind="value : txtFim"	type="time" >
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




