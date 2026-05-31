package com.middleearth.dto;

public class AtaqueDTO {
    private String nome;
    private int custoStamina;
    private boolean isEspecial;
    
    public AtaqueDTO() {}
    
    public AtaqueDTO(String nome, int custoStamina, boolean isEspecial) {
        this.nome = nome;
        this.custoStamina = custoStamina;
        this.isEspecial = isEspecial;
    }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public int getCustoStamina() { return custoStamina; }
    public void setCustoStamina(int custoStamina) { this.custoStamina = custoStamina; }
    
    public boolean isEspecial() { return isEspecial; }
    public void setEspecial(boolean isEspecial) { this.isEspecial = isEspecial; }
}
