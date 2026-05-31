package com.middleearth.controller;

import com.middleearth.dto.AtaqueDTO;
import com.middleearth.dto.AtaqueResponse;
import com.middleearth.dto.JornadaDTO;
import com.middleearth.dto.JornadaResponse;
import com.middleearth.dto.StatusAventuraDTO;
import com.middleearth.patterns.JornadaFacade;
import com.middleearth.patterns.SessaoAventura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/aventura")
@CrossOrigin(origins = {"http://localhost:4200", "https://www.qudtecnologia.com.br"})
public class AventuraController {

    @Autowired
    private JornadaFacade jornadaFacade;

    @GetMapping("/status")
    public synchronized ResponseEntity<StatusAventuraDTO> getStatus() {
        SessaoAventura sessao = SessaoAventura.getInstance();
        StatusAventuraDTO dto = new StatusAventuraDTO();
        
        dto.setEstadoJogo(sessao.getEstadoJogo());
        if ("GAMEOVER".equals(sessao.getEstadoJogo()) || "VITORIA".equals(sessao.getEstadoJogo())) {
            return ResponseEntity.ok(dto);
        }

        dto.setHeroi(sessao.getHeroiAtual());
        dto.setTitulo(sessao.getTitulo());
        dto.setGrupo(sessao.getGrupo());
        dto.setLocal(sessao.getLocalAtual());
        dto.setHpAtual(sessao.getHpAtual());
        dto.setHpMax(sessao.getHpMax());
        dto.setStaminaAtual(sessao.getStaminaAtual());
        dto.setStaminaMax(sessao.getStaminaMax());
        dto.setXpAtual(sessao.getXpAtual());
        dto.setXpMaximo(sessao.getXpMaximo());
        dto.setNivel(sessao.getNivel());
        dto.setDinheiro(sessao.getDinheiro());

        dto.setDestinosDisponiveis(jornadaFacade.getDestinos(sessao.getLocalAtual()));

        dto.setAtaquesHeroiContador(sessao.getAtaquesHeroiContador());

        dto.setPocoes(sessao.getPocoes());
        dto.setPocoesStamina(sessao.getPocoesStamina());
        dto.setPaes(sessao.getPaes());
        dto.setLembas(sessao.getLembas());
        dto.setCarne(sessao.getCarne());
        dto.setBatata(sessao.getBatata());
        dto.setEspeciarias(sessao.getEspeciarias());

        dto.setEnsopadoEowynComido(sessao.isEnsopadoEowynComido());
        dto.setEventoEspecialConcluido(sessao.isEventoEspecialConcluido());
        dto.setInimigosMortosLocal(sessao.getInimigosMortosLocal());

        dto.setInimigosRestantes(sessao.getInimigosRestantes());
        dto.setInimigoAtualNome(sessao.getInimigoAtualNome());
        dto.setInimigoAtualHp(sessao.getInimigoAtualHp());
        dto.setMissaoAtiva(sessao.getMissaoAtiva());
        dto.setMissaoProgresso(sessao.getMissaoProgresso());
        dto.setMissaoObjetivo(sessao.getMissaoObjetivo());

        // Attacks
        List<AtaqueDTO> ataques = getAtaquesHeroi(sessao);
        dto.setAtaquesDisponiveis(ataques);
        
        // Texto Especial
        String txtEspecial = "";
        if ("Portão Negro".equals(sessao.getLocalAtual())) {
            txtEspecial = "O momento final chegou.";
        } else if (sessao.isEventoEspecialConcluido()) {
            txtEspecial = "Inimigos derrotados: " + sessao.getInimigosMortosLocal();
        } else {
            String h = sessao.getHeroiAtual();
            String loc = sessao.getLocalAtual();
            boolean temE = (h != null) && (
                ("Gandalf".equals(h) && "Moria".equals(loc)) ||
                ("Boromir".equals(h) && "Amon Hen".equals(loc)) ||
                ("Sam".equals(h) && "Covil de Laracna".equals(loc)) ||
                ("Pippin".equals(h) && "Minas Tirith".equals(loc)) ||
                ("Aragorn".equals(h) && "Campos do Pelennor".equals(loc)) ||
                ("Merry".equals(h) && "Campos do Pelennor".equals(loc)) ||
                ("Legolas".equals(h) && "Campos do Pelennor".equals(loc)) ||
                ("Gimli".equals(h) && "Abismo de Helm".equals(loc))
            );
            
            if (temE) {
                if (verificarCondicaoEspecial(sessao)) {
                    txtEspecial = "⚠️ O evento épico de " + h + " está pronto! Faça um ataque especial!";
                } else {
                    txtEspecial = "🌟 Evento Especial de " + h + " nesta região!\nRequisitos: Nível > 5 e 30 inimigos derrotados.\nProgresso: Nível " + sessao.getNivel() + " | Inimigos: " + sessao.getInimigosMortosLocal() + "/30";
                }
            } else {
                txtEspecial = "Inimigos derrotados aqui: " + sessao.getInimigosMortosLocal() + " / 30";
            }
        }
        dto.setTextoEventoEspecial(txtEspecial);

        return ResponseEntity.ok(dto);
    }

