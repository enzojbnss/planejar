package net.ddns.enzojbnss.planejar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "meta")
public class Meta {

	@Id
	@GeneratedValue
	@Column(name = "idMeta")
	private Long id;
	private String descricao;
	private Long prazo;
	@ManyToOne
	@JoinColumn(name = "idStatus")
	private Status status;

	public Meta() {
	}

	public Meta(Long id, String descricao, Long prazo, Status status) {
		this.id = id;
		this.descricao = descricao;
		this.prazo = prazo;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getPrazo() {
		return prazo;
	}

	public void setPrazo(Long prazo) {
		this.prazo = prazo;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
