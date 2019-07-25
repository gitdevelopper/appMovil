package com.example.apptec.Adapters;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apptec.Clases.ObjetosJueves;
import com.example.apptec.R;

import java.util.List;

public class ReciclerViewAdapter_jueves extends RecyclerView.Adapter<ReciclerViewAdapter_jueves.MyViewHolder> {

    Context mContext;
    List<ObjetosJueves> mData;

    public ReciclerViewAdapter_jueves(Context mContext, List<ObjetosJueves> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_jueves,viewGroup,false);
        MyViewHolder vHolder = new MyViewHolder(v);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {

        holder.materia.setText(mData.get(i).getMateria());
        holder.grado.setText(mData.get(i).getGrado());
        holder.profesor.setText(mData.get(i).getProfesor());
        holder.app.setText(mData.get(i).getApp());
        holder.apm.setText(mData.get(i).getApm());
        holder.aula.setText(mData.get(i).getAula());
        holder.hinicio.setText(mData.get(i).getHinicio());
        holder.hfin.setText(mData.get(i).getHfin());
        holder.credito.setText(mData.get(i).getCredito());
        holder.opcion.setText(mData.get(i).getOpcion());
        holder.clave.setText(mData.get(i).getClave());
        holder.dia.setText(mData.get(i).getDia());








    }

    @Override
    public int getItemCount() {

        return mData.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{


        private TextView materia;
        private TextView grado;
        private TextView profesor;
        private TextView app;
        private TextView apm;
        private TextView aula;
        private TextView hinicio;
        private TextView hfin;
        private TextView credito;
        private TextView opcion;
        private  TextView clave;
        private TextView dia;

        public MyViewHolder(View itemView){
            super(itemView);

            materia = (TextView) itemView.findViewById(R.id.materia);
            grado = (TextView) itemView.findViewById(R.id.cargo);

            profesor = (TextView) itemView.findViewById(R.id.nombre);
            app = (TextView) itemView.findViewById(R.id.app);
            apm = (TextView) itemView.findViewById(R.id.apm);
            aula = (TextView) itemView.findViewById(R.id.aula);
            hinicio = (TextView) itemView.findViewById(R.id.hinicio);
            hfin = (TextView) itemView.findViewById(R.id.hfin);
            credito = (TextView) itemView.findViewById(R.id.credito);
            opcion = (TextView) itemView.findViewById(R.id.opcion);
            clave = (TextView) itemView.findViewById(R.id.clave);
            dia = (TextView) itemView.findViewById(R.id.dia);






        }
    }



}
