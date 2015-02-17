package com.cubero.miguel.miscontactos;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

// Implementamos la interfaz para que escuche cuando se pulse en las pestañas
public class MainActivity extends Activity implements View.OnTouchListener  {


    private ImageButton btnCrearContactos,btnListaContactos,btnEliminarContactos,btnSincronizar;
    private CrearContactoFragment fragmentoCrear;
    private ListaContactosFragment fragmentoLista;
    private ActionBar actionBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializaActionBar();
        inicializaComponentes();
    }



    private void inicializaActionBar() {
        actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(false);
    }

    private void inicializaComponentes() {
        btnCrearContactos = (ImageButton) findViewById(R.id.btn_crear_contacto);
        btnCrearContactos.setOnTouchListener(this);

        btnEliminarContactos = (ImageButton) findViewById(R.id.btn_eliminar_contacto);
        btnEliminarContactos.setOnTouchListener(this);

        btnListaContactos = (ImageButton) findViewById(R.id.btn_lista_contactos);
        btnListaContactos.setOnTouchListener(this);

        btnSincronizar = (ImageButton) findViewById(R.id.btn_sincronizar);
        btnSincronizar.setOnTouchListener(this);

        cargarFragmento(getFragmentoLista());
    }

    private void cargarFragmento(Fragment fragmento) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.contenedor,fragmento);
        transaction.commit();
    }


    //<editor-fold desc="INICIALIZACION BAJO DEMANDA (LAZY)">
    public ListaContactosFragment getFragmentoLista() {
        if( fragmentoLista == null ){
            fragmentoLista = new ListaContactosFragment();
        }
        return fragmentoLista;
    }


    public CrearContactoFragment getFragmentoCrear() {
        if( fragmentoCrear == null ){
            fragmentoCrear = new CrearContactoFragment();
        }
        return fragmentoCrear;
    }
    //</editor-fold>

    @Override
    public boolean onTouch(View view, MotionEvent evt) {
        ImageButton btn = (ImageButton) view;
        // Comprobamos el estado del boton
        int actionMasked = evt.getActionMasked();
        switch (actionMasked){
            case MotionEvent.ACTION_DOWN:
                btn.setColorFilter(R.color.entintado_oscuro);
                btn.invalidate();
                cambiarFragmento(btn);
                break;
            case MotionEvent.ACTION_UP:
                btn.clearColorFilter();
                btn.invalidate();
                break;
        }


        return false;
    }

    private void cambiarFragmento(View view) {
        switch (view.getId()){
            case R.id.btn_crear_contacto:
                cargarFragmento(getFragmentoCrear());
                break;
            case R.id.btn_lista_contactos:
                cargarFragmento(getFragmentoLista());
                break;

        }


    }
}