    private List<AtaqueDTO> getAtaquesHeroi(SessaoAventura sessao) {
        List<AtaqueDTO> ataques = new ArrayList<>();
        String heroi = sessao.getHeroiAtual();
        if (heroi == null) return ataques;

        if ("Portão Negro".equals(sessao.getLocalAtual())) {
            ataques.add(new AtaqueDTO("Por Frodo!", 0, true));
            return ataques;
        }

        switch (heroi) {
            case "Aragorn":
                ataques.add(new AtaqueDTO("Espada", 10, false));
                ataques.add(new AtaqueDTO("Arco", 15, false));
                ataques.add(new AtaqueDTO("Ataque do Guardião", 20, false));
                if (verificarCondicaoEspecial(sessao)) ataques.add(new AtaqueDTO("Juramento Cumprido", 0, true));
                break;
            case "Legolas":
                ataques.add(new AtaqueDTO("Adagas", 10, false));
                ataques.add(new AtaqueDTO("Arco", 15, false));
                ataques.add(new AtaqueDTO("Rajada de Flechas", 20, false));
                if (verificarCondicaoEspecial(sessao)) ataques.add(new AtaqueDTO("Caçador de Olifantes", 0, true));
                break;
            case "Gimli":
                ataques.add(new AtaqueDTO("Espada", 10, false));
                ataques.add(new AtaqueDTO("Machado", 15, false));
                ataques.add(new AtaqueDTO("Força de Durin", 20, false));
                if (verificarCondicaoEspecial(sessao)) ataques.add(new AtaqueDTO("Machado de Glóin", 0, true));
                break;
            case "Gandalf":
                ataques.add(new AtaqueDTO("Espada", 10, false));
                ataques.add(new AtaqueDTO("Ataque de Cajado", 15, false));
                ataques.add(new AtaqueDTO("Luz do Istari", 20, false));
                if (verificarCondicaoEspecial(sessao)) ataques.add(new AtaqueDTO("Não Passarás!", 0, true));
                break;
            case "Sam":
                ataques.add(new AtaqueDTO("Pedrada", 10, false));
                ataques.add(new AtaqueDTO("Adaga", 15, false));
                ataques.add(new AtaqueDTO("Poder da Frigideira", 20, false));
                if (verificarCondicaoEspecial(sessao)) ataques.add(new AtaqueDTO("Luz de Eärendil", 0, true));
                break;
            case "Merry":
                ataques.add(new AtaqueDTO("Pedrada", 10, false));
                ataques.add(new AtaqueDTO("Espada de Hobbit", 15, false));
                ataques.add(new AtaqueDTO("Golpe de Brandebuque", 20, false));
                if (verificarCondicaoEspecial(sessao)) ataques.add(new AtaqueDTO("Ataque ao Rei Bruxo", 0, true));
                break;
            case "Pippin":
                ataques.add(new AtaqueDTO("Pedrada", 10, false));
                ataques.add(new AtaqueDTO("Guarda da Cidadela", 15, false));
                ataques.add(new AtaqueDTO("Coragem dos Tooks", 20, false));
                if (verificarCondicaoEspecial(sessao)) ataques.add(new AtaqueDTO("Lealdade a Gondor", 0, true));
                break;
            case "Boromir":
                if (verificarCondicaoEspecial(sessao)) {
                    ataques.add(new AtaqueDTO("Última Resistência", 0, true));
                } else {
                    ataques.add(new AtaqueDTO("Espada de Gondor", 10, false));
                    ataques.add(new AtaqueDTO("Escudo de Gondor", 15, false));
                    ataques.add(new AtaqueDTO("Chifre de Gondor", 20, false));
                }
                break;
        }
        return ataques;
    }

