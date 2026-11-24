package br.com.controle;

public class MesaReserva {
    private int idReserva;
    private int idMesa;
    private String horaChegada;
    private String horaSaida;
    private String observacao;
    private String dataReserva;
    private String nomeCliente;
    private int numeroMesa;

    public MesaReserva(){}
    
    public int getIdReserva(){
        return idReserva;}
    public void setIdReserva(int idReserva){ 
        this.idReserva = idReserva;}

    public int getIdMesa(){ 
        return idMesa;}
    public void setIdMesa(int idMesa){ 
        this.idMesa = idMesa;}

    public String getHoraChegada(){ 
        return horaChegada;}
    public void setHoraChegada(String horaChegada){ 
        this.horaChegada = horaChegada;}

    public String getHoraSaida(){ 
        return horaSaida;}
    public void setHoraSaida(String horaSaida){ 
        this.horaSaida = horaSaida;}

    public String getObservacao(){ 
        return observacao;}
    public void setObservacao(String observacao){ 
        this.observacao = observacao;}
    
    public int getNumeroMesa(){ 
        return numeroMesa;}
    public void setNumeroMesa(int numeroMesa){ 
        this.numeroMesa = numeroMesa;}

    public String getNomeCliente(){ 
        return nomeCliente;}
    public void setNomeCliente(String nomeCliente){ 
        this.nomeCliente = nomeCliente;}

    public String getDataReserva(){ 
        return dataReserva;}
    public void setDataReserva(String dataReserva){ 
        this.dataReserva = dataReserva;}
}