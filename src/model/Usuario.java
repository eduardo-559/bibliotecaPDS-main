package model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    private String email;
    private List<ItemBiblioteca> itensEmprestados;

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.itensEmprestados = new ArrayList<ItemBiblioteca>();
    }

    public void receberNotificacao(String mensagem){
        System.out.println("Notificação para "+ nome +": " + mensagem);
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public List<ItemBiblioteca> getItensEmprestados() {
        return itensEmprestados;
    }
    public void setItensEmprestados(List<ItemBiblioteca> items) {
        this.itensEmprestados = items;
    }
}
