package primality_tester;

import java.math.BigInteger;
import java.util.Random;

public class Fermat {
    private final int k;
    private BigInteger n;

    public Fermat(int k){
        this.k = k; //Número de iterações
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

        for (int i = 0; i < this.k; i++) {
            Random rand = new Random();
            BigInteger a = new BigInteger(n.bitLength(), rand);

            a = a.mod(n.subtract(BigInteger.ONE)).add(BigInteger.ONE); // escolhe um número primo aleatório entre 1 e n-1

            //Se a^n-1 mod n != 1, é um número composto
            if (!a.modPow(n.subtract(BigInteger.ONE), n).equals(BigInteger.ONE)) {
                return isNotPrime;
            }
        }
        return isPrime; //Caso um número composto não seja encontrado, temos um provavel primo.
    }
}
