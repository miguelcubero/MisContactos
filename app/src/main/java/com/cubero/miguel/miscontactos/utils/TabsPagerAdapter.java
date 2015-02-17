package com.cubero.miguel.miscontactos.utils;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import com.cubero.miguel.miscontactos.CrearContactoFragment;
import com.cubero.miguel.miscontactos.ListaContactosFragment;

/**
 * Created by Miguel on 17/2/15.
 */

// Extendemos de la clase que se va a encargar de la manipulacion de los fragmentos en cada pestaña
public class TabsPagerAdapter extends FragmentPagerAdapter{


    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Devuelve el número de fragmentos con los que estamos trabajando (actualmente nosotros solo tenemos 2.
     */
    @Override
    public int getCount() {
        return 2;
    }

    /**
     * Aquí vamos a detectar que pestaña estamos seleccionando y así poder cargar un fragment u otro.
     */
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new CrearContactoFragment();
            case 1: return new ListaContactosFragment();
        }
        return null;
    }
}
