package arvorea;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

/**
 *
 * @author tatiane Paz
 */

public class No {
    
    //Matriz desejada
    int [][] matrizFinal = {{1,5,9,13},{2,6,10,14},{3,7,11,15},{4,8,12,0}};
    
    int [][] caminhoMatriz = { {0,0}, {1,0}, {2,0}, {3,0},
                              {3,1}, {3,2}, {3,3}, {2,3},
                              {1,3}, {0,3}, {0,2}, {0,1},
                              {1,1}, {2,1}, {2,2}, {1,2}
                            };
    
    int[] posicao = {1,5,9,13,2,6,10,14,3,7,11,15,4,8,12,0};
    
    //custo no menor caminho entre o estado inicial e o estado n qualquer. 
    public int g = 0;
    //custo do menor caminho entre o estado n e o estado final.
    public float h = 0;
    //public float h2 = 0;
    //public float h3 = 0;
    //public float h4 = 0;
    //public float h5 = 0;
    
    //o custo do caminho do estado inicial atÃ© o estado final passando pelo estado n
    //f(n) = g(n) + h(n)
    public float f = 0;
    //Tamanho da Matriz
    public int linha = 4;
    public int coluna = 4;
    
    private String [] estado;   
    private String chave;
    private No pai;

    //heurÃ­stica 1
    public float heuristica1 (String [] estado){
        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {
                int aux = Integer.valueOf(estado[(i*4)+j]);
                if( matrizFinal[i][j] != aux){
                    this.h++;
                };   
            }
        }
        return this.h;
    }

    public void heuristica2 (int [][] matrizFinal){
        
    }

    public void calculaH() {
        this.h = heuristica1 (this.estado);
        //this.h = heuristica2 (this.estado);
        //this.h = heuristica3 (this.estado);
        //this.h = heuristica4 (this.estado);
        //this.h = heuristica5 (this.estado);
    }
    
    public void imprimeMatriz (int[][] matrizImprime){
    //Imprime a matriz para verificaÃ§Ã£o dos testes
        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {
                System.out.print(matrizImprime[i][j] + "\t");
            }
            System.out.println("");
        }
    }    
 public No(String valor) {
        this.chave = valor.trim().replaceAll("(\\s)+", "#");
        this.estado = valor.split("(\\s)+");
        
        this.h = heuristica1 (this.estado);
        //this.h = heuristica2 (this.estado);
        //this.h = heuristica3 (this.estado);
        //this.h = heuristica4 (this.estado);
        //this.h = heuristica5 (this.estado);
        
        this.f = this.g + this.h;
        this.pai = null;
    }

    public No(String n, No pai) {
        this.chave = n.trim().replaceAll("(\\s)+", "#");
        this.g = pai.getAdcG1();
        this.estado = n.split("(\\s)+");
        
    }

    public List<No> calculaFilhos() {
        int zero = getZeroIndex();
        int i = zero / 4;
        int j = zero % 4;
        List<No> aux = new ArrayList<>();
        
        //filho da direita
        if (j < 3) {
            addFilho(i, j, i, j + 1,aux);
        }
        //filho da esquerda
        if (j > 0) {
            addFilho(i, j, i, j - 1,aux);
        }
        
        //filho de cima
        if (i > 0) {
            addFilho(i, j, i - 1, j,aux);
        }
        //filho de baixo
        if (i < 3) {
            addFilho(i, j, i + 1, j,aux);
        }
        return aux;
    }

    private void addFilho(int i, int j, int i2, int j2, List<No> add) {
        String[] Aux = estado.clone();
        String aux = estado[(4 * i2) + j2];
        Aux[(4 * i2) + j2] = "0";
        Aux[(4 * i) + j] = aux;
        add.add(new No(String.join(" ", Aux), this));
    }

    public Boolean RespFinal() {
        return this.getChave().equals("1#5#9#13#2#6#10#14#3#7#11#15#4#8#12#0");
    }

    public void calcularF() {
        this.f = this.h + this.g;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        No no = (No) o;

        if(chave != null){
            return chave.equals(no.chave);
        }else{
           return !(no.chave != null);
        }
    }

    @Override
    public String toString() {
        String toPrint = "";

        for (int i = 0; i < estado.length; i++) {
            toPrint += estado[i];
            if (i % 4 == 3 ) {
                toPrint += "\n";
            } else {
                toPrint += "\t";
            }

        }
            return toPrint;
    }

    public boolean isContido(List<No> fechados) {
        for(int i = 0; i < fechados.size(); i++){
            No no = fechados.get(i);
            if(no.getChave().equals(this.chave)) 
                return true;
        }
        return false;
    }
        private int getZeroIndex() {
        for (int i = 0; i < estado.length; i++) {
            if (estado[i].equals("0")) return i;
        }
        return -1;
    }

     
    public int getAdcG1() {
        return g + 1;
    }
        
    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String[] getEstado() {
        return estado;
    }

    public void setEstado(String[] estado) {
        this.estado = estado;
    }

    public float getF() {
        return f;
    }    
    
    public int getG() {
        return g;
    }
    
    public float geth() {
        return h;
    }
    
    public No getPai() {
        return pai;
    }

    public void setPai(No pai) {
        this.pai = pai;
    }

}
