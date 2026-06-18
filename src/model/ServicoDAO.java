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

public class ServicoDAO {
	
	private List<Servico> listaServicos;
	
	public ServicoDAO() {
		listaServicos = new ArrayList<Servico>();
		leArquivoServico();
	}
	
    public boolean salvar(Servico novoServico) {
        if (buscarPorCodigoServico(novoServico.getCodigoServico()) == null) {
            listaServicos.add(novoServico);
            escreveArquivoServico();
            System.out.println("Serviço salvo.");
            return true;
        } else {
            return false;
        }
    }
    
    public List<Servico> buscarTodos() {
        return listaServicos;
    }
    
    public Servico buscarPorCodigoServico(String cod) {
        Servico s = null;
        boolean achou = false;
        Iterator<Servico> iteS = listaServicos.iterator();
        cod = retiraPontuacao(cod);
        
        while (iteS.hasNext() & !achou) {
            s = (Servico) iteS.next();
            if (retiraPontuacao(s.getCodigoServico()).equals(cod)) {
                achou = true;
            }
        }
        
        if (achou) {
            return s;
        } else {
            return null;
        }
    }

    public String gerarRelatorio() {
        String relatorio = new String();
        Iterator<Servico> ite;
        relatorio += "\n\tRelatório de Serviços\n";
        if (listaServicos.isEmpty()) {
            relatorio += " - - não há serviços cadastrados - - ";
        } else {
            ite = listaServicos.iterator();
            while (ite.hasNext()) {
                relatorio += ((Servico) ite.next()).imprimir() + "\n";
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

    private void leArquivoServico() {
        try {
            // Deserialize from a file
            File file = new File("servico.ser");
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            // Deserialize the object
            listaServicos = (List<Servico>) in.readObject();
            in.close();

        } catch (ClassNotFoundException e) {
            System.out.println(e.fillInStackTrace());
        } catch (IOException e) {
            System.out.println(e.fillInStackTrace());
        }
    }

    private void escreveArquivoServico() {
        try {
            // Serialize to a file
            ObjectOutput out = new ObjectOutputStream(new FileOutputStream("servico.ser"));
            out.writeObject(listaServicos);
            out.close();
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

}
