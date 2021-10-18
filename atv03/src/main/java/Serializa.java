import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Serializa {
    Pessoa fulano = new Pessoa (1, "Marcelo", "marcelo@email.com", "1234567");
    Pessoa ciclano = new Pessoa (2, "Marta", "marta@email.com", "1356890");
    Pessoa beltrano = new Pessoa (3, "Nayara", "nay@email.com", "93849490");

    List<Pessoa> galera = new ArrayList<>();

    public void adicionar() throws IOException{
        galera.add(fulano);
        galera.add(ciclano);
        galera.add(beltrano);
    }

    public void serializarJava() throws IOException{
        adicionar();
        FileOutputStream fos = new FileOutputStream("arquivo.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(galera);
        oos.flush();
        oos.close();
    }

    public void serializarJSON() throws IOException{
        adicionar();
        ObjectMapper om = new ObjectMapper();
        File arquivo = new File("arquivo.json");
        om.writeValue(arquivo, galera);
    }

    public void serializarXML() throws IOException{
        adicionar();
        XmlMapper xm = new XmlMapper();
        File arquivo = new File("arquivo.xml");
        xm.writeValue(arquivo, galera);
    }
}

