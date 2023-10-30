package random_number_generator;

import java.math.BigInteger;
import java.util.ArrayList;

public class ParkMiller {
    private final long seed; //Seed recebida na classe main
    //Valores de 'a' e 'm' retirados de https://www.geeksforgeeks.org/java-program-to-implement-park-miller-random-number-generation-algorithm/
    private final BigInteger a = BigInteger.valueOf(48271); //Numero de alta ordem multiplicativa mod m
    private final BigInteger m = BigInteger.valueOf(2147483647); // Numero primo grande

    public ParkMiller(long seed) {
        this.seed = seed;
    }

    //Método que encontra um seed segura para o método ParkMiller. É necessário que a seed seja um coprimo de m.
    private BigInteger findSeed(long seed){
        BigInteger seedBigInteger = BigInteger.valueOf(seed); //Transforma a seed em BigInteger

        //Verifica se a seed e m são coprimos  (MDC entre a seed e m deve ser igual a 1)
        //Enquanto não forem coprimos, o loop se mantém
        while(!seedBigInteger.gcd(m).equals(BigInteger.ONE)){
            seedBigInteger = a.multiply(seedBigInteger).mod(m); //Atualiza o valor de seedBigInteger utilizando o método de ParkMiller (a*seed mod m)

        }
        return seedBigInteger;
    }

    public BigInteger generateRandom(int numBits) {
        ArrayList<Integer> arrayBits = new ArrayList<>();
        BigInteger secureSeed = findSeed(seed);

        for (int i = 0; i < numBits; i++) {
            secureSeed = a.multiply(secureSeed).mod(m); // Método Park-Miller para gerar o próximo número
            int bit = secureSeed.mod(BigInteger.valueOf(2)).intValue(); //Pega o bit do número gerado na iteração
            arrayBits.add(bit); // Adiciona o bit no ArrayList
        }

        //Converte o ArrayList de bits em uma string
        StringBuilder bitString = new StringBuilder();
        for (Integer bit : arrayBits) {
            bitString.append(bit);
        }
        //Converte a string de bits em um BigInteger e retorna o numero pseudo aleatório gerado
        return new BigInteger(bitString.toString(), 2);
    }
}
