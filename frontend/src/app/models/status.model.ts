export interface Ataque {
  nome: string;
  custoStamina: number;
  especial: boolean;
}

export interface StatusAventura {
  heroi: string;
  titulo: string;
  grupo: string[];
  local: string;
  estadoJogo: string;
  hpAtual: number;
  hpMax: number;
  staminaAtual: number;
  staminaMax: number;
  xpAtual: number;
  xpMaximo: number;
  nivel: number;
  dinheiro: number;
  ataquesHeroiContador: number;
  pocoes: number;
  pocoesStamina: number;
  paes: number;
  lembas: number;
  carne: number;
  batata: number;
  especiarias: number;
  ensopadoEowynComido: boolean;
  eventoEspecialConcluido: boolean;
  inimigosMortosLocal: number;
  inimigosRestantes: number;
  inimigoAtualNome: string;
  inimigoAtualHp: number;
  missaoAtiva: string | null;
  missaoProgresso: number;
  missaoObjetivo: number;
  destinosDisponiveis: string[];
  ataquesDisponiveis: Ataque[];
  textoEventoEspecial: string;
}
