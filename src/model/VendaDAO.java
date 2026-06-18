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

public class VendaDAO {
	
    private List<Venda> listaVendas;

    public VendaDAO() {
        listaVendas = new ArrayList<Venda>();
        leArquivoVenda();
    }
    
    public void salvar(Venda novaVenda) {
        listaVendas.add(novaVenda);
        gravaArquivoVenda();
        System.out.println("Venda salva.");

    }
    
    public List<Venda> buscarTodos() {
        return listaVendas;
    }

    public String gerarRelatorio() {
        String relatorio = new String();
        Iterator<Venda> ite;
        relatorio += "\n\tRelatório de Vendas\n";
        if (listaVendas.isEmpty()) {
            relatorio += " - - não há vendas registradas - - ";
        } else {
            ite = listaVendas.iterator();
            while (ite.hasNext()) {
                relatorio += ((Venda) ite.next()).imprimir() + "\n";
            }
        }
        return relatorio;
    }

    private void gravaArquivoVenda() {
        try {
            // Serialize to a file
            ObjectOutput out = new ObjectOutputStream(new FileOutputStream("venda.ser"));
            out.writeObject(listaVendas);
            out.close();
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    private void leArquivoVenda() {
        try {
            // Deserialize from a file
            File file = new File("venda.ser");
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            // Deserialize the object
            listaVendas = (List<Venda>) in.readObject();
            in.close();

        } catch (ClassNotFoundException e) {
            e.fillInStackTrace();
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

}
