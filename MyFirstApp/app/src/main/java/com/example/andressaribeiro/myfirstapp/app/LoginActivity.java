package com.example.andressaribeiro.myfirstapp.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.andressaribeiro.myfirstapp.App;
import com.example.andressaribeiro.myfirstapp.R;
import com.example.andressaribeiro.myfirstapp.model.Aluno;
import com.example.andressaribeiro.myfirstapp.model.Aluno_;

import java.util.List;

import io.objectbox.Box;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etSenha;
    private Box<Aluno> alunoBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setupView();
        alunoBox = ((App) getApplication()).getBoxStore().boxFor(Aluno.class);
    }


    private void setupView() {
        etEmail = findViewById(R.id.et_email);
        etSenha = findViewById(R.id.et_senhaUser);
    }


    public void cadastrarAluno(View view) {

        startActivity(new Intent(this,CadastroAlunoActivity.class));

    }

    private void logar(Aluno aluno) {
        SharedPreferences preferences = getSharedPreferences("anotaai.file",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong("alunoId", aluno.getId());

        editor.commit();

        startActivity(new Intent(LoginActivity.this,TelaInicialActivity.class));
        finish();
    }

    public void botaoAcessar(View view) {
        //obter dados inseridos nas editText
        String email = etEmail.getText().toString();
        String senha = etSenha.getText().toString();

        List<Aluno> result = (List<Aluno>) alunoBox.query()
                .equal(Aluno_.email,email)
                .equal(Aluno_.senha,senha)
                .build()
                .find();

        if(result.size()>0){
            logar(result.get(0));
        }else{
            etSenha.getText().clear();
            Toast.makeText(this, "Email e/ou senha incorreto(s)",Toast.LENGTH_SHORT).show();

        }
    }

}
