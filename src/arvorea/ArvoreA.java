package arvorea;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Tatiane Paz
 */

public class ArvoreA {
    //Testes
    //String teste1[] = lista.split("2 1 5 9 3 6 10 13 4 7 11 14 0 8 12 15");
    //String teste2[] = lista.split("6 5 13 0 1 7 9 14 2 8 10 15 3 4 11 12");
    //String teste3[] = lista.split("2 1 10 9 3 5 11 13 4 0 6 12 7 8 15 14");
    //String teste4[] = lista.split("2 1 5 0 7 9 10 13 6 4 3 15 8 11 12 14");
    //String teste5[] = lista.split("1 5 7 0 4 6 12 10 8 2 15 9 3 14 11 13");
    //String teste6[] = lista.split("9 13 12 8 0 5 7 14 1 11 15 4 6 10 2 3");
    //String listaInteiros[] = lista.split(" ");
    
    //Definido o tamanho da matriz
    static int colunas = 4;
    static int linhas = 4;
    
    public static void main(String[] args) {

        //no referente a cada elemento da matriz
        
        //Cria uma chave Ãºnica para cada elemento da 'lista'
        HashMap <String,No> A = new HashMap<> ();   //Aberto
        HashMap <String,No> F = new HashMap<> ();   //Fechado 
        HashMap <String,No> S = new HashMap<> ();   //Estado Inicial 
        
        
        //lÃª dado do teclado
        Scanner lerValor = new Scanner(System.in);      
        String lista;
        lista = lerValor.nextLine();
        
        long tempoInicial = System.currentTimeMillis();
        Runtime r = Runtime.getRuntime();
        
        No primeiro = new No(lista);
        S.put(primeiro.getChave(), primeiro);
        A.putAll(S);
        No v = calculaMenorF(A);
        while (v != null && !v.RespFinal()) {
            A.remove(v.getChave());
            F.put(v.getChave(), v);
            for (No m : v.calculaFilhos()) {
                No mLinha = A.get(m.getChave());
                if (mLinha != null && mLinha.getG() > m.getG()) {
                    A.remove(mLinha);
                }
                No contidoA = A.get(m.getChave());
                No contidoF = F.get(m.getChave());
                if (contidoA == null && contidoF == null) {
                    A.put(m.getChave(), m);
                    m.setPai(v);
                    m.calculaH();
                    m.calcularF();
                }
            }
//          Encontra o estado v com o menor f(v) em A que nÃ£o esta contido em F
            v = calculaMenorF(A);
        }


//        Caso sucesso imprima o g(v)
        No estadoFinal = calculaMenorF(A);
        if (estadoFinal != null && estadoFinal.RespFinal()) {
            //Imprime ao usuÃ¡rio a quantidade de movimentos
            System.out.println("Quantidade de movimentos");
            System.out.println(v.getG());
        }
         
        //Imprime valores para o relatÃ³rio
        System.out.println("Tempo de processamento: ");
        System.out.println(System.currentTimeMillis() - tempoInicial);
        System.out.println("Consumo de memória: ");
        long a = (r.maxMemory() - r.freeMemory()) /1024l; 
        System.out.println(a+" KB\n");
    }
    
    

    private static No calculaMenorF(HashMap<String, No> A) {
        No aux = null;
        for(No x: A.values()){
            if((aux == null) || (aux.getF() > x.getF()))
                aux = x;
        }
        return aux;
    }
    
    
}