    private boolean verificarCondicaoEspecial(SessaoAventura sessao) {
        if (sessao.isEventoEspecialConcluido()) return false;
        if (sessao.getHeroiAtual() == null) return false;
        if (sessao.getNivel() <= 5) return false;
        if (sessao.getInimigosMortosLocal() < 30) return false;
        
        String h = sessao.getHeroiAtual();
        String l = sessao.getLocalAtual();
        
        if ("Gandalf".equals(h) && "Moria".equals(l)) return true;
        if ("Boromir".equals(h) && "Amon Hen".equals(l)) return true;
        if ("Sam".equals(h) && "Covil de Laracna".equals(l)) return true;
        if ("Pippin".equals(h) && "Minas Tirith".equals(l)) return true;
        if ("Aragorn".equals(h) && "Campos do Pelennor".equals(l)) return true;
        if ("Merry".equals(h) && "Campos do Pelennor".equals(l)) return true;
        if ("Legolas".equals(h) && "Campos do Pelennor".equals(l)) return true;
        if ("Gimli".equals(h) && "Abismo de Helm".equals(l)) return true;
        
        return false;
    }

    @PostMapping("/grupo/selecionar")
    public synchronized ResponseEntity<?> selecionarGrupo(@RequestBody List<String> herois) {
        if (herois == null || herois.isEmpty()) {
            return ResponseEntity.badRequest().body(new AtaqueResponse("Selecione um herói principal.", ""));
        }
        SessaoAventura sessao = SessaoAventura.getInstance();
        
        // Check if it's a swap (Boromir died)
        if ("ESCOLHA_HEROI".equals(sessao.getEstadoJogo())) {
            sessao.setHeroiAtual(herois.get(0));
            List<String> ajudantes = new ArrayList<>(herois);
            ajudantes.remove(0);
            sessao.setGrupo(ajudantes);
            sessao.setEstadoJogo("JOGANDO");
            sessao.setEventoEspecialConcluido(false);
            sessao.setTitulo(null);
            
            aplicarPassivaInicial(sessao);
            return ResponseEntity.ok(new AtaqueResponse(herois.get(0) + " assumiu a liderança da Sociedade!", ""));
        }
        
        sessao.reset();
        sessao.setHeroiAtual(herois.get(0));
        List<String> ajudantes = new ArrayList<>(herois);
        ajudantes.remove(0);
        sessao.setGrupo(ajudantes);
        
        aplicarPassivaInicial(sessao);
        
        return ResponseEntity.ok(new AtaqueResponse("Grupo formado! A jornada de " + herois.get(0) + " começou no Condado.", ""));
    }
    
    private void aplicarPassivaInicial(SessaoAventura sessao) {
        if ("Aragorn".equals(sessao.getHeroiAtual())) {
            sessao.setHpMax(sessao.getHpMax() + (int)(sessao.getHpMax() * 0.20));
            sessao.setHpAtual(sessao.getHpMax());
        }
        if ("Gandalf".equals(sessao.getHeroiAtual())) {
            sessao.setStaminaMax(sessao.getStaminaMax() + (int)(sessao.getStaminaMax() * 0.20));
            sessao.setStaminaAtual(sessao.getStaminaMax());
        }
    }

    @PostMapping("/explorar")
    public synchronized ResponseEntity<?> explorar() {
        SessaoAventura sessao = SessaoAventura.getInstance();
        if (sessao.getInimigosRestantes() > 0) {
            return ResponseEntity.badRequest().body(new AtaqueResponse("Você já está em combate!", ""));
        }
        if (!"JOGANDO".equals(sessao.getEstadoJogo())) {
            return ResponseEntity.badRequest().body(new AtaqueResponse("O jogo não está em andamento.", ""));
        }

        if (verificarCondicaoEspecial(sessao)) {
            sessao.setInimigosRestantes(1);
            sessao.setInimigoAtualNome("Chefe/Evento (" + sessao.getHeroiAtual() + ")");
            sessao.setInimigoAtualHp(100 + sessao.getNivel() * 30);
            sessao.setInimigoAtualAtaque(20 + sessao.getNivel() * 8);
            sessao.setInimigoCooldown(2);
            return ResponseEntity.ok(new AtaqueResponse("O evento especial foi ativado! Use sua habilidade para prosseguir.", sessao.getInimigoAtualNome()));
        } else {
            Random rand = new Random();
            int numInimigos = rand.nextInt(4) + 1;
            sessao.setInimigosRestantes(numInimigos);
            gerarProximoInimigo(sessao);
            return ResponseEntity.ok(new AtaqueResponse("Inimigos a caminho! (" + numInimigos + ")", sessao.getInimigoAtualNome()));
        }
    }

