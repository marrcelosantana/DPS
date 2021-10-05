import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Question01 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite aqui o nome do arquivo: ");

        InputStream is = new FileInputStream(scanner.nextLine());

        ArrayList<String> places = new ArrayList<>();

        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader bf = new BufferedReader(isr);

        String line = bf.readLine();
        places.add(line);

        while (line != null){
            line = bf.readLine();
            places.add(line);
        }

        System.out.println("Digite a palavra que está procurando: ");
        String word = scanner.nextLine();

        for(String i : places){
            if(i != null && i.contains(word)){
                System.out.println(i);
            }
        }

        bf.close();

    }

}
