
package tcp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JTextArea;


public class Comunicacao extends Thread{
    
    protected final int PORTA = 5000;
    protected final String URL = "127.0.0.1";
    protected ObjectInputStream fluxo_entrada;
    protected ObjectOutputStream fluxo_saida;
    protected final JTextArea txt;
   
    
    
    public Comunicacao(JTextArea txt) {
        this.txt = txt;
    }
    
    protected void enviar(String titulo, String conteudo){
        try {
            Mensagem m = new Mensagem();
            m.setId((int)(Math.random()*10000));
            m.setTitulo(titulo);
            m.setConteudo(conteudo);
            
            fluxo_saida.writeObject(m);
            fluxo_saida.flush();
        } catch (IOException e) {
            System.err.println("ERRO: " + e.getMessage());
        }
    }
  
}

    