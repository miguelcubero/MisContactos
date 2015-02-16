package com.cubero.miguel.miscontactos;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
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


    public void onClick(View view) {
        agregarContactos(
                txtNombre.getText().toString(),
                txtTelefono.getText().toString(),
                txtEmail.getText().toString(),
                txtDireccion.getText().toString(),
                (Uri) imgViewContacto.getTag()
        ); // Obtenemos el atributo TAG con la URI de la imagen
        String msg = String.format("%s ha sido agregado a la lista!",txtNombre.getText());
        btnAgregar.setEnabled(false);
        limpiarCampos();


    }



    private void agregarContactos(String nombre, String telefono, String email, String direccion, Uri imageUri) {
       Contacto nuevo = new Contacto(nombre,telefono,email,direccion,imageUri);
       adapter.add(nuevo);

    }

    private void limpiarCampos() {
        txtNombre.getText().clear();
        txtDireccion.getText().clear();
        txtEmail.getText().clear();
        txtTelefono.getText().clear();
        // Reestablecemos la imagen de contacto predefinida.
        imgViewContacto.setImageResource(R.drawable.contacto2);
        txtNombre.requestFocus();
    }

    // Este método es para escoger una foto de nuestra galería del telefono
    public void onImgClick(View view) {
        Intent intent = null;
        // Verificamos la versión de la plataforma, ya que no en todas se puede hacer.
        if (Build.VERSION.SDK_INT < 19){
            // Android JellyBean 4.3 y anteriores.
            intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
        }else{
            // Android KitKar 4.4 o superiores.
            intent = new Intent();
            intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
        }
        intent.setType("image/*");
        startActivityForResult(intent, request_code);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if( resultCode ==  RESULT_OK && requestCode == request_code){
            imgViewContacto.setImageURI(data.getData());
            // Utilizamos el atributo TAG para almacenar la URI al archivo seleccionado. Los TAG son de tipo Object y puede almacenar cualquier objeto que queramos.
            imgViewContacto.setTag(data.getData());

        }
    }
}
