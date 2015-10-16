/**
 * Cristiano Vicente RA 443913
 * Claudio Roberto Costa RA 527033
 * Rafael Anselmo RA 525650
 * Melisa Cordeiro RA 532533
 */

package Puzzle;


import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

public class Busca {
    List<No> pilha_fila = new LinkedList<>();
    int limite = 30;

    // testa a solução
    public boolean testeObjetivo(int[] base) {
        int objetivo[] = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 0};
        return Arrays.equals(base, objetivo);
    }

    // localiza a posição do 0
    public int pos_0(No aux) {
        for (int i = 0; i < 9; i++) {
            if (aux.estado[i] == 0)
                return i;
        }
        return 9;
    }

    // grupo de ações possivéis para cada estado v2
    public void sucessor(No aux) {
        int index = pos_0(aux);
        int posX = index % 3;
        int mov = aux.pos_ant - index;
        if (index < 3)
            pilha_fila.add(movUP(aux, index));
        else if (index < 6) {
            if (mov != 3)
                pilha_fila.add(movUP(aux, index));
            if (mov != -3)
                pilha_fila.add(movDown(aux, index));
        } else
            pilha_fila.add(movDown(aux, index));

        if (posX == 0)
            pilha_fila.add(movLeft(aux, index));
        else if (posX == 1) {
            if (mov != 1)
                pilha_fila.add(movLeft(aux, index));
            if (mov != -1)
                pilha_fila.add(movRight(aux, index));
        } else
            pilha_fila.add(movRight(aux, index));
    }

    // conjunto de metodos de movimentos
    public No movUP(No pai, int i) {
        No novo = new No(pai.estado.clone(), "up", pai, pai.custocaminho + 1, pai.profundidade + 1, i);
        novo.estado[i] = pai.estado[i + 3];
        novo.estado[i + 3] = 0;
        return novo;
    }

    public No movDown(No pai, int i) {
        No novo = new No(pai.estado.clone(), "down", pai, pai.custocaminho + 1, pai.profundidade + 1, i);
        novo.estado[i] = pai.estado[i - 3];
        novo.estado[i - 3] = 0;
        return novo;
    }

    public No movLeft(No pai, int i) {
        No novo = new No(pai.estado.clone(), "left", pai, pai.custocaminho + 1, pai.profundidade + 1, i);
        novo.estado[i] = pai.estado[i + 1];
        novo.estado[i + 1] = 0;
        return novo;
    }

    public No movRight(No pai, int i) {
        No novo = new No(pai.estado.clone(), "right", pai, pai.custocaminho + 1, pai.profundidade + 1, i);
        novo.estado[i] = pai.estado[i - 1];
        novo.estado[i - 1] = 0;
        return novo;
    }

    // busca em profundidade com limite
    public No buscaProfLimit(No raiz) {
        pilha_fila.add(raiz);
        while (pilha_fila.size() != 0) {
            No aux = pilha_fila.remove(pilha_fila.size() - 1);
            if (testeObjetivo(aux.estado))
                return aux;
            else if (aux.profundidade < limite)
                sucessor(aux);
        }
        return raiz;
    }

    // busca em profundidade
    public No buscaProf(No raiz) {
        pilha_fila.add(raiz);
        while (pilha_fila.size() != 0) {
            No aux = pilha_fila.remove(pilha_fila.size() - 1);
            if (testeObjetivo(aux.estado))
                return aux;
            else
                sucessor(aux);
        }
        return raiz;
    }

    // busca em largura
    public No buscaLargura(No raiz) {
        pilha_fila.add(raiz);
        while (pilha_fila.size() != 0) {
            No aux = pilha_fila.remove(0);
            if (testeObjetivo(aux.estado))
                return aux;
            else
                sucessor(aux);
        }
        return raiz;
    }
}