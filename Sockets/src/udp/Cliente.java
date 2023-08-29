package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Cliente {
    
    private final String HOST = "172.25.3.147";
    private final int PORTA = 12345;
    
    public Cliente(){
    }
    
    public void enviar(String msg){
        try {
            //converter a mensagem em bytes
            byte[] buffer = msg.getBytes();
            
            //criar o pacote da mensagem
            DatagramPacket pct = new DatagramPacket(
                buffer,
                buffer.length,
                InetAddress.getByName(HOST),
                PORTA
            );
            
            //enviar a mensagem
            new DatagramSocket().send(pct);
            
        } catch (Exception e) {
            System.err.println("ERRO: " + e.getMessage());
        }
    }
    
}
