package resources;

public class Ejercicio {
    private String nombre;
    private String repeticiones;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(String repeticiones) {
        this.repeticiones = repeticiones;
    }

    public Ejercicio(String nombre, String repeticiones) {
        this.nombre = nombre;
        this.repeticiones = repeticiones;
    }
}
