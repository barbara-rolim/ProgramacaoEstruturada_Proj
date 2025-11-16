import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class FuncoesMenuCliente {
    public static String nomeAnimal(String caminhoAnimais, String idAnimal) throws FileNotFoundException {

        Scanner sc = new Scanner(new File(caminhoAnimais));
        sc.nextLine();

        while (sc.hasNextLine()) {
            String linha = sc.nextLine();
            String[] itens = linha.split(";");

            if (itens[0].equals(idAnimal)) {
                return itens[1];
            }
        }
        return "animalDesconhecido";
    }

    public static String obterEspecieAnimal(String caminhoAnimais, String idAnimal) throws FileNotFoundException {

        Scanner sc = new Scanner(new File(caminhoAnimais));
        sc.nextLine();

        while (sc.hasNextLine()) {
            String linha = sc.nextLine();
            String[] itens = linha.split(";");

            if (itens[0].equals(idAnimal)) {
                return itens[2];
            }
        }
        return "especieAnimal";
    }


    public static int contarHabitats(String caminhoAnimais, String[] habitats) throws FileNotFoundException {

        Scanner sc = new Scanner(new File(caminhoAnimais));
        sc.nextLine();

        int totalHabitats = 0;

        while (sc.hasNextLine()) {
            String linha = sc.nextLine();
            String[] itens = linha.split(";");

            String habitat = itens[3];

            boolean existe = false;

            for (int i = 0; i < totalHabitats; i++) {
                if (habitats[i].equals(habitat)) {
                    existe = true;
                }
            }

            if (!existe) {
                habitats[totalHabitats] = habitat;
                totalHabitats++;
            }
        }

        return totalHabitats;
    }

    public static int listarAnimaisDeHabitat(String caminhoAnimais, String habitat) throws FileNotFoundException {

        Scanner sc = new Scanner(new File(caminhoAnimais));
        sc.nextLine();

        while (sc.hasNextLine()) {
            String linha = sc.nextLine();
            String[] itens = linha.split(";");

            if (itens[3].equals(habitat)) {
                System.out.println("- " + itens[1]);
                System.out.println("  Espécie: " + itens[2]);
                System.out.println();
            }
        }
        return 0;
    }

    public static void catalogoPorHabitat(String caminhoAnimais, String caminhoInteracoes) throws FileNotFoundException {

        String[] habitats = new String[100]; // tamanho seguro precisei pesquisar
        int totalHabitats = contarHabitats(caminhoAnimais, habitats);

        System.out.println("\n\n-*-*-*-*-*-CATÁLOGO POR HABITAT-*-*-*-*-*-");

        for (int i = 0; i < totalHabitats; i++) {
            System.out.println("\n*** " + habitats[i] + " ***");
            listarAnimaisDeHabitat(caminhoAnimais, habitats[i]);
        }
    }

    public static boolean animalExiste(String caminhoAnimais, String idAnimal) throws FileNotFoundException {

        Scanner sc = new Scanner(new File(caminhoAnimais));
        sc.nextLine();

        while (sc.hasNextLine()) {
            String linha = sc.nextLine();
            String[] itens = linha.split(";");

            if (itens[0].equals(idAnimal)) {
                return true;
            }
        }
        return false;
    }

    public static int contarAtividades(String caminhoInteracoes, String idAnimal, String tipo) throws FileNotFoundException {

        Scanner sc = new Scanner(new File(caminhoInteracoes));
        sc.nextLine();

        int contador = 0;

        while (sc.hasNextLine()) {
            String linha = sc.nextLine();
            String[] itens = linha.split(";");

            if (itens[3].equals(idAnimal) && itens[2].equals(tipo)) {
                contador++;
            }
        }
        return contador;
    }

    public static void listarNomesAtividades(String caminhoInteracoes, String idAnimal, String tipo) throws FileNotFoundException {

        Scanner sc = new Scanner(new File(caminhoInteracoes));
        sc.nextLine();

        while (sc.hasNextLine()) {
            String linha = sc.nextLine();
            String[] itens = linha.split(";");

            if (itens[3].equals(idAnimal) && itens[2].equals(tipo)) {
                System.out.println("- " + itens[4]);
            }
        }
    }

    public static void verAtividadesAnimal(String caminhoAnimais, String caminhoInteracoes) throws FileNotFoundException {

        Scanner input = new Scanner(System.in);
        System.out.println("ID do animal: ");
        String idAnimal = input.nextLine();

        if (!animalExiste(caminhoAnimais, idAnimal)) {
            System.out.println("Animal não existe!!!.");
            return;
        }

        String nomeAnimal = nomeAnimal(caminhoAnimais, idAnimal);

        System.out.println("\n\n-*-*-*-*-*-ATIVIDADES DO ANIMAL -*-*-*-*-*-");
        System.out.println("Animal: " + idAnimal + " - " + nomeAnimal);

        int totalEspetaculo = contarAtividades(caminhoInteracoes, idAnimal, "ESPETACULO");
        int totalAlimentacao = contarAtividades(caminhoInteracoes, idAnimal, "ALIMENTACAO");

        System.out.println("\nEspetáculos (" + totalEspetaculo + "):");
        listarNomesAtividades(caminhoInteracoes, idAnimal, "ESPETACULO");

        System.out.println("\nAlimentações (" + totalAlimentacao + "):");
        listarNomesAtividades(caminhoInteracoes, idAnimal, "ALIMENTACAO");

        if (totalEspetaculo + totalAlimentacao == 0) {
            System.out.println("\nEste animal não tem atividades.");
        }
    }

    public static String determinarPlano(double valor) {

        if (valor <= 25.00) {
            return "Simples";
        }
        else if (valor <= 50.00) {
            return "Gold";
        }
        else {
            return "Diamond";
        }
    }

    public static void imprimirResumoApadrinhamento(
            String nomeCliente,
            String email,
            String nomeAnimal,
            String especieAnimal,
            double valor,
            String plano) {

        System.out.println("\n\n-*-*-*-*-*-RESUMO DO APADRINHAMENTO -*-*-*-*-*-");
        System.out.println("Padrinho: " + nomeCliente);
        System.out.println("Email: " + email);

        System.out.println("\nAnimal escolhido:");
        System.out.println("- " + nomeAnimal + " (" + especieAnimal + ")");

        System.out.println("\nValor mensal: " + valor + " €");
        System.out.println("Plano atribuído: " + plano);
    }

    public static void simularApadrinhamento(String caminhoAnimais) throws FileNotFoundException {

        Scanner input = new Scanner(System.in);

        System.out.println("Nome do cliente: ");
        String nomeCliente = input.nextLine();

        System.out.println("Email do cliente: ");
        String email = input.nextLine();

        System.out.println("ID do animal: ");
        String idAnimal = input.nextLine();

        if (!animalExiste(caminhoAnimais, idAnimal)) {
            System.out.println("Animal não existe!!!.");
            return;
        }

        System.out.println("Valor mensal (€): ");
        double valor = input.nextDouble();

        String nomeAnimal = nomeAnimal(caminhoAnimais, idAnimal);
        String especieAnimal = obterEspecieAnimal(caminhoAnimais, idAnimal);
        String plano = determinarPlano(valor);

        imprimirResumoApadrinhamento(
                nomeCliente,
                email,
                nomeAnimal,
                especieAnimal,
                valor,
                plano
        );
    }

    public static int contarAnimais(String caminhoAnimais) throws FileNotFoundException {

        Scanner sc = new Scanner(new File(caminhoAnimais));
        sc.nextLine(); // cabeçalho

        int contador = 0;

        while (sc.hasNextLine()) {
            sc.nextLine();
            contador++;
        }
        return contador;
    }

    public static void jogoAdivinhaEspecie(String caminhoAnimais) throws FileNotFoundException {

        Scanner input = new Scanner(System.in);

        // 1. Contar animais
        int total = contarAnimais(caminhoAnimais);

        // 2. Número aleatório entre 0 e total-1
        Random rd = new Random();
        int randomIndex = rd.nextInt(total);

        // 3. Buscar o animal correspondente
        Scanner sc = new Scanner(new File(caminhoAnimais));
        sc.nextLine(); // cabeçalho

        int atual = 0;
        String nome = "", especie = "", habitat = "", dieta = "", extincao = "";

        while (sc.hasNextLine()) {
            String linha = sc.nextLine();
            String[] itens = linha.split(";");

            if (atual == randomIndex) {
                nome = itens[1];
                especie = itens[2];
                habitat = itens[3];
                dieta = itens[4];
                extincao = itens[5];
            }
            atual++;
        }

        // 4. Mostrar pistas
        System.out.println("\n\n-*-*-*-*-*- ADIVINHA A ESPÉCIE -*-*-*-*-*-");
        System.out.println("Pista 1: Vive no habitat → " + habitat);
        System.out.println("Pista 2: Dieta → " + dieta);
        System.out.println("Pista 3: Em perigo de extinção → " + extincao);

        // 5. Tentar adivinhar
        int tentativas = 0;
        String palpite = "";

        while (!palpite.equals(especie)) {
            System.out.println("\nQual é a espécie?");
            palpite = input.nextLine();
            tentativas++;

            if (!palpite.equals(especie)) {
                System.out.println("Errado! Tenta novamente...");
            }
        }

        // 6. Acertou
        System.out.println("\n🎉 Acertou! 🎉");
        System.out.println("A espécie era: " + especie);
        System.out.println("Número de tentativas: " + tentativas);
    }

    public static void imprimirExit() throws FileNotFoundException {
        FuncoesMenuAdmin.imprimirFicheiro("Ficheiros/exit.txt");
    }

}