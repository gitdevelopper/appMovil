package com.example.apptec.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apptec.Adapters.ReciclerViewAdapter_hoy;
import com.example.apptec.Api.WebServiceApi;
import com.example.apptec.Api.WebServiceJWT;
import com.example.apptec.Clases.ObjetosHoy;
import com.example.apptec.Modelos.ModeloHoy;
import com.example.apptec.Modelos.ModeloToken;
import com.example.apptec.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HoyFragment extends Fragment {

    public static String token;
    TextView mensaje;
    View v;

    private RecyclerView myreciclerview;
    private List<ObjetosHoy> lstHoy;
    public static boolean estado = true;

    public static int i = 0, contador = 0;

    public static String[] modeloDia1;
    public static String[] modeloDia2;
    public static String[] modeloDia3;
    public static String[] modeloDia4;
    public static String[] modeloDia5;
    public static String[] modeloDia6;
    public static String[] modeloDia7;
    public static String[] modeloDia8;


    public HoyFragment() {
        // Required empty public constructor
    }


    public void obtenerHoy(String tok) {

        token = tok;

        ModeloToken tookDia = new ModeloToken();
        tookDia.setToken(token);
        Call<List<ModeloHoy>> callDia = WebServiceJWT
                .getInstance()
                .createService(WebServiceApi.class)
                .obtenerDia(tookDia);

        callDia.enqueue(new Callback<List<ModeloHoy>>() {
            @Override
            public void onResponse(Call<List<ModeloHoy>> call, Response<List<ModeloHoy>> response) {
                List<ModeloHoy> dia = response.body();

                if (dia.size() >= 1) {
                    estado = true;

                    modeloDia1 = new String[dia.size()];
                    modeloDia2 = new String[dia.size()];
                    modeloDia3 = new String[dia.size()];
                    modeloDia4 = new String[dia.size()];
                    modeloDia5 = new String[dia.size()];
                    modeloDia6 = new String[dia.size()];
                    modeloDia7 = new String[dia.size()];
                    modeloDia8 = new String[dia.size()];


                    for (int i = 0; i < dia.size(); i++) {

                        modeloDia1[i] = dia.get(i).getSalon();
                        modeloDia2[i] = dia.get(i).getMat();
                        modeloDia3[i] = dia.get(i).getHorain();
                        modeloDia4[i] = dia.get(i).getHorafin();
                        modeloDia5[i] = dia.get(i).getStud();
                        modeloDia6[i] = dia.get(i).getNom();
                        modeloDia7[i] = dia.get(i).getApp();
                        modeloDia8[i] = dia.get(i).getApm();



                }

                } else {

                    estado = false;

                }


            }

            @Override
            public void onFailure(Call<List<ModeloHoy>> call, Throwable t) {

            }
        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_hoy, container, false);
        if (estado == true) {

            myreciclerview = (RecyclerView) v.findViewById(R.id.contact_reciclerview_hoy);
            ReciclerViewAdapter_hoy reciclerViewAdapter = new ReciclerViewAdapter_hoy(getContext(), lstHoy);
            myreciclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
            myreciclerview.setAdapter(reciclerViewAdapter);
            return v;

        }

        //Toast.makeText(getContext(), "No hay clases", Toast.LENGTH_LONG).show();
        mensaje = v.findViewById(R.id.clasesid);
        mensaje.setText("No tienes clases.");
        return v;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (estado == true) {


            lstHoy = new ArrayList<>();


            for (i = 0; i <= contador; i++) {

                lstHoy.add(new ObjetosHoy("", modeloDia2[i], modeloDia3[i], modeloDia4[i], modeloDia5[i], modeloDia6[i], "", modeloDia8[i]));

            }


        }


    }
}
