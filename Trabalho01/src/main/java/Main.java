import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Transforma transforma = new Transforma();
        String origem = "arquivo.csv";
        File arquivoXml = new File("arquivo.xml");
        File arquivoJson = new File("arquivo.json");

        transforma.escrever(origem);
        transforma.transformarXML(origem, arquivoXml);
        transforma.transformarJSON(origem, arquivoJson);
    }
}
