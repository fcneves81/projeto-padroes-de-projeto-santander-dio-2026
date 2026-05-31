import { Component, ChangeDetectorRef, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AventuraService } from './services/aventura.service';
import { StatusAventura } from './models/status.model';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  status: StatusAventura | null = null;
  resultado: string = '';
  destinos: string[] = ['Bree', 'Rivendell'];
  
  opcoesHerois = ['Aragorn', 'Legolas', 'Gimli', 'Gandalf', 'Sam', 'Merry', 'Pippin', 'Boromir'];
  heroiPrincipal: string = '';
  ajudantesSelecionados: { [key: string]: boolean } = {};

  constructor(private aventuraService: AventuraService, private cdr: ChangeDetectorRef) {}

  ngOnInit() {
    this.atualizarStatus();
    setInterval(() => {
      if (this.status && this.status.heroi) {
        this.aventuraService.tick().subscribe(res => {
          if (res.resultado && res.resultado.trim() !== '') {
            this.resultado = res.resultado + "\n" + this.resultado;
          }
          this.atualizarStatusSemMensagem();
        });
      }
    }, 2000);
  }

  atualizarStatus(mensagem?: string) {
    this.aventuraService.getStatus().subscribe(res => {
      this.status = res;
      if (mensagem) {
        this.resultado = mensagem;
      }
      this.cdr.markForCheck();
    });
  }

  atualizarStatusSemMensagem() {
    this.aventuraService.getStatus().subscribe(res => {
      this.status = res;
      this.cdr.markForCheck();
    });
  }

  get numeroAjudantesSelecionados() {
    return Object.values(this.ajudantesSelecionados).filter(val => val).length;
  }

  get heroisDisponiveis() {
    if (this.status?.estadoJogo === 'ESCOLHA_HEROI' && this.status?.heroi) {
      return this.opcoesHerois.filter(h => h !== this.status!.heroi);
    }
    return this.opcoesHerois;
  }

  podeSelecionarAjudante(heroi: string) {
    return this.ajudantesSelecionados[heroi] || this.numeroAjudantesSelecionados < 2;
  }

  iniciarJornada() {
    if (!this.heroiPrincipal) {
      alert("Escolha um herói principal!");
      return;
    }
    const grupo = [this.heroiPrincipal];
    for (const [nome, selecionado] of Object.entries(this.ajudantesSelecionados)) {
      if (selecionado && nome !== this.heroiPrincipal) {
        grupo.push(nome);
      }
    }
    
    this.aventuraService.selecionarGrupo(grupo).subscribe({
      next: (res) => this.atualizarStatus(res.resultado),
      error: (err) => this.atualizarStatus(err.error?.resultado || "Erro ao iniciar")
    });
  }

  explorar() {
    this.aventuraService.explorar().subscribe({
      next: (res) => this.atualizarStatus(res.resultado),
      error: (err) => this.atualizarStatus(err.error?.resultado)
    });
  }

  atacar(index: number) {
    this.aventuraService.atacar(index).subscribe({
      next: (res) => this.atualizarStatus(res.resultado),
      error: (err) => this.atualizarStatus(err.error?.resultado)
    });
  }

  ataqueAjudantes() {
    this.aventuraService.ataqueAjudantes().subscribe({
      next: (res) => this.atualizarStatus(res.resultado),
      error: (err) => this.atualizarStatus(err.error?.resultado)
    });
  }
  
  reiniciar() {
    this.aventuraService.reiniciar().subscribe({
      next: (res) => {
        this.status = null;
        this.resultado = res.resultado;
        this.heroiPrincipal = '';
        this.ajudantesSelecionados = {};
        this.atualizarStatus();
      },
      error: (err) => {
        console.error("Erro no reiniciar:", err);
        alert("Erro ao reiniciar: " + err.message);
      }
    });
  }

  consumirItem(item: string) {
    this.aventuraService.consumirItem(item).subscribe({
      next: (res) => this.atualizarStatus(res.resultado),
      error: (err) => this.atualizarStatus(err.error?.resultado)
    });
  }

  lojaBree(tipo: string) {
    this.aventuraService.lojaBree(tipo).subscribe({
      next: (res) => this.atualizarStatus(res.resultado),
      error: (err) => this.atualizarStatus(err.error?.resultado)
    });
  }

  curarTotal() {
    this.aventuraService.curarTotal().subscribe({
      next: (res) => this.atualizarStatus(res.resultado),
      error: (err) => this.atualizarStatus(err.error?.resultado)
    });
  }
  
  pegarLembas() {
    this.aventuraService.pegarLembas().subscribe({
      next: (res) => this.atualizarStatus(res.resultado),
      error: (err) => this.atualizarStatus(err.error?.resultado)
    });
  }
  
  comerEowyn() {
    this.aventuraService.comerEowyn().subscribe({
      next: (res) => this.atualizarStatus(res.resultado),
      error: (err) => this.atualizarStatus(err.error?.resultado)
    });
  }
  
  cozinharSam() {
    this.aventuraService.cozinharSam().subscribe({
      next: (res) => this.atualizarStatus(res.resultado),
      error: (err) => this.atualizarStatus(err.error?.resultado)
    });
  }

  interagirMissao() {
    this.aventuraService.interagirMissao().subscribe({
      next: (res) => this.atualizarStatus(res.resultado),
      error: (err) => this.atualizarStatus(err.error?.resultado)
    });
  }

  viajar(destino: string) {
    if (!this.status) return;
    this.aventuraService.viajar(this.status.local, destino).subscribe({
      next: (res) => {
        this.atualizarStatus(res.mensagem);
      },
      error: (err) => {
        this.atualizarStatus("Erro: " + err.error?.mensagem);
      }
    });
  }
}
