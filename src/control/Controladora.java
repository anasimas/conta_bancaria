package control;
import view.Visualizacao;
import model.*;

public class Controladora {

private static Conta conta = null;

public static void exibeMenu() {
	int opcao;

	do {
		opcao = Visualizacao.escolheOpcao(conta);
		switch(opcao) {
		case 0: //criar conta
			conta = Visualizacao.solicitarInformacoesConta();
		break;
		case 1: //saldo
			conta.gerarSaldo();
		break;
		case 2: //depositar
			conta.depositar();
		break;
		case 3: //sacar
			conta.sacar();
		break;
		case 4://dados da conta
			conta.gerarDadosDaConta();
		break;
		case 5: //extratos
			Visualizacao.extrato(conta);
		break;
		case 6:
			System.exit(0);
		break;
		}
	} while(opcao!=6);
}

}
