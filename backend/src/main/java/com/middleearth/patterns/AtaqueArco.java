package com.middleearth.patterns;

import org.springframework.stereotype.Component;

@Component
public class AtaqueArco implements EstrategiaAtaque {
    @Override
    public String atacar() {
        return "🏹 Flecha de Legolas atingiu o coração!";
    }
    
    @Override
    public int getDano() { return 35; }
}
