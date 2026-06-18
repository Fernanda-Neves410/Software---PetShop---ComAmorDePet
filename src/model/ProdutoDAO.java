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

public class ProdutoDAO {
	
	private List<Produto> listaProdutos;
	
	public ProdutoDAO() {
		listaProdutos = new ArrayList<Produto>();
		leArquivoProduto();
	}
	
    public boolean salvar(Produto novoProduto) {
        if (buscarPorCodigoBarras(novoProduto.getCodigoBarras()) == null) {
            listaProdutos.add(novoProduto);
            escreveArquivoProduto();
            System.out.println("Produto salvo.");
            return true;
        } else {
            return false;
        }
    }
    
    public List<Produto> buscarTodos() {
        return listaProdutos;
    }
    
    public Produto buscarPorCodigoBarras(String cod) {
        Produto p = null;
        boolean achou = false;
        Iterator<Produto> iteP = listaProdutos.iterator();
        cod = retiraPontuacao(cod);
        
        while (iteP.hasNext() & !achou) {
            p = (Produto) iteP.next();
            if (retiraPontuacao(p.getCodigoBarras()).equals(cod)) {
                achou = true;
            }
        }
        
        if (achou) {
            return p;
        } else {
            return null;
        }
    }

    public String gerarRelatorio() {
        String relatorio = new String();
        Iterator<Produto> ite;
        relatorio += "\n\tRelatório de Produtos\n";
        if (listaProdutos.isEmpty()) {
            relatorio += " - - não há produtos cadastrados - - ";
        } else {
            ite = listaProdutos.iterator();
            while (ite.hasNext()) {
                relatorio += ((Produto) ite.next()).imprimir() + "\n";
            }
        }
        return relatorio;
    }

    private String retiraPontuacao(String texto) {
        texto = texto.replace(".", "");
        texto = texto.replace("-", "");
        texto = texto.replace("/", "");
        texto = texto.replace("*", "");
        return texto;
    }

    private void leArquivoProduto() {
        try {
            // Deserialize from a file
            File file = new File("produto.ser");
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            // Deserialize the object
            listaProdutos = (List<Produto>) in.readObject();
            in.close();

        } catch (ClassNotFoundException e) {
            System.out.println(e.fillInStackTrace());
        } catch (IOException e) {
            System.out.println(e.fillInStackTrace());
        }
    }

    private void escreveArquivoProduto() {
        try {
            // Serialize to a file
            ObjectOutput out = new ObjectOutputStream(new FileOutputStream("produto.ser"));
            out.writeObject(listaProdutos);
            out.close();
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }
}
