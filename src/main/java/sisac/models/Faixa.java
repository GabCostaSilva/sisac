package sisac.models;

class Faixa {
    private static final String[] faixas = {
            "Branca", "Amarela", "Laranja", "Roxa", "Verde", "Azul", "Vermelha", "Preta"
    };

    static String getFaixa(int i) {
        return faixas[i];
    }

}
