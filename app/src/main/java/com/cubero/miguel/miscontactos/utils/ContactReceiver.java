package com.cubero.miguel.miscontactos.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Miguel on 16/2/15.
 * Esta clase va a implementarla estructura del Boardcast Receiver, ya que la documentacion lo recomienda si queremos tener comunicacion entre fragmentos.
 * Va a contener 3 ctes que me va indicar cual es la operacion que se está realizando.
 */
public class ContactReceiver extends BroadcastReceiver {

       public static final int CONTACTO_AGREGADO = 1;
       public static final int CONTACTO_ELIMINADO = 2;
       public static final int CONTACTO_ACTUALIZADO = 3;

    private final ArrayAdapter<Contacto> adapter;

    public ContactReceiver(ArrayAdapter<Contacto> adapter) {
        this.adapter = adapter;
    }

    @Override
    // Este método se va a llamar cada vez que el usuario le de a agregar/modificar/eliminar contacto.
    public void onReceive(Context context, Intent intent) {
        int operacion = intent.getIntExtra("operacion",-1); // Por defecto le damos un valor de -1 (que no existe)
        switch (operacion){
            case CONTACTO_AGREGADO:
                agregarContacto(intent);
                break;
            case CONTACTO_ELIMINADO:
                eliminarContacto(intent);
                break;
            case CONTACTO_ACTUALIZADO:
                actualizarContacto(intent);
                break;
        }
    }

    // Vamos a hacer que los fragments se comuniquen a través de un BroadCastReceiver

    private void agregarContacto(Intent intent) {
        Contacto contacto = (Contacto) intent.getSerializableExtra("datos");
        adapter.add(contacto);
    }


    private void eliminarContacto(Intent intent) {
        ArrayList<Contacto> lista = (ArrayList<Contacto>) intent.getSerializableExtra("datos");
        for( Contacto c: lista) adapter.remove(c);
    }


    private void actualizarContacto(Intent intent) {
        Contacto contacto = (Contacto) intent.getSerializableExtra("datos");
        int posicion = adapter.getPosition(contacto);
        adapter.insert(contacto,posicion);
    }
}
