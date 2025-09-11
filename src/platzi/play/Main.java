package platzi.play;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Platzi Play 🍿");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Cual es tu nombre?");
        String nombre = scanner.nextLine();
        System.out.println("Hola " + nombre + ", esto es Platzi Play!");
        System.out.println(nombre + " ¿cuantos años tienes?");
        int edad = scanner.nextInt();
        System.out.println(nombre + " puedes ver contenido +" + edad);
    }
}
