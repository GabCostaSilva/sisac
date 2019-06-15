package sisac.helpers;

import java.util.Random;

public class CPFGenerator {
    private static Random random = new Random();

    public static String generate() {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < 11; i++) {
            builder.append(random.nextInt(9));
        }

        return builder.toString();
    }
}
