package resources;

public class Record {
    private String ejercicio;
    private double peso;

    public Record(String ejercicio, double peso) {
        this.ejercicio = ejercicio;
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
}
