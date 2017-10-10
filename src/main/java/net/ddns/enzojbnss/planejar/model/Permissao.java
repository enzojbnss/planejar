package net.ddns.enzojbnss.planejar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="permissao")
public class Permissao {
	@Id
	@GeneratedValue
	@Column(name="idPermissao")
	private Long id;
	@ManyToOne
	@JoinColumn(name="idProjeto")
	private Projeto projeto;
	@ManyToOne
	@JoinColumn(name="idTarefa")
	private Tarefa tarefa;
	@ManyToOne
	@JoinColumn(name="idAcao")
	private Acao acao;

	public Permissao() {
	}

	public Permissao(Long id, Projeto projeto, Tarefa tarefa, Acao acao) {
		this.id = id;
		this.projeto = projeto;
		this.tarefa = tarefa;
		this.acao = acao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public Acao getAcao() {
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

}
