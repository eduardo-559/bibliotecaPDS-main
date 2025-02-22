package model;

import java.util.Calendar;

public class Livro extends ItemBiblioteca {
    private String isbn;

    public Livro(String titulo, String autor, String isbn) {
        super(titulo, autor);
        this.isbn = isbn;
    }

    @Override
    public void emprestar(Usuario usuario) {
        if (this.dataDevolucao != null) {
            throw new IllegalStateException("Livro já emprestado: " + this.titulo);
        }
        // Define prazo de 7 dias para devolução
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 7);
        this.dataDevolucao = c.getTime();
        usuario.getItensEmprestados().add(this);
        System.out.println("Livro emprestado: " + this.titulo + " para " + usuario.getNome());
    }

    @Override
    public void devolver() {
        if (this.dataDevolucao == null) {
            throw new IllegalStateException("Livro não está emprestado: " + this.titulo);
        }
        this.dataDevolucao = null;
        System.out.println("Livro devolvido: " + this.titulo);
    }

    public String getIsbn() {
        return isbn;
    }
}