    private void gerarProximoInimigo(SessaoAventura sessao) {
        Random rand = new Random();
        String[] nomes = {"Orc Menor", "Uruk-hai", "Goblin", "Troll das Cavernas"};
        sessao.setInimigoAtualNome(nomes[rand.nextInt(nomes.length)]);
        int hpBase = 30 + sessao.getNivel() * 15;
        int atkBase = 10 + sessao.getNivel() * 5;
        sessao.setInimigoAtualHp(hpBase + rand.nextInt(20));
        sessao.setInimigoAtualAtaque(atkBase + rand.nextInt(10));
        sessao.setInimigoCooldown(2);
    }
    
    @GetMapping("/tick")
    public synchronized ResponseEntity<?> tick() {
        SessaoAventura sessao = SessaoAventura.getInstance();
        if (!"JOGANDO".equals(sessao.getEstadoJogo()) || sessao.getHeroiAtual() == null) {
            return ResponseEntity.ok(new AtaqueResponse("", ""));
        }
        
        sessao.setStaminaAtual(Math.min(sessao.getStaminaMax(), sessao.getStaminaAtual() + 1));
        
        StringBuilder log = new StringBuilder();
        
        if (sessao.getInimigosRestantes() > 0) {
            sessao.setInimigoCooldown(sessao.getInimigoCooldown() - 1);
            if (sessao.getInimigoCooldown() <= 0) {
                sessao.setInimigoCooldown(2);
                int danoSofrido = sessao.getInimigoAtualAtaque();
                
                // Passivas
                if ("Gimli".equals(sessao.getHeroiAtual())) danoSofrido = (int)(danoSofrido * 0.70);
                if ("Merry".equals(sessao.getHeroiAtual()) && new Random().nextInt(100) < 15) {
                    log.append("Merry esquivou! ");
                    danoSofrido = 0;
                }
                if ("Mordor".equals(sessao.getLocalAtual())) danoSofrido = (int)(danoSofrido * 1.5);
                
                if (danoSofrido > 0) {
                    sessao.setHpAtual(sessao.getHpAtual() - danoSofrido);
                    log.append("⚠️ ").append(sessao.getInimigoAtualNome()).append(" atacou (Dano: ").append(danoSofrido).append("). ");
                }
                
                if (sessao.getHpAtual() <= 0) {
                    log.append("VOCÊ MORREU! A escuridão consumiu a Terra-média...");
                    sessao.setEstadoJogo("GAMEOVER");
                }
            }
        }
        return ResponseEntity.ok(new AtaqueResponse(log.toString(), sessao.getInimigoAtualNome()));
    }
    
    @GetMapping("/ataque/{index}")
    public synchronized ResponseEntity<?> atacar(@PathVariable int index) {
        SessaoAventura sessao = SessaoAventura.getInstance();
        if (!"JOGANDO".equals(sessao.getEstadoJogo())) {
            return ResponseEntity.badRequest().body(new AtaqueResponse("Jogo não está em andamento.", ""));
        }
        if (sessao.getInimigosRestantes() <= 0) {
            return ResponseEntity.badRequest().body(new AtaqueResponse("Não há inimigos.", "Nenhum"));
        }

        List<AtaqueDTO> ataques = getAtaquesHeroi(sessao);
        if (index < 0 || index >= ataques.size()) return ResponseEntity.badRequest().body(new AtaqueResponse("Ataque inválido", ""));
        AtaqueDTO ataque = ataques.get(index);
        
        if (sessao.getStaminaAtual() < ataque.getCustoStamina()) {
            return ResponseEntity.ok(new AtaqueResponse("Stamina insuficiente para " + ataque.getNome() + "!", sessao.getInimigoAtualNome()));
        }
        
        sessao.setStaminaAtual(sessao.getStaminaAtual() - ataque.getCustoStamina());
        sessao.setAtaquesHeroiContador(sessao.getAtaquesHeroiContador() + 1);
        StringBuilder log = new StringBuilder();
        
        if (ataque.isEspecial()) {
            return resolverEventoEspecial(sessao, ataque.getNome());
        }
        
        int dano = 0;
        if (index == 0) dano = 10 + sessao.getNivel() * 5;
        if (index == 1) dano = 20 + sessao.getNivel() * 8;
        if (index == 2) dano = 50 + sessao.getNivel() * 20;
        
        // Passivas
        if ("Boromir".equals(sessao.getHeroiAtual())) dano = (int)(dano * 1.20);
        if ("Legolas".equals(sessao.getHeroiAtual()) && new Random().nextInt(100) < 20) {
            dano *= 2;
            log.append("ACERTO CRÍTICO! ");
        }
        if ("Mordor".equals(sessao.getLocalAtual())) dano = (int)(dano * 0.75);
        
        sessao.setInimigoAtualHp(sessao.getInimigoAtualHp() - dano);
        log.append("Você usou ").append(ataque.getNome()).append(" (Dano: ").append(dano).append("). ");
        
        verificarMorteInimigo(sessao, log);
        
        return ResponseEntity.ok(new AtaqueResponse(log.toString(), sessao.getInimigoAtualNome()));
    }

