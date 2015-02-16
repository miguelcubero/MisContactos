package com.cubero.miguel.miscontactos;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;

import com.cubero.miguel.miscontactos.utils.ContacListAdapter;
import com.cubero.miguel.miscontactos.utils.Contacto;
import com.cubero.miguel.miscontactos.utils.TextChangedListener;

import java.util.ArrayList;


public class MainActivity extends Activity {

    private EditText txtNombre,txtTelefono,txtDireccion,txtEmail;
    private ArrayAdapter<Contacto> adapter;
    private ImageView imgViewContacto;
    private ListView contactsListView;
    private Button btnAgregar;
    // Le ponemos el valor de 1. Lo suyo es tener un request_code diferente para cada una de las distintas notificaciones que tengamos en nuestra app
    private int request_code = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarComponentesUI();
        inicializarListaContactos();
        inicializarTabs();

    }

    private void inicializarListaContactos() {
        adapter = new ContacListAdapter(this, new ArrayList<Contacto>());
        contactsListView.setAdapter(adapter);

    }


    private void inicializarTabs() {
        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec spec = tabHost.newTabSpec("tab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Crear");
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("tab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Lista");
        tabHost.addTab(spec);

    }

    // Inicializar Componentes
    private void inicializarComponentesUI() {
        txtNombre = (EditText) findViewById(R.id.cmpNombre);
        txtTelefono = (EditText) findViewById(R.id.cmpTelefono);
        txtDireccion = (EditText) findViewById(R.id.cmpDireccion);
        txtEmail = (EditText) findViewById(R.id.cmpEmail);
        contactsListView = (ListView) findViewById(R.id.listView);
        imgViewContacto = (ImageView) findViewById(R.id.imgViewContacto);


        txtNombre.addTextChangedListener(new TextChangedListener(){
            @Override
            // Cada vez que se escriba, borre o lo que sea en el textview se va a ver reflejado en este evento
            public void onTextChanged(CharSequence seq, int start, int before, int count) {
                btnAgregar = (Button) findViewById(R.id.btnAgregar);
                // seq es la secuencia de caracteres del Edittext. Si no está vacía, le decimos que se habilite el boton.
                btnAgregar.setEnabled(!seq.toString().trim().isEmpty());


            }
        });
    }

}
