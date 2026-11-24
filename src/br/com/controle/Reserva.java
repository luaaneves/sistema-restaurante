package br.com.controle;

public class Reserva {
    private int idReserva;
    private int idCliente;
    private String dataReserva; 
    private String horario;   
    private int qntPessoas;
    private int status;

    private String nomeCliente;

    public Reserva(){}
    
    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(String dataReserva) {
        this.dataReserva = dataReserva;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getQntPessoas() {
        return qntPessoas;
    }

    public void setQntPessoas(int qntPessoas) {
        this.qntPessoas = qntPessoas;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getStatusTexto() {
        return status == 1 ? "Ativa" : "Inativa";
    }
}
