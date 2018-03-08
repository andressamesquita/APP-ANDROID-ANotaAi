package com.example.andressaribeiro.myfirstapp.model;

import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;

/**
 * Created by Andressa Ribeiro on 12/02/2018.
 */
@Entity
public class Aluno {

    @Id private long id;
    private String nome;
    private String escola;
    private double mediaEscola;
    private double mediaPessoal;
    private String email;
    private String senha;

    @Backlink
    private ToMany<Agenda> agendaToMany;
    private ToMany<Disciplina> disciplinaToMany;
    private ToMany<BoletimBimestral> boletimBimestralToMany;
    private ToMany<BoletimSemestral> boletimSemestralToMany;




    public Aluno(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEscola() {
        return escola;
    }

    public void setEscola(String escola) {
        this.escola = escola;
    }

    public double getMediaEscola() {
        return mediaEscola;
    }

    public void setMediaEscola(double mediaEscola) {
        this.mediaEscola = mediaEscola;
    }

    public double getMediaPessoal() {
        return mediaPessoal;
    }

    public void setMediaPessoal(double mediaPessoal) {
        this.mediaPessoal = mediaPessoal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public ToMany<Disciplina> getDisciplinaToMany() {
        return disciplinaToMany;
    }

    public void setDisciplinaToMany(ToMany<Disciplina> disciplinaToMany) {
        this.disciplinaToMany = disciplinaToMany;
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

    public ToMany<Agenda> getAgendaToMany() {
        return agendaToMany;
    }

    public void setAgendaToMany(ToMany<Agenda> agendaToMany) {
        this.agendaToMany = agendaToMany;
    }
}
