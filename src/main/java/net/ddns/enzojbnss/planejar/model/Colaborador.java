package net.ddns.enzojbnss.planejar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "colaborador")
public class Colaborador {
	@Id
	@GeneratedValue
	@Column(name = "idColaborador")
	private Long id;
	private String nome;
	private String email;
	private Boolean ativo;
	@OneToOne
	@JoinColumn(name = "idUsuario")
	private Usuario usuario;

	public Colaborador() {
	}

	public Colaborador(Long id, String nome, String email, Boolean ativo, Usuario usuario) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.ativo = ativo;
		this.usuario = usuario;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
