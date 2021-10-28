import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Transforma {
    public Animal cadastrar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do Animal:");
        String nome = scanner.nextLine();

        System.out.println("Digite a espécie do Animal");
        String especie = scanner.nextLine();

        System.out.println("Digite o gênero do Animal");
        String genero = scanner.nextLine();

        System.out.println("Digite a idade do Animal");
        int idade = scanner.nextInt();

        return new Animal(nome, especie, genero, idade);
    }

    public void escrever(String arquivo) throws IOException{
        FileOutputStream fos = new FileOutputStream(arquivo, true);
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        BufferedWriter bw = new BufferedWriter(osw);

        Animal animal = cadastrar();
        bw.write(animal.getNome()+","+animal.getEspecie()+","+animal.getGenero()+","+animal.getIdade());
        bw.newLine();
        bw.close();
    }

    public List<Animal> ler(String arquivo) throws IOException{
        InputStream is = new FileInputStream(arquivo);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        List<Animal> animais = new ArrayList<>();

        String linha = br.readLine();

        while(linha != null){
            String[] objetos = linha.split(",");
            String nome = objetos[0];
            String especie = objetos[1];
            String genero = objetos[2];
            int idade = Integer.parseInt(objetos[3]);

            Animal animal = new Animal(nome, especie, genero, idade);
            animais.add(animal);

            linha = br.readLine();
        }
        return animais;
    }

    public void transformarXML(String origem, File destino) throws IOException{
        List<Animal> animais = ler(origem);
        XmlMapper xm = new XmlMapper();
        xm.writeValue(destino, animais);
    }

    public void transformarJSON(String origem, File destino) throws IOException{
        List<Animal> animais = ler(origem);
        ObjectMapper om = new ObjectMapper();
        om.writeValue(destino, animais);
    }
}
