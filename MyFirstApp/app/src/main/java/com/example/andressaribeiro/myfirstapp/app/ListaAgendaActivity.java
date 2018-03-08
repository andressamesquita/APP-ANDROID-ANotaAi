package com.example.andressaribeiro.myfirstapp.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.andressaribeiro.myfirstapp.App;
import com.example.andressaribeiro.myfirstapp.R;
import com.example.andressaribeiro.myfirstapp.adapters.ListaAgendaRVAdapter;
import com.example.andressaribeiro.myfirstapp.model.Agenda;
import com.example.andressaribeiro.myfirstapp.model.Agenda_;
import com.example.andressaribeiro.myfirstapp.model.Aluno;
import com.example.andressaribeiro.myfirstapp.model.Disciplina;
import com.example.andressaribeiro.myfirstapp.model.Disciplina_;

import java.util.List;

import io.objectbox.Box;
import io.objectbox.query.QueryBuilder;

public class ListaAgendaActivity extends AppCompatActivity {

    Box<Agenda> agendaBox;
    RecyclerView rvAgendas;
    private Aluno alunoLogado;
    private Box<Aluno> alunoBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_agenda);

        alunoBox = ((App)getApplication()).getBoxStore().boxFor(Aluno.class);

        agendaBox = ((App) getApplication()).getBoxStore().boxFor(Agenda.class);
        rvAgendas = findViewById(R.id.rv_agendas);

        alunoLogado = obterAlunoLogado();
    }

    @Override
    protected void onResume() {
        super.onResume();

        QueryBuilder<Agenda> builder = agendaBox.query();
        builder.equal(Agenda_.alunoToOneId,alunoLogado.getId());
        List<Agenda> agendas = builder.build().find();

        //Adapter
        ListaAgendaRVAdapter adapter = new ListaAgendaRVAdapter(this,agendas,agendaBox);
        rvAgendas.setAdapter(adapter);

        //layoutManager
        rvAgendas.setLayoutManager(new LinearLayoutManager(this));

    }

    private Aluno obterAlunoLogado(){
        final SharedPreferences preferences = getSharedPreferences("anotaai.file", MODE_PRIVATE);
        long id = preferences.getLong("alunoId", -1);
        return alunoBox.get(id);
    }

    public void abrirCadastroAgenda(View view) {
        Intent intent = new Intent(this, CadastroAgendaActivity.class);
        startActivity(intent);
    }
}
