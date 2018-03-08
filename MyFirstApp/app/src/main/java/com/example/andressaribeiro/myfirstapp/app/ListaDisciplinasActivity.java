package com.example.andressaribeiro.myfirstapp.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.andressaribeiro.myfirstapp.App;
import com.example.andressaribeiro.myfirstapp.R;
import com.example.andressaribeiro.myfirstapp.adapters.ListaDisciplinasRVAdapter;
import com.example.andressaribeiro.myfirstapp.app.CadastroDisciplinaActivity;
import com.example.andressaribeiro.myfirstapp.model.Aluno;
import com.example.andressaribeiro.myfirstapp.model.Disciplina;
import com.example.andressaribeiro.myfirstapp.model.Disciplina_;

import java.util.List;

import io.objectbox.Box;
import io.objectbox.query.QueryBuilder;

public class ListaDisciplinasActivity extends AppCompatActivity {

    Box<Disciplina>disciplinaBox;
    RecyclerView rvDisciplinas;
    private Aluno alunoLogado;
    private Box<Aluno> alunoBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_disciplinas);

        alunoBox = ((App)getApplication()).getBoxStore().boxFor(Aluno.class);

        disciplinaBox = ((App) getApplication()).getBoxStore().boxFor(Disciplina.class);
        rvDisciplinas = findViewById(R.id.rv_disciplinas);

        alunoLogado = obterAlunoLogado();

    }

    @Override
    protected void onResume() {
        super.onResume();

        QueryBuilder<Disciplina> builder = disciplinaBox.query();
        builder.equal(Disciplina_.alunoToOneId,alunoLogado.getId());
        List<Disciplina> disciplinas = builder.build().find();

        //Adapter
        ListaDisciplinasRVAdapter adapter = new ListaDisciplinasRVAdapter(this,disciplinas,disciplinaBox);
        rvDisciplinas.setAdapter(adapter);

        //layoutManager
        rvDisciplinas.setLayoutManager(new LinearLayoutManager(this));

    }

    public void abrirCadastroDisciplina(View view) {
        Intent intent = new Intent(this, CadastroDisciplinaActivity.class);
        startActivity(intent);
    }

    private Aluno obterAlunoLogado(){
        final SharedPreferences preferences = getSharedPreferences("anotaai.file", MODE_PRIVATE);
        long id = preferences.getLong("alunoId", -1);
        return alunoBox.get(id);
    }
}
