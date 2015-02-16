package com.cubero.miguel.miscontactos;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.cubero.miguel.miscontactos.utils.Contacto;
import com.cubero.miguel.miscontactos.utils.TextChangedListener;

/**
 * Created by Miguel on 16/2/15.
 */
public class CrearContactoFragment extends Fragment implements View.OnClickListener {

    private EditText txtNombre,txtTelefono,txtDireccion,txtEmail;

    private ImageView imgViewContacto;

    private Button btnAgregar;
    // Le ponemos el valor de 1. Lo suyo es tener un request_code diferente para cada una de las distintas notificaciones que tengamos en nuestra app
    private int request_code = 1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmento_crear_contacto, container, false); // Le colocamos false, porque no queremos que lo adjunte al contenedor raiz.
        inicializarComponentesUI(rootView);
        return rootView;
    }


    // Inicializar Componentes
    private void inicializarComponentesUI(final View view) {
        txtNombre = (EditText) view.findViewById(R.id.cmpNombre);
        txtTelefono = (EditText) view.findViewById(R.id.cmpTelefono);
        txtDireccion = (EditText) view.findViewById(R.id.cmpDireccion);
        txtEmail = (EditText) view.findViewById(R.id.cmpEmail);
        imgViewContacto = (ImageView) view.findViewById(R.id.imgViewContacto);


        txtNombre.addTextChangedListener(new TextChangedListener(){
            @Override
            // Cada vez que se escriba, borre o lo que sea en el textview se va a ver reflejado en este evento
            public void onTextChanged(CharSequence seq, int start, int before, int count) {
                btnAgregar = (Button) view.findViewById(R.id.btnAgregar);
                // seq es la secuencia de caracteres del Edittext. Si no está vacía, le decimos que se habilite el boton.
                btnAgregar.setEnabled(!seq.toString().trim().isEmpty());


            }
        });
    }


    @Override
    public void onClick(View view) {
        // Si no es el boton agregar, entonces debe ser la imagen de contacto.
        if ( view == btnAgregar ){
            agregarContactos(
                    txtNombre.getText().toString(),
                    txtTelefono.getText().toString(),
                    txtEmail.getText().toString(),
                    txtDireccion.getText().toString(),
                    String.valueOf(imgViewContacto.getTag())
            ); // Obtenemos el atributo TAG con la URI de la imagen
            String msg = String.format("%s ha sido agregado a la lista!",txtNombre.getText());
            Toast.makeText(view.getContext(),msg,Toast.LENGTH_LONG).show();
            btnAgregar.setEnabled(false);
            limpiarCampos();
        }else if( view == imgViewContacto){

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
    }

    private void agregarContactos(String nombre, String telefono, String email, String direccion, String imageUri) {
        Contacto nuevo = new Contacto(nombre,telefono,email,direccion,imageUri);
        //TODO: Corregir adapter.add(nuevo);

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if( resultCode == Activity.RESULT_OK && requestCode == request_code){
            imgViewContacto.setImageURI(data.getData());
            // Utilizamos el atributo TAG para almacenar la URI al archivo seleccionado. Los TAG son de tipo Object y puede almacenar cualquier objeto que queramos.
            imgViewContacto.setTag(data.getData());
        }
    }
}
