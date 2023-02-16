package model;
import java.util.ArrayList;
import java.util.Calendar;

public class Movimentacoes {
	
	// Movimentacoes n�o pode ser "static" pois deve ser acessado diretamente atrav�s da inst�ncia do objeto movimentacoes.
	
	// -------------------------------------------------------------------- atributos ----------------------------------------------------------------------------//
	
	private double valor;
	private String data;
	private String tipoMovimentacao;
	
	// -------------------------------------------------------------------- in�cio getters e setters ----------------------------------------------------------------------------//
	public double getValor() {
		return valor;
	}
	public  void setValor(double valor) {
		this.valor = valor;
	}
	public String getTipoMovimentacao() {
		return tipoMovimentacao;
	}
	public void setTipoMovimentacao(String tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

	// -------------------------------------------------------------------- fim getters e setters ----------------------------------------------------------------------------//
	
}
