package com.example.apptec.Pantallas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apptec.Api.WebServiceApi;
import com.example.apptec.Api.WebServiceJWT;
import com.example.apptec.Fragments.DiaFragment;
import com.example.apptec.Fragments.JuevesFragment;
import com.example.apptec.Fragments.LunesFragment;
import com.example.apptec.Fragments.MartesFragment;
import com.example.apptec.Fragments.MiercolesFragment;
import com.example.apptec.Fragments.PerfilFragment;
import com.example.apptec.Fragments.SabadoFragment;
import com.example.apptec.Fragments.ViernesFragment;
import com.example.apptec.Modelos.ModeloHoy;
import com.example.apptec.Modelos.ModeloLogin;
import com.example.apptec.Modelos.Token;
import com.example.apptec.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Logeo extends AppCompatActivity {


    EditText mat,pass;
    Button mandar;

    public static int matricula, contrasena;
    public static int estado;
    public static String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logeo);

            mandar = findViewById(R.id.iniciar);


            mandar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                try{
                    mat = (EditText) findViewById(R.id.matricula);
                    pass = (EditText) findViewById(R.id.password);

                    matricula = Integer.parseInt(mat.getText().toString());
                    contrasena = Integer.parseInt(pass.getText().toString());

                    if (matricula == contrasena){

                        Call<Token> call = WebServiceJWT
                                .getInstance()
                                .createService(WebServiceApi.class)
                                .obtenerToken(matricula,contrasena,"password");

                        call.enqueue(new Callback<Token>() {
                            @Override
                            public void onResponse(Call<Token> call, Response<Token> response) {
                                if (response.code() == 200){
                                    Log.d("TAG1", "Acceso de token: "+response.body().getAccessToken());

                                    token = response.body().getAccessToken();

                                    ModeloHoy modeloHoy = new ModeloHoy();
                                    modeloHoy.setApm("");
                                    modeloHoy.setApp("");
                                    modeloHoy.setHorafin("");
                                    modeloHoy.setHorain("");
                                    modeloHoy.setMat("");
                                    modeloHoy.setSalon("");
                                    modeloHoy.setStud("");
                                    modeloHoy.setNom("");


                                    DiaFragment diaFragment = new DiaFragment();
                                    diaFragment.ObtenerDia(token);

                                    LunesFragment lunessFragment = new LunesFragment();
                                    lunessFragment.ObtenerLu(token);

                                    MartesFragment martesFragment = new MartesFragment();
                                    martesFragment.ObtenerLu(token);

                                    MiercolesFragment miercolesFragment = new MiercolesFragment();
                                    miercolesFragment.ObtenerMiercoles(token);

                                    JuevesFragment juevesFragment = new JuevesFragment();
                                    juevesFragment.ObtenerJueves(token);

                                    ViernesFragment viernesFragment = new ViernesFragment();
                                    viernesFragment.ObtenerViernes(token);

                                    SabadoFragment sabadoFragment = new SabadoFragment();
                                    sabadoFragment.ObtenerSabado(token);

                                    PerfilFragment perfilFragment = new PerfilFragment();
                                    perfilFragment.obteniendoDaos(token);


                                    mandar();

                                }else if (response.code() == 404) {
                                    Log.d("Tag1", "No autorizado");

                                    Toast.makeText(getApplicationContext(),"Datos incorrectos",Toast.LENGTH_SHORT).show();
                                    mat.setText("");
                                    pass.setText("");


                                } else {
                                    Log.d("Tag1", "No obtenido Token");
                                    Toast.makeText(getApplicationContext(),"Hay problemas estamos trabajando en esto",Toast.LENGTH_SHORT).show();

                                    mat.setText("");
                                    pass.setText("");

                                }
                            }

                            @Override
                            public void onFailure(Call<Token> call, Throwable throwable) {
                                Log.d("Error: ", "" + throwable);

                                Toast.makeText(getApplicationContext(),"No hay servicio",Toast.LENGTH_SHORT).show();
                            }
                        });

                    }else{
                        Toast.makeText(getApplicationContext(),"Sus datos son incorrectos",Toast.LENGTH_LONG).show();
                    }




                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Ingresa los datos",Toast.LENGTH_SHORT).show();

                }



                }
            });










    }

    public void mandar(){

        Intent intent = new Intent(getApplicationContext(), Menu.class);
        //intent.putExtra("took",token);
        startActivity(intent);

    }




}