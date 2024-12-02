package br.com.OrganizedDev.ProjectOrganizedDev.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Costumer{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private Long id;

    private String cnpj;

    private String telefone;

    private String nomeFantasia;

    private String setorDeAtuacao;

    private String descricao;

    private int numeroDeFuncionarios;

    private LocalDate dataFundacao;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "appuser_id")
    private AppUser appUser;

    @OneToMany( mappedBy = "costumer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Project> projects;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getSetorDeAtuacao() {
        return setorDeAtuacao;
    }

    public void setSetorDeAtuacao(String setorDeAtuacao) {
        this.setorDeAtuacao = setorDeAtuacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(LocalDate dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public int getNumeroDeFuncionarios() {
        return numeroDeFuncionarios;
    }

    public void setNumeroDeFuncionarios(int numeroDeFuncionarios) {
        this.numeroDeFuncionarios = numeroDeFuncionarios;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }


    public List<Project> getProjects() {
        return projects;
    }

    public void setProject(List<Project> projects) {
        this.projects = projects;
    }
}
