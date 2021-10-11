import java.io.*;
import java.util.Properties;
import java.util.Scanner;

public class Question03 {

    public static Properties pegarConfigs(Properties prop) throws IOException {
        InputStream is = new FileInputStream("./resources/config.properties");
        prop.load(is);
        return prop;
    }

    public static void ler(String arquivo) throws IOException{
        FileInputStream fis = new FileInputStream(arquivo);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        String linha;

        Properties prop = new Properties();
        pegarConfigs(prop);

        int inicio = Integer.parseInt(prop.getProperty("linha_inicial"));
        int fim = Integer.parseInt(prop.getProperty("linha_final"));

        for(int i = 1; i < inicio; i++){
            linha = br.readLine();
        }

        for(int i = inicio; i <= fim; i++){
            linha = br.readLine();
            if(linha != null){
                System.out.println(linha);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do arquivo: ");
        String arquivo = scanner.next();

        ler(arquivo);

    }
}