    private ResponseEntity<?> resolverEventoEspecial(SessaoAventura sessao, String nomeAtaque) {
        StringBuilder log = new StringBuilder();
        sessao.setInimigosRestantes(0);
        sessao.limparCombate();
        sessao.setEventoEspecialConcluido(true);
        sessao.setNivel(sessao.getNivel() + 1);
        
        if ("Juramento Cumprido".equals(nomeAtaque)) {
            log.append("Você cumpriu o Juramento e libertou o Exército dos Mortos! ");
            sessao.setTitulo("Rei de Gondor");
        } else if ("Caçador de Olifantes".equals(nomeAtaque)) {
            log.append("Gimli fica surpreso, mas ainda diz que conta como um! ");
            sessao.setTitulo("Matador de Olifantes");
        } else if ("Machado de Glóin".equals(nomeAtaque)) {
            log.append("Me jogue! Mas não conte ao elfo! ");
            sessao.setTitulo("Senhor dos Machados");
            sessao.setHpAtual(sessao.getHpMax());
            sessao.setStaminaAtual(sessao.getStaminaMax());
        } else if ("Não Passarás!".equals(nomeAtaque)) {
            log.append("Eu sou um servo do Fogo Secreto, empunhador da chama de Anor. Você não pode passar! ");
            sessao.setTitulo("O Branco");
            sessao.setHpMax(sessao.getHpMax() + 25);
            sessao.setStaminaMax(sessao.getStaminaMax() + 25);
        } else if ("Luz de Eärendil".equals(nomeAtaque)) {
            log.append("Ó Elbereth, Gilthoniel! A luz brilhou na escuridão e Laracna fugiu. ");
            sessao.setTitulo("Jardineiro Corajoso");
        } else if ("Ataque ao Rei Bruxo".equals(nomeAtaque)) {
            log.append("Eu a protegerei, senhora Éowyn! ");
            sessao.setTitulo("Escudeiro de Rohan");
        } else if ("Lealdade a Gondor".equals(nomeAtaque)) {
            log.append("Por Gondor! ");
            sessao.setTitulo("Guarda da Cidadela");
        } else if ("Última Resistência".equals(nomeAtaque)) {
            log.append("Um dia você será rei. Mas não hoje... Boromir sucumbe aos ferimentos. ");
            sessao.setTitulo("Herói de Gondor");
            sessao.setEstadoJogo("ESCOLHA_HEROI");
        } else if ("Por Frodo!".equals(nomeAtaque)) {
            log.append("E quando tudo parecia perdido, o Um Anel foi consumido pelas chamas da Montanha da Perdição. " +
                       "A Sombra passou, o Reino do Inimigo caiu, e a esperança floresceu mais uma vez na Terra-média!");
            sessao.setEstadoJogo("VITORIA");
        }
        
        return ResponseEntity.ok(new AtaqueResponse(log.toString(), ""));
    }

