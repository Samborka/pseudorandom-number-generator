import primality_tester.Fermat;
import primality_tester.MillerRabin;
import random_number_generator.BlumBlumShub;
import random_number_generator.ParkMiller;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        //A semente usada para gerar o número pseudoaleatório é o horário atual do sistema em milissegundos.
        long seed = System.currentTimeMillis();
        int numBits =  40; //Número de bits do número pseudoaleatório que será gerado

        // P e Q devem ser números primos grandes. Por simplicidade, estou usando números primos fixos.
        BigInteger p = BigInteger.valueOf(3263849);
        BigInteger q = BigInteger.valueOf(1302498943);

        BlumBlumShub bbs = new BlumBlumShub(p, q, seed); //Instância do objeto BlumBlumShub
        BigInteger randomNumberBBS = bbs.generateRandom(numBits); //Método que gera o número pseudoaleatório usando BlumBlumShub

        System.out.println("Numero aleatório BlumBlumShub: " + randomNumberBBS); //Print do número gerado

        ParkMiller parkMiller = new ParkMiller(seed);//Instância do objeto ParkMiller
        BigInteger randomNumberPM = parkMiller.generateRandom(numBits); //Método que gera um numero pseudoaleatório usando ParkMiller

        System.out.println("Numero aleatório ParkMiller: " + randomNumberPM); //Print do número gerado

        int k = 100; // Precisão do teste de Miller-Rabin (Quanto maior, mais preciso)
        MillerRabin millerRabin = new MillerRabin(k);
        System.out.println("Miller-Rabin:");
        System.out.println(millerRabin.isPrime(randomNumberBBS));

        Fermat fermat = new Fermat(k);
        System.out.println("Fermat:");
        System.out.println(fermat.isPrime(randomNumberBBS));
    }
}