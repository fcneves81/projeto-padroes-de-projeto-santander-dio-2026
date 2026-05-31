package com.middleearth.dto;

public class AtaqueResponse {
    private String resultado;
    private String inimigo;

    public AtaqueResponse(String resultado, String inimigo) {
        this.resultado = resultado;
        this.inimigo = inimigo;
    }

    public String getResultado() { return resultado; }
    public void setResultado(String resultado) { this.resultado = resultado; }

    public String getInimigo() { return inimigo; }
    public void setInimigo(String inimigo) { this.inimigo = inimigo; }
}
