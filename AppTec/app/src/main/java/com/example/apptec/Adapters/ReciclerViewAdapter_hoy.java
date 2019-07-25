package com.example.apptec.Adapters;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apptec.Clases.ObjetosHoy;
import com.example.apptec.R;

import java.util.List;

public class ReciclerViewAdapter_hoy extends RecyclerView.Adapter<ReciclerViewAdapter_hoy.MyViewHolder> {

    Context mContext;
    List<ObjetosHoy> mData;

    public ReciclerViewAdapter_hoy(Context mContext, List<ObjetosHoy> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_hoy,viewGroup,false);
        MyViewHolder vHolder = new MyViewHolder(v);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {

        holder.materia.setText(mData.get(i).getMat());
        holder.aula.setText(mData.get(i).getSalon());
        holder.grado.setText(mData.get(i).getEstud());
        holder.profesor.setText(mData.get(i).getNom());
        holder.app.setText(mData.get(i).getAp());
        holder.apm.setText(mData.get(i).getAm());
        holder.hinicio.setText(mData.get(i).getHorain());
        holder.hfin.setText(mData.get(i).getHorafin());









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







        }
    }
}
