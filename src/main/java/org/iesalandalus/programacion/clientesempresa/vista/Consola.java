package org.iesalandalus.programacion.clientesempresa.vista;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import org.iesalandalus.programacion.clientesempresa.modelo.dominio.Cliente;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

    public static void mostrarMenu() {
        System.out.println("Bienvenido al programa de gestión de clientes de la empresa.");
        System.out.println("Seleccione una opción:");
        System.out.println("1 - Añadir un nuevo cliente.");
        System.out.println("2 - Buscar un cliente existente.");
        System.out.println("3 - Eliminar un cliente.");
        System.out.println("4 - Ver clientes según fecha de nacimiento.");
        System.out.println("5 - Ver todos los clientes registrados.");
        System.out.println("0 - Salir del programa.");
        System.out.println("");
    }
    public static Opcion elegirOpcion() {
        int opcion;
        do {
            System.out.println("Elije una opción del 0 al 5:");
            System.out.println("");
            opcion = Entrada.entero();
        } while (opcion < 0 || opcion > 5);

        return Opcion.values()[opcion];
    }

    public static Cliente leerCliente() {
        String nombre, dni, correo = null, telefono;
        LocalDate fechaNacimiento = null;
        Cliente cliente = null;
        boolean error;
        do {

            error = false;

            System.out.println("Ingresa el nombre del cliente:");
            nombre = Entrada.cadena();

            System.out.println("Ingresa el DNI del cliente:");
            dni = Entrada.cadena();

            System.out.println("Ingresa el correo electrónico del cliente:");
            correo = Entrada.cadena();

            System.out.println("Ingresa el número de teléfono del cliente:");
            telefono = Entrada.cadena();

            System.out.println("Ingresa la fecha de nacimiento del cliente (dd/mm/yyyy):");
            fechaNacimiento = leerFechaNacimiento();

            try {
                cliente = new Cliente(nombre, dni, correo, telefono, fechaNacimiento);

            } catch (NullPointerException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
                error = true;
            }
        } while (error);
        return cliente;
    }

    public static Cliente leerClienteDni() {
        Cliente cliente = Consola.leerCliente();
        return new Cliente(cliente);
    }

    public static LocalDate leerFechaNacimiento() {
        LocalDate fecha = null;
        do {
            System.out.println("Introduce la fecha (dd/MM/yyyy):");
            try {
                fecha = LocalDate.parse(Entrada.cadena(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (DateTimeParseException e) {
                fecha = null;
            }
        } while (fecha == null);
        return fecha;
    }

}