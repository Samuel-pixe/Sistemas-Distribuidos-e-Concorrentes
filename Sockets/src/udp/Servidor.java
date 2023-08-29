package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Servidor extends Thread {
    
    private final int PORTA = 12345;
    
    public Servidor(){
        System.out.println(
            "Servidor iniciado na porta " + PORTA
        );
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
                System.out.println("\nMsg: " + msg);
                System.out.println("De.: " + pct.getAddress().getHostAddress());
                //System.out.println("De.: " + pct.getAddress().getHostName());
            }
            
        } catch (Exception e) {
            System.err.println("ERRO: " + e.getMessage());
        }
    }
   
    public static void main(String[] args) {
        Servidor svr = new Servidor ();
        svr.start();
    }
}
