package br.escola.bibliohaydee.app;

import br.escola.bibliohaydee.model.Usuario;
import java.util.ArrayList;
import java.util.Scanner;
import br.escola.bibliohaydee.model.Livro;
import br.escola.bibliohaydee.model.Autor;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Usuario> usuarios = new ArrayList<>();
        ArrayList<Livro> livros = new ArrayList<>();
        cadastrarLivrosTeste(livros);

        int opcao;

        do {
            System.out.println("\n=== BIBLIOTECA BIBLIOHAYDEE ===");
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Listar usuários");
            System.out.println("3 - Pesquisar livro por título");
            System.out.println("4 - Pesquisar livro por autor");
            System.out.println("5 - Gerar Relatório de Acervo");
            System.out.println("0 - Sair");
            try {
                opcao = Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException erro) {
                opcao = -1;
            }

            switch (opcao) {

                case 1:
                    cadastrarUsuario(scanner, usuarios);
                    break;

                case 2:
                    listarUsuarios(usuarios);
                    break;

                case 3:
                    pesquisarPorTitulo(scanner, livros);
                    break;

                case 4:
                    pesquisarPorAutor(scanner, livros);
                    break;

                case 5:
                    gerarRelatorio(livros);
                    break;

                case 0:
                    System.out.println("Programa encerrado.");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        scanner.close();
    }

    public static void cadastrarUsuario(
            Scanner scanner, ArrayList<Usuario> usuarios) {

        String nome;
        String matricula;
        String tipo;
        Integer limite;

        // Solicitar um nome válido
        while (true) {
            System.out.print("Nome: ");
            nome = scanner.nextLine();

            if (nome.trim().isEmpty()) {
                System.out.println(
                        "Erro: o nome não pode ser vazio. Tente novamente."
                );
            } else {
                break;
            }
        }

        System.out.print("Matrícula: ");
        matricula = scanner.nextLine();

        // Solicitar um tipo válido
        while (true) {
            System.out.print("Tipo (ALUNO ou PROFESSOR): ");
            tipo = scanner.nextLine();

            if (tipo.equalsIgnoreCase("ALUNO") ||
                    tipo.equalsIgnoreCase("PROFESSOR")) {
                break;
            }

            System.out.println(
                    "Erro: digite ALUNO ou PROFESSOR."
            );
        }

        // Solicitar um limite válido
        while (true) {
            System.out.print("Limite de empréstimos: ");

            try {
                limite = Integer.parseInt(scanner.nextLine());

                if (limite < 0) {
                    System.out.println(
                            "O limite não pode ser negativo."
                    );
                    continue;
                }

                break;

            } catch (NumberFormatException erro) {
                System.out.println("Digite um número inteiro válido.");
            }
        }

        // Criar e guardar o usuário
        try {
            Usuario usuario = new Usuario(
                    nome, matricula, tipo, limite
            );

            usuarios.add(usuario);
            System.out.println("Usuário cadastrado com sucesso!");

        } catch (IllegalArgumentException erro) {
            System.out.println("Erro: " + erro.getMessage());
        }
    }

    public static void listarUsuarios(ArrayList<Usuario> usuarios) {

        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        System.out.println("\n=== USUÁRIOS CADASTRADOS ===");

        for (Usuario usuario : usuarios) {
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("Matrícula: " + usuario.getMatricula());
            System.out.println("Tipo: " + usuario.getTipo());
            System.out.println(
                    "Limite de empréstimos: "
                            + usuario.getLimiteEmprestimos()
            );
            System.out.println(
                    "Empréstimos ativos: "
                            + usuario.getEmprestimosAtivos()
            );
            System.out.println("-------------------------");
        }
    }
    public static void pesquisarPorAutor(
            Scanner scanner, ArrayList<Livro> livros) {

        System.out.print("Digite o nome ou sobrenome do autor: ");
        String termo = scanner.nextLine();

        if (termo.trim().isEmpty()) {
            System.out.println("Digite um nome válido!");
            return;
        }

        termo = termo.trim().toLowerCase();

        boolean encontrou = false;

        for (Livro livro : livros) {

            String nomeAutor = livro.getAutor().getNome()
                    .trim().toLowerCase();

            boolean corresponde = nomeAutor.equals(termo);

            String[] partesNome = nomeAutor.split("\\s+");

            for (String parte : partesNome) {
                if (parte.equals(termo)) {
                    corresponde = true;
                }
            }

            if (corresponde) {
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Autor: " + livro.getAutor().getNome());
                System.out.println("-------------------------");

                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum livro encontrado para esse autor.");
        }
    }

    public static void pesquisarPorTitulo(
            Scanner scanner, ArrayList<Livro> livros) {

        System.out.print("Digite o título ou parte dele: ");
        String termo = scanner.nextLine();

        if (termo.trim().isEmpty()) {
            System.out.println("Digite um título válido!");
            return;
        }

        termo = termo.trim().toLowerCase();

        boolean encontrou = false;

        for (Livro livro : livros) {

            String titulo = livro.getTitulo().trim().toLowerCase();

            if (titulo.contains(termo)) {
                System.out.println("Livro encontrado: " + livro.getTitulo());
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum livro encontrado!");
        }
    }


    public static void cadastrarLivrosTeste(ArrayList<Livro> livros) {

        Autor machado = new Autor("Machado de Assis", "Brasileira", 1839);
        Autor graciliano = new Autor("Graciliano Ramos", "Brasileira", 1892);
        Autor aluisio = new Autor("Aluísio Azevedo", "Brasileira", 1857);
        Autor jorge = new Autor("Jorge Amado", "Brasileira", 1912);

        livros.add(new Livro("Dom Casmurro", "001", machado, 1899, "Romance"));
        livros.add(new Livro("Memórias Póstumas de Brás Cubas", "002", machado, 1881, "Romance"));
        livros.add(new Livro("Quincas Borba", "003", machado, 1891, "Romance"));
        livros.add(new Livro("Vidas Secas", "004", graciliano, 1938, "Romance"));
        livros.add(new Livro("O Cortiço", "005", aluisio, 1890, "Romance"));
        livros.add(new Livro("Capitães da Areia", "006", jorge, 1937, "Romance"));

        System.out.println("Livros de teste cadastrados!");
    }
    public static void gerarRelatorio(ArrayList<Livro> livros) {

        StringBuilder relatorio = new StringBuilder();

        relatorio.append("\n=== RELATÓRIO DO ACERVO ===\n");

        if (livros.isEmpty()) {

            relatorio.append("Não há livros cadastrados.\n");

        } else {

            int numero = 1;

            for (Livro livro : livros) {

                relatorio.append("\nLivro ").append(numero).append("\n");

                relatorio.append("Título: ")
                        .append(livro.getTitulo()).append("\n");

                relatorio.append("Autor: ")
                        .append(livro.getAutor().getNome()).append("\n");

                relatorio.append("Disponibilidade: ")
                        .append(livro.isDisponibilidade()
                                ? "Disponível" : "Indisponível")
                        .append("\n");

                relatorio.append("-------------------------\n");

                numero++;
            }

            relatorio.append("\nTotal de livros: ")
                    .append(livros.size()).append("\n");
        }

        System.out.println(relatorio.toString());
    }
}