    private void verificarMorteInimigo(SessaoAventura sessao, StringBuilder log) {
        if (sessao.getInimigoAtualHp() <= 0) {
            log.append("Inimigo derrotado! ");
            sessao.setInimigosMortosLocal(sessao.getInimigosMortosLocal() + 1);
            sessao.setInimigosMortosSessao(sessao.getInimigosMortosSessao() + 1);
            sessao.setInimigosRestantes(sessao.getInimigosRestantes() - 1);
            
            // Drop de item a cada 5 mortes globais
            if (sessao.getInimigosMortosSessao() % 5 == 0) {
                int tipoDrop = new Random().nextInt(3);
                if (tipoDrop == 0) { sessao.setPocoes(sessao.getPocoes() + 1); log.append("Você encontrou uma Poção de Cura! "); }
                else if (tipoDrop == 1) { sessao.setPocoesStamina(sessao.getPocoesStamina() + 1); log.append("Você encontrou uma Poção de Stamina! "); }
                else { sessao.setPaes(sessao.getPaes() + 1); log.append("Você encontrou um Pão! "); }
            }
            
            // Passivas pós combate
            if (sessao.getInimigosRestantes() <= 0) {
                // Quest progress
                if (sessao.getMissaoAtiva() != null && sessao.getMissaoProgresso() < sessao.getMissaoObjetivo()) {
                    sessao.setMissaoProgresso(sessao.getMissaoProgresso() + 1);
                    log.append(" [Missão: ").append(sessao.getMissaoProgresso()).append("/").append(sessao.getMissaoObjetivo()).append("] ");
                }
                
                if ("Sam".equals(sessao.getHeroiAtual())) sessao.setHpAtual(Math.min(sessao.getHpMax(), sessao.getHpAtual() + (int)(sessao.getHpMax() * 0.1)));
                if ("Pippin".equals(sessao.getHeroiAtual())) sessao.setStaminaAtual(Math.min(sessao.getStaminaMax(), sessao.getStaminaAtual() + (int)(sessao.getStaminaMax() * 0.1)));
                
                if ("Mordor".equals(sessao.getLocalAtual())) {
                    sessao.setBatalhasMordor(sessao.getBatalhasMordor() + 1);
                    if (sessao.getBatalhasMordor() >= 10) {
                        jornadaFacade.viajar("Mordor", "Portão Negro");
                        log.append("\n\nVocê avançou o exército até o Portão Negro!");
                    }
                }
            }

            Random rand = new Random();
            int moedas = 5 + rand.nextInt(11);
            sessao.setDinheiro(sessao.getDinheiro() + moedas);
            
            // Ganhar XP
            int ganhoXp = 20 + sessao.getNivel() * 5 + rand.nextInt(10);
            sessao.setXpAtual(sessao.getXpAtual() + ganhoXp);
            log.append("\n[+").append(ganhoXp).append(" XP] ");
            
            if (sessao.getXpAtual() >= sessao.getXpMaximo()) {
                sessao.setXpAtual(sessao.getXpAtual() - sessao.getXpMaximo());
                sessao.setXpMaximo(sessao.getXpMaximo() + 50);
                sessao.setNivel(sessao.getNivel() + 1);
                sessao.setHpMax(sessao.getHpMax() + 20);
                sessao.setStaminaMax(sessao.getStaminaMax() + 10);
                sessao.setHpAtual(sessao.getHpMax());
                sessao.setStaminaAtual(sessao.getStaminaMax());
                log.append("\n🌟 LEVEL UP! Você alcançou o Nível ").append(sessao.getNivel()).append("! HP/ST Restaurados.");
            }
            
            if (sessao.getInimigosRestantes() > 0) {
                gerarProximoInimigo(sessao);
            } else {
                sessao.limparCombate();
            }
        }
    }

    @PostMapping("/viajar")
    public synchronized ResponseEntity<?> viajar(@RequestBody JornadaDTO jornada) {
        SessaoAventura sessao = SessaoAventura.getInstance();
        if (sessao.getInimigosRestantes() > 0) {
            return ResponseEntity.ok(new JornadaResponse("Derrote os inimigos antes de viajar!", jornadaFacade.getDestinos(sessao.getLocalAtual())));
        }
        String resultado = jornadaFacade.viajar(sessao.getLocalAtual(), jornada.getDestino());
        return ResponseEntity.ok(new JornadaResponse(resultado, jornadaFacade.getDestinos(sessao.getLocalAtual())));
    }

    // Other Endpoints (consumir, loja, curar-total, missao, etc) unchanged in logic but kept here to compile
    @PostMapping("/consumir/{item}")
    public synchronized ResponseEntity<?> consumir(@PathVariable String item) {
        SessaoAventura sessao = SessaoAventura.getInstance();
        switch(item) {
            case "cura":
                if(sessao.getPocoes() > 0) { sessao.setPocoes(sessao.getPocoes() - 1); sessao.setHpAtual(Math.min(sessao.getHpMax(), sessao.getHpAtual() + 50)); return ResponseEntity.ok(new AtaqueResponse("Poção de Cura usada!", "")); } break;
            case "stamina":
                if(sessao.getPocoesStamina() > 0) { sessao.setPocoesStamina(sessao.getPocoesStamina() - 1); sessao.setStaminaAtual(Math.min(sessao.getStaminaMax(), sessao.getStaminaAtual() + 50)); return ResponseEntity.ok(new AtaqueResponse("Poção de Stamina usada!", "")); } break;
            case "pao":
                if(sessao.getPaes() > 0) { sessao.setPaes(sessao.getPaes() - 1); sessao.setHpAtual(Math.min(sessao.getHpMax(), sessao.getHpAtual() + 20)); sessao.setStaminaAtual(Math.min(sessao.getStaminaMax(), sessao.getStaminaAtual() + 20)); return ResponseEntity.ok(new AtaqueResponse("Pão consumido!", "")); } break;
            case "lembas":
                if(sessao.getLembas() > 0) { sessao.setLembas(sessao.getLembas() - 1); sessao.setHpAtual(sessao.getHpMax()); sessao.setStaminaAtual(sessao.getStaminaMax()); return ResponseEntity.ok(new AtaqueResponse("Lembas consumido!", "")); } break;
        }
        return ResponseEntity.badRequest().body(new AtaqueResponse("Item não disponível.", ""));
    }

