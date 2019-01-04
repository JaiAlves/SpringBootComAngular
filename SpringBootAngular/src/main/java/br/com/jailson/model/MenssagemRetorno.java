package br.com.jailson.model;

public class MenssagemRetorno {
	
	public static final String OK="0'";
	public static final String ERRO_CHAMADA="06";
	public static final String ERRO_SERVER="90";
	

	public String codigoRetorno; //01 - ok, 06 - erro por regra de validacao, 90 - erro exception
	public String menssagemRetorno;
	public Object objetoRetorno;
	
	public String getCodigoRetorno() {
		return codigoRetorno;
	}
	public void setCodigoRetorno(String codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}
	public String getMenssagemRetorno() {
		return menssagemRetorno;
	}
	public void setMenssagemRetorno(String menssagemRetorno) {
		this.menssagemRetorno = menssagemRetorno;
	}
	public Object getObjetoRetorno() {
		return objetoRetorno;
	}
	public void setObjetoRetorno(Object objetoRetorno) {
		this.objetoRetorno = objetoRetorno;
	}
}
