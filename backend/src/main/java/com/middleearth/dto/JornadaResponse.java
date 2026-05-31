package com.middleearth.dto;

import java.util.List;

public class JornadaResponse {
    private String mensagem;
    private List<String> destinosDisponiveis;

    public JornadaResponse(String mensagem, List<String> destinosDisponiveis) {
        this.mensagem = mensagem;
        this.destinosDisponiveis = destinosDisponiveis;
    }
    
    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
    
    public List<String> getDestinosDisponiveis() { return destinosDisponiveis; }
    public void setDestinosDisponiveis(List<String> destinosDisponiveis) { this.destinosDisponiveis = destinosDisponiveis; }
}