    @PostMapping("/ataque-ajudantes")
    public synchronized ResponseEntity<?> ataqueAjudantes() {
        SessaoAventura sessao = SessaoAventura.getInstance();
        if (sessao.getInimigosRestantes() <= 0 || sessao.getGrupo().isEmpty()) return ResponseEntity.badRequest().body(new AtaqueResponse("Indisponível", ""));
        boolean vidaBaixa = sessao.getHpAtual() < (sessao.getHpMax() * 0.1);
        if (sessao.getAtaquesHeroiContador() >= 2 || vidaBaixa) {
            if (!vidaBaixa) sessao.setAtaquesHeroiContador(sessao.getAtaquesHeroiContador() - 2);
            int dano = sessao.getGrupo().size() * (15 + sessao.getNivel()*5);
            if ("Mordor".equals(sessao.getLocalAtual())) dano = (int)(dano * 0.75);
            sessao.setInimigoAtualHp(sessao.getInimigoAtualHp() - dano);
            StringBuilder log = new StringBuilder("Ajudantes atacam (Dano: ").append(dano).append("). ");
            verificarMorteInimigo(sessao, log);
            return ResponseEntity.ok(new AtaqueResponse(log.toString(), sessao.getInimigoAtualNome()));
        }
        return ResponseEntity.badRequest().body(new AtaqueResponse("Cargas insuficientes", ""));
    }

    @PostMapping("/cidade/loja")
    public synchronized ResponseEntity<?> loja(@RequestParam String tipo) {
        SessaoAventura sessao = SessaoAventura.getInstance();
        if (sessao.getInimigosRestantes() > 0) return ResponseEntity.badRequest().body(new AtaqueResponse("Você está em combate!", ""));
        if ("Bree".equals(sessao.getLocalAtual())) {
            if (sessao.getDinheiro() >= 20) {
                sessao.setDinheiro(sessao.getDinheiro() - 20);
                if ("cura".equals(tipo)) sessao.setPocoes(sessao.getPocoes() + 1);
                if ("stamina".equals(tipo)) sessao.setPocoesStamina(sessao.getPocoesStamina() + 1);
                return ResponseEntity.ok(new AtaqueResponse("Comprado com sucesso!", ""));
            }
            return ResponseEntity.ok(new AtaqueResponse("Dinheiro insuficiente! (20 moedas)", ""));
        }
        return ResponseEntity.badRequest().body(new AtaqueResponse("Loja fechada", ""));
    }
    
    @PostMapping("/cidade/evento/lembas")
    public synchronized ResponseEntity<?> lembas() {
        SessaoAventura sessao = SessaoAventura.getInstance();
        if (sessao.getInimigosRestantes() > 0) return ResponseEntity.badRequest().body(new AtaqueResponse("Você está em combate!", ""));
        if ("Lothlórien".equals(sessao.getLocalAtual())) {
            if (sessao.getDinheiro() >= 50) {
                sessao.setDinheiro(sessao.getDinheiro() - 50);
                sessao.setLembas(sessao.getLembas() + 1);
                return ResponseEntity.ok(new AtaqueResponse("Você recebeu um pão de Lembas.", ""));
            }
            return ResponseEntity.ok(new AtaqueResponse("Faltam moedas (50 moedas)", ""));
        }
        return ResponseEntity.badRequest().body(new AtaqueResponse("Apenas em Lothlórien", ""));
    }
    
    @PostMapping("/cidade/evento/eowyn")
    public synchronized ResponseEntity<?> eowyn() {
        SessaoAventura sessao = SessaoAventura.getInstance();
        if (sessao.getInimigosRestantes() > 0) return ResponseEntity.badRequest().body(new AtaqueResponse("Você está em combate!", ""));
        if ("Rohan".equals(sessao.getLocalAtual()) && !sessao.isEnsopadoEowynComido()) {
            sessao.setHpAtual(10);
            sessao.setStaminaAtual(10);
            sessao.setEnsopadoEowynComido(true);
            return ResponseEntity.ok(new AtaqueResponse("Terrível! Sua vida e stamina caíram para 10.", ""));
        }
        return ResponseEntity.badRequest().body(new AtaqueResponse("Você já comeu o ensopado.", ""));
    }
    
