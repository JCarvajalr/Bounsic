package co.edu.upb.Bounsic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Usuario {
	Scanner sc = new Scanner(System.in);
	private String nombreUsuario;
	private String contraseña;
	private String correoElectronico;
	private String numeroTarjeta;
	private int codigoSeguridad;

	public Usuario() {
		nombreUsuario = "";
		contraseña = "";
		correoElectronico = "";
		numeroTarjeta = "";
		codigoSeguridad = 0;
	}
	
	
	public boolean iniciarSesion() {
		pedirUsuario();
		pedirContrasena();
		System.out.println("Sesión iniciada correctamente.");
		
		return true;
	}
	
	public boolean registrarUsuario() {
		pedirUsuario();
		pedirContrasena();
		System.out.print("Confirme su contraseña: ");
		while (!contraseña.equals(sc.nextLine())) {
			System.out.println("Las contraseñas no coinciden, ingresela nuevamente:");
		}
		System.out.println("Contraseña confirmada.");
		pedirCorreoElectronico();
		pedirTarjeta();
		/* Requisitos Tarjeta:
		 	Número de tarjeta.
			Fecha de vencimiento de la tarjeta.
			Codigo de seguridad de la tarjeta.
		 */
		return true;
	}
	
	private String pedirUsuario() {
		System.out.print("Ingrese su nombre de usuario: ");
		nombreUsuario = sc.nextLine();
		while (!FuncionesUsuario.verificarCadena(nombreUsuario)) {
			System.out.println("Nombre de usuario no valido. Debe contener al menos 5 caracteres sin caracteres especiales.");
			System.out.print("Ingrese su nombre de usuario: ");
			nombreUsuario = sc.nextLine();
		}
		return nombreUsuario;
	}	
	
	private String pedirContrasena() {
		System.out.print("Ingrese su contraseña: ");
		contraseña = sc.nextLine();
		while (!FuncionesUsuario.verificarCadena(contraseña)) {
			System.out.println("Contraseña no valida. Debe contener al menos 5 caracteres sin caracteres especiales.");
			System.out.print("Ingrese su contraseña nuevamente: ");
			contraseña = sc.nextLine();
		}
		return contraseña;
	}
	
	private String pedirCorreoElectronico() {
		System.out.print("Ingrese su correo electrónico: ");
		correoElectronico = sc.nextLine();
		while (!FuncionesUsuario.verificarCorreo(correoElectronico)) {
			System.out.print("Correo electronico no valido.\nIngreselo nuevamente:");
			correoElectronico = sc.nextLine();
		}
		return correoElectronico;
	}
	
	private boolean pedirTarjeta() {
		pedirNumeroTarjeta();
		pedirFechaDeVencimiento();
		pedirCodigoSeguridad();
		
		return true;
	}
	
	private String pedirNumeroTarjeta() {
		System.out.print("Ingrese su número de tarjeta de crédito (16 dígitos): ");
		numeroTarjeta = sc.nextLine();
		while (!FuncionesUsuario.verificarNumeroTarjeta(numeroTarjeta)) {
			System.out.println("Numero de tarjeta invalido.\nIngreselo nuevamente:");
			numeroTarjeta = sc.nextLine();
		}
		
		return numeroTarjeta;
	}
	
	private Date pedirFechaDeVencimiento() {
		System.out.print("Ingrese la fecha de vencimiento de la tarjeta (DD/MM/AAAA): ");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		Date fechaDeVencimiento;
		while (true) {
			try {
				fechaDeVencimiento = sdf.parse(sc.nextLine());
				Calendar fechaVencimiento = Calendar.getInstance();
				fechaVencimiento.setTime(fechaDeVencimiento);
				if (!FuncionesUsuario.verificarFechaDeVencimiento(fechaDeVencimiento)) {
					System.out.println("La fecha de vencimiento debe ser de hoy en adelante. Intente de nuevo.");
					continue;
				}
				break;
			} catch (ParseException e) {
				System.out.println("La fecha ingresada no es válida. Intente de nuevo.");

			}
		}
		return fechaDeVencimiento;
	}
	
	private int pedirCodigoSeguridad() {
		System.out.print("Ingrese el codigo de seguridad de su tarjeta: ");
		while (true) {
			try {
				codigoSeguridad = sc.nextInt();
				while (!FuncionesUsuario.verificarCodigoSeguridad(codigoSeguridad)) {
					System.out.println("El codigo de seguridad debe tener 3 o 4 digitos. Intente de nuevo.");
					System.out.print("Ingrese nuevamente el codigo: ");
					codigoSeguridad = sc.nextInt();
				}
				break;
			} catch (InputMismatchException e) {
				System.out.println("La entrada no es un número entero. Intente de nuevo.");
				System.out.print("Ingrese nuevamente el codigo: ");
				sc.nextLine();
			}
		}
		return codigoSeguridad;
	}
}
