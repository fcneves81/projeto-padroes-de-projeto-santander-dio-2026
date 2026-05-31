import { Injectable, isDevMode } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { StatusAventura } from '../models/status.model';

@Injectable({
  providedIn: 'root'
})
export class AventuraService {
  private apiUrl = isDevMode() ? 'http://localhost:8080/api/aventura' : 'https://www.qudtecnologia.com.br/api/aventura';

  constructor(private http: HttpClient) {}

  getStatus(): Observable<StatusAventura> {
    return this.http.get<StatusAventura>(`${this.apiUrl}/status`, { withCredentials: true });
  }

  selecionarGrupo(grupo: string[]): Observable<any> {
    return this.http.post(`${this.apiUrl}/grupo/selecionar`, grupo, { responseType: 'text', withCredentials: true });
  }

  explorar(): Observable<any> {
    return this.http.post(`${this.apiUrl}/explorar`, {}, { withCredentials: true });
  }

  tick(): Observable<any> {
    return this.http.get(`${this.apiUrl}/tick`, { withCredentials: true });
  }

  atacar(index: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/ataque/${index}`, { withCredentials: true });
  }

  ataqueAjudantes(): Observable<any> {
    return this.http.post(`${this.apiUrl}/ataque-ajudantes`, {}, { withCredentials: true });
  }
  
  reiniciar(): Observable<any> {
    return this.http.post(`${this.apiUrl}/reiniciar`, {}, { withCredentials: true });
  }

  consumirItem(item: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/consumir/${item}`, {}, { withCredentials: true });
  }

  lojaBree(tipo: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/cidade/loja?tipo=${tipo}`, {}, { withCredentials: true });
  }

  curarTotal(): Observable<any> {
    return this.http.post(`${this.apiUrl}/cidade/curar-total`, {}, { withCredentials: true });
  }

  pegarLembas(): Observable<any> {
    return this.http.post(`${this.apiUrl}/cidade/evento/lembas`, {}, { withCredentials: true });
  }

  comerEowyn(): Observable<any> {
    return this.http.post(`${this.apiUrl}/cidade/evento/eowyn`, {}, { withCredentials: true });
  }

  cozinharSam(): Observable<any> {
    return this.http.post(`${this.apiUrl}/cozinhar-sam`, {}, { withCredentials: true });
  }

  interagirMissao(): Observable<any> {
    return this.http.post(`${this.apiUrl}/cidade/missao`, {}, { withCredentials: true });
  }

  viajar(origem: string, destino: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/viajar`, { origem, destino }, { withCredentials: true });
  }
}
