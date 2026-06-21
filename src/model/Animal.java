package model;

import java.io.Serializable;

public class Animal implements Serializable {

    private int idAnimal;
    private String nome;
    private String especie;
    private String raca;
    private String porte;
    private String temperamento;

    private Cliente cliente;

    public Animal(int idAnimal, String nome, String especie, String raca, String porte, String temperamento,
            Cliente cliente) {
        setIdAnimal(idAnimal);
        setNome(nome);
        setEspecie(especie);
        setRaca(raca);
        setPorte(porte);
        setTemperamento(temperamento);
        setCliente(cliente);
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public String getTemperamento() {
        return temperamento;
    }

    public void setTemperamento(String temperamento) {
        this.temperamento = temperamento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String imprimir() {
        String nomeDono = (cliente != null) ? cliente.getNome() : "Sem dono associado";

        return "ID: " + getIdAnimal() +
                " / Nome: " + getNome() +
                " / Espécie: " + getEspecie() +
                " / Raça: " + getRaca() +
                " / Porte: " + getPorte() +
                " / Temperamento: " + getTemperamento() +
                " / Dono: " + nomeDono;
    }
}