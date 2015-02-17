package com.cubero.miguel.miscontactos;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import com.cubero.miguel.miscontactos.utils.TabsPagerAdapter;

// IMplementamos la interfaz para que escuche cuando se pulse en las pestañas
public class MainActivity extends Activity implements ActionBar.TabListener, ViewPager.OnPageChangeListener {

    // Control de Fichas Tabs
    private ViewPager viewPager;
    // Va a ser una clase que va a administrar los componentes que vamos a tener dentro de nuestras pestañas
    private TabsPagerAdapter adapter;
    // Objeto de referencia a nuestro objeto ActionBar
    private android.support.v7.app.ActionBar actionBar;
    // Títulos de las pestañas que va a tener nuestro ActionBar
    private String[] titulos = {"Crear Contacto","Lista Contactos"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarTabs();

    }



    private void inicializarTabs() {
        // Sacamos el elemento raiz de nuestro nuevo xml limpito.
        viewPager = (ViewPager) findViewById(R.id.pager);
        // Continuamos con las pestañas.
        //actionBar = getActionBar();
        actionBar = getSupportActionBar();

        // El FragmentManager va a ser el administrador de los fragmentos que tenga nuestra actividad
        adapter = new TabsPagerAdapter(getFragmentManager());
        viewPager.setAdapter(adapter);
        // Le decimos que la primera pestaña o tab no va a estar enabled por defecto
        actionBar.setHomeButtonEnabled(false);
        // Y que el modo de navegacion es por pestañas.
        //actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Agregamos los títulos de cada una de las pestañas

        for(String nombre:titulos){
            android.support.v7.app.ActionBar.Tab tab = actionBar.newTab().setText(nombre);
            tab.setTabListener(this);
            actionBar.addTab(tab);
        }


        // Le decimos al ViewPager que su auditor va a ser la página principal.
        viewPager.setOnPageChangeListener(this);
    }

    //<editor-fold desc="METODOS VIEW CHANGE LISTENER">
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        actionBar.setSelectedNavigationItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    //</editor-fold>

    //<editor-fold desc="METODO TABS CHANGE LISTENER">
    @Override
    public void onTabSelected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    //</editor-fold>

}
