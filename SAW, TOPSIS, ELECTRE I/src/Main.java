import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int n = 5, m = 7;
        int[] result = new int[n];
        int[] saw;
        int[] topsis;
        int[] electre;
        double[][] x = {
                {0.8, 0.9, 0.7, 0.7, 0.7, 0.5, 0.7},
                {0.6, 0.7, 0.9, 0.81, 0.6, 0.6, 0.8},
                {0.92, 0.5, 0.8, 0.6, 0.8, 0.9, 0.6},
                {0.7, 0.6, 0.5, 0.45, 0.9, 0.3, 0.4},
                {0.4, 0.8, 0.6, 0.93, 0.5, 0.4, 0.93}
        };
        double[] weight = {0.19, 0.09, 0.2, 0.22, 0.1, 0.14, 0.06};
        saw = Methods.saw(x, weight, n, m);
        topsis = Methods.topsis(x, weight, n, m);
        electre = Methods.electre(x, weight, n, m);
        System.out.println(Arrays.toString(saw));
        System.out.println(Arrays.toString(topsis));
        System.out.println(Arrays.toString(electre));
        result[saw[0]]++;
        result[topsis[0]]++;
        result[electre[0]]++;
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (result[i] == 1) {
                index = i;
            }
            if (result[i] > 1) {
                System.out.println("Лучшее ПО находится под номером " + i);
                break;
            }
            if (i == n - 1) {
                System.out.println("Однозначного лидера обнаружить не удалось. Один из ПО-лидеров находится под номером " + index);
            }
        }
    }
}