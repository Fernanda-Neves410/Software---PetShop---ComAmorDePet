import model.CriarTabelas;

public class TesteBanco {

    public static void main(String[] args) {

        CriarTabelas.criarTabelas();

        System.out.println("Banco inicializado.");
    }
}