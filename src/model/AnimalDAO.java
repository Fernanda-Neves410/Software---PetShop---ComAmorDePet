package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO {

    public AnimalDAO() {
    }

    public boolean salvar(Animal novoAnimal) {
        String sql = "INSERT INTO animal(nome, especie, raca, porte, temperamento, cpf_cliente) VALUES(?,?,?,?,?,?)";

        try (
                Connection conn = ConexaoBD.conectar();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, novoAnimal.getNome());
            ps.setString(2, novoAnimal.getEspecie());
            ps.setString(3, novoAnimal.getRaca());
            ps.setString(4, novoAnimal.getPorte());
            ps.setString(5, novoAnimal.getTemperamento());

            if (novoAnimal.getCliente() != null) {
                ps.setString(6, retiraPontuacao(novoAnimal.getCliente().getCpf()));
            } else {
                ps.setString(6, null);
            }

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Animal> buscarTodos() {

        List<Animal> animais = new ArrayList<>();
        String sql = "SELECT * FROM animal";

        ClienteDAO clienteDAO = new ClienteDAO();

        try (
                Connection conn = ConexaoBD.conectar();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                Cliente dono = clienteDAO.buscarPorCpf(rs.getString("cpf_cliente"));

                Animal a = new Animal(
                        rs.getInt("idAnimal"),
                        rs.getString("nome"),
                        rs.getString("especie"),
                        rs.getString("raca"),
                        rs.getString("porte"),
                        rs.getString("temperamento"),
                        dono);

                animais.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return animais;
    }

    public String gerarRelatorio() {

        StringBuilder relatorio = new StringBuilder();

        relatorio.append("\n\tRelatório de Animais\n");

        List<Animal> animais = buscarTodos();

        if (animais.isEmpty()) {

            relatorio.append(" - - não há animais cadastrados - - ");

        } else {

            for (Animal a : animais) {
                relatorio.append(a.imprimir()).append("\n");
            }

        }

        return relatorio.toString();
    }

    private String retiraPontuacao(String texto) {

        if (texto == null)
            return null;

        texto = texto.replace(".", "");
        texto = texto.replace("-", "");
        texto = texto.replace("/", "");
        texto = texto.replace("*", "");

        return texto;
    }
}