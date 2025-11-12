import java.io.FileNotFoundException;
import java.util.Scanner;

public class Menu {
    public static void listarConteudoFicheiros() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);

        int opcao;

        do {

            System.out.println("\n\n-*-*-*-*-*- ESCOLHA UMA DAS OPÇÕES -*-*-*-*-*-");
            System.out.println("1. Animais");
            System.out.println("2. Clientes");
            System.out.println("3. Interacoes");
            System.out.println("0. Voltar");

            System.out.print("Opção: ");
            opcao = input.nextInt();

            switch (opcao) {

                case 1: // Animais
                    System.out.println("\nAnimais");
                    FuncoesMenuAdmin.imprimirFicheiro("Ficheiros/animais.csv");
                    break;

                case 2: //
                    System.out.println("\nClientes");
                    FuncoesMenuAdmin.imprimirFicheiro("Ficheiros/clientes.csv");
                    break;

                case 3: //
                    System.out.println("\nInteracoes");
                    FuncoesMenuAdmin.imprimirFicheiro("Ficheiros/interacoes.csv");
                    break;

                case 0: // Voltar
                    break;

                default:
                    System.out.println("\nOpção Inválida");
                    break;
            }

        } while (opcao != 0);
    }

    public static void menuAdmin(String caminhoAnimais, String caminhoClientes, String caminhoInteracoes) throws FileNotFoundException {

        Scanner input = new Scanner(System.in);

        int opcao;

        do {


            System.out.println("\n\n-*-*-*-*-*- MENU ADMIN CODESAVANNA -*-*-*-*-*-");
            System.out.println("1. Listar conteúdo dos ficheiros");
            System.out.println("2. Estatísticas gerais de interações");
            System.out.println("3. Receita total por tipo de interação");
            System.out.println("4. Animal mais popular ");
            System.out.println("5. Top 3 espécies com mais apadrinhamentos");
            System.out.println("6. Listar padrinhos de um animal");
            System.out.println("7. Espetáculo mais rentável");
            System.out.println("8. Ranking de animais em perigo de extinção");
            System.out.println("9. Estatísticas por habitat");
            System.out.println("0. Sair");

            System.out.print("Opção: ");
            opcao = input.nextInt();

            switch (opcao) {

                case 1: // Consultar Vendas
                    listarConteudoFicheiros();
                    break;

                case 2: // Pesquisar Clientes
                    System.out.println("\nEstatísticas gerais de interações");
                    FuncoesMenuAdmin.estatisticasInteracoes(caminhoInteracoes);
                    break;

                case 3: //
                    System.out.println("\nReceita total por tipo de interação");
                   FuncoesMenuAdmin.calcularReceitas(caminhoInteracoes);
                    break;

                case 4: // ...
                    break;

                case 5: // ...
                    break;

                case 6: // ...
                    break;

                case 0: //
                    break;

                default:
                    System.out.println("\nOpção Inválida");
                    break;
            }

        } while (opcao != 0);
    }

    public static void menuCliente(String caminhoAnimais, String caminhoClientes, String caminhoInteracoes) {


        Scanner input = new Scanner(System.in);

        int opcao;

        do {

            System.out.println("\n\n-*-*-*-*-*- MENU CLIENTE -*-*-*-*-*-");
            System.out.println("1. Ver catálogo de animais por habitat");
            System.out.println("2. Ver atividades de um animal");
            System.out.println("3. Simular apadrinhamento de um animal");
            System.out.println("4. Jogo:advinha a espécie'");
            System.out.println("0. Sair");

            System.out.print("Opção: ");
            opcao = input.nextInt();

            switch (opcao) {

                case 1: //
                    break;

                case 2: //
                    break;

                case 3: //
                    break;

                case 4: //
                    break;

                case 0: //
                    break;

                default:
                    System.out.println("\nOpção Inválida");
                    break;
            }

        } while (opcao != 0);

    }

    public static void menuLogin(String caminhoAnimais, String caminhoClientes, String caminhoInteracoes) throws FileNotFoundException {

        Scanner input = new Scanner(System.in);

        int opcaoLogin;
        String username, password;

        do {

            System.out.println("\n\n-*-*-*-*-*- MENU LOGIN -*-*-*-*-*-");
            System.out.println("1. ADMIN");
            System.out.println("2. CLIENTE");
            System.out.println("0. SAIR");

            System.out.print("Tipo de Utilizador: ");
            opcaoLogin = input.nextInt();

            switch (opcaoLogin) {

                case 1: // ADMIN

                    System.out.print("\nUsername: ");
                    username = input.next();

                    System.out.print("Password: ");
                    password = input.next();

                    if (username.equals("admin") && password.equals("123")) {
                        // Login válido
                        menuAdmin(caminhoAnimais, caminhoClientes, caminhoInteracoes);
                    } else {
                        System.out.println("Login incorreto");
                    }

                    break;

                case 2: // CLIENTE
                    menuCliente(caminhoAnimais, caminhoClientes, caminhoInteracoes);
                    break;

                case 0: // SAIR
                    System.out.println("\nObrigado! Volte sempre...");
                    break;

                default:
                    System.out.println("\nOpção Inválida");
                    break;
            }

        } while (opcaoLogin != 0);
    }


    public static void main(String[] args) throws FileNotFoundException {

        String caminhoAnimais = "";
        String caminhoClientes = "";
        String caminhoInteracoes = "Ficheiros/interacoes.csv";

        menuLogin(caminhoAnimais, caminhoClientes, caminhoInteracoes);
    }

}
