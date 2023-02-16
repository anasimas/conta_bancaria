package view;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.util.*;
import java.text.NumberFormat;
import model.*;

public class Visualizacao {

//----------------------------------------------------- métodos do menu inicial------------------------------------------------------//
public static int escolheOpcao(Conta conta) {
	String[] opcoes = null; 
	
	if(conta == null) {
		opcoes = new String[]{"Criar conta"}; //new String é para previnir o erro "Array constants can only be used in initializer"
	} else {
		opcoes = new String[]{"Criar nova conta", "Saldo", "Depositar", "Sacar", "Dados da conta", "Extratos", "Sair"};
	}
	
	JComboBox<String> menu = new JComboBox<String>(opcoes);
	JOptionPane.showConfirmDialog(null, menu, "Selecione a opção desejada", JOptionPane.OK_CANCEL_OPTION);
	
	return menu.getSelectedIndex();
}

//----------------------------------------------------- métodos da conta (informações, etc...) ------------------------------------------------------//

public static Conta solicitarInformacoesConta() {
	//titular, tipo de conta (1 poupança, 2 corrente)
	Conta conta = new Conta();
	String nomeTitular = JOptionPane.showInputDialog("Informe o nome do titular: ");
	
	String[]tipoDeContaOpcoes = null;
	tipoDeContaOpcoes = new String[]{"Conta Poupança", "Conta Corrente"};
	JComboBox<String> menu = new JComboBox<String>(tipoDeContaOpcoes);
	JOptionPane.showConfirmDialog(null, menu, "Selecione o tipo de conta desejado", JOptionPane.OK_CANCEL_OPTION);
	int tipoDeConta = menu.getSelectedIndex();
	
	String contaTipo;
	if(tipoDeConta == 0) {
		contaTipo = "Poupança";
	} else {
			contaTipo = "Corrente";
	}
	
	conta.setTitularConta(nomeTitular);
	conta.setTipoDeConta(contaTipo);
	
	return conta;
}

public static void exibirDadosDaConta(String dadosGerados) {
// exibir nome do titular, tipo da conta e saldo
	JOptionPane.showMessageDialog(null, dadosGerados);
}
//----------------------------------------------------- métodos de saldo e depósito ------------------------------------------------------//

public static void exibirSaldo(double saldo) {
	String saldoFormatado = NumberFormat.getCurrencyInstance().format(saldo);
	JOptionPane.showMessageDialog(null, "Saldo disponível: "+saldoFormatado, "Saldo", JOptionPane.INFORMATION_MESSAGE);
}

public static double solicitarInformacoesDeposito() {
	return Double.parseDouble(JOptionPane.showInputDialog(null, "Informe o valor a ser depositado: ", JOptionPane.INFORMATION_MESSAGE));
}

public static double solicitarInformacoesSaque() {
	return Double.parseDouble(JOptionPane.showInputDialog(null, "Informe a quantidade a ser sacada: "));
}
//----------------------------------------------------- mensagens de erro ou finalização do método ------------------------------------------------------//

public static void erroSaldoInsuficiente() {
	JOptionPane.showMessageDialog(null, "Você não possui saldo para esta ação, entre em contato com seu gerente e saiba nossas propostas de empréstimo!", null, JOptionPane.WARNING_MESSAGE);
}

public static void transacaoFinalizada() {
	JOptionPane.showMessageDialog(null, "Transação concluída com sucesso!");
}

//----------------------------------------------------- métodos de extratos ------------------------------------------------------//

public static void extrato(Conta conta) {
	
	String[]opcoesDeExtrato = null;
	opcoesDeExtrato = new String[]{"Depósitos", "Saques", "Completo"};
	JComboBox<String> menu = new JComboBox<String>(opcoesDeExtrato);
	JOptionPane.showConfirmDialog(null, menu, "Selecione o tipo de extrato desejado", JOptionPane.OK_CANCEL_OPTION);
	int tipoDeExtratoSelecionado = menu.getSelectedIndex();
	
	String opcaoDeExtratoSelecionado = null;
	if(tipoDeExtratoSelecionado == 0) {
		opcaoDeExtratoSelecionado = "Depósito";
	} else {
		if(tipoDeExtratoSelecionado == 1) {
			opcaoDeExtratoSelecionado = "Saque";
		} else {
			opcaoDeExtratoSelecionado = "Completo";
		}
	}
	
	String informacoes = conta.geraExtrato(opcaoDeExtratoSelecionado);
	JOptionPane.showMessageDialog(null, informacoes);
}
}
