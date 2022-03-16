public class Methods {
    public static int[] saw(double[][] x, double[] weight, int n, int m) {
        double max = -1, min = 2, c;
        double[][] p = new double[n][m];
        double[] r = new double[n];
        int[] index = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (x[j][i] > max) {
                    max = x[j][i];
                }
                if (x[j][i] < min) {
                    min = x[j][i];
                }
            }
            for (int j = 0; j < n; j++) {
                p[j][i] = ((x[j][i] - min) / (max - min)) * weight[i];
            }
            max = -1;
            min = 2;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                r[i] += p[i][j];
            }
            r[i] /= n;
        }
        for (int i = 0; i < n; i++) {
            max = -100;
            for (int j = 0; j < n; j++) {
                if (r[j] > max) {
                    max = r[j];
                    index[i] = j;
                }
            }
            r[index[i]] = -100;
        }
        return index;
    }

    public static int[] topsis(double[][] x, double[] weight, int n, int m) {
        double[][] p = new double[n][m];
        double sum, dif;
        int[] index = new int[n];
        for (int i = 0; i < m; i++) {
            sum = 0;
            for (int j = 0; j < n; j++) {
                sum += x[j][i] * x[j][i];
            }
            sum = Math.sqrt(sum);
            for (int j = 0; j < n; j++) {
                p[j][i] = (x[j][i] / sum) * weight[i];
            }
        }
        double[] maxArr = new double[m];
        double[] minArr = new double[m];
        double max = -100, min = 100;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (p[j][i] > max) {
                    max = p[j][i];
                }
                if (p[j][i] < min) {
                    min = p[j][i];
                }
            }
            maxArr[i] = max;
            minArr[i] = min;
            max = -100;
            min = 100;
        }
        double[] maxS = new double[n];
        double[] minS = new double[n];
        for (int i = 0; i < n; i++) {
            sum = 0;
            dif = 0;
            for (int j = 0; j < m; j++) {
                sum = sum + (maxArr[j] - p[i][j]) * (maxArr[j] - p[i][j]);
                dif = dif + (minArr[j] - p[i][j]) * (minArr[j] - p[i][j]);
            }
            maxS[i] = Math.sqrt(sum);
            minS[i] = Math.sqrt(dif);
        }
        double[] r = new double[n];
        for (int i = 0; i < n; i++) {
            r[i] = minS[i] / (maxS[i] + minS[i]);
        }
        for (int i = 0; i < n; i++) {
            max = -100;
            for (int j = 0; j < n; j++) {
                if (r[j] > max) {
                    max = r[j];
                    index[i] = j;
                }
            }
            r[index[i]] = -100;
        }
        return index;
    }

    public static int[] electre(double[][] x, double[] weight, int n, int m) {
        double[][] a = new double[n][n];
        double[][] ab = new double[n][n];
        double[][] b = new double[n][n];
        double[][] c = new double[n][n];
        double[][] d = new double[n][n];
        double[][] r = new double[n][n];
        int[] index = new int[n];
        for (int i = 0; i < n; i++) {
            a[i][i] = -1;
            ab[i][i] = -1;
            b[i][i] = -1;
            c[i][i] = -1;
            d[i][i] = -1;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    for (int k = 0; k < m; k++) {
                        if (x[i][k] > x[j][k]) {
                            a[i][j] += weight[k];
                        }
                        if (x[i][k] == x[j][k]) {
                            ab[i][j] += weight[k];
                        }
                        if (x[i][k] < x[j][k]) {
                            b[i][j] += weight[k];
                        }
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    c[i][j] = a[i][j] + ab[i][j];
                }
            }
        }
        double max, p = 0.5, q = 0.5;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    max = 0;
                    for (int k = 0; k < m; k++) {
                        if (max < x[j][k] - x[i][k]) {
                            max = x[j][k] - x[i][k];
                        }
                    }
                    d[i][j] = max / 10;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((i != j) && (c[i][j] >= p) && (d[i][j] <= q)) {
                    r[i][j] = 1;
                }
            }
        }
        int[] sum = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (r[i][j] == 1) {
                    sum[i]++;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            max = -100;
            for (int j = 0; j < n; j++) {
                if (sum[j] > max) {
                    max = sum[j];
                    index[i] = j;
                }
            }
            sum[index[i]] = -100;
        }
        return index;
    }
}