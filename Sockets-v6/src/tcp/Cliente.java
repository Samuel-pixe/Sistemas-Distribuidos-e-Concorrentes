package tcp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JTextArea;

public class Cliente extends Comunicacao  {
    
    public Cliente(JTextArea txt){
        super(txt);
    }
    
    @Override
    public void run(){
        try {
            Socket con = new Socket(URL, PORTA);
            fluxo_saida = new ObjectOutputStream(con.getOutputStream());
            fluxo_entrada = new ObjectInputStream(con.getInputStream());
            
            Mensagem msg = null;
            do{
                msg = (Mensagem)fluxo_entrada.readObject();
                txt.append(msg.toString());
            }while (!msg.getTitulo().toUpperCase().equals("FIM"));
            fluxo_entrada.close();
            fluxo_saida.close();
            con.close();
            
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("ERRO: " + e.getMessage());
        }
    }
    
}
