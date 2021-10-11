import java.io.*;
import java.util.Scanner;
import static java.lang.System.in;

public class Question01 {

    public static void gravar(String origem, String destino) throws IOException{
        long time = System.currentTimeMillis();
        InputStream is = new BufferedInputStream(new FileInputStream(origem));
        OutputStream os = new BufferedOutputStream(new FileOutputStream(destino));

        byte[] bytes = is.readAllBytes();

        os.write(bytes);
        os.close();

        System.out.println("Tempo utilizado em milisegundos: "+(System.currentTimeMillis() - time)+"ms");
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(in);
        System.out.println("Qual o arquivo de origem?");
        String origem = scanner.next();

        System.out.println("Qual o arquivo de destino?");
        String destino = scanner.next();

        gravar(origem, destino);
    }
}
