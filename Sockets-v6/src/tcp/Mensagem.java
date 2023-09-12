
package tcp;

import java.io.Serializable;

/*
  Serializable = transforma o objeto em um conjunto
                 de bytes para ser transmitido pela 
                 rede.
*/
public class Mensagem implements Serializable{
  
    private int id;
    private String titulo;
    private String conteudo;

    public Mensagem() {
    }

    public Mensagem(int id, String titulo, String conteudo) {
        this.id = id;
        this.titulo = titulo;
        this.conteudo = conteudo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    @Override
    public String toString() {
        return "\nId: " + getId() + 
               "\nTítulo: " + getTitulo() +
               "\nConteúdo: " + getConteudo() + "\n";
    }
       
}
