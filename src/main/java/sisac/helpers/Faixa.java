package sisac.helpers;

public class Faixa {
    private static final String[] faixas = {
            "Branca", "Amarela", "Laranja", "Roxa", "Verde", "Azul", "Vermelha", "Preta"
    };

    public static String getFaixa(int i) {
        return faixas[i];
    }

}

