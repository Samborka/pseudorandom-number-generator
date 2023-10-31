package primality_tester;

import java.math.BigInteger;
import java.util.Random;

public class MillerRabin {

    private BigInteger n; // Número a ser verificado
    private final int k; // Precisão do teste de Miller-Rabin

    public MillerRabin(int k) {
        this.k = k;
    }

    public String isPrime(BigInteger number) {
        this.n = number;
        String isPrime = n + " Provavelmente é um número primo";
        String isNotPrime =  n + " Provavelmente é um número composto";

        // Tratando casos básicos para os números 2, 3 ou pares
        if(n.equals(BigInteger.TWO) || n.equals(BigInteger.valueOf(3)))
            return isPrime;
        else if (n.mod(BigInteger.TWO).equals(BigInteger.ZERO))
            return isNotPrime;

        int r = 0;
        BigInteger s = this.n.subtract(BigInteger.ONE);

        // Encontrando valores para r e s, de forma que n - 1 = (2^r) * s, onde s é ímpar
        while (s.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            s = s.divide(BigInteger.TWO);
            r++;
        }

        for (int i = 0; i < this.k; i++) {
            Random rand = new Random();

            // Escolhendo um número aleatório 'a' entre 1 e n-1
            BigInteger a = new BigInteger(this.n.bitLength(), rand).mod(this.n.subtract(BigInteger.ONE)).add(BigInteger.ONE);

            // Calculando x = a^s mod n
            BigInteger x = a.modPow(s, this.n)
                    ;
            //Verifica se x é igual a 1 ou n - 1. Se sim, para satisfazer o miller-rabin, interrompe a iteração atual e inicia a próxima
            if (x.equals(BigInteger.ONE) || x.equals(this.n.subtract(BigInteger.ONE))) {
                continue;
            }

            for (int j = 0; j < r - 1; j++) {
                // Atualizando x para x^2 mod n
                x = x.modPow(BigInteger.TWO, this.n);

                if (x.equals(this.n.subtract(BigInteger.ONE))) {
                    break;
                } else if (j == r - 2) {
                    return isNotPrime;
                }
            }
        }
        return isPrime;
    }
}
