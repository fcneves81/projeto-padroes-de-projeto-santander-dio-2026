package com.middleearth.patterns;

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessaoAventura {
    private String heroiAtual;
    private String titulo;
    private List<String> grupo;
    private String localAtual;
    private String estadoJogo; // JOGANDO, GAMEOVER, VITORIA, ESCOLHA_HEROI
    
    private int hpAtual;
    private int hpMax;
    private int staminaAtual;
    private int staminaMax;
    private int xpAtual;
    private int xpMaximo;
    private int nivel;
    private int dinheiro;
    
    private int pocoes;
    private int pocoesStamina;
    private int paes;
    private int lembas;
    private int carne;
    private int batata;
    private int especiarias;
    
    private int inimigosRestantes;
    private String inimigoAtualNome;
    private int inimigoAtualHp;
    private int inimigoAtualAtaque;
    private int inimigoCooldown;
    
    private int ataquesHeroiContador;
    
    private boolean ensopadoEowynComido;
    private boolean eventoEspecialConcluido;
    private int inimigosMortosLocal;
    private int inimigosMortosSessao;
    private int batalhasMordor;
    
    private String missaoAtiva;
    private int missaoProgresso;
    private int missaoObjetivo;
    
    public SessaoAventura() {
        reset();
    }
    
    public void reset() {
        this.heroiAtual = null;
        this.localAtual = "Condado";
        this.grupo = new ArrayList<>();
        this.titulo = "";
        this.estadoJogo = "JOGANDO";
        this.hpMax = 100;
        this.hpAtual = 100;
        this.staminaMax = 100;
        this.staminaAtual = 100;
        this.xpAtual = 0;
        this.xpMaximo = 100;
        this.nivel = 1;
        this.dinheiro = 0;
        this.pocoes = 3;
        this.pocoesStamina = 1;
        this.paes = 0;
        this.lembas = 0;
        this.carne = 0;
        this.batata = 0;
        this.especiarias = 0;
        this.ensopadoEowynComido = false;
        this.eventoEspecialConcluido = false;
        this.inimigosMortosLocal = 0;
        this.inimigosMortosSessao = 0;
        this.batalhasMordor = 0;
        limparCombate();
        this.missaoAtiva = null;
        this.missaoProgresso = 0;
        this.missaoObjetivo = 0;
    }
    
    public void limparCombate() {
        this.inimigosRestantes = 0;
        this.inimigoAtualNome = null;
        this.inimigoAtualHp = 0;
        this.inimigoAtualAtaque = 0;
        this.inimigoCooldown = 0;
        this.ataquesHeroiContador = 0;
    }
    
    // Getters and Setters
    public String getHeroiAtual() { return heroiAtual; }
    public void setHeroiAtual(String heroiAtual) { this.heroiAtual = heroiAtual; }
    
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public List<String> getGrupo() { return grupo; }
    public void setGrupo(List<String> grupo) { this.grupo = grupo; }

    public String getLocalAtual() { return localAtual; }
    public void setLocalAtual(String localAtual) { this.localAtual = localAtual; }
    
    public String getEstadoJogo() { return estadoJogo; }
    public void setEstadoJogo(String estadoJogo) { this.estadoJogo = estadoJogo; }

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
    
    public int getInimigosRestantes() { return inimigosRestantes; }
    public void setInimigosRestantes(int inimigosRestantes) { this.inimigosRestantes = inimigosRestantes; }
    
    public String getInimigoAtualNome() { return inimigoAtualNome; }
    public void setInimigoAtualNome(String inimigoAtualNome) { this.inimigoAtualNome = inimigoAtualNome; }
    
    public int getInimigoAtualHp() { return inimigoAtualHp; }
    public void setInimigoAtualHp(int inimigoAtualHp) { this.inimigoAtualHp = inimigoAtualHp; }
    
    public int getInimigoAtualAtaque() { return inimigoAtualAtaque; }
    public void setInimigoAtualAtaque(int inimigoAtualAtaque) { this.inimigoAtualAtaque = inimigoAtualAtaque; }
    
    public int getInimigoCooldown() { return inimigoCooldown; }
    public void setInimigoCooldown(int inimigoCooldown) { this.inimigoCooldown = inimigoCooldown; }
    
    public int getAtaquesHeroiContador() { return ataquesHeroiContador; }
    public void setAtaquesHeroiContador(int ataquesHeroiContador) { this.ataquesHeroiContador = ataquesHeroiContador; }

    public boolean isEnsopadoEowynComido() { return ensopadoEowynComido; }
    public void setEnsopadoEowynComido(boolean ensopadoEowynComido) { this.ensopadoEowynComido = ensopadoEowynComido; }

    public boolean isEventoEspecialConcluido() { return eventoEspecialConcluido; }
    public void setEventoEspecialConcluido(boolean eventoEspecialConcluido) { this.eventoEspecialConcluido = eventoEspecialConcluido; }

    public int getInimigosMortosLocal() { return inimigosMortosLocal; }
    public void setInimigosMortosLocal(int inimigosMortosLocal) { this.inimigosMortosLocal = inimigosMortosLocal; }

    public int getInimigosMortosSessao() { return inimigosMortosSessao; }
    public void setInimigosMortosSessao(int inimigosMortosSessao) { this.inimigosMortosSessao = inimigosMortosSessao; }

    public int getBatalhasMordor() { return batalhasMordor; }
    public void setBatalhasMordor(int batalhasMordor) { this.batalhasMordor = batalhasMordor; }

    public String getMissaoAtiva() { return missaoAtiva; }
    public void setMissaoAtiva(String missaoAtiva) { this.missaoAtiva = missaoAtiva; }
    
    public int getMissaoProgresso() { return missaoProgresso; }
    public void setMissaoProgresso(int missaoProgresso) { this.missaoProgresso = missaoProgresso; }
    
    public int getMissaoObjetivo() { return missaoObjetivo; }
    public void setMissaoObjetivo(int missaoObjetivo) { this.missaoObjetivo = missaoObjetivo; }
}
