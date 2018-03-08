package com.example.andressaribeiro.myfirstapp.app;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.andressaribeiro.myfirstapp.App;
import com.example.andressaribeiro.myfirstapp.R;
import com.example.andressaribeiro.myfirstapp.model.BoletimBimestral;
import com.example.andressaribeiro.myfirstapp.model.BoletimSemestral;

import io.objectbox.Box;

public class BoletimSemestralActivity extends AppCompatActivity {

    private TextView semestreNota1;
    private TextView semestreNota2;
    private TextView semestreNota3;
    private TextView semestreNota4;

    private BoletimSemestral semestre;

    private Box<BoletimSemestral>boletimSemestralBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boletim_semestral);

        setupViews();

        boletimSemestralBox = (((App) getApplication()).getBoxStore().boxFor(BoletimSemestral.class));
    }


    private void preencherBoletim(BoletimSemestral bimestre) {

        semestreNota1.setText((int) bimestre.getNotaS1());
        semestreNota2.setText((int) bimestre.getNotaS2());
        semestreNota3.setText((int) bimestre.getNotaS3());
        semestreNota4.setText((int) bimestre.getNotaS4());

    }

    private void setupViews() {
        semestreNota1 = findViewById(R.id.nota1_semestre);
        semestreNota2 = findViewById(R.id.nota2_semestre);
        semestreNota3 = findViewById(R.id.nota3_semestre);
        semestreNota4 = findViewById(R.id.nota4_semestre);

        SeekBar seekBarNota1 = findViewById(R.id.seekBar_n1);
        seekBarNota1.setMax(20);
        seekBarNota1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String texto = String.valueOf(progress*0.5);
                semestreNota1.setText(texto);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        SeekBar seekBarNota2 = findViewById(R.id.seekBar_n2);
        seekBarNota2.setMax(20);
        seekBarNota2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String texto = String.valueOf(progress*0.5);
                semestreNota2.setText(texto);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        SeekBar seekBarNota3 = findViewById(R.id.seekBar_n3);
        seekBarNota3.setMax(20);
        seekBarNota3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String texto = String.valueOf(progress*0.5);
                semestreNota3.setText(texto);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        SeekBar seekBarNota4 = findViewById(R.id.seekBar_n4);
        seekBarNota4.setMax(20);
        seekBarNota4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String texto = String.valueOf(progress*0.5);
                semestreNota4.setText(texto);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        semestre = new BoletimSemestral();
    }

    public void salvarNotasSemestre(View view) {

        double nota1 = Double.valueOf(semestreNota1.getText().toString());
        double nota2 = Double.valueOf(semestreNota2.getText().toString());
        double nota3 = Double.valueOf(semestreNota3.getText().toString());
        double nota4 = Double.valueOf(semestreNota4.getText().toString());

        //preencher os atributos
        semestre.setNotaS1(nota1);
        semestre.setNotaS2(nota2);
        semestre.setNotaS3(nota3);
        semestre.setNotaS4(nota4);

        //salvar ou atualizar
        boletimSemestralBox.put(semestre);
        Snackbar.make(view, "Notas Salvas", Snackbar.LENGTH_LONG).show();

        //encerra
        finish();
    }
}
