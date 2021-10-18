import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Desserializa {
    public void desserializar() throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream("arquivo.txt");
        ObjectInputStream ois = new ObjectInputStream(fi);
        System.out.println("Aqui está sua lista de pessoas: "+ois.readObject());
        fi.close();
        ois.close();
    }
}
