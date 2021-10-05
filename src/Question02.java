/* import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Question02 {
    public static void main(String[] args) throws IOException {

        try {
            FileWriter fw = new FileWriter("arquivo2.txt");
            fw.write("Olá mundo, seja bem vindo ao meu código!\n");
            fw.close();
        } catch (Exception e) {
            System.out.println("Erro "+e.getMessage());
        }

        try {
            FileReader fr = new FileReader("arquivo2.txt");
            BufferedReader br = new BufferedReader(fr);
            String linha = br.readLine();
            System.out.println(linha);

        } catch (Exception e) {
            System.out.println("Erro "+e.getMessage());
        }
    }
}

 */
