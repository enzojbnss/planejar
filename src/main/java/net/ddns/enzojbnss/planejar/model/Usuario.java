package net.ddns.enzojbnss.planejar.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {
	@Id
	@GeneratedValue
	@Column(name = "idUsuario")
	private Long id;
	private String ativo;
	@ManyToMany
	@JoinTable(name = "usuarioPermissao", joinColumns = @JoinColumn(name = "idUsuario"), inverseJoinColumns = @JoinColumn(name = "idPermissao"))
	private List<Permissao> permissoes;

	public Usuario() {
	}

	public Usuario(Long id, String ativo) {
		this.id = id;
		this.ativo = ativo;
	}

	public Usuario(Long id, String ativo, List<Permissao> permissoes) {
		this.id = id;
		this.ativo = ativo;
		this.permissoes = permissoes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

}
