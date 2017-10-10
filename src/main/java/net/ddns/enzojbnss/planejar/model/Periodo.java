package net.ddns.enzojbnss.planejar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "periodo")
public class Periodo {
	@Id
	@GeneratedValue
	@Column(name = "idPeriodo")
	private Long id;
	@ManyToOne
	@JoinColumn(name = "idHorario")
	private Horario horario;
	private Boolean ativo;

	public Periodo() {
	}

	public Periodo(Horario horario) {
		this.horario = horario;
		this.ativo = true;
	}
	
	public Periodo(Long id, Horario horario, Boolean ativo) {
		this.id = id;
		this.horario = horario;
		this.ativo = ativo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}


	public Boolean getAtivo() {
		return ativo;
	}


	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

}
