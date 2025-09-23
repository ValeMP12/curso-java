package platzi.play.plataforma;

import platzi.play.contenido.Contenido;

import java.time.LocalDateTime;

public class Usuario {
    public String nombre;
    public LocalDateTime fechaRegistro;
    public String email;

    public void ver(Contenido pelicula){
        System.out.println(nombre + " esta viendo... ");
        System.out.println(fechaRegistro);
        pelicula.reproducir();
    }

    public Usuario(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
        this.fechaRegistro = LocalDateTime.now();
    }
}
