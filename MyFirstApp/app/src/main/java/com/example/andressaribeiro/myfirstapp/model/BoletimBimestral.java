package com.example.andressaribeiro.myfirstapp.model;

/**
 * Created by Andressa Ribeiro on 05/03/2018.
 */

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;
import io.objectbox.relation.ToOne;

@Entity
public class BoletimBimestral {

    @Id
    private long id;
    private double notaB1;
    private double notaB2;


    private ToOne<Disciplina> disciplinaToOne;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public double getNotaB1() {
        return notaB1;
    }

    public void setNotaB1(double notaB1) {
        this.notaB1 = notaB1;
    }

    public double getNotaB2() {
        return notaB2;
    }

    public void setNotaB2(double notaB2) {
        this.notaB2 = notaB2;
    }

    public ToOne<Disciplina> getDisciplinaToOne() {
        return disciplinaToOne;
    }

    public void setDisciplinaToOne(ToOne<Disciplina> disciplinaToOne) {
        this.disciplinaToOne = disciplinaToOne;
    }

    public double getMedia() {
        return ((this.notaB1 + this.notaB2) / 2);
    }


}
