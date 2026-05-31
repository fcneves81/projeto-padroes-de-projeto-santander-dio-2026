package com.middleearth.patterns;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class JornadaFacade {
    private final List<String> rotaLinear = Arrays.asList(
        "Condado", "Bree", "Valfenda", "Moria", "Lothlórien", "Amon Hen", "Rohan", 
        "Abismo de Helm", "Gondor", "Minas Tirith", "Campos do Pelennor", 
        "Minas Morgul", "Cirith Ungol", "Covil de Laracna", "Mordor", 
        "Portão Negro", "Monte da Perdição"
    );
    
    private final SessaoAventura sessao;
    
    public JornadaFacade(SessaoAventura sessao) {
        this.sessao = sessao;
    }
    
    public List<String> getDestinos(String localAtual) {
        List<String> destinos = new ArrayList<>();
        
        int index = rotaLinear.indexOf(localAtual);
        if (index == -1) return destinos;

        // Regra: Não pode voltar de Mordor em diante
        if (index >= rotaLinear.indexOf("Mordor")) {
            if (index < rotaLinear.size() - 1) {
                destinos.add(rotaLinear.get(index + 1));
            }
            return destinos; // Apenas para frente
        }
        
        if (index > 0) {
            destinos.add(rotaLinear.get(index - 1));
        }
        if (index < rotaLinear.size() - 1) {
            destinos.add(rotaLinear.get(index + 1));
        }
        return destinos;
    }
    
    public String viajar(String origem, String destino) {
        List<String> disponiveis = getDestinos(origem);
        
        if ("Mordor".equals(destino) && !sessao.isEventoEspecialConcluido()) {
            return "❌ Você precisa concluir seu Evento Especial antes de entrar em Mordor!";
        }
        
        if (disponiveis.contains(destino)) {
            sessao.setLocalAtual(destino);
            sessao.setInimigosMortosLocal(0); // Reseta kills do local
            return "✅ Jornada concluída! Você chegou em " + destino;
        }
        
        return "❌ Caminho impossível! O caminho está bloqueado ou muito distante.";
    }
}
