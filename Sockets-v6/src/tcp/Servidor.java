package tcp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JTextArea;

public class Servidor extends Comunicacao {

    public Servidor(JTextArea txt) {
        super(txt);
    }

    @Override
    public void run() {
        try {

            ServerSocket srv = new ServerSocket(PORTA);
            txt.append("Servidor iniciado\n");

            while (true) {
                txt.append("Aguardando conexão... \n");
                Socket con = srv.accept();

                txt.append("Conexão de "
                        + con.getInetAddress().getHostAddress());

                fluxo_saida = new ObjectOutputStream(con.getOutputStream());
                fluxo_entrada = new ObjectInputStream(con.getInputStream());
                enviar("INFO", "Conexão realizada com sucesso.\n");

                Mensagem msg = null;
                do {
                    msg = (Mensagem) fluxo_entrada.readObject();
                    txt.append(msg.toString());
                } while (!msg.getTitulo().toUpperCase().equals("\nFIM"));

                fluxo_entrada.close();
                fluxo_saida.close();
                con.close();

            }

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("\nERRO: " + e.getMessage());
        }
    }
}
