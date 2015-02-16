package com.cubero.miguel.miscontactos;

import android.app.Fragment;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cubero.miguel.miscontactos.utils.ContacListAdapter;
import com.cubero.miguel.miscontactos.utils.ContactReceiver;
import com.cubero.miguel.miscontactos.utils.Contacto;

import java.util.ArrayList;

/**
 * Created by Miguel on 16/2/15.
 */
public class ListaContactosFragment extends Fragment {
    private ArrayAdapter<Contacto> adapter;
    private ListView contactsListView;
    private ContactReceiver receiver;

    /**
     Inicializa la Estructura y termina conectar nuestro XML
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmento_lista_contactos,container,false);
        inicializarComponentes(rootView);
        setHasOptionsMenu(true); // Habilita el ActionBar de este fragment para que tenga botones.
        return rootView;
    }


    /**
     Se va a ejecutar cada vez que aparezca el fragmento en nuestro dispositivo.
     */
    @Override
    public void onResume() {
        super.onResume();
        receiver = new ContactReceiver(adapter);
        // Aquí está indicando que sólo quiere los que estén escuchando por listacontactos.
        getActivity().registerReceiver(receiver,new IntentFilter("listacontactos"));
    }

    /**
        Cuando pasa a modo pausa, le quitamos el registro, para no ocupar memoria
     */
    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(receiver);
    }

    private void inicializarComponentes(View view) {
        contactsListView = (ListView) view.findViewById(R.id.listView);
        // Como el fragmento puede estar dentro de cualquier actividad, no podemos apuntar a una actividad en especifico
        // Por eso ponemos getActivity()
        adapter = new ContacListAdapter(getActivity(), new ArrayList<Contacto>());
        // Se configura para que el adapter notifique cambios en el dataset automáticamente.
        adapter.setNotifyOnChange(true);
        contactsListView.setAdapter(adapter);

    }


    /**

     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main,menu);

    }

    /**

     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_eliminar_contacto:
                eliminarContacto(item);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void eliminarContacto(MenuItem item) {
        // Esto nos va a permitir saber todos los que han sido seleccionados dentro de la lista de contactos.
        SparseBooleanArray array = contactsListView.getCheckedItemPositions();
        ArrayList<Contacto> seleccion = new ArrayList<Contacto>();
        for (int i=0; i<array.size(); i++ ){
            // posicion del contacto dentro del adaptador.
            int posicion = array.keyAt(i);
            if(array.valueAt(i)){
                seleccion.add(adapter.getItem(posicion));
            }
            Intent intent = new Intent("listacontactos");
            intent.putExtra("operacion",ContactReceiver.CONTACTO_ELIMINADO);
            // Le pasamos la seleccion de contactos que han sido eliminados.
            intent.putExtra("datos",seleccion);
            getActivity().sendBroadcast(intent);
            // Limpiamos las opciones de Listview
            contactsListView.clearChoices();
        }
    }
}
