package net.ddns.enzojbnss.planejar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "horario")
public class Horario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idHorario")
	private Long id;
	private String inicio;
	private String fim;
	@ManyToOne
	@JoinColumn(name="idDiaDaSemana")
	private DiaDaSemana diaDaSemana;

	public Horario() {
	}

	public Horario(Long id, String inicio, String fim, DiaDaSemana diaDaSemana) {
		this.id = id;
		this.inicio = inicio;
		this.fim = fim;
		this.diaDaSemana = diaDaSemana;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	public String getFim() {
		return fim;
	}

	public void setFim(String fim) {
		this.fim = fim;
	}

	public DiaDaSemana getDiaDaSemana() {
		return diaDaSemana;
	}

	public void setDiaDaSemana(DiaDaSemana diaDaSemana) {
		this.diaDaSemana = diaDaSemana;
	}

}
