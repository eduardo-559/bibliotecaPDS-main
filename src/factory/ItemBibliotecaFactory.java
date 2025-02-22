package factory;

import model.ItemBiblioteca;
import model.Livro;
import model.Revista;

public class ItemBibliotecaFactory {
    public static ItemBiblioteca criarItem(String tipo, String titulo, String autor, String extra) {
        try {
            switch (tipo.toLowerCase()) {
                case "livro":
                    return new Livro(titulo, autor, extra); // extra é o ISBN
                case "revista":
                    return new Revista(titulo, autor, Integer.parseInt(extra)); // extra é a edição
                default:
                    throw new IllegalArgumentException("Tipo de item inválido");
            }  
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Extra inválido para o tipo " + tipo + ": " + extra);
        }
        
    }
}
