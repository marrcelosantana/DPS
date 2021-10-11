import java.io.*;
import java.util.Scanner;

public class Question02 {
    public static void Escrever(String conteudo, String destino) throws IOException{
        OutputStream os = new FileOutputStream(destino, true);
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);

        if(conteudo != null){
            bw.write(conteudo);
            bw.newLine();
        }
        bw.close();
    }

    public static void Copiar(String arquivo, String destino) throws IOException{
        InputStream is = new FileInputStream(arquivo);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader bf = new BufferedReader(isr);

        String linha = bf.readLine();

        if(linha != null){
            Escrever(linha, destino);
        }

        while(linha != null){
            linha = bf.readLine();
            Escrever(linha, destino);
        }

        bf.close();
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Qual o nome do seu primeiro arquivo? ");
        String primeiroArquivo = scanner.nextLine();
        System.out.println("Qual o nome do seu segundo arquivo? ");
        String segundoArquivo = scanner.nextLine();
        System.out.println("Digite o nome do arquivo para onde quer copiar os dados: ");
        String arquivoDestino = scanner.nextLine();

        Copiar(primeiroArquivo, arquivoDestino);
        Copiar(segundoArquivo, arquivoDestino);
    }
}

