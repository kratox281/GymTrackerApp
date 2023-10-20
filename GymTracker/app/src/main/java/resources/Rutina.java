package resources;

import java.util.ArrayList;
import java.util.Map;

public class Rutina {
    private String nombre;
    private Map<String,Integer> ejercicios;

    public Rutina(String nombre, Map<String, Integer> ejercicios) {
        this.nombre = nombre;
        this.ejercicios = ejercicios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Map<String, Integer> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(Map<String, Integer> ejercicios) {
        this.ejercicios = ejercicios;
    }
}
