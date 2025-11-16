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
            String[] itensDaLinha = linha.split(";");

            if (itensDaLinha[0].equals(idAnimal)) {
                return itensDaLinha[1];
            }
        }
        return "animalDesconhecido";
    }

    public static String obterEspecieAnimal(String caminhoAnimais, String idAnimal) throws FileNotFoundException {

        Scanner sc = new Scanner(new File(caminhoAnimais));
        sc.nextLine();

        while (sc.hasNextLine()) {
            String linha = sc.nextLine();
            String[] itensDaLinha = linha.split(";");

            if (itensDaLinha[0].equals(idAnimal)) {
                return itensDaLinha[2];
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
            String[] itensDaLinha = linha.split(";");

            String habitat = itensDaLinha[3];

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
            String[] itensDaLinha = linha.split(";");

            if (itensDaLinha[3].equals(habitat)) {
                System.out.println("- " + itensDaLinha[1] + "  Espécie: " + itensDaLinha[2]);
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
            String[] itensDaLinha = linha.split(";");

            if (itensDaLinha[0].equals(idAnimal)) {
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
            String[] itensDaLinha = linha.split(";");

            if (itensDaLinha[3].equals(idAnimal) && itensDaLinha[2].equals(tipo)) {
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
            String[] itensDaLinha = linha.split(";");

            if (itensDaLinha[3].equals(idAnimal) && itensDaLinha[2].equals(tipo)) {
                System.out.println(" - " + itensDaLinha[4]);
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
        System.out.println("Padrinho: " + nomeCliente + ("Email: " + email));
        System.out.println("\nAnimal: " + nomeAnimal  + " - " + especieAnimal);
        System.out.println("\nPlano: " + plano);
        System.out.println("Valor: " + valor + " €/mes");
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
        sc.nextLine();

        int contador = 0;

        while (sc.hasNextLine()) {
            sc.nextLine();
            contador++;
        }
        return contador;
    }

    public static void jogoAdivinhaEspecie(String caminhoAnimais) throws FileNotFoundException {

        Scanner input = new Scanner(System.in);

        int total = contarAnimais(caminhoAnimais);

        Random numeroRandom = new Random();
        int randomIndex = numeroRandom.nextInt(total);

        Scanner sc = new Scanner(new File(caminhoAnimais));
        sc.nextLine();

        int contador = 0;
        String nome = "";
        String especie = "";
        String habitat = "";
        String dieta = "";
        String extincao = "";

        while (sc.hasNextLine()) {
            String linha = sc.nextLine();
            String[] itensDaLinha = linha.split(";");

            if (contador == randomIndex) {
                nome = itensDaLinha[1];
                especie = itensDaLinha[2];
                habitat = itensDaLinha[3];
                dieta = itensDaLinha[4];
                extincao = itensDaLinha[5];
            }
            contador++;
        }

        System.out.println("\n\n-*-*-*-*-*- ADIVINHA A ESPÉCIE -*-*-*-*-*-");
        System.out.println("Pista: Vive no habitat → " + habitat);
        System.out.println("Pista: Tem essa dieta → " + dieta);
        System.out.println("Pista: Perigo de extinção → " + extincao);

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
        System.out.println("\nAcertou!");
        System.out.println("A espécie era: " + especie);
        System.out.println("Número de tentativas: " + tentativas);
    }

    public static void imprimirExit() throws FileNotFoundException {
        System.out.println("Ficheiros/exit.txt");
    }
}