package com.example.andressaribeiro.myfirstapp.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.andressaribeiro.myfirstapp.App;
import com.example.andressaribeiro.myfirstapp.R;
import com.example.andressaribeiro.myfirstapp.model.Agenda;
import com.example.andressaribeiro.myfirstapp.model.Aluno;

import io.objectbox.Box;

public class CadastroAgendaActivity extends AppCompatActivity {

    private EditText evento;
    private EditText data;
    private EditText descricao;

    private Aluno alunoLogado;
    private Agenda agenda;
    private Box<Aluno> alunoBox;
    private Box<Agenda>agendaBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_agenda);

        setupViews();
        Intent intent = getIntent();
        long disciplinaId = intent.getLongExtra("disciplinaId", -1);

        alunoBox = ((App)getApplication()).getBoxStore().boxFor(Aluno.class);
        agendaBox = ((App)getApplication()).getBoxStore().boxFor(Agenda.class);

        alunoLogado = obterAlunoLogado();

        if (disciplinaId == -1){
            agenda = new Agenda();
        }else {
            agenda = agendaBox.get(disciplinaId);
            preencherAgenda(agenda);
        }
    }

    private void preencherAgenda(Agenda agenda) {
        evento.setText(agenda.getTituloEvento());
        data.setText(agenda.getData());
        descricao.setText(agenda.getDescricao());

    }

    public void salvarAgenda(View view) {
        String tituloEvento = evento.getText().toString();
        String dataEvento = data.getText().toString();
        String descricaoEvento = descricao.getText().toString();

        agenda.setTituloEvento(tituloEvento);
        agenda.setData(dataEvento);
        agenda.setDescricao(descricaoEvento); //setTarget faz a associação entre id e aluno
        agenda.getAlunoToOne().setTarget(alunoLogado);

        agendaBox.put(agenda);

        Toast.makeText(this,"Evento salvo", Toast.LENGTH_SHORT).show();
        finish();

    }

    private void setupViews() {
        evento = findViewById(R.id.et_evento);
        data = findViewById(R.id.et_data);
        descricao = findViewById(R.id.et_descricao);
    }

    private Aluno obterAlunoLogado() {
        SharedPreferences preferences = getSharedPreferences("anotaai.file", MODE_PRIVATE);
        long id = preferences.getLong("alunoId", -1);
        return alunoBox.get(id);

    }
}
