import factory.ItemBibliotecaFactory;
import model.ItemBiblioteca;
import model.Usuario;
import observer.Notificador;
import strategy.IMultaStrategy;
import strategy.MultaPadrao;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private static Biblioteca instance;
    private List<ItemBiblioteca> items;
    private List<Usuario> usuarios;
    private Notificador notificador;
    private IMultaStrategy estrategiaMulta;

    private Biblioteca() {
        items = new ArrayList<ItemBiblioteca>();
        usuarios = new ArrayList<Usuario>();
        notificador = new Notificador();
        estrategiaMulta = new MultaPadrao();
    }

    public static Biblioteca getInstance() {
        if (instance == null) {
            instance = new Biblioteca();
        }
        return instance;
    }

    public void adicionarItem(String tipo, String titulo, String autor, String extra) {
        ItemBiblioteca item = ItemBibliotecaFactory.criarItem(tipo, titulo, autor, extra);
        items.add(item);
    }

    public void removerItem(ItemBiblioteca item) {
        items.remove(item);
    }

    public void registrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        notificador.adicionarObservador(usuario);
    }

    public void notificarUsuarios(String mensagem) {
        notificador.notificarObservadores(mensagem);
    }

    public void emprestarItem(ItemBiblioteca item, Usuario usuario) {
        try {
            item.emprestar(usuario);
        } catch(Exception e) {
            System.out.println("Erro ao emprestar item: " + e.getMessage());
        }
    }

    public void devolverItem(ItemBiblioteca item) {
        try {
            boolean found = false;
            for(Usuario u : usuarios) {
                if(u.getItensEmprestados().contains(item)) {
                    u.getItensEmprestados().remove(item);
                    item.devolver();
                    found = true;
                    break;
                }
            }
            if(!found) {
                throw new IllegalStateException("Item não está emprestado a nenhum usuário.");
            }
        } catch(Exception e) {
            System.out.println("Erro ao devolver item: " + e.getMessage());
        }
    }

    public void definirEstrategiaMulta(IMultaStrategy estrategia) {
        this.estrategiaMulta = estrategia;
    }

    public double calcularMulta(ItemBiblioteca item) {
        if (item.getDataDevolucao() == null) {
            return 0;
        }

        LocalDate hoje = LocalDate.now();
        LocalDate dataDevolucao = item.getDataDevolucao().toInstant()
                                      .atZone(java.time.ZoneId.systemDefault())
                                      .toLocalDate();
        
        long diasAtraso = ChronoUnit.DAYS.between(dataDevolucao, hoje);

        if (diasAtraso > 0) {
            return estrategiaMulta.calcularMulta((int) diasAtraso);
        }
        return 0;
    }

    public List<ItemBiblioteca> getItems() {
        return items;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    
}

