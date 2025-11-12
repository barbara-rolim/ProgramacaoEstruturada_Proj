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


    public static int contarInteracoes(String caminho, String tipoInteracao) throws FileNotFoundException {

        Scanner in = new Scanner(new File(caminho));

        String linha = in.nextLine();

        int contador = 0;

        while (in.hasNextLine()) {
            linha = in.nextLine();
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

    public static double receitaInteracoes(String caminho, String tipoInteracao) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(caminho));

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
}