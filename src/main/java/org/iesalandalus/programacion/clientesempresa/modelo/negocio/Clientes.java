package org.iesalandalus.programacion.clientesempresa.modelo.negocio;
import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.clientesempresa.modelo.dominio.Cliente;

public class Clientes {

    int capacidad;
    int tamano;
    Cliente[] clientes;

    public Clientes(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        clientes = new Cliente[capacidad];
    }

    public int getTamano() {
        return tamano;
    }

    public int getCapacidad() {
        return capacidad;
    }

    private boolean tamanoSuperado(int tam) {
        return tam >= tamano;
    }

    private boolean capacidadSuperada(int cap) {
        return cap >= capacidad;
    }

    private int buscarIndice(Cliente cliente) {
        int indice = tamano + 1;
        boolean encontrado = false;
        for (int i = 0; i < tamano && !encontrado; i++) {
            if (clientes[i].equals(cliente)) {
                encontrado = true;
                indice = i;
            }
        }
        return indice;
    }

    public void insertar(Cliente cliente) throws OperationNotSupportedException {
        if (cliente == null) {
            throw new IllegalArgumentException("ERROR: No se puede insertar un cliente nulo.");
        }
        if (capacidadSuperada(tamano)) {
            throw new IllegalArgumentException("ERROR: El número máximo de clientes ha sido alcanzado.");
        }
        int indice = buscarIndice(cliente);
        if (tamanoSuperado(indice)) {
            clientes[tamano] = new Cliente(cliente);
        } else {
            throw new IllegalArgumentException("ERROR: Ya existe un cliente con ese DNI.");
        }
        tamano++;
    }

    public Cliente buscar(Cliente cliente) {
        if (cliente == null) {
            throw new NullPointerException("ERROR: La búsqueda no puede ser realizada con un cliente nulo.");
        }
        int indice = buscarIndice(cliente);
        if (tamanoSuperado(indice)) {
            return null;
        } else {
            return new Cliente(clientes[indice]);
        }
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        int i;
        for (i = indice; i < clientes.length - 1; i++) {
            clientes[i] = clientes[i + 1];
        }
        clientes[i] = null;
    }

    public void borrar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede borrar un cliente nulo.");
		}
		int indice = buscarIndice(cliente);
		if (tamanoSuperado(indice)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún cliente como el indicado.");
		}
		desplazarUnaPosicionHaciaIzquierda(indice);
		tamano--;
	}

    public Cliente[] get() {
        Cliente[] clientesGet = new Cliente[tamano];
        for (int i = 0; i < tamano; i++) {
            clientesGet[i] = new Cliente(clientes[i]);
        }
        return clientesGet;

    }
}
