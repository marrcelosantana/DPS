import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Transforma transformar = new Transforma();
        String origem = "arquivo.csv";
        File arquivoXml = new File("arquivo.xml");
        File arquivoJson = new File("arquivo.json");

        transformar.escrever(origem);
        transformar.transformarXML(origem, arquivoXml);
        transformar.transformarJSON(origem, arquivoJson);
    }
}
