import random_number_generator.BlumBlumShub;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Main {
    public static void main(String[] args) {
        // P e Q devem ser números primos grandes. Por simplicidade, estou usando números primos fixos.
        BigInteger p = BigInteger.valueOf(3263849);
        BigInteger q = BigInteger.valueOf(1302498943);

        //A semente usada para gerar o número pseudoaleatório é o horário atual do sistema em milissegundos.
        long seed = System.currentTimeMillis();

        int numBits = 8; //Número de bits do número pseudoaleatório que será gerado
        BlumBlumShub bbs = new BlumBlumShub(p, q, seed); //Instância do objeto BlumBlumShub
        BigInteger randomNumber = bbs.generateRandom(numBits); //Método que gera o número pseudoaleatório
        System.out.println("Numero aleatório: " + randomNumber); //Print do número gerado

    }
    }