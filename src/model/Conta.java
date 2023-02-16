package model;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.Calendar;
import view.Visualizacao;

public class Conta {
	
	// conta n�o pode ser "static" pois deve ser acessado diretamente atrav�s da inst�ncia do objeto conta.
	
	// -------------------------------------------------------------------- atributos  ----------------------------------------------------------------------------//
	
	private ArrayList<Movimentacoes> listaDeMovimentacoes = new ArrayList<Movimentacoes>();
	private double saldo;
	private String titularConta;
	private String tipoDeConta;
	
	// -------------------------------------------------------------------- in�cio getters e setters ----------------------------------------------------------------------------//
	
	public String getTitularConta() {
		return titularConta;
	}
	public void setTitularConta(String titularConta) {
		this.titularConta = titularConta;
	}
	public String getTipoDeConta() {
		return tipoDeConta;
	}
	public void setTipoDeConta(String tipoDeConta) {
		this.tipoDeConta = tipoDeConta;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public ArrayList<Movimentacoes> getListaDeMovimentacoes() {
		return listaDeMovimentacoes;
	}
	public void setListaDeMovimentacoes(ArrayList<Movimentacoes> listaDeMovimentacoes) {
		this.listaDeMovimentacoes = listaDeMovimentacoes;
	}
	
	// -------------------------------------------------------------------- Movimenta��es ----------------------------------------------------------------------------//
	
	public void sacar() {
		Movimentacoes movSaque = new Movimentacoes();
		double valorSacado = 0;
			while(valorSacado <= 0) {
				valorSacado = Visualizacao.solicitarInformacoesSaque();
			}
			if(this.saldo - valorSacado < -1000.00) { //a conta n�o pode ficar mais negativa que -1000
				Visualizacao.erroSaldoInsuficiente();
			} else {
				this.setSaldo(this.saldo - valorSacado);
				
				SimpleDateFormat dataFormatada = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
				Calendar data = Calendar.getInstance();
				movSaque.setData(dataFormatada.format(data.getTime())); //a classe Calendar � mais utilizada que Date mas n�o pode ser instanciada, ent�o utiliza-se getTime() para acess�-la 
				movSaque.setTipoMovimentacao("Saque");
				movSaque.setValor(valorSacado);
				
				listaDeMovimentacoes.add(movSaque);
			}
	}
	
	public void depositar() {
		Movimentacoes movDeposito = new Movimentacoes();
		double valorDepositado = 0;
			while(valorDepositado <= 0) {
				valorDepositado = Visualizacao.solicitarInformacoesDeposito();
			}
		double saldoFinal = this.saldo + valorDepositado;
		this.setSaldo(saldoFinal);
		
		SimpleDateFormat dataFormatada = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar data = Calendar.getInstance();
		movDeposito.setData(dataFormatada.format(data.getTime())); 
		movDeposito.setTipoMovimentacao("Dep�sito");
		movDeposito.setValor(valorDepositado);
		
		listaDeMovimentacoes.add(movDeposito);
	}
	
	//-------------------------------------------------------------------- Gerar informa��es ----------------------------------------------------------------------------//
	
	public void gerarSaldo() {
		Visualizacao.exibirSaldo(this.saldo);
	}
	
	public void gerarDadosDaConta() {
		// exibir nome do titular, tipo da conta e saldo	
		String valorSaldoFormatado = NumberFormat.getCurrencyInstance().format(this.saldo);
		String informacoesConta = "Nome do titular: "+this.titularConta+"\nTipo de conta: "+this.tipoDeConta+"\nSaldo dispon�vel: "+valorSaldoFormatado;
		Visualizacao.exibirDadosDaConta(informacoesConta);
	}
	
	public String geraExtrato(String tipoDeExtratoSelecionado) {
		String informacoesExtrato = null;
		
		if(listaDeMovimentacoes.size() == 0) {
			informacoesExtrato = "Nenhuma movimenta��o";
		} else {
			if(tipoDeExtratoSelecionado == "Completo") {
				for(Movimentacoes movExtrato : this.listaDeMovimentacoes) {
					informacoesExtrato += "\nValor: "+NumberFormat.getCurrencyInstance().format(movExtrato.getValor())
							+"\nData: "+movExtrato.getData()
							+"\nTipo: "+movExtrato.getTipoMovimentacao()
							+"\n-----------";
				}
			} else {
				if(tipoDeExtratoSelecionado == "Saque" || tipoDeExtratoSelecionado == "Dep�sito") {
					for(Movimentacoes movExtrato : this.listaDeMovimentacoes) {
						if(movExtrato.getTipoMovimentacao() == tipoDeExtratoSelecionado) {
							informacoesExtrato += "\nValor: "+NumberFormat.getCurrencyInstance().format(movExtrato.getValor())
									+"\nData: "+movExtrato.getData()
									+"\nTipo: "+movExtrato.getTipoMovimentacao()
									+"\n-----------";;
						}
					}
				}
			}
		}
		return informacoesExtrato;
	}
}
