package com.middleearth.dto;

import java.util.List;

public class StatusAventuraDTO {
    private String heroi;
    private String titulo;
    private List<String> grupo;
    private String local;
    private int hpAtual;
    private int hpMax;
    private int staminaAtual;
    private int staminaMax;
    private int xpAtual;
    private int xpMaximo;
    private int nivel;
    private int dinheiro;
    
    private int ataquesHeroiContador;
    
    private int pocoes;
    private int pocoesStamina;
    private int paes;
    private int lembas;
    private int carne;
    private int batata;
    private int especiarias;
    
    private boolean ensopadoEowynComido;
    private boolean eventoEspecialConcluido;
    private int inimigosMortosLocal;
    private String estadoJogo; // JOGANDO, GAMEOVER, VITORIA, ESCOLHA_HEROI
    
    private List<AtaqueDTO> ataquesDisponiveis;
    private String textoEventoEspecial;
    
    private int inimigosRestantes;
    private String inimigoAtualNome;
    private int inimigoAtualHp;
    private String missaoAtiva;
    private int missaoProgresso;
    private int missaoObjetivo;
    private List<String> destinosDisponiveis;

    public StatusAventuraDTO() {}

    public String getHeroi() { return heroi; }
    public void setHeroi(String heroi) { this.heroi = heroi; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public List<String> getGrupo() { return grupo; }
    public void setGrupo(List<String> grupo) { this.grupo = grupo; }

    public String getLocal() { return local; }
    public void setLocal(String local) { this.local = local; }

    public int getHpAtual() { return hpAtual; }
    public void setHpAtual(int hpAtual) { this.hpAtual = hpAtual; }

    public int getHpMax() { return hpMax; }
    public void setHpMax(int hpMax) { this.hpMax = hpMax; }

    public int getStaminaAtual() { return staminaAtual; }
    public void setStaminaAtual(int staminaAtual) { this.staminaAtual = staminaAtual; }

    public int getStaminaMax() { return staminaMax; }
    public void setStaminaMax(int staminaMax) { this.staminaMax = staminaMax; }

    public int getXpAtual() { return xpAtual; }
    public void setXpAtual(int xpAtual) { this.xpAtual = xpAtual; }

    public int getXpMaximo() { return xpMaximo; }
    public void setXpMaximo(int xpMaximo) { this.xpMaximo = xpMaximo; }

    public int getNivel() { return nivel; }
    public void setNivel(int nivel) { this.nivel = nivel; }

    public int getDinheiro() { return dinheiro; }
    public void setDinheiro(int dinheiro) { this.dinheiro = dinheiro; }

    public int getAtaquesHeroiContador() { return ataquesHeroiContador; }
    public void setAtaquesHeroiContador(int ataquesHeroiContador) { this.ataquesHeroiContador = ataquesHeroiContador; }

    public int getPocoes() { return pocoes; }
    public void setPocoes(int pocoes) { this.pocoes = pocoes; }

    public int getPocoesStamina() { return pocoesStamina; }
    public void setPocoesStamina(int pocoesStamina) { this.pocoesStamina = pocoesStamina; }

    public int getPaes() { return paes; }
    public void setPaes(int paes) { this.paes = paes; }

    public int getLembas() { return lembas; }
    public void setLembas(int lembas) { this.lembas = lembas; }

    public int getCarne() { return carne; }
    public void setCarne(int carne) { this.carne = carne; }

    public int getBatata() { return batata; }
    public void setBatata(int batata) { this.batata = batata; }

    public int getEspeciarias() { return especiarias; }
    public void setEspeciarias(int especiarias) { this.especiarias = especiarias; }

    public boolean isEnsopadoEowynComido() { return ensopadoEowynComido; }
    public void setEnsopadoEowynComido(boolean ensopadoEowynComido) { this.ensopadoEowynComido = ensopadoEowynComido; }

    public boolean isEventoEspecialConcluido() { return eventoEspecialConcluido; }
    public void setEventoEspecialConcluido(boolean eventoEspecialConcluido) { this.eventoEspecialConcluido = eventoEspecialConcluido; }

    public int getInimigosMortosLocal() { return inimigosMortosLocal; }
    public void setInimigosMortosLocal(int inimigosMortosLocal) { this.inimigosMortosLocal = inimigosMortosLocal; }

    public String getEstadoJogo() { return estadoJogo; }
    public void setEstadoJogo(String estadoJogo) { this.estadoJogo = estadoJogo; }

    public List<AtaqueDTO> getAtaquesDisponiveis() { return ataquesDisponiveis; }
    public void setAtaquesDisponiveis(List<AtaqueDTO> ataquesDisponiveis) { this.ataquesDisponiveis = ataquesDisponiveis; }

    public String getTextoEventoEspecial() { return textoEventoEspecial; }
    public void setTextoEventoEspecial(String textoEventoEspecial) { this.textoEventoEspecial = textoEventoEspecial; }

    public int getInimigosRestantes() { return inimigosRestantes; }
    public void setInimigosRestantes(int inimigosRestantes) { this.inimigosRestantes = inimigosRestantes; }

    public String getInimigoAtualNome() { return inimigoAtualNome; }
    public void setInimigoAtualNome(String inimigoAtualNome) { this.inimigoAtualNome = inimigoAtualNome; }

    public int getInimigoAtualHp() { return inimigoAtualHp; }
    public void setInimigoAtualHp(int inimigoAtualHp) { this.inimigoAtualHp = inimigoAtualHp; }

    public String getMissaoAtiva() { return missaoAtiva; }
    public void setMissaoAtiva(String missaoAtiva) { this.missaoAtiva = missaoAtiva; }

    public int getMissaoProgresso() { return missaoProgresso; }
    public void setMissaoProgresso(int missaoProgresso) { this.missaoProgresso = missaoProgresso; }

    public int getMissaoObjetivo() { return missaoObjetivo; }
    public void setMissaoObjetivo(int missaoObjetivo) { this.missaoObjetivo = missaoObjetivo; }

    public List<String> getDestinosDisponiveis() { return destinosDisponiveis; }
    public void setDestinosDisponiveis(List<String> destinosDisponiveis) { this.destinosDisponiveis = destinosDisponiveis; }
}
