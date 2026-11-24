package br.com.controle;

public class Cargo {
    private int idCargo;
    private String nomeCargo;
    private double salario;
    private int cargaHoraria;
    private String descricao;
    private int ativo;

    public Cargo(){}
    
    public int getIdCargo(){ 
        return idCargo;}
    public void setIdCargo(int idCargo){ 
        this.idCargo = idCargo;}

    public String getNomeCargo(){ 
        return nomeCargo;}
    public void setNomeCargo(String nomeCargo){ 
        this.nomeCargo = nomeCargo;}

    public double getSalario(){ 
        return salario;}
    public void setSalario(double salario){ 
        this.salario = salario;}

    public int getCargaHoraria(){ 
        return cargaHoraria;}
    public void setCargaHoraria(int cargaHoraria){ 
        this.cargaHoraria = cargaHoraria;}

    public String getDescricao(){
        return descricao;}
    public void setDescricao(String descricao){ 
        this.descricao = descricao;}

    public int getAtivo(){ 
        return ativo;}
    public void setAtivo(int ativo){ 
        this.ativo = ativo;}
    public String getAtivoTexto(){
        return ativo == 1? "Ativo" : "Inativo";}
    
    @Override
    public String toString(){
        return nomeCargo;
    }
}

