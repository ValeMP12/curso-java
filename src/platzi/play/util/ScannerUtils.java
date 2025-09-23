package platzi.play.util;

import platzi.play.contenido.Genero;

import java.util.Scanner;

public class ScannerUtils {
    public static Scanner scanner = new Scanner(System.in);
    public static String capturarTexto(String mensaje) {
        System.out.println(mensaje + " ");
        return scanner.nextLine();
    }
    public static int capturarNumero(String mensaje) {
        System.out.println(mensaje + " ");
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor ingresa un dato numérico válido." + mensaje + ": ");
            scanner.next(); // Limpiar la entrada inválida
        }
        int dato = scanner.nextInt();
        scanner.nextLine();
        return dato;
    }
    public static double capturarDecimal(String mensaje) {
        System.out.println(mensaje + ": ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Por favor ingresa un dato numérico válido." + mensaje + ": ");
            scanner.next(); // Limpiar la entrada inválida
        }
        double dato = scanner.nextDouble();
        scanner.nextLine();
        return dato;
    }
    public static String capturarFecha(String mensaje) {
        System.out.println(mensaje + " (YYYY-MM-DD): ");
        return scanner.nextLine();
    }
    public static Genero capturarGenero(String mensaje) {
        while (true) {
            System.out.println(mensaje + " Opciones: ");
            for (Genero genero : Genero.values()) {
                System.out.println("- " + genero.name());
            }
            System.out.println("Que genero desea " );
            try {
                String input = scanner.nextLine().toUpperCase();
                return Genero.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("No tenemos ninguna pelicula con ese genero. Ingrese otro genereso si lo decea 😊 \n" + mensaje + ": ");
            }
        }
    }
}
