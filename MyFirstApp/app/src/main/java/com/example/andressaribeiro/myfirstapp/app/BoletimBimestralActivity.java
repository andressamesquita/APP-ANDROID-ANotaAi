package com.example.andressaribeiro.myfirstapp.app;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andressaribeiro.myfirstapp.App;
import com.example.andressaribeiro.myfirstapp.R;
import com.example.andressaribeiro.myfirstapp.adapters.ListaRendimentoRVAdapter;
import com.example.andressaribeiro.myfirstapp.model.BoletimBimestral;
import com.example.andressaribeiro.myfirstapp.model.Disciplina;

import io.objectbox.Box;

public class BoletimBimestralActivity extends AppCompatActivity {

    private TextView bimestreNota1;
    private TextView bimestreNota2;
    private BoletimBimestral bimestre;
    private Box<Disciplina> disciplinaBox;
    private Disciplina disciplina;

    private Box<BoletimBimestral>boletimBimestralBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boletim_bimestral);

        setupViews();

        boletimBimestralBox = (((App) getApplication()).getBoxStore().boxFor(BoletimBimestral.class));
        disciplinaBox = (((App) getApplication()).getBoxStore().boxFor(Disciplina.class));

        disciplina = obterDisciplina();
    }

    private Disciplina obterDisciplina() {
        Intent intent = getIntent();
        long id = intent.getLongExtra("disciplinaId",-1);
        Disciplina disciplina = new Disciplina();
        disciplina = disciplinaBox.get(id);
        return disciplina;
    }

    private void preencherBoletim(BoletimBimestral bimestre) {

        bimestreNota1.setText((int) bimestre.getNotaB1());
        bimestreNota2.setText((int) bimestre.getNotaB2());

    }

    private void setupViews() {

        bimestreNota1 = findViewById(R.id.nota1_bimestre);
        bimestreNota2 = findViewById(R.id.nota2_bimestre);

        SeekBar seekBarNota1 = findViewById(R.id.seekbar_nota1);
        seekBarNota1.setMax(20);
        seekBarNota1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String texto = String.valueOf(progress*0.5);
                bimestreNota1.setText(texto);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        SeekBar seekBarNota2 = findViewById(R.id.seekbar_nota2);
        seekBarNota2.setMax(20);
        seekBarNota2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String texto = String.valueOf(progress*0.5);
                bimestreNota2.setText(texto);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        bimestre = new BoletimBimestral();
    }


    public void salvarNotasBimestre(View view) {

        double nota1 = Double.valueOf(bimestreNota1.getText().toString());
        double nota2 = Double.valueOf(bimestreNota2.getText().toString());


        //preencher os atributos
        bimestre.setNotaB1(nota1);
        bimestre.setNotaB2(nota2);
        bimestre.getDisciplinaToOne().setTarget(disciplina);

        //salvar ou atualizar
        boletimBimestralBox.put(bimestre);
        Snackbar.make(view, "Notas Salvas", Snackbar.LENGTH_LONG).show();


        //encerra
        finish();
    }


}
