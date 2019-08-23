package com.example.apptec.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apptec.Api.WebServiceApi;
import com.example.apptec.Api.WebServiceJWT;
import com.example.apptec.Clases.ObjetosPerfil;
import com.example.apptec.Modelos.ModeloPerfil;
import com.example.apptec.Modelos.ModeloToken;
import com.example.apptec.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilFragment extends Fragment {

    RecyclerView reciclerPerfil;
    ArrayList<ObjetosPerfil> listaPerfil;

    private static String dat1, dat2,dat3,dat4,dat5,dat6,dat7;
    private static String token;


    public PerfilFragment() {
        // Required empty public constructor
    }

    public void obteniendoDaos(String took){

        token = took;

        ModeloToken too = new ModeloToken();
        too.setToken(token);
        Call<List<ModeloPerfil>> callPerfil = WebServiceJWT
                .getInstance()
                .createService(WebServiceApi.class)
                .PerfilAlumno(too);


        callPerfil.enqueue(new Callback<List<ModeloPerfil>>() {
            @Override
            public void onResponse(Call<List<ModeloPerfil>> call, Response<List<ModeloPerfil>> response) {



                Log.d("Tag2","Perfil: "+response.body());

                List<ModeloPerfil> perfil = response.body();

                dat1 =  perfil.get(0).getEnrollment();
                dat2 = perfil.get(0).getName();
                dat3 = perfil.get(0).getLastNameP();
                dat4 =  perfil.get(0).getLastNameM();
                dat5 =  perfil.get(0).getGrade();
                dat6 =  perfil.get(0).getGroups();
                dat7 =  perfil.get(0).getInstitution();


            }

            @Override
            public void onFailure(Call<List<ModeloPerfil>> call, Throwable t) {

            }
        });




    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView nombreText,matriculaText, gradoText,semestreText, carreraText;

        View vista =inflater.inflate(R.layout.fragment_perfil, container, false);


        nombreText = vista.findViewById(R.id.nombre);
        matriculaText = vista.findViewById(R.id.matricula);
        gradoText = vista.findViewById(R.id.grado);
        carreraText = vista.findViewById(R.id.carrera);

        nombreText.setText(dat2+" "+dat3+" "+dat4);

        matriculaText.setText(dat1);
        gradoText.setText("Grupo: "+dat5+"    "+dat6+" Semestre");
        carreraText.setText(dat7);

        return vista;
    }


}
