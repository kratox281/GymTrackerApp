package resources;

import java.util.ArrayList;
import java.util.Map;

public class Rutina {
    private String nombre;
    private Map<String,Integer> ejercicios;
    private String usuario;
    private String referencia;
    public Rutina(String nombre, Map<String, Integer> ejercicios,String usuario) {
        this.nombre = nombre;
        this.ejercicios = ejercicios;
        this.usuario = usuario;
    }

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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    @Override
    public String toString() {
        return getNombre();
    }
}
