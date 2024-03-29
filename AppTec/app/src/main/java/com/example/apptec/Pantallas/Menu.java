package com.example.apptec.Pantallas;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.apptec.Clases.ObjetosMartes;
import com.example.apptec.Clases.Utilidades;
import com.example.apptec.Fragments.AcercaFragment;
import com.example.apptec.Fragments.ContenedorFragment;
import com.example.apptec.Fragments.DiaFragment;
import com.example.apptec.Fragments.LunesFragment;
import com.example.apptec.Fragments.MartesFragment;
import com.example.apptec.Fragments.PerfilFragment;
import com.example.apptec.Modelos.ModeloPerfil;
import com.example.apptec.R;

public class Menu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
ContenedorFragment.OnFragmentInteractionListener{

    TextView nombretxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



        Fragment fragment = new DiaFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.conten_menu,fragment).commit();


        if (Utilidades.validaPantalla = true){
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
            Utilidades.validaPantalla = false;

        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, (android.view.Menu) menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;
        boolean fragmenSeleccionado = false;

        if (id == R.id.nav_camera) {
            // Handle the camera action

            fragment = new DiaFragment();
            fragmenSeleccionado = true;


        } else if (id == R.id.nav_gallery) {

            fragment = new ContenedorFragment();
            fragmenSeleccionado = true;



        } else if (id == R.id.nav_slideshow) {

            fragment = new PerfilFragment();
            fragmenSeleccionado = true;



        } else if (id == R.id.nav_share) {
            fragment = new AcercaFragment();
            fragmenSeleccionado = true;



        } else if (id == R.id.nav_send) {

            ObjetosMartes objetosMartes = new ObjetosMartes();
            objetosMartes.Limpiar_Martes();

            Intent intent1 = new Intent();
            intent1.setClass(Menu.this, Logeo.class);
            Menu.this.startActivity(intent1);
            Menu.this.finish();


        }
        if (fragmenSeleccionado == true){
            getSupportFragmentManager().beginTransaction().replace(R.id.conten_menu, fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK){
            moveTaskToBack(true);
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
