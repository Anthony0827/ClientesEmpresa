package org.iesalandalus.programacion.clientesempresa.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Cliente {

    private static final String ER_CORREO = "^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$";
    private static final String ER_TELEFONO = "[69]\\d{8}";
    private static final String ER_DNI = "([XY]?)([0-9]{7,8})([A-Za-z])";
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd//yyyy");
    private String nombre;
    private String dni;
    private String correo;
    private String telefono;
    private LocalDate fechaNacimiento;

    public Cliente(String nombre, String dni, String correo, String telefono, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.dni = dni;
        this.correo = correo;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
    }

    private String formateaNombre(String nombre) {
        nombre = nombre.trim(); // elimina los espacios en blanco al principio y al final
        String[] palabras = nombre.split(" "); // divide el nombre en palabras
        StringBuilder sb = new StringBuilder();
        for (String palabra : palabras) {
            sb.append(Character.toUpperCase(palabra.charAt(0))); // pone la primera letra en mayúscula
            sb.append(palabra.substring(1).toLowerCase()); // pone las demás letras en minúscula
            sb.append(" "); // añade un espacio entre cada palabra
        }
        return sb.toString().trim(); // elimina el último espacio y devuelve el nombre formateado
    }

    /*private String formateaNombre(String nombre) {
        String[] palabras = nombre.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String palabra: palabras) {
            sb.append(Character.toUpperCase(palabra.charAt(0)));
            sb.append(palabra.substring(1).toLowerCase());
            sb.append(" ");
        }
        return sb.toString().trim();
    }*/
    
   

    private boolean comprobarLetraDni(String dni) {
        String numeros = "";
        String letra = "";
        String nuevoDni = dni.replaceAll("\\s+", "").toUpperCase();
        for (int i = 0; i < nuevoDni.length(); i++) {
            char c = nuevoDni.charAt(i);
            if (Character.isDigit(c)) {
                numeros += c;
            } else if (Character.isLetter(c)) {
                letra = Character.toString(c);
            }
        }
        boolean comprobarDni = false;
        int dniInt = Integer.valueOf(numeros);
        int resto = dniInt % 23;
        char letraObtenida = "TRWAGMYFPDXBNJZSQVHLCKE".charAt(resto);
        if (letra.equals(Character.toString(letraObtenida))) {
            comprobarDni = true;
        }
        return comprobarDni;
    }

    private String getIniciales() {
        String iniciales = "";
        String[] nombreDividido = nombre.split("\s");
        for (int i = 0; i < nombreDividido.length; i++) {
            String primeraLetra = nombreDividido[i].substring(0, 1);
            primeraLetra = primeraLetra.toUpperCase();
            nombreDividido[i] = nombreDividido[i] = primeraLetra;
        }
        for (int i = 0; i < nombreDividido.length; i++) {
            iniciales = iniciales + nombreDividido[i];
        }
        return iniciales;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null) {
            throw new NullPointerException("ERROR: El nombre de un cliente no puede ser nulo.");
        }
        if (nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: El nombre de un cliente no puede estar vacío.");
        }
        this.nombre = formateaNombre(nombre);
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        if (dni == null) {
            throw new NullPointerException("ERROR: El dni de un cliente no puede ser nulo.");
        }
        if (!dni.matches(ER_DNI)) {
            throw new IllegalArgumentException("ERROR: El dni del cliente no tiene un formato válido.");
        }
        if (!comprobarLetraDni(dni)) {
            throw new IllegalArgumentException("ERROR: La letra del dni del cliente no es correcta.");
        }
        this.dni = dni;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        if (correo == null) {
            throw new NullPointerException("ERROR: El correo de un cliente no puede ser nulo.");
        }
        if (!correo.matches(ER_CORREO)) {
            throw new IllegalArgumentException("ERROR: El correo del cliente no tiene un formato válido.");
        }
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono == null) {
            throw new NullPointerException("ERROR: El teléfono de un cliente no puede ser nulo.");
        }
        if (!telefono.matches(ER_TELEFONO)) {
            throw new IllegalArgumentException("ERROR: El teléfono del cliente no tiene un formato válido.");
        }
        this.telefono = telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) {
            throw new NullPointerException("ERROR: La fecha de nacimiento de un cliente no puede ser nula.");
        }
        this.fechaNacimiento = fechaNacimiento;
    }

    public Cliente(Cliente cliente) {
        if (cliente == null) {
            throw new NullPointerException("ERROR: No es posible copiar un cliente nulo.");
        }
        this.nombre = cliente.getNombre();
        this.dni = cliente.getDni();
        this.correo = cliente.getCorreo();
        this.telefono = cliente.getTelefono();
        this.fechaNacimiento = cliente.getFechaNacimiento();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cliente other = (Cliente) obj;
        return Objects.equals(correo, other.correo) && Objects.equals(dni, other.dni) &&
            Objects.equals(fechaNacimiento, other.fechaNacimiento) && Objects.equals(nombre, other.nombre) &&
            Objects.equals(telefono, other.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(correo, dni, fechaNacimiento, nombre, telefono);
    }

    @Override
    public String toString() {
        return "nombre=" + nombre + " (" + getIniciales() + ")" + ", DNI=" + dni + ", correo=" + correo + ", teléfono=" +
            telefono + ", fecha nacimiento=" + fechaNacimiento.format(FORMATO_FECHA);
    }

}