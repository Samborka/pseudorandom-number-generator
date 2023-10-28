package random_number_generator;

import java.math.BigInteger;
import java.util.ArrayList;

public class BlumBlumShub {
    final BigInteger p;
    final BigInteger q;
    final BigInteger m;
    final long seed;

    public BlumBlumShub(BigInteger p, BigInteger q, long seed) {
        this.p = p;
        this.q = q;
        this.m = p.multiply(q);
        this.seed = seed;
    }

    public BigInteger generateRandom(int numBits) {
        BigInteger bigIntegerSeed = BigInteger.valueOf(seed);
        ArrayList<Integer> arrayBits = new ArrayList<>();

        for(int i = 0; i < numBits; i++){
            bigIntegerSeed = bigIntegerSeed.modPow(BigInteger.valueOf(2), m); //Usa o método BlumBlumShub para gerar o numero (x^2) mod m
            int bit = bigIntegerSeed.mod(BigInteger.valueOf(2)).intValue(); //Pega o bit do número gerado na iteração

            arrayBits.add(bit); //Adiciona o bit no ArrayList
        }

        //Converte o ArrayList de bits em uma string
        StringBuilder bitString = new StringBuilder();
        for (Integer bit : arrayBits) {
            bitString.append(bit);
        }

        //Converte a string de bits em um BigInteger
        return new BigInteger(bitString.toString(), 2);
    }
}