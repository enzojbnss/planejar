package net.ddns.enzojbnss.planejar.util;

public class TesteExecute {

	private boolean status;
	private String mensagem;

	public TesteExecute() {
		// TODO Auto-generated constructor stub
	}

	public TesteExecute(boolean status, String mensagem) {
		this.status = status;
		this.mensagem = mensagem;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
