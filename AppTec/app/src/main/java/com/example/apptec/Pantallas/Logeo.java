package com.example.apptec.Pantallas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
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
import com.github.ybq.android.spinkit.style.Wave;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Logeo extends AppCompatActivity {


    EditText mat,pass;
    Button mandar;
    ProgressBar progressBar;

    TextView txt_advertencia, txt_incorrecto, txt_sinacceso;
    LayoutInflater inflater;
    View layout_sinacceso, layout_advertencia, layout_incorrecto;
    ImageView img_incorrecto, img_advertencia, img_sinacceso;
    Toast toast;

    public static int matricula, contrasena;
    public static int estado;
    public static String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logeo);

        inflater = getLayoutInflater();

        layout_sinacceso = inflater.inflate(R.layout.toast_incorrecto,
                (ViewGroup)findViewById(R.id.toast_layout_incorrecto));
        layout_advertencia = inflater.inflate(R.layout.toast_advertencia,
                (ViewGroup)findViewById(R.id.totas_layout_advertencia));
        layout_incorrecto = inflater.inflate(R.layout.toast_error,
                (ViewGroup)findViewById(R.id.toast_layout_error));

        img_sinacceso = (ImageView)layout_sinacceso.findViewById(R.id.img_incorrecto);
        img_advertencia = (ImageView)layout_advertencia.findViewById(R.id.img_advertencia);
        img_incorrecto = (ImageView)layout_incorrecto.findViewById(R.id.img_error);

        txt_sinacceso = (TextView)layout_sinacceso.findViewById(R.id.incorrecto);
        txt_advertencia = (TextView)layout_advertencia.findViewById(R.id.txt_advertencia);
        txt_incorrecto = (TextView)layout_incorrecto.findViewById(R.id.txt_error);





            mandar = findViewById(R.id.iniciar);
            progressBar = (ProgressBar)findViewById(R.id.Skin);
                Wave wave = new Wave();
                progressBar.setVisibility(View.INVISIBLE);

            mandar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mat = (EditText) findViewById(R.id.matricula);
                    pass = (EditText) findViewById(R.id.password);

                    String date1 = mat.getText().toString();
                    String date2 = pass.getText().toString();

                    if (date1.length() == 0 || date2.length() == 0 ){
                        img_advertencia.setImageResource(R.drawable.advertencia);
                        txt_advertencia.setText("Ingresa tus datos.");

                        Toast toast = new Toast(getApplicationContext());
                        toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.setView(layout_advertencia);
                        toast.show();
                    }


                    if (date1.length() != 0 && date2.length() != 0){


                        matricula = Integer.parseInt(mat.getText().toString());
                        //contrasena = Integer.parseInt(pass.getText().toString());

                        progressBar.setVisibility(View.VISIBLE);

                        Call<Token> call = WebServiceJWT
                                            .getInstance()
                                            .createService(WebServiceApi.class)
                                            .obtenerToken(matricula, matricula, "password");

                        call.enqueue(new Callback<Token>() {

                            @Override

                            public void onResponse(Call<Token> call, Response<Token> response) {


                                progressBar.setVisibility(View.VISIBLE);

                                if (response.code() == 200) {

                                    Log.d("TAG1", "Acceso de token: " + response.body().getAccessToken());

                                    token = response.body().getAccessToken();

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


                                } else if (response.code() == 404) {

                                    Log.d("Tag1", "No autorizado");
                                    img_incorrecto.setImageResource(R.drawable.error);
                                    txt_incorrecto.setText("Tus datos son incorrectos");

                                    Toast toast = new Toast(getApplicationContext());
                                    toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                                    toast.setDuration(Toast.LENGTH_SHORT);
                                    toast.setView(layout_incorrecto);
                                    toast.show();

                                    mat.setText("");
                                    pass.setText("");
                                    progressBar.setVisibility(View.INVISIBLE);

                                } else {
                                    Log.d("Tag1", "No obtenido Token");
                                    img_incorrecto.setImageResource(R.drawable.error);
                                    txt_incorrecto.setText("El usuario o contraseña es incorrecto");

                                    Toast toast = new Toast(getApplicationContext());
                                    toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                                    toast.setDuration(Toast.LENGTH_SHORT);
                                    toast.setView(layout_incorrecto);
                                    toast.show();
                                    mat.setText("");
                                    pass.setText("");
                                    progressBar.setVisibility(View.INVISIBLE);
                                }
                            }

                            @Override
                            public void onFailure(Call<Token> call, Throwable throwable) {
                                Log.d("Error: ", "" + throwable);

                                progressBar.setVisibility(View.INVISIBLE);

                                img_sinacceso.setImageResource(R.drawable.incorrecto);
                                txt_sinacceso.setText("Lo sentimos el servicio está inactivo.");

                                Toast toast = new Toast(getApplicationContext());
                                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                                toast.setDuration(Toast.LENGTH_SHORT);
                                toast.setView(layout_sinacceso);
                                toast.show();
                            }
                        });

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