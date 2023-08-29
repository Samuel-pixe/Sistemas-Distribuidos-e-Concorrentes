package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import javax.swing.JTextArea;

public class Servidor extends Thread {
    
    private final int PORTA = 12345;
    private final JTextArea txt;
    
    public Servidor(JTextArea txt){
        this.txt = txt;
        txt.append("\nServidor iniciado na porta " + PORTA);
    }
    
    @Override
    public void run(){
        try {
            DatagramSocket s = new DatagramSocket(PORTA);
            
            while(true){
                //definir o buffer da mensagem
                byte[] buffer = new byte[256];
                
                //definir o pacote que será recebido
                DatagramPacket pct = new DatagramPacket(
                    buffer, buffer.length
                );
                
                //receber o pacote
                s.receive(pct);
                
                //converter o pacote de bytes para String
                String msg = new String(pct.getData()).trim();
                
                //exibir mensagem
                txt.append("\n");
                txt.append("\nMsg: " + msg);
                txt.append("\nDe.: " + pct.getAddress().getHostAddress());
                
                //System.out.println("\nMsg: " + msg);
                //System.out.println("De.: " + pct.getAddress().getHostAddress());
                //System.out.println("De.: " + pct.getAddress().getHostName());
            }
            
        } catch (Exception e) {
            System.err.println("ERRO: " + e.getMessage());
        }
    }
    
    
}
