package com.middleearth.patterns;

import org.springframework.stereotype.Component;

@Component
public class AtaqueMagia implements EstrategiaAtaque {
    @Override
    public String atacar() {
        return "🔮 Gandalf invoca a luz de Valinor!";
    }
    
    @Override
    public int getDano() { return 50; }
}
