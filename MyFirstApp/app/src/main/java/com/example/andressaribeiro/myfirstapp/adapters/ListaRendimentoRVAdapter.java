package com.example.andressaribeiro.myfirstapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.andressaribeiro.myfirstapp.R;
import com.example.andressaribeiro.myfirstapp.model.Aluno;
import com.example.andressaribeiro.myfirstapp.model.BoletimBimestral;
import com.example.andressaribeiro.myfirstapp.model.Disciplina;

import java.util.List;

import io.objectbox.Box;

/**
 * Created by Andressa Ribeiro on 07/03/2018.
 */

//1 - Declarar a lista de objetos e a variavel de contexto
//2 - criar classe view holder
//3 - cria o construtor da classe adapter
//4 - extende o adapter para a viewholder do proprio adapter
//5 - implementa os metodos

public class ListaRendimentoRVAdapter extends RecyclerView.Adapter<ListaRendimentoRVAdapter.ViewHolder> {

    private final Context context;
    private final List<Disciplina> disciplinas;
    private Box<Disciplina> disciplinaBox;
    private Box<BoletimBimestral>boletimBimestralBox;
    private Aluno alunoLogado;


    public ListaRendimentoRVAdapter(Context context, List<Disciplina> disciplinas, Box<Disciplina> disciplinaBox, Box<BoletimBimestral> boletimBimestralBox, Aluno alunoLogado) {
        this.context = context;
        this.disciplinas = disciplinas;
        this.disciplinaBox = disciplinaBox;
        this.boletimBimestralBox = boletimBimestralBox;
        this.alunoLogado = alunoLogado;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        protected TextView tvDisciplina;
        protected TextView tvSituacao;
        protected TextView tvSugestaoNota;

        public ViewHolder(View itemView) {
            super(itemView);

            tvDisciplina = itemView.findViewById(R.id.tv_disciplina);
            tvSituacao = itemView.findViewById(R.id.tv_situacao);
            tvSugestaoNota = itemView.findViewById(R.id.tv_sugestao_nota);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_rendimento, parent,false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Disciplina disciplina = this.disciplinas.get(position);

        holder.tvDisciplina.setText(disciplina.getNomeDisciplina());
    }

//        BoletimBimestral boletimBimestral = obterBoletimBimestral(boletimBimestralId);
//
//        if(disciplina.getPeriodo().equals("Bimestral")){
//            String situacao = verSituacaoAluno(alunoLogado.getMediaEscola(),alunoLogado.getMediaPessoal(),boletimBimestral.getMedia());
//        }else if(disciplina.getPeriodo().equals("Semestral")){
//
//        }
//
//
//        holder.tvSituacao.setText(situacao);
//
//        String sugestao = sugerirNota();
//        holder.tvSugestaoNota.setText(sugestao);
//
//    }



    private String verSituacaoAluno(double mediaEscola, double mediaPessoal, double mediaAtual) {
        if (mediaAtual>=mediaPessoal){
            return "Media maior que a pessoal";
        }else if(mediaAtual>=mediaEscola){
            return "Media maior que a escola";
        }
        return "Media menor que a pessoal";
    }

    @Override
    public int getItemCount() {
        return this.disciplinas.size();
    }


    public BoletimBimestral obterBoletimBimestral(long id){
        BoletimBimestral boletimBimestral = boletimBimestralBox.get(id);
        return boletimBimestral;
    }

}
