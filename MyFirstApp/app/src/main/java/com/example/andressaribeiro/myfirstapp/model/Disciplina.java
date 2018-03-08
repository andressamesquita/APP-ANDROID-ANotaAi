package com.example.andressaribeiro.myfirstapp.model;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;
import io.objectbox.relation.ToOne;

/**
 * Created by Andressa Ribeiro on 27/02/2018.
 */
@Entity
public class Disciplina {

    @Id private long id;
    private String nomeDisciplina;
    private String professor;
    private String periodo;
    private String conteudos;


    private ToOne<Aluno> alunoToOne;

    @Backlink
    private ToMany<BoletimBimestral> boletimBimestralToMany;
    private ToMany<BoletimSemestral> boletimSemestralToMany;


    public Disciplina(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public ToOne<Aluno> getAlunoToOne() {
        return alunoToOne;
    }

    public void setAlunoToOne(ToOne<Aluno> alunoToOne) {
        this.alunoToOne = alunoToOne;
    }

    public ToMany<BoletimBimestral> getBoletimBimestralToMany() {
        return boletimBimestralToMany;
    }

    public void setBoletimBimestralToMany(ToMany<BoletimBimestral> boletimBimestralToMany) {
        this.boletimBimestralToMany = boletimBimestralToMany;
    }

    public ToMany<BoletimSemestral> getBoletimSemestralToMany() {
        return boletimSemestralToMany;
    }

    public void setBoletimSemestralToMany(ToMany<BoletimSemestral> boletimSemestralToMany) {
        this.boletimSemestralToMany = boletimSemestralToMany;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }


    public String getConteudos() {
        return conteudos;
    }

    public void setConteudos(String conteudos) {
        this.conteudos = conteudos;
    }
}
