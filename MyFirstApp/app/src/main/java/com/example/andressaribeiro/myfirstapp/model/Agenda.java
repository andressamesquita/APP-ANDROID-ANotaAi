package com.example.andressaribeiro.myfirstapp.model;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToOne;

/**
 * Created by Andressa Ribeiro on 07/03/2018.
 */
@Entity
public class Agenda {

    @Id private long id;
    private String tituloEvento;
    private String data;
    private String descricao;

    private ToOne<Aluno> alunoToOne;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTituloEvento() {
        return tituloEvento;
    }

    public void setTituloEvento(String tituloEvento) {
        this.tituloEvento = tituloEvento;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ToOne<Aluno> getAlunoToOne() {
        return alunoToOne;
    }

    public void setAlunoToOne(ToOne<Aluno> alunoToOne) {
        this.alunoToOne = alunoToOne;
    }
}
