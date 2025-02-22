import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.ItemBiblioteca;
import model.Usuario;
import strategy.MultaPadrao;
import strategy.MultaProgressiva;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = Biblioteca.getInstance();
        Scanner scanner = new Scanner(System.in);
        boolean sair = false;

        System.out.println("Bem-vindo à Biblioteca Interativa!");

        while (!sair) {
            System.out.println("\n--- Menu da Biblioteca ---");
            System.out.println("1. Registrar novo usuário");
            System.out.println("2. Adicionar novo item");
            System.out.println("3. Emprestar item");
            System.out.println("4. Devolver item");
            System.out.println("5. Notificar usuários");
            System.out.println("6. Listar itens");
            System.out.println("7. Listar usuários");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            int opcao;
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida! Por favor, digite um número.");
                continue;
            }
            
            switch (opcao) {
                case 1:
                    // Registrar novo usuário
                    System.out.print("Digite o nome do usuário: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o email do usuário: ");
                    String email = scanner.nextLine();
                    Usuario usuario = new Usuario(nome, email);
                    biblioteca.registrarUsuario(usuario);
                    System.out.println("Usuário registrado com sucesso!");
                    break;
                case 2:
                    // Adicionar novo item
                    System.out.print("Digite o tipo do item (livro ou revista): ");
                    String tipo = scanner.nextLine();
                    System.out.print("Digite o título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Digite o autor: ");
                    String autor = scanner.nextLine();
                    System.out.print("Digite o extra (ISBN para livro ou edição para revista): ");
                    String extra = scanner.nextLine();
                    try {
                        biblioteca.adicionarItem(tipo, titulo, autor, extra);
                        System.out.println("Item adicionado com sucesso!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro ao adicionar item: " + e.getMessage());
                    }
                    break;
                case 3:
                    // Emprestar item: listar apenas os itens disponíveis
                    List<ItemBiblioteca> availableItems = new ArrayList<>();
                    for (ItemBiblioteca item : biblioteca.getItems()) {
                        if (item.getDataDevolucao() == null) {
                            availableItems.add(item);
                        }
                    }
                    if (availableItems.isEmpty()) {
                        System.out.println("Nenhum item disponível para empréstimo.");
                        break;
                    }
                    System.out.println("Itens disponíveis para empréstimo:");
                    for (int i = 0; i < availableItems.size(); i++) {
                        ItemBiblioteca item = availableItems.get(i);
                        System.out.println(i + ". " + item.getTitulo());
                    }
                    System.out.print("Escolha o número do item para emprestar: ");
                    int indiceItem;
                    try {
                        indiceItem = Integer.parseInt(scanner.nextLine());
                        if (indiceItem < 0 || indiceItem >= availableItems.size()) {
                            System.out.println("Índice de item inválido.");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Índice inválido.");
                        break;
                    }
                    // Selecionar usuário para o empréstimo
                    List<Usuario> usuarios = biblioteca.getUsuarios();
                    if (usuarios.isEmpty()) {
                        System.out.println("Nenhum usuário registrado para efetuar o empréstimo.");
                        break;
                    }
                    System.out.println("Usuários registrados:");
                    for (int i = 0; i < usuarios.size(); i++) {
                        Usuario u = usuarios.get(i);
                        System.out.println(i + ". " + u.getNome());
                    }
                    System.out.print("Escolha o número do usuário: ");
                    int indiceUsuario;
                    try {
                        indiceUsuario = Integer.parseInt(scanner.nextLine());
                        if (indiceUsuario < 0 || indiceUsuario >= usuarios.size()) {
                            System.out.println("Índice de usuário inválido.");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Índice inválido.");
                        break;
                    }
                    biblioteca.emprestarItem(availableItems.get(indiceItem), usuarios.get(indiceUsuario));
                    break;
                case 4:
                    List<ItemBiblioteca> emprestados = new ArrayList<>();
                    for (ItemBiblioteca item : biblioteca.getItems()) {
                        if (item.getDataDevolucao() != null) {
                            emprestados.add(item);
                        }
                    }
                
                    if (emprestados.isEmpty()) {
                        System.out.println("Não há itens emprestados para devolver.");
                        break;
                    }
                
                    System.out.println("Itens emprestados:");
                    for (int i = 0; i < emprestados.size(); i++) {
                        ItemBiblioteca item = emprestados.get(i);
                        System.out.println(i + ". " + item.getTitulo());
                    }
                
                    System.out.print("Escolha o número do item para devolver: ");
                    int indiceDevolver;
                    try {
                        indiceDevolver = Integer.parseInt(scanner.nextLine());
                        if (indiceDevolver < 0 || indiceDevolver >= emprestados.size()) {
                            System.out.println("Índice de item inválido.");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Índice inválido.");
                        break;
                    }
                
                    ItemBiblioteca itemSelecionado = emprestados.get(indiceDevolver);
                
                    System.out.print("Houve atraso na devolução? (s/n): ");
                    String resposta = scanner.nextLine().trim().toLowerCase();
            
                    if (resposta.equals("s")) {
                        System.out.println("Escolha a estratégia de multa:");
                        System.out.println("1. Multa Padrão (R$ 1,00 por dia)");
                        System.out.println("2. Multa Progressiva (R$ 0,50 * dias²)");
                        System.out.print("Opção: ");
                        
                        int escolhaMulta;
                        try {
                            escolhaMulta = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Opção inválida!");
                            break;
                        }
                
                        switch (escolhaMulta) {
                            case 1:
                                biblioteca.definirEstrategiaMulta(new MultaPadrao());
                                System.out.println("Estratégia de multa definida para Multa Padrão.");
                                break;
                            case 2:
                                biblioteca.definirEstrategiaMulta(new MultaProgressiva());
                                System.out.println("Estratégia de multa definida para Multa Progressiva.");
                                break;
                            default:
                                System.out.println("Opção inválida.");
                                break;
                        }
                        double multa = biblioteca.calcularMulta(itemSelecionado);
                        System.out.printf("Multa aplicada: R$ %.2f\n", multa);
                    }
                
                    biblioteca.devolverItem(itemSelecionado);
                    System.out.println("Item devolvido com sucesso!");
                    break;
                case 5:
                    // Notificar usuários
                    System.out.print("Digite a mensagem a ser enviada aos usuários: ");
                    String mensagem = scanner.nextLine();
                    biblioteca.notificarUsuarios(mensagem);
                    break;
                case 6:
                    // Listar todos os itens
                    List<ItemBiblioteca> todosItens = biblioteca.getItems();
                    if (todosItens.isEmpty()) {
                        System.out.println("Nenhum item cadastrado.");
                    } else {
                        System.out.println("Itens cadastrados:");
                        for (int i = 0; i < todosItens.size(); i++) {
                            ItemBiblioteca item = todosItens.get(i);
                            String status = (item.getDataDevolucao() == null) ? "Disponível" : "Emprestado";
                            System.out.println(i + ". " + item.getTitulo() + " (" + status + ")");
                        }
                    }
                    break;
                case 7:
                    // Listar todos os usuários
                    List<Usuario> todosUsuarios = biblioteca.getUsuarios();
                    if (todosUsuarios.isEmpty()) {
                        System.out.println("Nenhum usuário registrado.");
                    } else {
                        System.out.println("Usuários registrados:");
                        for (int i = 0; i < todosUsuarios.size(); i++) {
                            Usuario u = todosUsuarios.get(i);
                            System.out.println(i + ". " + u.getNome() + " - " + u.getEmail());
                        }
                    }
                    break;
                case 0:
                    sair = true;
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }
        scanner.close();
    }
} 