package com.cubero.miguel.miscontactos.utils;

import android.net.Uri;

/**
 * Created by Miguel on 15/2/15.
 */
public class Contacto {

    private String nombre, direccion, telefono, email;
    // Ruta a la imagen que el usuario ha seleccionado.
    private Uri imageUri;

    public Contacto(String nombre, String telefono, String email, String direccion, Uri imageUri) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.imageUri = imageUri;

    }

    //<editor-fold desc="GETTER METHODS">


    public Uri getImageUri() {
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


    public void setImageUri(Uri imageUri) {
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


}
