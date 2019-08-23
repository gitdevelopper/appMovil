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
import android.widget.Toast;

import com.example.apptec.Adapters.ReciclerViewAdapter_hoy;
import com.example.apptec.Adapters.ReciclerViewAdapter_sabado;
import com.example.apptec.Api.WebServiceApi;
import com.example.apptec.Api.WebServiceJWT;
import com.example.apptec.Clases.ObjetosHoy;
import com.example.apptec.Clases.ObjetosSabado;
import com.example.apptec.Modelos.ModeloHoy;
import com.example.apptec.Modelos.ModeloSabado;
import com.example.apptec.Modelos.ModeloToken;
import com.example.apptec.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiaFragment extends Fragment {

    public static String token;
    TextView mensaje;

    View v;
    public static boolean estado = true, borrar = false;
    private RecyclerView myreciclerview;
    private List<ObjetosHoy> lstContact;

    public static String[] datoSemana1;
    public static String[] datoSemana2;
    public static String[] datoSemana3;
    public static String[] datoSemana4;
    public static String[] datoSemana5;
    public static String[] datoSemana6;
    public static String[] datoSemana7;
    public static String[] datoSemana8;
    public static String[] datoSemana9;
    public static int[] datoSemana10;
    public static String[] datoSemana11;
    public static String[] datoSemana12;


    public static int i = 0, contador = 0;

    public DiaFragment() {
        // Required empty public constructor
    }


    public void ObtenerDia(String tok) {
        token = tok;

        if(token == "borrar"){
            borrar = true;

        }else {




            Log.d("mensaje", "Token para semana " + token);

            ModeloToken too2 = new ModeloToken();
            too2.setToken(token);

            Call<List<ModeloHoy>> callDia = WebServiceJWT
                    .getInstance()
                    .createService(WebServiceApi.class)
                    .obtenerDay(too2);

            callDia.enqueue(new Callback<List<ModeloHoy>>() {
                @Override
                public void onResponse(Call<List<ModeloHoy>> call, Response<List<ModeloHoy>> response) {
                    List<ModeloHoy> semana = response.body();


                    Log.d(">>>>>>>> DIA DE HOYY", "" + semana.size());

                    if (semana.size() >= 1) {
                        estado = true;

                        datoSemana1 = new String[semana.size()];
                        datoSemana2 = new String[semana.size()];
                        datoSemana3 = new String[semana.size()];
                        datoSemana4 = new String[semana.size()];
                        datoSemana5 = new String[semana.size()];
                        datoSemana6 = new String[semana.size()];
                        datoSemana7 = new String[semana.size()];
                        datoSemana8 = new String[semana.size()];
                        datoSemana9 = new String[semana.size()];
                        datoSemana10 = new int[semana.size()];
                        datoSemana11 = new String[semana.size()];
                        datoSemana12 = new String[semana.size()];

                        //contador = semana.size();
                        for (i = 0; i < semana.size(); i++) {
                            datoSemana1[i] = semana.get(i).getSalon();
                            datoSemana2[i] = semana.get(i).getMat();
                            datoSemana3[i] = semana.get(i).getHorain();
                            datoSemana4[i] = semana.get(i).getHorafin();
                            datoSemana5[i] = semana.get(i).getStud();
                            datoSemana6[i] = semana.get(i).getNom();
                            datoSemana7[i] = semana.get(i).getApp();
                            datoSemana8[i] = semana.get(i).getApm();

                            contador = contador + i;

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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_hoy, container, false);



        if (estado == true) {
            myreciclerview = v.findViewById(R.id.contact_reciclerview_hoy);
            ReciclerViewAdapter_hoy reciclerViewAdapter_hoy = new ReciclerViewAdapter_hoy(getContext(), lstContact);
            myreciclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
            myreciclerview.setAdapter(reciclerViewAdapter_hoy);


            return v;

        }
        mensaje = v.findViewById(R.id.claseshoy);
        mensaje.setText("No tienes clases este día.");


        return v;


    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lstContact = new ArrayList<>();

        ReciclerViewAdapter_hoy reciclerViewAdapter_hoy = new ReciclerViewAdapter_hoy(getContext(), lstContact);





        if (estado == true){



            if (contador<=1){
                lstContact.clear();

                for (i = 0; i<=contador; i++){
                    lstContact.add(new ObjetosHoy(datoSemana1[i],datoSemana2[i],datoSemana3[i],datoSemana4[i],datoSemana5[i],datoSemana6[i],datoSemana7[i],datoSemana8[i]));

                }
            }else if (contador>1){
                lstContact.clear();
                for (i = 0; i<contador; i++){
                    lstContact.add(new ObjetosHoy(datoSemana1[i],datoSemana2[i],datoSemana3[i],datoSemana4[i],"",datoSemana6[i],datoSemana7[i],datoSemana8[i]));

                }
            }

        }else if (estado == false){



        }



    }


}
