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

public class ClienteDAO {

    private List<Cliente> listaClientes;
    
    public ClienteDAO() {
        listaClientes = new ArrayList<Cliente>();
        leArquivoCliente();
    }
    
    public boolean salvar(Cliente novoCliente) {
        if (buscarPorCpf(novoCliente.getCpf()) == null) {
            listaClientes.add(novoCliente);
            escreveArquivoCliente();
            System.out.println("Cliente salvo.");
            return true;
        } else {
            return false;
        }
    }
    
    public List<Cliente> buscarTodos() {
        return listaClientes;
    }
    
    public Cliente buscarPorCpf(String cpf) {
        Cliente c = null;
        boolean achou = false;
        Iterator<Cliente> iteC = listaClientes.iterator();
        cpf = retiraPontuacao(cpf);

        while (iteC.hasNext() & !achou) {
            c = (Cliente) iteC.next();
            if (retiraPontuacao(c.getCpf()).equals(cpf)) {
                achou = true;
            }
        }
        
        if (achou) {
            return c;
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
        Iterator<Cliente> ite;
        relatorio += "\tRelatório de Clientes\n";
        if (listaClientes.isEmpty()) {
            relatorio += " - - não há clientes cadastrados - - ";
        } else {
            ite = listaClientes.iterator();
            while (ite.hasNext()) {
                relatorio += ((Cliente) ite.next()).imprimir() + "\n";
            }
        }
        return relatorio;
    }

    private void leArquivoCliente() {
        try {
            // Deserialize from a file
            File file = new File("cliente.ser");
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            // Deserialize the object
            listaClientes = (List<Cliente>) in.readObject();
            in.close();

        } catch (ClassNotFoundException e) {
            e.fillInStackTrace();
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    private void escreveArquivoCliente() {
        try {
            // Serialize to a file
            ObjectOutput out = new ObjectOutputStream(new FileOutputStream("cliente.ser"));
            out.writeObject(listaClientes);
            out.close();
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }
}
