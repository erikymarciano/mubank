/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacao;

/**
 *
 * @author Matth
 */
public class Transacao {
    private int id;
    private String tipo;
    private double valor;
    private int id_conta;

    public Transacao(){
        
    }

    public Transacao(int id, String tipo, double valor, int id_conta) {
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
        this.id_conta = id_conta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getIdConta() {
        return id_conta;
    }

    public void setIdConta(int id_conta) {
        this.id_conta = id_conta;
    }
    
}
