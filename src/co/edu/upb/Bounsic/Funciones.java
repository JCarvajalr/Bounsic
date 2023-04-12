package co.edu.upb.Bounsic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Funciones {
	public static void reproducirCancion(Cancion cancion) {

		String[] cancionSeparada = cancion.getLetra().split("\r\n|\r|\n");

		for (String linea : cancionSeparada) {
			System.out.println(linea); // mostrar la linea actual
			try {
				Thread.sleep(1000); // esperar 1 segundo antes de mostrar al otra linea
			} catch (InterruptedException ex) {
				// Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	public static void buscarPorNombre(BibliotecaCanciones biblioteca, String nombre) {
		System.out.println("Canciones con el nombre \"" + nombre + "\":");
        System.out.println("------------------------");

        // Obtener la lista de canciones que contienen el nombre buscado
        List<Cancion> canciones = biblioteca.getCanciones();
        List<Cancion> cancionesEncontradas = new ArrayList<>();
        int index = 0;
        for (Cancion cancion : canciones) {
            if (cancion.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                cancionesEncontradas.add(cancion);
                System.out.println((index++) + ": " + cancion.getNombre() + " - " + cancion.getArtista());
            }
        }

        if (cancionesEncontradas.isEmpty()) {
            System.out.println("No se encontraron canciones con el nombre \"" + nombre + "\"");
            return;
        }

        System.out.println();
        System.out.print("Ingrese el número de la canción que desea reproducir: ");
        Scanner scanner = new Scanner(System.in);
        int seleccion = scanner.nextInt();

        if (seleccion < 0 || seleccion >= cancionesEncontradas.size()) {
            System.out.println("Número de canción inválido");
            return;
        }

        System.out.println("Reproduciendo \"" + cancionesEncontradas.get(seleccion).getNombre() + "\" por " + cancionesEncontradas.get(seleccion).getArtista());
        reproducirCancion(cancionesEncontradas.get(seleccion));
	}

	public static void buscarPorGenero(BibliotecaCanciones biblioteca, String genero) {
		 System.out.println("Canciones de " + genero + ":");
	        System.out.println("------------------------");

	        // Obtener la lista de canciones del género seleccionado
	        List<Cancion> canciones = biblioteca.getCanciones();
	        List<Cancion> cancionesEncontradas = new ArrayList<>();
	        int index = 0;
	        for (Cancion cancion : canciones) {
	            if (cancion.getGenero().equalsIgnoreCase(genero)) {
	                cancionesEncontradas.add(cancion);
	                System.out.println((index++) + ": " + cancion.getNombre() + " - " + cancion.getArtista());
	            }
	        }

	        if (cancionesEncontradas.isEmpty()) {
	            System.out.println("No se encontraron canciones de " + genero);
	            return;
	        }

	        System.out.println();
	        System.out.print("Ingrese el número de la canción que desea reproducir: ");
	        Scanner scanner = new Scanner(System.in);
	        int seleccion = scanner.nextInt();

	        if (seleccion < 0 || seleccion >= cancionesEncontradas.size()) {
	            System.out.println("Número de canción inválido");
	            return;
	        }

	        System.out.println("Reproduciendo \"" + cancionesEncontradas.get(seleccion).getNombre() + "\" por " + cancionesEncontradas.get(seleccion).getArtista());
	        reproducirCancion(cancionesEncontradas.get(seleccion));
	}

	public static boolean iniciarSesion() {
		String usuario;
		String contrasena;
		boolean sesionIniciada = false;

		while (!sesionIniciada) {
			Scanner sc = new Scanner(System.in);
			System.out.print("Ingrese su nombre de usuario: ");
			usuario = sc.nextLine();
			System.out.print("Ingrese su contraseña: ");
			contrasena = sc.nextLine();

			if (usuario.length() >= 5 && contrasena.length() >= 5 && !contrasena.contains("@")
					&& !contrasena.contains(" ") && !usuario.contains(" ") && !usuario.contains("!")
					&& !contrasena.contains("!") && !contrasena.contains("?") && !usuario.contains("?")
					&& !contrasena.contains("¿") && !usuario.contains("¿") && !contrasena.contains("¡")
					&& !usuario.contains("¡")) {
				sesionIniciada = true;
				System.out.println("Sesión iniciada correctamente.");
			} else {
				System.out.println(
						"El nombre de usuario y la contraseña deben contener al menos 5 caracteres sin espacios "
								+ "y no se aceptan caracteres especiales como '@,!,?,¿,¡'. Intente de nuevo.");
			}
		}
		return sesionIniciada;
	}

	public static void registrarUsuario(Scanner sc, BibliotecaCanciones biblioteca) throws ParseException {
		while (true) {
			System.out.print("Ingrese su nombre de usuario: ");
			String usuarioRegistro = sc.nextLine();

			while (usuarioRegistro.length() < 5) {
				System.out.println("El nombre de usuario debe tener al menos 5 caracteres.");
				System.out.print("Ingrese su nombre de usuario: ");
				usuarioRegistro = sc.nextLine();
			}

			System.out.print("Ingrese su contraseña: ");
			String contrasenaRegistro = sc.nextLine();

			while (contrasenaRegistro.length() < 5) {
				System.out.println("La contraseña debe tener al menos 5 caracteres.");
				System.out.print("Ingrese su contraseña: ");
				contrasenaRegistro = sc.nextLine();
			}

			System.out.print("Ingrese nuevamente su contraseña: ");
			String contrasenaRegistro2 = sc.nextLine();

			if (!contrasenaRegistro.equals(contrasenaRegistro2)) {
				System.out.println("Las contraseñas no coinciden. Intente de nuevo.");
				return;
			}

			while (true) {
				System.out.print("Ingrese su correo electrónico: ");
				String correoRegistro = sc.nextLine();

				if (!correoRegistro.endsWith("@hotmail.com") && !correoRegistro.endsWith("@gmail.com")) {
					System.out.println("El correo debe acabar en @gmail.com o @hotmail.com, intente de nuevo.");
				} else {
					break;
				}
			}

			String numeroTarjeta;
			while (true) {
				System.out.print("Ingrese su número de tarjeta de crédito (16 dígitos): ");
				numeroTarjeta = sc.nextLine();

				if (numeroTarjeta.matches("\\d{16}")) {
					break;
				} else {
					System.out.println("El número de tarjeta ingresado no es válido. Intente de nuevo.");
				}
			}

			System.out.print("Ingrese la fecha de vencimiento de la tarjeta (DD/MM/AAAA): ");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			sdf.setLenient(false);
			Date fechaDeVencimiento;
			while (true) {
				try {
					fechaDeVencimiento = sdf.parse(sc.nextLine());
					Calendar hoy = Calendar.getInstance();
					Calendar fechaVencimiento = Calendar.getInstance();
					fechaVencimiento.setTime(fechaDeVencimiento);
					if (fechaVencimiento.before(hoy)) {
						System.out.println("La fecha de vencimiento debe ser de hoy en adelante. Intente de nuevo.");
						continue;
					}
					break;
				} catch (ParseException e) {
					System.out.println("La fecha ingresada no es válida. Intente de nuevo.");

				}
			}

			System.out.print("Ingrese el codigo de seguridad de la tarjeta: ");
			int codigoSeguridad;
			while (true) {
				try {
					codigoSeguridad = sc.nextInt();
					if (String.valueOf(codigoSeguridad).length() == 3
							|| String.valueOf(codigoSeguridad).length() == 4) {
						break;
					} else {
						System.out.println("El codigo de seguridad debe tener 3 o 4 digitos. Intente de nuevo.");
						System.out.print("Ingrese el codigo de seguridad de la tarjeta: ");
					}
				} catch (InputMismatchException e) {
					System.out.println("La entrada no es un número entero. Intente de nuevo.");
					System.out.print("Ingrese el codigo de seguridad de la tarjeta: ");
					sc.nextLine();
				}
			}
			sc.nextLine();

			if (usuarioRegistro.length() >= 5 && contrasenaRegistro.length() >= 5 && numeroTarjeta.length() == 16
					&& numeroTarjeta.matches("\\d{16}") && !contrasenaRegistro.contains(" ")
					&& !usuarioRegistro.contains(" ") && !usuarioRegistro.contains("!")
					&& !contrasenaRegistro.contains("!") && !contrasenaRegistro.contains("?")
					&& !usuarioRegistro.contains("?") && !contrasenaRegistro.contains("¿")
					&& !usuarioRegistro.contains("¿") && !contrasenaRegistro.contains("¡")
					&& !usuarioRegistro.contains("¡")

					&& !numeroTarjeta.contains(" ")

					&& !numeroTarjeta.contains("!")

					&& !numeroTarjeta.contains("?")

					&& !numeroTarjeta.contains("¿")

					&& !numeroTarjeta.contains("¡")) {

				System.out.println("¡Registro exitoso!");
				System.out.println("--------------------");
				mostrarMenu(biblioteca, sc);
				break;
			} else {
				System.out
						.println("no se aceptan espacios, ni caracteres especiales como ´!,?,¿,¡'. Intente de nuevo.");
			}
		}
	}

	public static void mostrarMenu(BibliotecaCanciones biblioteca, Scanner sc) {
		String opcion;

		while (true) {
			System.out.println("Seleccione una opción:");
			System.out.println("1. Buscar canción");
			System.out.println("2. Ver la biblioteca de canciones");
			System.out.println("3. Canción aleatoria");
			System.out.println("4. Cerrar sesión");
			opcion = sc.nextLine();

			if (opcion.equals("1")) {
				boolean opcionValida = false;
				while (!opcionValida) {
					System.out.println("¿Cómo quieres buscar la canción? (por nombre o por género)");
					String opcionBusq = sc.nextLine();

					if (opcionBusq.equalsIgnoreCase("nombre")) {
						System.out.println("Introduce el nombre de la canción:");
						String nombre = sc.nextLine();
						Funciones.buscarPorNombre(biblioteca, nombre);
						opcionValida = true;
					} else if (opcionBusq.equalsIgnoreCase("genero")) {
						System.out.println("¿Qué género de canciones quieres ver? (rock, pop, hip hop, electronica)");
						String genero = sc.nextLine();
						Funciones.buscarPorGenero(biblioteca, genero);
						opcionValida = true;
					} else {
						System.out.println("Opción no válida. Introduce una opción válida.");
					}
				}
			} else if (opcion.equals("2")) {
				List<Cancion> canciones = biblioteca.getCanciones();
				System.out.println("\nCanciones disponibles:\n");

				int index = 0;
				for (Cancion cancion : biblioteca.getCanciones()) {
					System.out.println((index++) + ". " + cancion.getNombre());
				}

				System.out.println("\nElije una cancion:\n");
				int numero = sc.nextInt();
				Funciones.reproducirCancion(canciones.get(numero));
			} else if (opcion.equals("3")) {
				Random random = new Random();
                int randomIndex = random.nextInt(biblioteca.size());
                Cancion cancionAleatoria = biblioteca.get(randomIndex);
                System.out.println("Canción aleatoria seleccionada:");
                System.out.println("Nombre: " + cancionAleatoria.getNombre());
                System.out.println("Género: " + cancionAleatoria.getGenero());
                System.out.println("Artista: " + cancionAleatoria.getArtista());
                System.out.println("Letra:");
                Funciones.reproducirCancion(cancionAleatoria);
				break;
			}
			else if (opcion.equals("4")){
				System.out.println("Sesión cerrada.");
			} else {
				System.out.println("Opción no válida. Intente de nuevo.");
			}

		}
	}

}