package udp.bidirecional;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.swing.JTextArea;

public class ClienteServidor extends Thread {

    private final String HOST = "127.0.0.1";
    private final int PORTA_ENVIAR;
    private final int PORTA_RECEBER;
    private final JTextArea txt;

    public ClienteServidor(JTextArea txt, int PORTA_ENVIAR, int PORTA_RECEBER) {
        this.txt = txt;
        this.PORTA_ENVIAR = PORTA_ENVIAR;
        this.PORTA_RECEBER = PORTA_RECEBER;
        txt.append("\nAplicação executando na porta " + PORTA_RECEBER);
    }

    public void enviar(String msg) {
        try {
            //converter a mensagem em bytes
            byte[] buffer = msg.getBytes();

            //criar o pacote da mensagem
            DatagramPacket pct = new DatagramPacket(
                    buffer,
                    buffer.length,
                    InetAddress.getByName(HOST),
                    PORTA_ENVIAR
            );

            //enviar a mensagem
            new DatagramSocket().send(pct);

        } catch (IOException e) {
            System.err.println("ERRO: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            DatagramSocket s = new DatagramSocket(PORTA_RECEBER);
            while (true) {
                byte[] buffer = new byte[256];
                DatagramPacket pct = new DatagramPacket(
                        buffer, buffer.length
                );
                s.receive(pct);
                String msg = new String(pct.getData()).trim();
                txt.append("\n");
                txt.append("\nMsg: " + msg);
                txt.append("\nDe.: " + pct.getAddress().getHostAddress());
            }
        } catch (IOException e) {
            System.err.println("ERRO: " + e.getMessage());
        }
    }

}
