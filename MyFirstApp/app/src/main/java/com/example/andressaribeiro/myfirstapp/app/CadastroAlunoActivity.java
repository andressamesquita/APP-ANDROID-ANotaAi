package com.example.andressaribeiro.myfirstapp.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.andressaribeiro.myfirstapp.App;
import com.example.andressaribeiro.myfirstapp.R;
import com.example.andressaribeiro.myfirstapp.model.Aluno;

import io.objectbox.Box;

public class CadastroAlunoActivity extends AppCompatActivity {

    private EditText nome;
    private EditText escola;
    private EditText senha;
    private EditText email;

    private TextView mA;
    private TextView mP;

    private Aluno aluno;

    private Box<Aluno> alunoBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_aluno);

        setupViews();

        Intent intent = getIntent();
        long id = intent.getLongExtra("cadastroId", -1);


        alunoBox = (((App) getApplication()).getBoxStore().boxFor(Aluno.class));

        if (id == -1){
            aluno = new Aluno();
        }else{
            aluno = alunoBox.get(id);
            preencherCadastro(aluno);
        }

    }

    private void preencherCadastro(Aluno aluno) {
        nome.setText(aluno.getNome());
        escola.setText(aluno.getEscola());
        senha.setText(aluno.getSenha());
        email.setText(aluno.getEmail());
        mA.setText((int) aluno.getMediaEscola());
        mP.setText((int) aluno.getMediaPessoal());

    }

    private void setupViews() {
        nome = findViewById(R.id.et_nome);
        escola = findViewById(R.id.et_escola);
        email = findViewById(R.id.et_email);
        senha = findViewById(R.id.et_senha);

        mA = findViewById(R.id.mApro);
        mP = findViewById(R.id.mPes);

        final SeekBar seekBarMedPessoal = findViewById(R.id.seekBar_medPessoal);
        seekBarMedPessoal.setMax(20);
        seekBarMedPessoal.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                String texto = String.valueOf(progress*0.5);
                mP.setText(texto);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        final SeekBar seekBarMedAprovativa = findViewById(R.id.seekBar_medAprovativa);
        seekBarMedAprovativa.setMax(20);
        seekBarMedAprovativa.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                String texto = String.valueOf(progress*0.5);
                mA.setText(texto);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }

    public void salvarCadastro(){

        //obter dados inseridos nas Edit Texts
        String nome_user = nome.getText().toString();
        String escola_user = escola.getText().toString();
        String senha_user = senha.getText().toString();
        String email_user = email.getText().toString();
        double mAprovativa = Double.valueOf(mA.getText().toString());
        double mPessoal = Double.valueOf(mP.getText().toString());



        //preencher os atributos
        aluno.setNome(nome_user);
        aluno.setEscola(escola_user);
        aluno.setSenha(senha_user);
        aluno.setEmail(email_user);
        aluno.setMediaEscola(mAprovativa);
        aluno.setMediaPessoal(mPessoal);



        //salvar ou atualizar
        alunoBox.put(aluno);

        Toast toastCadastrado = Toast.makeText(getApplicationContext(),"Aluno cadastrado",Toast.LENGTH_LONG);
        toastCadastrado.show();
        //encerra
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_salvar_aluno,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()== R.id.salvar_aluno){
            salvarCadastro();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
