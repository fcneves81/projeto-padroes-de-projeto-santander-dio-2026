package com.middleearth.patterns;

import org.springframework.stereotype.Component;

@Component
public class AtaqueEspada implements EstrategiaAtaque {
    @Override
    public String atacar() {
        return "⚔️ Golpe devastador com Andúril!";
    }
    
    @Override
    public int getDano() { return 45; }
}
