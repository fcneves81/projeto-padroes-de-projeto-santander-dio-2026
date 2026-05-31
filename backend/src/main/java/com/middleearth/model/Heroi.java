package com.middleearth.model;

import javax.persistence.*;

@Entity
public class Heroi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String raca;
    private int nivel;
    private int experiencia;
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getRaca() { return raca; }
    public void setRaca(String raca) { this.raca = raca; }
    
    public int getNivel() { return nivel; }
    public void setNivel(int nivel) { this.nivel = nivel; }
    
    public int getExperiencia() { return experiencia; }
    public void setExperiencia(int experiencia) { this.experiencia = experiencia; }
}
