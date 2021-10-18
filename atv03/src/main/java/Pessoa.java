import java.io.Serializable;

public class Pessoa implements Serializable {
    private int number;
    private String name;
    private String email;
    private String telefone;

    public Pessoa(int number, String name, String email, String telefone) {
        this.number = number;
        this.name = name;
        this.email = email;
        this.telefone = telefone;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Pessoa: (" +
                "id: " + number +
                ", nome: '" + name + '\'' +
                ", email: '" + email + '\'' +
                ", telefone: '" + telefone + '\'' +
                ')';
    }
}
