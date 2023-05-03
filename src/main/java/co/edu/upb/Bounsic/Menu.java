package co.edu.upb.Bounsic;

import java.util.Scanner;

public class Menu {

    public static boolean crearMenu(BibliotecaCanciones biblioteca, Scanner sc) {
        String opcion;
        boolean menu = true;
        //MENU
        while (menu) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Buscar canción");
            System.out.println("2. Ver la biblioteca de canciones");
            System.out.println("3. Cancion aleatoria");
            System.out.println("4. Lista de hits");
            System.out.println("5. Crear lista de 7 canciones aleatorias");
            System.out.println("6. Cerrar sesión");
            opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    MetodosMenu.buscarCacion(biblioteca, sc);
                    break;
                //-------------------------------------------------------------------------------------//
                case "2":
                    MetodosMenu.verBibliotecaCanciones(biblioteca, sc);
                    break;
                //-------------------------------------------------------------------------------------//
                case "3":
                    MetodosMenu.cancionAleatoria(biblioteca);
                    break;
                //-------------------------------------------------------------------------------------//
                case "4":
                    MetodosMenu.listaReproduccionHits(biblioteca, sc);
                    break;
                //-------------------------------------------------------------------------------------//
                case "5":
                    MetodosMenu.crearListaCancionesAleatorias(biblioteca, sc);                    
                    break;
                //-------------------------------------------------------------------------------------//
                case "6":
                    System.out.println("Sesión cerrada.");
                    menu = false;
                    break;
                //-------------------------------------------------------------------------------------//
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }
        return false;
    }
}
