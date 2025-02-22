package observer;

import java.util.ArrayList;
import java.util.List;
import model.Usuario;

public class Notificador {
    private List<Usuario> observadores = new ArrayList<>();

    public void adicionarObservador(Usuario usuario) {
        observadores.add(usuario);
    }

    public void removerObservador(Usuario usuario) {
        observadores.remove(usuario);
    }

    public void notificarObservadores(String mensagem) {
        for (Usuario usuario : observadores) {
            usuario.receberNotificacao(mensagem);
        }
    }
}
