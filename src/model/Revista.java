package model;

import java.util.Calendar;

public class Revista extends ItemBiblioteca {
    private int edicao;

    public Revista(String titulo, String autor, int edicao) {
        super(titulo, autor);
        this.edicao = edicao;
    }

    @Override
    public void emprestar(Usuario usuario) {
        if (this.dataDevolucao != null) {
            throw new IllegalStateException("Revista já emprestada: " + this.titulo);
        }
        // Define prazo de 7 dias para devolução
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 7);
        this.dataDevolucao = c.getTime();
        usuario.getItensEmprestados().add(this);
        System.out.println("Revista emprestada: " + this.titulo + " para " + usuario.getNome());
    }

    @Override
    public void devolver() {
        if (this.dataDevolucao == null) {
            throw new IllegalStateException("Revista não está emprestada: " + this.titulo);
        }
        this.dataDevolucao = null;
        System.out.println("Revista devolvida: " + this.titulo);

    }

    public int getEdicao() {
        return edicao;
    }
}
