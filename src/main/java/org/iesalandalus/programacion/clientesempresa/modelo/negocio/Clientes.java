package org.iesalandalus.programacion.clientesempresa.modelo.negocio;
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

    public Cliente[] get() {
        Cliente[] clientesGet = new Cliente[tamano];
        for (int i = 0; i < tamano; i++) {
            clientesGet[i] = new Cliente(clientes[i]);
        }
        return clientesGet;

    }

    public int getTamano() {
        return tamano;
    }

    public int getCapacidad() {
        return capacidad;
    }

    private boolean tamanoSuperado(int t) {
        return t >= tamano;
    }

    private boolean capacidadSuperada(int cs) {
        return cs >= capacidad;
    }

    public void insertar(Cliente cliente) throws IllegalArgumentException {
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

    public void borrar(Cliente cliente) throws IllegalArgumentException {
        if (cliente == null) {
            throw new NullPointerException("ERROR: El cliente no puede ser nulo al momento de insertarlo.");
        }
        int indice = buscarIndice(cliente);
        if (tamanoSuperado(indice)) {
            throw new IllegalArgumentException("ERROR: No se ha encontrado ningún cliente con los datos especificados.");
        }
        desplazarUnaPosicionHaciaIzquierda(indice);
        tamano--;
    }

}