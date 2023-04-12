package co.edu.upb.Bounsic;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        String opcion;
        BibliotecaCanciones biblioteca = new BibliotecaCanciones();

        List<Cancion> canciones = new ArrayList<Cancion>();
        canciones = biblioteca.getCanciones();

        boolean sesionIniciada = false;

        System.out.println("Bienvenido a Bounsic");
        System.out.println("--------------------");

        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrarse");
            System.out.println("3. Salir");
            opcion = sc.nextLine();

            if (opcion.equals("1")) {
                sesionIniciada = Funciones.iniciarSesion();
                if (sesionIniciada) {
                    Funciones.mostrarMenu(biblioteca, sc);
                }
            } 
            else if (opcion.equals("2")) {
                Funciones.registrarUsuario(sc, biblioteca);
            }
            else if (opcion.equals("3")) {
                System.out.println("¡Hasta luego!");
                break;
            } 
            else {
                System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
}