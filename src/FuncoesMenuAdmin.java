import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FuncoesMenuAdmin {

    public static void imprimirFicheiro(String caminho) throws FileNotFoundException {

        File ficheiro = new File(caminho);

        Scanner sc = new Scanner(ficheiro);

        while (sc.hasNextLine()) {
            String linha = sc.nextLine();
            System.out.println(linha);
        }
    }

    public static int contarInteracoes(String caminhoInteracoes, String tipoInteracao) throws FileNotFoundException {

        Scanner sc = new Scanner(new File(caminhoInteracoes));

        String linha = sc.nextLine();

        int contador = 0;

        while (sc.hasNextLine()) {
            linha = sc.nextLine();
            String[] itensDaLinha = linha.split(";");

            if (itensDaLinha[2].equals(tipoInteracao)) {
                contador++;
            }
        }
        return contador;
    }

    public static void estatisticasInteracoes(String caminhoInteracoes) throws FileNotFoundException {

        int contadorVisitas = FuncoesMenuAdmin.contarInteracoes(caminhoInteracoes, "VISITA");
        int contadorEspetaculos = FuncoesMenuAdmin.contarInteracoes(caminhoInteracoes, "ESPETACULO");
        int contadorAlimentacao = FuncoesMenuAdmin.contarInteracoes(caminhoInteracoes, "ALIMENTACAO");
        int contadorApadrinhamento = FuncoesMenuAdmin.contarInteracoes(caminhoInteracoes, "APADRINHAMENTO");
        int contadorGeral = contadorVisitas + contadorEspetaculos + contadorAlimentacao + contadorApadrinhamento;

        System.out.println("Total de Interações: " + contadorGeral);
        System.out.println("VISITAS: " + contadorVisitas);
        System.out.println("ESPETACULOS: " + contadorEspetaculos);
        System.out.println("ALIMENTAÇÕES: " + contadorAlimentacao);
        System.out.println("APADRINHAMENTOS: " + contadorApadrinhamento);
    }

    public static double receitaInteracoes(String caminhoInteracoes, String tipoInteracao) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(caminhoInteracoes));
        String linha = sc.nextLine();
        double soma = 0.0;

        while (sc.hasNextLine()) {
            linha = sc.nextLine();
            String[] itensDaLinha = linha.split(";");

            if (tipoInteracao.equals(itensDaLinha[2])) {
                soma += Double.parseDouble(itensDaLinha[5]);
            }
        }
        return soma;
    }

    public static void calcularReceitas(String caminhoInteracoes) throws FileNotFoundException {

        double receitasVisitas = FuncoesMenuAdmin.receitaInteracoes(caminhoInteracoes, "VISITA");
        double receitasEspetaculos = FuncoesMenuAdmin.receitaInteracoes(caminhoInteracoes, "ESPETACULO");
        double receitasAlimentacao = FuncoesMenuAdmin.receitaInteracoes(caminhoInteracoes, "ALIMENTACAO");
        double receitasApadrinhamento = FuncoesMenuAdmin.receitaInteracoes(caminhoInteracoes, "APADRINHAMENTO");
        double contadorGeral = receitasVisitas + receitasEspetaculos + receitasAlimentacao + receitasApadrinhamento;

        System.out.println("Total de Interações: " + contadorGeral);
        System.out.println("VISITAS: " + receitasVisitas);
        System.out.println("ESPETACULOS: " + receitasEspetaculos);
        System.out.println("ALIMENTAÇÕES: " + receitasAlimentacao);
        System.out.println("APADRINHAMENTOS: " + receitasApadrinhamento);
    }

    public static void animalMaisPopular(String caminhoAnimais, String caminhoInteracoes) throws FileNotFoundException {

        Scanner sc = new Scanner(new File(caminhoAnimais));
        sc.nextLine();
        String idPopular = "";
        String nomePopular = "";
        String especiePopular = "";
        String habitatPopular = "";
        int maiorInteracoes = -1;

        while (sc.hasNextLine()) {
            String linha = sc.nextLine();
            String[] itens = linha.split(";");

            String idAnimal = itens[0];
            String nome = itens[1];
            String especie = itens[2];
            String habitat = itens[3];

            int interacoes = contarInteracoesAnimal(caminhoInteracoes, idAnimal);

            if (interacoes > maiorInteracoes) {
                maiorInteracoes = interacoes;
                idPopular = idAnimal;
                nomePopular = nome;
                especiePopular = especie;
                habitatPopular = habitat;
            }
        }

        System.out.println("\n\n-*-*-*-*-*-ANIMAL MAIS POPULAR-*-*-*-*-*-");
        System.out.println("Nome: " + nomePopular);
        System.out.println("Espécie: " + especiePopular);
        System.out.println("Habitat: " + habitatPopular);
        System.out.println("Total de interações: " + maiorInteracoes);
    }

    public static void top3EspeciesApadrinhadas() {

        System.out.println("\n\n-*-*-*-*-*-TOP 3 ESPÉCIES COM MAIS APADRINHAMENTOS-*-*-*-*-*-");
        System.out.println("\nInfelizmente...");
        System.out.println("Depois de várias horas de cálculos altamente complexos,");
        System.out.println("a aluna por mais aplicada que seja se rendeu.");
        System.out.println("\nMas não se preocupe!");
        System.out.println("Todas as espécies são incríveis, então considere todas no TOP 3!");
        System.out.println("\n(Esta funcionalidade será adicionada na versão 2.0 do projeto)");
    }


    public static void procurarPadrinhos(String caminhoInteracoes, String caminhoClientes, String idAnimal) throws FileNotFoundException {

        Scanner sc = new Scanner(new File(caminhoInteracoes));
        sc.nextLine();

        boolean padrinhoEncontrado = false;

        while (sc.hasNextLine()) {
            String linha = sc.nextLine();
            String[] itensDaLinha = linha.split(";");

            if (itensDaLinha[2].equals("APADRINHAMENTO") && itensDaLinha[3].equals(idAnimal)) {

                padrinhoEncontrado = true;

                String idCliente = itensDaLinha[1];
                String plano = itensDaLinha[4];
                String valor = itensDaLinha[5];

                Scanner in = new Scanner(new File(caminhoClientes));
                in.nextLine();

                String nomeCliente = "";
                String emailCliente = "";

                while (in.hasNextLine()) {
                    String linhaCliente = in.nextLine();
                    String[] itensDaLinhaCliente = linhaCliente.split(";");

                    if (itensDaLinhaCliente[0].equals(idCliente)) {
                        nomeCliente = itensDaLinhaCliente[1];
                        emailCliente = itensDaLinhaCliente[3];
                    }
                }
                System.out.println("\n\n-*-*-*-*-*-CLIENTE -*-*-*-*-*-");
                System.out.println("Cliente: " + nomeCliente);
                System.out.println("Email: " + emailCliente);
                System.out.println("Nome do plano de apadrinhamento: " + plano);
                System.out.println("Valor mensal: " + valor);
            }
        }

        if (!padrinhoEncontrado) {
            System.out.println("Este animal não tem apadrinhamentos.");
        }
    }


    public static void listarPadrinhos(String caminhoInteracoes, String caminhoCliente) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        System.out.println("ID do animal: ");
        String idAnimal = input.nextLine();

        Scanner sc = new Scanner(new File(caminhoInteracoes));
        sc.hasNextLine();

        boolean animalExiste = false;

        while (sc.hasNextLine()) {
            String linha = sc.nextLine();
            String[] itensDaLinha = linha.split(";");

            if (itensDaLinha[3].equals(idAnimal)) {
                animalExiste = true;
            }
        }

        if (!animalExiste) {
            System.out.println("Animal não existe!!!.");
        }
        procurarPadrinhos(caminhoInteracoes, caminhoCliente, idAnimal);
    }

    public static void receitaPorEspetaculo(String caminhoInteracoes, String[] nomeEvento, double[] receitaEvento) throws FileNotFoundException {

        Scanner sc = new Scanner(new File(caminhoInteracoes));
        sc.nextLine();

        int totalEventos = 0;

        while (sc.hasNextLine()) {
            String linha = sc.nextLine();
            String[] itens = linha.split(";");

            if (itens[2].equals("ESPETACULO")) {

                String evento = itens[4];
                double valor = Double.parseDouble(itens[5]);

                boolean existe = false;

                for (int i = 0; i < totalEventos; i++) {
                    if (nomeEvento[i].equals(evento)) {
                        receitaEvento[i] += valor;
                        existe = true;
                    }
                }

                if (!existe) {
                    nomeEvento[totalEventos] = evento;
                    receitaEvento[totalEventos] = valor;
                    totalEventos++;
                }
            }
        }
    }

    public static String encontrarAnimalDoEspetaculo(String caminhoInteracoes, String nomeEvento) throws FileNotFoundException {

        Scanner sc = new Scanner(new File(caminhoInteracoes));
        sc.nextLine();

        while (sc.hasNextLine()) {
            String linha = sc.nextLine();
            String[] itens = linha.split(";");

            if (itens[2].equals("ESPETACULO") && itens[4].equals(nomeEvento)) {
                return itens[3];
            }
        }
        return "idAnimal";
    }

    public static void imprimirAnimalPrincipal(String caminhoAnimais, String idAnimal) throws FileNotFoundException {

        Scanner sc = new Scanner(new File(caminhoAnimais));
        sc.nextLine();

        while (sc.hasNextLine()) {
            String linha = sc.nextLine();
            String[] itens = linha.split(";");

            if (itens[0].equals(idAnimal)) {
                System.out.println("- " + itens[1]);
                System.out.println("  Animal principal: " + itens[2]);
                System.out.println();
            }
        }

        System.out.println("Animal não encontrado.");
    }

    public static void espetaculoMaisRentavel(String caminhoInteracoes, String caminhoAnimais) throws FileNotFoundException {

        int totalEspetaculos = contarInteracoes(caminhoInteracoes, "ESPETACULO");

        String[] eventos = new String[totalEspetaculos];
        double[] receitas = new double[totalEspetaculos];

        receitaPorEspetaculo(caminhoInteracoes, eventos, receitas);

        double maiorReceita = receitas[0];
        int indiceMaiorReceita = 0;

        for (int i = 1; i < totalEspetaculos; i++) {
            if (receitas[i] > maiorReceita) {
                maiorReceita = receitas[i];
                indiceMaiorReceita = i;
            }
        }

        String melhorEspetaculo = eventos[indiceMaiorReceita];
        String idAnimal = encontrarAnimalDoEspetaculo(caminhoInteracoes, melhorEspetaculo);

        System.out.println("\n\n-*-*-*-*-*-ESPETACULO MAIS RENTAVEL -*-*-*-*-*-");
        System.out.println("Nome: " + melhorEspetaculo);
        System.out.println("Receita total: " + maiorReceita + "€");

        imprimirAnimalPrincipal(caminhoAnimais, idAnimal);
    }

    public static int animaisExtincao(String caminhoAnimais) throws FileNotFoundException {

        Scanner sc = new Scanner(new File(caminhoAnimais));
        sc.nextLine();

        int contador = 0;

        while (sc.hasNextLine()) {
            String linha = sc.nextLine();
            String[] itensDaLinha = linha.split(";");

            if (itensDaLinha[5].equals("SIM")) {
                contador++;
            }
        }
        return contador;
    }

    public static int contarInteracoesAnimal(String caminhoInteracoes, String idAnimal) throws FileNotFoundException {

        Scanner sc = new Scanner(new File(caminhoInteracoes));
        sc.nextLine();
        int contador = 0;

        while (sc.hasNextLine()) {
            String linha = sc.nextLine();
            String[] itensDaLinha = linha.split(";");

            if (itensDaLinha[3].equals(idAnimal)) {
                contador++;
            }
        }

        return contador;
    }

    public static double somarDinheiroDoAnimal(String caminhoInteracoes, String idAnimal) throws FileNotFoundException {

        Scanner sc = new Scanner(new File(caminhoInteracoes));
        sc.nextLine();

        double soma = 0;

        while (sc.hasNextLine()) {
            String linha = sc.nextLine();
            String[] itensDaLinha = linha.split(";");

            if (itensDaLinha[3].equals(idAnimal)) {
                soma += Double.parseDouble(itensDaLinha[5]);
            }
        }

        return soma;
    }

    public static void listarAnimaisExtincao(
            String caminhoAnimais, String caminhoInteracoes,
            String[] idsAnimais, String[] nomes, String[] especies,
            int[] interacoes, double[] dinheiro) throws FileNotFoundException {

        Scanner sc = new Scanner(new File(caminhoAnimais));
        sc.nextLine();

        int contador = 0;

        while (sc.hasNextLine()) {
            String linha = sc.nextLine();
            String[] itens = linha.split(";");

            if (itens[5].equals("SIM")) {

                String idAnimal = itens[0];

                idsAnimais[contador] = idAnimal;
                nomes[contador] = itens[1];
                especies[contador] = itens[2];

                interacoes[contador] = contarInteracoesAnimal(caminhoInteracoes, idAnimal);
                dinheiro[contador] = somarDinheiroDoAnimal(caminhoInteracoes, idAnimal);

                contador++;
            }
        }
    }

    public static void mostrarRankingExtincao(String caminhoAnimais, String caminhoInteracoes) throws FileNotFoundException {

        int total = animaisExtincao(caminhoAnimais);

        String[] ids = new String[total];
        String[] nomes = new String[total];
        String[] especies = new String[total];
        int[] interacoes = new int[total];
        double[] dinheiro = new double[total];

        listarAnimaisExtincao(caminhoAnimais, caminhoInteracoes, ids, nomes, especies, interacoes, dinheiro);

        System.out.println("\n\n-*-*-*-*-*-ANIMAIS EM PERIGO DE EXTINCAO -*-*-*-*-*-");

        for (int i = 0; i < total; i++) {
            System.out.println("\nAnimal: " + nomes[i] + " (" + especies[i] + ")");
            System.out.println("ID: " + ids[i]);
            System.out.println("Interações: " + interacoes[i]);
            System.out.println("Total: " + dinheiro[i] + "€");
        }
    }
    public static int contarAnimaisDoHabitat(String caminhoAnimais, String habitat) throws FileNotFoundException {

        Scanner sc = new Scanner(new File(caminhoAnimais));
        sc.nextLine();

        int contador = 0;

        while (sc.hasNextLine()) {
            String linha = sc.nextLine();
            String[] itensDaLinha = linha.split(";");

            if (itensDaLinha[3].equals(habitat)) {
                contador++;
            }
        }
        return contador;
    }
    public static int contarInteracoesHabitat(String caminhoAnimais, String caminhoInteracoes, String habitat) throws FileNotFoundException {

        Scanner sc = new Scanner(new File(caminhoAnimais));
        sc.nextLine();

        int totalInteracoes = 0;

        while (sc.hasNextLine()) {
            String linha = sc.nextLine();
            String[] itens = linha.split(";");

            if (itens[3].equals(habitat)) {
                String idAnimal = itens[0];
                totalInteracoes += contarInteracoesAnimal(caminhoInteracoes, idAnimal);
            }
        }
        return totalInteracoes;
    }
    public static double somarReceitaHabitat(String caminhoAnimais, String caminhoInteracoes, String habitat) throws FileNotFoundException {

        Scanner sc = new Scanner(new File(caminhoAnimais));
        sc.nextLine();

        double total = 0;

        while (sc.hasNextLine()) {
            String linha = sc.nextLine();
            String[] itens = linha.split(";");

            if (itens[3].equals(habitat)) {
                String idAnimal = itens[0];
                total += somarDinheiroDoAnimal(caminhoInteracoes, idAnimal);
            }
        }
        return total;
    }
    public static void estatisticasPorHabitat(String caminhoAnimais, String caminhoInteracoes) throws FileNotFoundException {

        Scanner sc = new Scanner(new File(caminhoAnimais));
        sc.nextLine();

        System.out.println("\n\n-*-*-*-*-*-ESTATISTICA POR HABITAT-*-*-*-*-*-");

        while (sc.hasNextLine()) {
            String linha = sc.nextLine();
            String[] itens = linha.split(";");

            String habitat = itens[3];

            int animais = contarAnimaisDoHabitat(caminhoAnimais, habitat);
            int interacoes = contarInteracoesHabitat(caminhoAnimais, caminhoInteracoes, habitat);
            double receita = somarReceitaHabitat(caminhoAnimais, caminhoInteracoes, habitat);

            System.out.println("\nHabitat: " + habitat);
            System.out.println("Numero de animais: " + animais);
            System.out.println("Numero de interações: " + interacoes);
            System.out.println("Receita: " + receita + "€");
        }
    }
}