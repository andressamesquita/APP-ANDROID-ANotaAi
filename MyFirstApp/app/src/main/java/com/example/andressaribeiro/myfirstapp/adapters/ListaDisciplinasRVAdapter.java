package com.example.andressaribeiro.myfirstapp.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andressaribeiro.myfirstapp.app.BoletimBimestralActivity;
import com.example.andressaribeiro.myfirstapp.app.BoletimSemestralActivity;
import com.example.andressaribeiro.myfirstapp.app.CadastroDisciplinaActivity;
import com.example.andressaribeiro.myfirstapp.R;
import com.example.andressaribeiro.myfirstapp.model.Disciplina;

import java.util.List;

import io.objectbox.Box;

/**
 * Created by Andressa Ribeiro on 27/02/2018.
 */

public class ListaDisciplinasRVAdapter extends RecyclerView.Adapter<ListaDisciplinasRVAdapter.ViewHolder> {

    private final Context context;
    private final List<Disciplina> disciplinas;
    private Box<Disciplina>disciplinaBox;



    public ListaDisciplinasRVAdapter(Context context, List<Disciplina> disciplinas, Box<Disciplina>disciplinaBox){

        this.context = context;
        this.disciplinas = disciplinas;
        this.disciplinaBox = disciplinaBox;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //infla o layout da linha
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_disciplina, parent,false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Disciplina disciplina = this.disciplinas.get(position);

        holder.tvDisciplinaNome.setText(disciplina.getNomeDisciplina());
        holder.tvDisciplinaProfessor.setText(disciplina.getProfessor());
        holder.tvDisciplinaPeriodoAvaliativo.setText(disciplina.getPeriodo());



        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View view) {
                final PopupMenu menuPopup = new PopupMenu(context,view);
                MenuInflater inflater = menuPopup.getMenuInflater();

                inflater.inflate(R.menu.popup_menu_lista_disciplinas, menuPopup.getMenu());
                menuPopup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        switch (menuItem.getItemId()){

                            case R.id.item_remover_disciplina :
                                excluirDisciplina(view,disciplina,position);
                                break;

                            case R.id.item_addnota_disciplina:
                                if(disciplina.getPeriodo().equals("Bimestral")){
                                    pegarNotasBimestre(disciplina);
                                }
                                if(disciplina.getPeriodo().equals("Semestral")){
                                    pegarNotasSemestre(disciplina);
                                }
                                break;

                            case R.id.item_editar_disciplina:
                                editarDisciplina(view,disciplina,position);
                                break;
                        }

                        return false;
                    }


                });

                menuPopup.show();

                return true;// significa que o item é dono do click
            }
        });
    }

    private void pegarNotasBimestre(Disciplina disciplina) {
        Intent intent = new Intent(context,BoletimBimestralActivity.class);
        intent.putExtra("disciplinaId",disciplina.getId());
        context.startActivity(intent);
    }

    private void pegarNotasSemestre(Disciplina disciplina) {
        Intent intent = new Intent(context,BoletimSemestralActivity.class);
        intent.putExtra("disciplinaId",disciplina.getId());
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return disciplinas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        protected TextView tvDisciplinaNome;
        protected TextView tvDisciplinaProfessor;
        protected TextView tvDisciplinaPeriodoAvaliativo;

        public ViewHolder(View itemView) {
            super(itemView);

            tvDisciplinaNome = itemView.findViewById(R.id.tv_disciplina_nome);
            tvDisciplinaProfessor = itemView.findViewById(R.id.tv_disciplina_professor);
            tvDisciplinaPeriodoAvaliativo = itemView.findViewById(R.id.tv_disciplina_aplicacao);
        }
    }

    private void editarDisciplina(View view, Disciplina disciplina, int position){

        //enviar o id da disciplina selecionada
        Intent intent = new Intent(context, CadastroDisciplinaActivity.class);
        intent.putExtra("disciplinaId",disciplina.getId());

        //Iniciar o formulario
        context.startActivity(intent);

        //Avisar à intent que um item mudou
        notifyItemChanged(position);
    }

    private void excluirDisciplina(View view, Disciplina disciplina, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);

        builder.setTitle("Remover disciplina?")
                .setPositiveButton("SIM", ((dialog, which) -> {
                    this.disciplinas.remove(disciplina);
                    this.disciplinaBox.remove(disciplina);
                    notifyItemRemoved(position);
                    notifyItemChanged(position);
                    Snackbar.make(view, "Disciplina " + disciplina.getNomeDisciplina() + " foi removida", Snackbar.LENGTH_SHORT).show();
                }));
                builder.setNegativeButton("NÃO", ((dialog, which) -> {
                    Toast.makeText(context, "Disciplina "+disciplina.getNomeDisciplina()+" permanece!", Toast.LENGTH_SHORT).show();
                }));
                builder.create().show();
    }
}
