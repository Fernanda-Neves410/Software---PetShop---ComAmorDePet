package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FuncionarioDAO {

    private List<Funcionario> listaFuncionarios;
    
    public FuncionarioDAO() {
        listaFuncionarios = new ArrayList<Funcionario>();
        leArquivoFuncionario();
    }
    
    public boolean salvar(Funcionario novoFuncionario) {
        if (buscarPorMatricula(novoFuncionario.getMatricula()) == null) {
            listaFuncionarios.add(novoFuncionario);
            escreveArquivoFuncionario();
            System.out.println("Funcionario salvo.");
            return true;
        } else {
            return false;
        }
    }
    
    public List<Funcionario> buscarTodos() {
        return listaFuncionarios;
    }
    
    public Funcionario buscarPorMatricula(String matricula) { // A busca deveria ser por matrícula?
        Funcionario f = null;
        boolean achou = false;
        Iterator<Funcionario> iteF = listaFuncionarios.iterator();
        matricula = retiraPontuacao(matricula);

        while (iteF.hasNext() & !achou) {
            f = (Funcionario) iteF.next();
            if (retiraPontuacao(f.getMatricula()).equals(matricula)) {
                achou = true;
            }
        }
        
        if (achou) {
            return f;
        } else {
            return null;
        }
    }

    private String retiraPontuacao(String texto) {
        texto = texto.replace(".", "");
        texto = texto.replace("-", "");
        texto = texto.replace("/", "");
        texto = texto.replace("*", "");
        return texto;
    }

    public String gerarRelatorio() {
        String relatorio = new String();
        Iterator<Funcionario> ite;
        relatorio += "\tRelatório de Funcionários\n";
        if (listaFuncionarios.isEmpty()) {
            relatorio += " - - não há funcionários cadastrados - - ";
        } else {
            ite = listaFuncionarios.iterator();
            while (ite.hasNext()) {
                relatorio += ((Funcionario) ite.next()).imprimir() + "\n";
            }
        }
        return relatorio;
    }

    private void leArquivoFuncionario() {
        try {
            // Deserialize from a file
            File file = new File("funcionario.ser");
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            // Deserialize the object
            listaFuncionarios = (List<Funcionario>) in.readObject();
            in.close();

        } catch (ClassNotFoundException e) {
            e.fillInStackTrace();
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    private void escreveArquivoFuncionario() {
        try {
            // Serialize to a file
            ObjectOutput out = new ObjectOutputStream(new FileOutputStream("funcionario.ser"));
            out.writeObject(listaFuncionarios);
            out.close();
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

}
