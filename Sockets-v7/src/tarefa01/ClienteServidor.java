package tarefa01;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import javax.swing.JOptionPane;
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
    
    private boolean isAnoValido(String texto) {
        try {
            int ano = Integer.parseInt(texto);

            if (ano >= 1900 && ano <= 2100) {
                return true;
            } else {
                System.out.println("Ano fora do intervalo desejado: " + ano);
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro de formatação: " + e.getMessage());
            return false; // Se não for um número, não é um ano válido
        }
    }
    
    public static int calcularIdade(int anoNascimento) {
        int anoAtual = LocalDate.now().getYear();
        int idade = anoAtual - anoNascimento;
        return idade;
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

                //System.out.println(msg);
                if (msg.equals("DATA")) {
                    LocalDateTime dataAtual = ClienteServidor.DataAtualDoServidor.obterDataAtual();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");// Definindo o padrão brasileiro
                    //String mensagemData = "Data atual do servidor: " + dataAtual.toString();
                    String dataFormatada = dataAtual.format(formatter);

                    String mensagemData = "Data atual do servidor: " + dataFormatada;
                    enviar(mensagemData);                                  
                    // Formatando a data no padrão brasileiro                          
                    
                }
                if (msg.equals("DATA2")) {                               
                    LocalDateTime dataAtual = LocalDateTime.now();   
                    // Defina um formato com padrão brasileiro e locale em português
                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy 'às' HH:mm:ss", new Locale("pt", "BR"));
                    // Formate a data por extenso
                    String dataPorExtenso = dataAtual.format(formato);
                    String mensagemData = "Data atual do servidor: " + dataPorExtenso;
                    enviar(mensagemData);
                    
                }
                if (msg.equals("HORA")) {                               
                    LocalDateTime dataAtual = LocalDateTime.now();
                    // Defina um formato para a hora (HH:mm:ss)
                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm:ss");
                    // Formate apenas a hora
                    String horaFormatada = dataAtual.format(formato);
                    String mensagemHora = "Hora atual do servidor: " + horaFormatada;
                    enviar(mensagemHora);                 
                }
                if(msg.equals("DIAS-RESTANTES")){
                    LocalDate dataAtual = LocalDate.now();
                    // Obtém a data do último dia do ano atual
                    LocalDate ultimoDiaDoAno = LocalDate.of(dataAtual.getYear(), 12, 31);

                    // Calcula os dias restantes até o fim do ano
                    long diasRestantes = ChronoUnit.DAYS.between(dataAtual, ultimoDiaDoAno);
                    String texto = String.valueOf(diasRestantes);
                    enviar(texto);
                    // Exibe a quantidade de dias restantes
                    
                    } 
                if(!msg.isEmpty()){
                   if(!msg.isEmpty()){
                    if (isAnoValido(msg)) {
                     // Obtém a data atual
                     int anoAtual = LocalDate.now().getYear(); 
                     int anoNascimento = Integer.parseInt(msg);  
                     int temp = anoAtual - anoNascimento;
                     // Calcula a diferença entre as datas
                       //Period periodo = Period.between(anoNascimento, anoAtual);                              
                       String idade = String.valueOf(temp);
                       enviar("Você tem " + idade + " anos.");                     
                            
                        } else {
                           // Exibe uma mensagem de erro ou lida com a entrada inválida de outra forma
                           System.err.println("ERRO: " );
                            
                        }


                    }
                } 
                
                txt.append("\n");
                txt.append("\nMsg: " + msg);
                txt.append("\nDe.: " + pct.getAddress().getHostAddress());
                        }
                } catch (IOException e) {
                System.err.println("ERRO: " + e.getMessage());
        }
    }

    static class DataAtualDoServidor {

        public static LocalDateTime obterDataAtual() {
            return LocalDateTime.now(); // Isso irá criar uma instância de LocalDateTime representando a data e hora atuais do servidor.
        }
    }

}
