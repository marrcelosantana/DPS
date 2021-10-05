import java.io.*;
import java.util.Scanner;

public class Question01 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome do seu arquivo: ");
        String arquivo = scanner.nextLine();

        System.out.println("Digite a palavra que você está buscando: ");
        String palavra = scanner.nextLine();

        InputStream is = new FileInputStream(arquivo);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        String linha = br.readLine();

        while(linha != null) {
            if(linha.contains(palavra)){
                System.out.println(linha);
            }
            linha = br.readLine();
        }
        br.close();
    }
}
