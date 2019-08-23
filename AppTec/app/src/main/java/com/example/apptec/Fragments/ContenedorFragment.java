package com.example.apptec.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apptec.Adapters.SeccionesAdapter;
import com.example.apptec.Clases.Utilidades;
import com.example.apptec.R;


public class ContenedorFragment extends Fragment {

    private AppBarLayout appBarLayout;
    private TabLayout pestañas;
    private ViewPager viewPager;
    View vista;


    private OnFragmentInteractionListener mListener;

    public ContenedorFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().onBackPressed();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment}
        vista = inflater.inflate(R.layout.fragment_contenedor, container, false);




        if (Utilidades.rotacion == 0){

            View parent = (View) container.getParent();

            if (appBarLayout == null){
                appBarLayout = (AppBarLayout) parent.findViewById(R.id.appBar);
                pestañas = new TabLayout(getActivity());

                pestañas.setTabTextColors(Color.parseColor("#FFFFFF"),Color.parseColor("#FFFFFF"));


                appBarLayout.addView(pestañas);

                viewPager = (ViewPager) vista.findViewById(R.id.id_viewPaperInformation);
                llenarViewPaper(viewPager);

                viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){

                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                    }
                });

                pestañas.setupWithViewPager(viewPager);

            }
            pestañas.setupWithViewPager(viewPager);

        }else{
            Utilidades.rotacion = 1;
        }




        return vista;
    }

    private void llenarViewPaper(ViewPager viewPager) {

        SeccionesAdapter adapter = new SeccionesAdapter(getFragmentManager());

        adapter.addFragment(new LunesFragment(),"Lu");
        adapter.addFragment(new MartesFragment(),"Ma");
        adapter.addFragment(new MiercolesFragment(),"Mi");
        adapter.addFragment(new JuevesFragment(),"Ju");
        adapter.addFragment(new ViernesFragment() ,"Vi");
        adapter.addFragment(new SabadoFragment(),"Sa");

        viewPager.setAdapter(adapter);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (Utilidades.rotacion == 0){
            appBarLayout.removeView(pestañas);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
