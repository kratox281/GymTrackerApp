package resources;

public class Usuario {
   private String nombre;
   private Integer repeticiones;

   public Usuario(String nombre, Integer repeticiones) {
      this.nombre = nombre;
      this.repeticiones = repeticiones;
   }

   public String getNombre() {
      return nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public Integer getRepeticiones() {
      return repeticiones;
   }

   public void setRepeticiones(Integer repeticiones) {
      this.repeticiones = repeticiones;
   }
}
