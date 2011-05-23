/*
 * Programa desenvolvido por
 * Fabr√≠cio Raphael Baliza
 * Nome prog: Reverse.
 * 
 * fabricioraphael.b@gmail.com
 */


import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.JOptionPane;

public final class Index implements ClipboardOwner {

	public static void main (String... args){
		Index principal = new Index();

		String linkAntes = new String(); 
		String linkFinal = new String();
		String linkSeparado[];
		String [] opcao = {"Aplicar e Continuar", "Sair"};
		int aux;
		String valorEscolido = "";
		do{
			valorEscolido = JOptionPane.showInputDialog("Digite o ponto de que deseja separar o link." , "");
		}while(valorEscolido.equalsIgnoreCase(""));

		do{
			aux = JOptionPane.showOptionDialog(null, "Escolha uma opção.", "Programa Reverse Fabricio R. Vs.1.0.0", 0, 0, null, opcao, opcao);
			if(aux == 0){

				linkAntes = principal.getClipboardContents();
				if(!linkAntes.equalsIgnoreCase("") && linkAntes != null){
					try {
						linkSeparado = linkAntes.split(valorEscolido);
						StringBuffer linkF = new StringBuffer();
						linkF.append(linkSeparado[1]);
						linkF.reverse();
						linkFinal = linkF.toString();
						principal.setClipboardContents(linkFinal);
					} catch (Exception e) {

						JOptionPane.showMessageDialog(null, "Valor Inválido! esse programa não opera esse link... sry");
						aux = 0;
					}
				}else{
					JOptionPane.showMessageDialog(null, "Nenhum valor Copiado!");
					aux = 0;
				}
			}else if(aux == 1){
				principal.setClipboardContents("");
				System.exit(0);
			}
		}while(aux == 0);
	}

	public void reverse(){
		StringBuffer stgB = new StringBuffer();
		String stg;
		stg = JOptionPane.showInputDialog("digite aqui o link");
		stgB.append(stg);
		stgB.reverse();
		JOptionPane.showInputDialog("link:", stgB);
	}

	public void lostOwnership( Clipboard aClipboard, Transferable aContents) {
		//do nothing
	}

	/*
	 * metodo que joga um valor na memória do Ctl+V
	 */
	public void setClipboardContents( String aString ){
		StringSelection stringSelection = new StringSelection( aString );
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents( stringSelection, this );
	}

	/*
	 * metodo que copia o conteúdo da memória do Ctl+C
	 * 
	 */
	public String getClipboardContents() {
		String result = "";
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		//odd: the Object param of getContents is not currently used
		Transferable contents = clipboard.getContents(null);
		boolean hasTransferableText =
			(contents != null) &&
			contents.isDataFlavorSupported(DataFlavor.stringFlavor)
			;
		if ( hasTransferableText ) {
			try {
				result = (String)contents.getTransferData(DataFlavor.stringFlavor);
			}
			catch (UnsupportedFlavorException ex){
				//highly unlikely since we are using a standard DataFlavor
				System.out.println(ex);
				ex.printStackTrace();
			}
			catch (IOException ex) {
				System.out.println(ex);
				ex.printStackTrace();
			}
		}
		return result;
	}
} 
