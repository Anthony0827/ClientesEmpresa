package org.iesalandalus.programacion.clientesempresa;

import java.time.LocalDate;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.clientesempresa.modelo.dominio.Cliente;
import org.iesalandalus.programacion.clientesempresa.modelo.negocio.Clientes;
import org.iesalandalus.programacion.clientesempresa.vista.Consola;
import org.iesalandalus.programacion.clientesempresa.vista.Opcion;

public class MainApp {

    private static final int NUM_MAX_CITAS = 10;
    private static Clientes listaClientes = new Clientes(NUM_MAX_CITAS);

    public static void main(String[] args) {
        Opcion opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            System.out.println("");
            ejecutarOpcion(opcion);
        } while (opcion != Opcion.SALIR);
        System.out.println("Saliendo...");
    }

    private static void ejecutarOpcion(Opcion opcion) {
        switch (opcion) {
        case INSERTAR_CLIENTE:
            insertarCliente();
            break;
        case BUSCAR_CLIENTE:
            buscarCliente();
            break;
        case BORRAR_CLIENTE:
            borrarCliente();
            break;
        case MOSTRAR_CLIENTES_FECHA:
            mostrarClientesFecha();
            break;
        case MOSTRAR_CLIENTES:
            mostrarClientes();
            break;
        default:
            break;
        }
    }

    private static void insertarCliente() {
        try {
            listaClientes.insertar(Consola.leerCliente());
            System.out.println("El cliente ha sido agregado exitosamente.");
        } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void buscarCliente() {
        try {
            Cliente cliente = new Cliente(Consola.leerCliente());
            cliente = listaClientes.buscar(cliente);
            if (cliente != null) {
                System.out.println("Cliente encontrado: " + cliente);
            } else {
                System.out.println("No se ha encontrado ningún cliente.");
            }
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void borrarCliente() {
        try {
            Cliente cliente = new Cliente(Consola.leerCliente());
            listaClientes.borrar(cliente);
            System.out.println("El cliente ha sido eliminado exitosamente.");
        } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }


    
    public static void mostrarClientesFecha() {
        Cliente[] clientes = listaClientes.get();
        int citasMostradas = 0;
        LocalDate fecha = Consola.leerFechaNacimiento();
        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i].getFechaNacimiento().equals(fecha)) {
                System.out.println(clientes[i]);
                citasMostradas++;
            }
        }
        if (citasMostradas == 0) {
            System.out.println("No existen clientes con esa fecha de nacimiento.");
        } else {
            System.out.println("");
        }
    }		

    private static void mostrarClientes() {
        int citasMostradas = 0;
        Cliente[] clientes = listaClientes.get();
        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i] != null) {
                System.out.println(clientes[i]);
                citasMostradas++;
            }
        }
        if (citasMostradas == 0) {
            System.out.println("No existen clientes.");
        }
    }

}