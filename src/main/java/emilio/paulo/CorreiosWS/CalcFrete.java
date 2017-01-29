package emilio.paulo.CorreiosWS;

import java.math.BigDecimal;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.tempuri.CResultado;
import org.tempuri.CServico;
import org.tempuri.CalcPrecoPrazoWSLocator;
import org.tempuri.CalcPrecoPrazoWSSoap;

public class CalcFrete {
	public static void main(String[] args) throws ServiceException, RemoteException {
        
		String nCdEmpresa = null;	
		String sDsSenha = null;
		
		// 41106 - Via PAC
        // 40010 - Via SEDEX
		// 40045 - Via SEDEX a cobrar
		// 40215 - Via SEDEX 10
		// 40290 - Via SEDEX Hoje
		String nCdServico = "41106,40010,40045,40215,40290";
		String sCepOrigem = "57040507";
		String sCepDestino = "18273740";
		String nVlPeso = "10";
		int nCdFormato = 1;
		
		// Comprimento entre 16 e 105 cm
		BigDecimal nVlComprimento = new BigDecimal(20);
		
		// Altura entre 2 e 105 cm 
		BigDecimal nVlAltura = new BigDecimal(10);
		
		// Largura entre 11 e 105 cm
		BigDecimal nVlLargura = new BigDecimal(15);
		
		// Diâmetro entre 5 e 91 cm
		BigDecimal nVlDiametro = new BigDecimal(10);
		String sCdMaoPropria = "N";
		BigDecimal nVlValorDeclarado = new BigDecimal(0);
		String sCdAvisoRecebimento = "N";
		
        CalcPrecoPrazoWSSoap calculador = new CalcPrecoPrazoWSLocator().getCalcPrecoPrazoWSSoap();
        CResultado resultado = calculador.calcPrecoPrazo(nCdEmpresa, sDsSenha, nCdServico, sCepOrigem, sCepDestino, nVlPeso, nCdFormato, nVlComprimento, nVlAltura, nVlLargura, nVlDiametro, sCdMaoPropria, nVlValorDeclarado, sCdAvisoRecebimento);
        
        CServico[] servicos = resultado.getServicos();
        for (CServico servico : servicos) {
        	if(servico.getErro().equals("0")){
        		System.out.println("Entrega via: " + servico.getCodigo());
        		System.out.println("Prazo para entrega: " + servico.getPrazoEntrega() + " dia(s) úteis");
                System.out.println("R$ = " + servico.getValor());
                System.out.println("");
        	}
        	else{
        		System.out.println("Entrega via: " + servico.getCodigo());
        		System.out.println(servico.getMsgErro());
        		System.out.println("");
        	}	            
        }        
    }
}
