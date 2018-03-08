package com.example.andressaribeiro.myfirstapp.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.andressaribeiro.myfirstapp.R;
import com.example.andressaribeiro.myfirstapp.app.ListaDisciplinasActivity;
import com.example.andressaribeiro.myfirstapp.app.LoginActivity;

public class TelaInicialActivity extends AppCompatActivity {

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        if(!logado()){
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        }
    }

    public void IrParaListaDisciplinas(View view) {
        Intent intent = new Intent(this, ListaDisciplinasActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_tela_inicial,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.sair_conta){
            sair();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean logado(){
        preferences = getSharedPreferences("anotaai.file",MODE_PRIVATE);
        final long alunoId = preferences.getLong("alunoId",-1);

        return alunoId != -1;
    }

    private void sair(){
        preferences.edit().clear().apply();//apply funciona como o commit
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }


    public void IrParaListaAgendas(View view) {

        startActivity(new Intent(this,ListaAgendaActivity.class));

    }

    public void IrParaListaRendimentos(View view) {
        startActivity(new Intent(this,ListaRendimentoActivity.class));
    }
}
