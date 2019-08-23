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

import com.example.apptec.Adapters.ReciclerViewAdapter_miercoles;
import com.example.apptec.Adapters.ReciclerViewAdapter_viernes;
import com.example.apptec.Api.WebServiceApi;
import com.example.apptec.Api.WebServiceJWT;
import com.example.apptec.Clases.ObjetosMiercoles;
import com.example.apptec.Clases.ObjetosViernes;
import com.example.apptec.Modelos.ModeloMiercoles;
import com.example.apptec.Modelos.ModeloToken;
import com.example.apptec.Modelos.ModeloViernes;
import com.example.apptec.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViernesFragment extends Fragment {

    public static String token;
    TextView mensaje;

    View v;
    public static boolean estado = true;
    private RecyclerView myreciclerview;
    private List<ObjetosViernes> lstContact;

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

    public ViernesFragment() {
        // Required empty public constructor
    }


    public void ObtenerViernes(String tok) {
        token = tok;


        Log.d("mensaje", "Token para semana " + token);

        ModeloToken too2 = new ModeloToken();
        too2.setToken(token);

        Call<List<ModeloViernes>> callViernes = WebServiceJWT
                .getInstance()
                .createService(WebServiceApi.class)
                .obtenerFriday(too2);

        callViernes.enqueue(new Callback<List<ModeloViernes>>() {
            @Override
            public void onResponse(Call<List<ModeloViernes>> call, Response<List<ModeloViernes>> response) {
                List<ModeloViernes> semana = response.body();


                Log.d(">>>>>>>>", "" + semana.size());

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
                        datoSemana1[i] = semana.get(i).getEstudios();
                        datoSemana2[i] = semana.get(i).getNombre();
                        datoSemana3[i] = semana.get(i).getApep();
                        datoSemana4[i] = semana.get(i).getApem();
                        datoSemana5[i] = semana.get(i).getSalon();
                        datoSemana6[i] = semana.get(i).getMateria();
                        datoSemana7[i] = semana.get(i).getDia();
                        datoSemana8[i] = semana.get(i).getHorain();
                        datoSemana9[i] = semana.get(i).getHorafin();
                        datoSemana10[i] = semana.get(i).getCredito();
                        datoSemana11[i] = semana.get(i).getClavemat();
                        datoSemana12[i] = semana.get(i).getOpcioncur();

                        contador = contador + i;

                    }


                } else {

                    estado = false;

                }


            }

            @Override
            public void onFailure(Call<List<ModeloViernes>> call, Throwable t) {

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_viernes, container, false);

        if (estado == true) {
            myreciclerview = v.findViewById(R.id.contact_reciclerview_viernes);
            ReciclerViewAdapter_viernes reciclerViewAdapter_viernes = new ReciclerViewAdapter_viernes(getContext(), lstContact);
            myreciclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
            myreciclerview.setAdapter(reciclerViewAdapter_viernes);


            return v;

        }
        mensaje = v.findViewById(R.id.mensajeviernes);
        mensaje.setText("No tienes clases este día.");


        return v;

    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (estado == true){


            lstContact = new ArrayList<>();
            String ca = "";
            if (contador<=1){
                lstContact.clear();
                ca = "";
                for (i = 0; i<=contador; i++){
//                    ca = Integer.toString(datoSemana10[i]);

                    lstContact.add(new ObjetosViernes(datoSemana6[i],datoSemana2[i]+" ", datoSemana3[i]+" ",datoSemana4[i]+" ",datoSemana1[i],ca,datoSemana11[i],datoSemana12[i],"",datoSemana8[i],datoSemana9[i],datoSemana5[i]));

                }
            }else if (contador>1){
                lstContact.clear();
                ca = "";
                for (i = 0; i<contador; i++){
                    ca = Integer.toString(datoSemana10[i]);

                    lstContact.add(new ObjetosViernes(datoSemana6[i],datoSemana2[i]+" ", datoSemana3[i]+" ",datoSemana4[i]+" ",datoSemana1[i],ca,datoSemana11[i],datoSemana12[i],"",datoSemana8[i],datoSemana9[i],datoSemana5[i]));

                }
            }



        }else if (estado == false){



        }


    }

    public void borrarData(){
        lstContact.clear();
    }


}
