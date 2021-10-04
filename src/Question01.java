import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Question01 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite aqui o nome do arquivo: ");

        InputStream is = new FileInputStream(scanner.nextLine());

        ArrayList<String> lugares = new ArrayList<>();

        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader bf = new BufferedReader(isr);

        String line = bf.readLine();
        lugares.add(line);

        while (line != null){
            line = bf.readLine();
            lugares.add(line);
        }

        System.out.println("Digite a palavra que está procurando: ");
        String palavra = scanner.nextLine();

        for(String x: lugares){
            if(x != null && x.contains(palavra)){
                System.out.println(x);
            }
        }

    }

}
