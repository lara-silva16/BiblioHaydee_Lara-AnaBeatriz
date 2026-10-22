

package br.escola.bibliohaydee.app;

import br.escola.bibliohaydee.model.Usuario;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Usuario> usuarios = new ArrayList<>();

        int opcao;

        do {
            System.out.println("\n=== BIBLIOTECA BIBLIOHAYDEE ===");
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Listar usuários");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

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
}

