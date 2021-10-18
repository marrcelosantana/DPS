import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Serializa serializa = new Serializa();
        Desserializa desserializa = new Desserializa();

        serializa.serializarJava();
        serializa.serializarJSON();
        serializa.serializarXML();

        desserializa.desserializar();
    }
}
