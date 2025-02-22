package model;

import java.util.Date;

public abstract class ItemBiblioteca {
    protected String titulo;
    protected String autor;
    protected Date dataDevolucao;

    public ItemBiblioteca(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.dataDevolucao = null;
    }

    public abstract void emprestar(Usuario usuario);

    public abstract void devolver();

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

}
