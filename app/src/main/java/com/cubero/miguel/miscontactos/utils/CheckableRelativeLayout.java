package com.cubero.miguel.miscontactos.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase es para agregar un checkbox al listview de contactos al lado derecho para poder seleccionar contactos y editarlos/eliminarlos.
 */
public class CheckableRelativeLayout extends RelativeLayout implements Checkable {
    private boolean isChecked;
    private List<Checkable> checkableView;



    // Como el usuario está acostumbrado a pulsar la casilla entera sin necesidad de tocar el checkbox, vamos a implementar ciertos metodos
    // Para que al tocar la casilla, se activa/desactive el checkbox.


    public CheckableRelativeLayout(Context context) {
        super(context);
        inicializar(null);
    }

    public CheckableRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        inicializar(attrs);
    }

    public CheckableRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inicializar(attrs);
    }


    //<editor-fold desc="METODOS INTERFAZ CHECKABLE">
    @Override
    public void setChecked(boolean isChecked) {
        // Hace una busqueda para recopilar cuales están marcados como seleccionados.
        this.isChecked = isChecked;
        for(Checkable c: checkableView){
            c.setChecked(isChecked);
        }

    }

    @Override
    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public void toggle() {
        // Método para alternar entre si está seleccionado o no.
        this.isChecked = !this.isChecked;
        for(Checkable c: checkableView){
            c.toggle();
        }
    }
    //</editor-fold>


    // Metodo para cuando la pantalla ya ha sido totalmente inicializada.

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        // Vamos a buscar cuantos componentes hijos tenemos dentro de nuestro contenedor: nombre, direccion, etc... aunque el que nos interesa es el checkeable
        final int childCount = this.getChildCount();
        for(int i=0; i<childCount; i++){
            buscarComponentesCheckable(this.getChildAt(i));
        }
    }


    // Método recursivo que se va a llamar a si mismo.
    private void buscarComponentesCheckable(View view) {
        // Primero vemos si la vista que recibimos es de checckeable y en ese caso lo agregamos.
        if( view instanceof Checkable){
            this.checkableView.add((Checkable) view);
        }
        // Si es un viewGroup, hay que volver a llamar para saber cuanto shijos hay dentro de este viewgroup
        if( view instanceof ViewGroup){
            final ViewGroup vg =  (ViewGroup) view;
            final int childCount = vg.getChildCount();
            for(int i=0; i<childCount; i++){
                buscarComponentesCheckable(vg.getChildAt(i));
            }
        }
    }


    // Método de inicialización
    private void inicializar(AttributeSet attrs){
        // Cuando se crea pro primera vez la interfaz para un contacto, le decimos que NO está seleccionado.
        this.isChecked = false;
        this.checkableView = new ArrayList<Checkable>();
    }
}
