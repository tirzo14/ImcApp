package com.tirzorivas.android.imcapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ImcFragment extends Fragment {

    private EditText mCampoPeso;
    private EditText mCampoEstatura;
    private Button mBotonCalcular;
    private Button mBotonLimpiar;
    private TextView mEtiquetaImc;
    private TextView mSituacionaNutricional;

    View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_imc, container,false);
        mCampoPeso = (EditText) v.findViewById(R.id.campo_peso);
        mCampoEstatura = (EditText) v.findViewById(R.id.campo_estatura);
        mBotonCalcular = (Button) v.findViewById(R.id.boton_calcular);
        mBotonLimpiar = (Button) v.findViewById(R.id.boton_limpiar);
        mEtiquetaImc = (TextView) v.findViewById(R.id.etiqueta_imc);
        mSituacionaNutricional =
                (TextView) v.findViewById(R.id.etiqueta_situacion);

        mBotonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = mCampoPeso.getText().toString();
                double peso = Double.parseDouble(s);
                s = mCampoEstatura.getText().toString();
                double estatura = Double.parseDouble(s);
                double imc = peso / (estatura * estatura);
                s = String.format("%2.2f", imc);
                mEtiquetaImc.setText(s);
                if (imc < 18.5) {
                    mSituacionaNutricional.setText(
                            R.string.texto_situacion_peso_bajo);
                } else if (imc < 25.0) {
                    mSituacionaNutricional.setText(
                            R.string.texto_situacion_peso_normal);
                } else if (imc < 30.0) {
                    mSituacionaNutricional.setText(
                            R.string.texto_situacion_sobrepeso);
                } else if (imc < 40.0) {
                    mSituacionaNutricional.setText(
                            R.string.texto_situacion_obesidad);
                } else {
                    mSituacionaNutricional.setText(
                            R.string.texto_situacion_obesidad_extrema);
                }
            }
        });
        mBotonLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCampoPeso.setText("");
                mCampoEstatura.setText("");
                mEtiquetaImc.setText("0.0");
                mSituacionaNutricional.setText("");
            }
        });

        return v;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}