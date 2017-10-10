package net.ddns.enzojbnss.planejar.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "projeto")
public class Projeto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idProjeto")
	private Long id;
	private String nome;
	private String descricao;
	private Boolean ativo;
	private LocalDate inicio;
	private LocalDate fim;
	@ManyToOne
	@JoinColumn(name = "idStatus")
	private Status status;
	@ManyToMany
	@JoinTable(name = "metaProjeto", joinColumns = @JoinColumn(name = "idProjeto"), inverseJoinColumns = @JoinColumn(name = "idMeta"))
	private List<Meta> metas;
	@ManyToMany
	@JoinTable(name = "projetoTarefa", joinColumns = @JoinColumn(name = "idProjeto"), inverseJoinColumns = @JoinColumn(name = "idTarefa"))
	private List<Tarefa> tarefas;

	public Projeto() {
	}

	public Projeto(Long id, String nome, String descricao, Status status) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.status = status;
		this.ativo = true;
	}

	public Projeto(Long id, String nome, String descricao, Boolean ativo, Status status) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.ativo = ativo;
		this.status = status;
	}

	public Projeto(Long id, String nome, String descricao, Boolean ativo, LocalDate inicio, LocalDate fim,
			Status status) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.ativo = ativo;
		this.inicio = inicio;
		this.fim = fim;
		this.status = status;
	}

	public Projeto(Long id, String nome, String descricao, Boolean ativo, LocalDate inicio, LocalDate fim,
			Status status, List<Meta> metas, List<Tarefa> tarefas) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.ativo = ativo;
		this.inicio = inicio;
		this.fim = fim;
		this.status = status;
		this.metas = metas;
		this.tarefas = tarefas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public LocalDate getInicio() {
		return inicio;
	}

	public void setInicio(LocalDate inicio) {
		this.inicio = inicio;
	}

	public LocalDate getFim() {
		return fim;
	}

	public void setFim(LocalDate fim) {
		this.fim = fim;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Meta> getMetas() {
		return metas;
	}

	public void setMetas(List<Meta> metas) {
		this.metas = metas;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

	
	
	
}
