package br.com.controle;

import java.time.LocalDateTime;

public class Funcionario  {
    private int idFuncionario;
    private int idCargo;
    private String nomeCargo; 
    private String nome;
    private String cpf; 
    private String email;
    private String telefone;
    private LocalDateTime dataAdmissao;
  
    public Funcionario(){}
    
    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public int getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }   
    
    public String getNomeCargo(){
        return nomeCargo;
    }
    
    public void setNomeCargo(String nomeCargo){
        this.nomeCargo = nomeCargo;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public LocalDateTime getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDateTime dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }
    
    @Override
    
    public String toString() {
        return idFuncionario + " - " + nome;
    }
}

