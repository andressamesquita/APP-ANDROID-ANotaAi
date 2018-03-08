package com.example.andressaribeiro.myfirstapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.andressaribeiro.myfirstapp.R;
import com.example.andressaribeiro.myfirstapp.model.Agenda;

import java.util.List;

import io.objectbox.Box;

/**
 * Created by Andressa Ribeiro on 07/03/2018.
 */

public class ListaAgendaRVAdapter extends RecyclerView.Adapter<ListaAgendaRVAdapter.ViewHolder>{

    private final Context context;
    private final List<Agenda> agendas;
    private Box<Agenda> agendaBox;

    public ListaAgendaRVAdapter(Context context, List<Agenda> agendas, Box<Agenda>agendaBox){

        this.context = context;
        this.agendas = agendas;
        this.agendaBox = agendaBox;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_agenda, parent,false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Agenda agenda = this.agendas.get(position);

        holder.tvEvento.setText(agenda.getTituloEvento());
        holder.tvData.setText(agenda.getData());
        holder.tvDescricao.setText(agenda.getDescricao());
    }

    @Override
    public int getItemCount() {
        return agendas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        protected TextView tvEvento;
        protected TextView tvData;
        protected TextView tvDescricao;

        public ViewHolder(View itemView) {
            super(itemView);

            tvEvento = itemView.findViewById(R.id.tv_evento);
            tvData = itemView.findViewById(R.id.tv_data);
            tvDescricao = itemView.findViewById(R.id.tv_descricao);
        }
    }
}
