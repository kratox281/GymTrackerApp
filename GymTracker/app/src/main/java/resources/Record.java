package resources;

public class Record {
    private String ejercicio;
    private double peso;
    private String referencia;
    public Record(String ejercico, double peso) {
        this.ejercicio = ejercico;
        this.peso = peso;
    }

    public String getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(String ejercicio) {
        this.ejercicio = ejercicio;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
}
