package aplicacao;

import java.util.HashMap;
import java.util.Map;

public class Pi {

    private Map<Integer, Boolean> buffer;
    private int limiteInferior;
    private int limiteSuperior;
    private String decimaisPi;
    private String sequenciaPrimos;

    public static void main(String args[]) {

        String decimaisPi = "14159265358979323846";
        int primoPrimeiro = 2;
        int primoUltimo = 9973;

        Pi pi = new Pi(decimaisPi, primoPrimeiro, primoUltimo);
        pi.calculaMaiorSequenciaPrimos();
        System.out.println(pi.getSequenciaPrimos());
    }

    public String getSequenciaPrimos() {
        return this.sequenciaPrimos;
    }

    public Pi(String decimaisPi, int limiteInferior, int limiteSuperior) {
        this.buffer = new HashMap<>();
        this.limiteInferior = limiteInferior;
        this.limiteSuperior = limiteSuperior;
        this.decimaisPi = decimaisPi;
        this.sequenciaPrimos = "";
    }

    public void calculaMaiorSequenciaPrimos() {
        for (int i = 0; i < this.decimaisPi.length(); i++) {
            StringBuilder sequencia = new StringBuilder();
            int proximoIndice = i;
            do {
                String proximoPrimo = proximoSequencialPrimo(proximoIndice);
                if (proximoPrimo == null) {
                    break;
                }
                sequencia.append(proximoPrimo);
                proximoIndice += proximoPrimo.length();
            } while (true);
            salvaSequencia(sequencia.toString());
        }
    }

    private void salvaSequencia(String sequencia) {
        if (sequencia.length() > this.sequenciaPrimos.length())
            this.sequenciaPrimos = sequencia;
    }


    private String proximoSequencialPrimo(int indice) {
        int tamanhoMinimo = Integer.toString(this.limiteInferior).length();
        int tamanhoMaximo = Integer.toString(this.limiteSuperior).length();

        String proximoSequencialPrimo = null;

        for (int tamanho = tamanhoMinimo; indice + tamanho < decimaisPi.length() && tamanho <= tamanhoMaximo; tamanho++) {
            int numero = Integer.parseInt(this.decimaisPi.substring(indice, indice + tamanho));
            if (numero < this.limiteInferior)
                continue;

            if (numero > this.limiteSuperior)
                break;

            if (verificaNumeroPrimo(numero)) {
                proximoSequencialPrimo = Integer.toString(numero);
            }
        }
        return proximoSequencialPrimo;

    }

    private boolean verificaNumeroPrimo(int numero) {
        if (this.buffer.containsKey(numero))
            return this.buffer.get(numero);

        boolean numeroEhPrimo = ehPrimo(numero);
        this.buffer.put(numero, numeroEhPrimo);
        return numeroEhPrimo;
    }

    private boolean ehPrimo(int numero) {
        for (int i = 2; i < numero; i++)
            if (numero % i == 0)
                return false;

        return true;
    }
}
