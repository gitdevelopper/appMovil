package com.example.apptec.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apptec.Clases.ObjetosHoy;
import com.example.apptec.R;

import java.util.ArrayList;

public class AdapterHoy extends RecyclerView.Adapter<AdapterHoy.ViewHolderDatos> {

    ArrayList<ObjetosHoy> listaDatos;

    public AdapterHoy(ArrayList<ObjetosHoy> listaDatos) {
        this.listaDatos = listaDatos;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fragment_lunes, null,false);

        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos viewHolderDatos, int i) {
        viewHolderDatos.dato.setText(listaDatos.get(i).getAm());
    }

    @Override
    public int getItemCount() {
        return listaDatos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView dato;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            dato = (TextView)itemView.findViewById(R.id.id_viewPaperInformation);
        }


    }
}
