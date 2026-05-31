import { Injectable, isDevMode } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { StatusAventura } from '../models/status.model';

@Injectable({
  providedIn: 'root'
})
export class AventuraService {
  private apiUrl = isDevMode() ? 'http://localhost:8080/api/aventura' : 'https://www.qudtecnologia.com.br/api-terra-media';

  constructor(private http: HttpClient) {}

  getStatus(): Observable<StatusAventura> {
    return this.http.get<StatusAventura>(`${this.apiUrl}/status`);
  }

  selecionarGrupo(grupo: string[]): Observable<any> {
    return this.http.post(`${this.apiUrl}/grupo/selecionar`, grupo, { responseType: 'text' });
  }

  explorar(): Observable<any> {
    return this.http.post(`${this.apiUrl}/explorar`, {});
  }

  tick(): Observable<any> {
    return this.http.get(`${this.apiUrl}/tick`);
  }

  atacar(index: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/ataque/${index}`);
  }

  ataqueAjudantes(): Observable<any> {
    return this.http.post(`${this.apiUrl}/ataque-ajudantes`, {});
  }
  
  reiniciar(): Observable<any> {
    return this.http.post(`${this.apiUrl}/reiniciar`, {});
  }

  consumirItem(item: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/consumir/${item}`, {});
  }

  lojaBree(tipo: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/cidade/loja?tipo=${tipo}`, {});
  }

  curarTotal(): Observable<any> {
    return this.http.post(`${this.apiUrl}/cidade/curar-total`, {});
  }

  pegarLembas(): Observable<any> {
    return this.http.post(`${this.apiUrl}/cidade/evento/lembas`, {});
  }

  comerEowyn(): Observable<any> {
    return this.http.post(`${this.apiUrl}/cidade/evento/eowyn`, {});
  }

  cozinharSam(): Observable<any> {
    return this.http.post(`${this.apiUrl}/cozinhar-sam`, {});
  }

  interagirMissao(): Observable<any> {
    return this.http.post(`${this.apiUrl}/cidade/missao`, {});
  }

  viajar(origem: string, destino: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/viajar`, { origem, destino });
  }
}
