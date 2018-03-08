package com.example.andressaribeiro.myfirstapp.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.andressaribeiro.myfirstapp.App;
import com.example.andressaribeiro.myfirstapp.R;
import com.example.andressaribeiro.myfirstapp.adapters.ListaDisciplinasRVAdapter;
import com.example.andressaribeiro.myfirstapp.adapters.ListaRendimentoRVAdapter;
import com.example.andressaribeiro.myfirstapp.model.Aluno;
import com.example.andressaribeiro.myfirstapp.model.BoletimBimestral;
import com.example.andressaribeiro.myfirstapp.model.Disciplina;
import com.example.andressaribeiro.myfirstapp.model.Disciplina_;

import java.util.List;

import io.objectbox.Box;
import io.objectbox.query.QueryBuilder;

public class ListaRendimentoActivity extends AppCompatActivity {

    Box<Disciplina> disciplinaBox;
    Box<BoletimBimestral>boletimBimestralBox;
    RecyclerView rvRendimento;
    private Aluno alunoLogado;
    private Box<Aluno> alunoBox;
    private long idBoletimBimestral;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_rendimento);

        alunoBox = ((App)getApplication()).getBoxStore().boxFor(Aluno.class);

        disciplinaBox = ((App) getApplication()).getBoxStore().boxFor(Disciplina.class);
        rvRendimento = findViewById(R.id.rv_rendimento);
        alunoLogado = obterAlunoLogado();

        idBoletimBimestral = pegarIdBoletimBimestral();
    }

    private long pegarIdBoletimBimestral() {
        Intent intent = getIntent();
        long idBoletim = intent.getLongExtra("boletimBimestralId",-1);
        return idBoletim;
    }

    @Override
    protected void onResume() {
        super.onResume();

        QueryBuilder<Disciplina> builder = disciplinaBox.query();
        builder.equal(Disciplina_.alunoToOneId,alunoLogado.getId());
        List<Disciplina> disciplinas = builder.build().find();

        //Adapter
        ListaRendimentoRVAdapter adapter = new ListaRendimentoRVAdapter(this,disciplinas,disciplinaBox,boletimBimestralBox, alunoLogado);
        rvRendimento.setAdapter(adapter);

        //layoutManager
        rvRendimento.setLayoutManager(new LinearLayoutManager(this));

    }

    private Aluno obterAlunoLogado(){
        final SharedPreferences preferences = getSharedPreferences("anotaai.file", MODE_PRIVATE);
        long id = preferences.getLong("alunoId", -1);
        return alunoBox.get(id);
    }
}