    @PostMapping("/cozinhar-sam")
    public synchronized ResponseEntity<?> cozinhar() {
        SessaoAventura sessao = SessaoAventura.getInstance();
        if (sessao.getInimigosRestantes() > 0) return ResponseEntity.badRequest().body(new AtaqueResponse("Você está em combate!", ""));
        if (sessao.getGrupo().contains("Sam")) {
            if (sessao.getCarne() > 0 && sessao.getBatata() > 0 && sessao.getEspeciarias() > 0) {
                sessao.setCarne(sessao.getCarne() - 1); sessao.setBatata(sessao.getBatata() - 1); sessao.setEspeciarias(sessao.getEspeciarias() - 1);
                sessao.setHpAtual(Math.min(sessao.getHpMax(), sessao.getHpAtual() + sessao.getHpMax() / 2));
                sessao.setStaminaAtual(Math.min(sessao.getStaminaMax(), sessao.getStaminaAtual() + sessao.getStaminaMax() / 2));
                return ResponseEntity.ok(new AtaqueResponse("Ensopado do Sam feito! (+50% HP/ST)", ""));
            }
            return ResponseEntity.badRequest().body(new AtaqueResponse("Faltam ingredientes!", ""));
        }
        return ResponseEntity.badRequest().body(new AtaqueResponse("Sam não está no grupo.", ""));
    }
    
    @PostMapping("/cidade/curar-total")
    public synchronized ResponseEntity<?> curarT() {
        SessaoAventura sessao = SessaoAventura.getInstance();
        if (sessao.getInimigosRestantes() > 0) return ResponseEntity.badRequest().body(new AtaqueResponse("Você está em combate!", ""));
        if ("Valfenda".equals(sessao.getLocalAtual()) || "Lothlórien".equals(sessao.getLocalAtual())) {
            sessao.setHpAtual(sessao.getHpMax());
            sessao.setStaminaAtual(sessao.getStaminaMax());
            return ResponseEntity.ok(new AtaqueResponse("Totalmente restaurado!", ""));
        }
        return ResponseEntity.badRequest().body(new AtaqueResponse("Apenas em Valfenda ou Lothlórien", ""));
    }
    
    @PostMapping("/cidade/missao")
    public synchronized ResponseEntity<?> missao() {
        SessaoAventura sessao = SessaoAventura.getInstance();
        if (sessao.getInimigosRestantes() > 0) return ResponseEntity.badRequest().body(new AtaqueResponse("Você está em combate!", ""));
        if ("Valfenda".equals(sessao.getLocalAtual()) || "Lothlórien".equals(sessao.getLocalAtual())) {
            if (sessao.getMissaoAtiva() == null) {
                sessao.setMissaoAtiva("Completar 5 batalhas na região");
                sessao.setMissaoObjetivo(5);
                sessao.setMissaoProgresso(0);
                return ResponseEntity.ok(new AtaqueResponse("Missão Aceita! Complete 5 batalhas na região.", ""));
            } else if (sessao.getMissaoProgresso() >= sessao.getMissaoObjetivo()) {
                sessao.setMissaoAtiva(null);
                sessao.setNivel(sessao.getNivel() + 1);
                sessao.setHpMax(sessao.getHpMax() + 20);
                sessao.setStaminaMax(sessao.getStaminaMax() + 10);
                sessao.setHpAtual(sessao.getHpMax());
                sessao.setStaminaAtual(sessao.getStaminaMax());
                return ResponseEntity.ok(new AtaqueResponse("Missão Cumprida! Você subiu para o Nível " + sessao.getNivel() + "!", ""));
            } else {
                return ResponseEntity.ok(new AtaqueResponse("Progresso da Missão: " + sessao.getMissaoProgresso() + "/" + sessao.getMissaoObjetivo(), ""));
            }
        }
        return ResponseEntity.badRequest().body(new AtaqueResponse("Não há missões aqui.", ""));
    }
    @PostMapping("/reiniciar")
    public synchronized ResponseEntity<?> reiniciar() {
        SessaoAventura.getInstance().reset();
        return ResponseEntity.ok(new AtaqueResponse("A jornada recomeça.", ""));
    }
}
