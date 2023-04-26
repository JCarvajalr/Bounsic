package co.edu.upb.Bounsic;

import java.util.Scanner;

public class Inicio {
    public static void iniciar() {
        Scanner sc = new Scanner(System.in);
        BibliotecaCanciones biblioteca = new BibliotecaCanciones();
        String opcion;
        Usuario usuario = new Usuario();
        boolean on = true, sesionIniciada = false;

        System.out.println("------------------------------------------------------------");
        System.out.printf("%40s %n","Bienvenido a Bounsic");
        System.out.println("------------------------------------------------------------");

        while (on) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrarse");
            System.out.println("3. Salir");
            opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    sesionIniciada = usuario.iniciarSesion();
                /*if (sesionIniciada) {
                    Menu.crearMenu(biblioteca, sc);
                }*/

                    break;
                //-------------------------------------------------------------------------------------//
                case "2":
                    sesionIniciada = usuario.registrarUsuario();
                    break;
                //-------------------------------------------------------------------------------------//
                case "3":
                    System.out.println("¡Hasta luego!");
                    on = false;
                    break;
                //-------------------------------------------------------------------------------------//
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
            while (sesionIniciada) {
                sesionIniciada = Menu.crearMenu(biblioteca, sc);
            }


        }
    }
}
