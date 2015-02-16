package com.cubero.miguel.miscontactos.utils;

import java.io.Serializable;

/**
 * Created by Miguel on 15/2/15.
 */
public class Contacto implements Serializable {

    private String nombre, direccion, telefono, email;
    // Ruta a la imagen que el usuario ha seleccionado.
    private String imageUri; // No es posible serializar objetos URI, por eso lo ponemos tipos String.

    public Contacto(String nombre, String telefono, String email, String direccion, String imageUri) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.imageUri = imageUri;

    }

    //<editor-fold desc="GETTER METHODS">


    public String getImageUri() {
        return imageUri;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }
    //</editor-fold>

    //<editor-fold desc="SETTER METHODS">


    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    //</editor-fold>


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contacto contacto = (Contacto) o;

        if (!direccion.equals(contacto.direccion)) return false;
        if (!email.equals(contacto.email)) return false;
        if (!imageUri.equals(contacto.imageUri)) return false;
        if (!nombre.equals(contacto.nombre)) return false;
        if (!telefono.equals(contacto.telefono)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nombre.hashCode();
        result = 31 * result + direccion.hashCode();
        result = 31 * result + telefono.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + imageUri.hashCode();
        return result;
    }
}
