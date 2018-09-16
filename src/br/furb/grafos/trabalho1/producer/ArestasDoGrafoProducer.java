package br.furb.grafos.trabalho1.producer;

import br.furb.grafos.trabalho1.provider.ArestasDoGrafoProvider;

/**
 *
 * @author ariel, sidnei
 *
 */
public class ArestasDoGrafoProducer implements ArestasDoGrafoProvider {

    @Override
    public String arestasDoGrafo(int[][] matrizAdjacencia) {
        boolean dirigido = true;
        int linha = 0;
        int coluna = coluna = 0;
        int qntArestas = 0;
        if (dirigido) {
            for (linha = 0;; linha++) {
                for (coluna = 0;; coluna++) {
                    if (matrizAdjacencia[coluna][linha] != 0) {
                        qntArestas += matrizAdjacencia[coluna][linha];
                    }
                }
            }

        } else {
            for (;; linha++) {
                for (coluna = 0;; coluna++) {
                    if(linha>coluna){
                        coluna = linha;
                    }
                    if (matrizAdjacencia[coluna][linha] != 0) {
                       qntArestas += matrizAdjacencia[coluna][linha]; 
                    }                
                }
            }
        }
        
      
    }

}
