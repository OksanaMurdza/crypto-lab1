package org.blacksun.crypto;

public class Functions {
    public static int fi(int m, int k, int e) {
        return Tables.E.get(k, m) == e ? 1 : 0;
    }

    public static double Pme(int m, int e) {
        double sum = 0;
        for (int k = 0; k < Tables.KEY_COUNT; ++k) {
            sum += Tables.Pk.get(0, k) * fi(m, k, e);
        }
        return sum;
    }

    public static double Pe(int e) {
        double sum = 0;
        for (int m = 0; m < Tables.MESSAGE_COUNT; ++m) {
            for (int k = 0; k < Tables.KEY_COUNT; ++k) {
                sum += Tables.Pk.get(0, k) * Tables.Pm.get(0, m) * fi(m, k, e);
            }
        }
        return sum;
    }

    public static double Pem(int m, int e) {
        return Tables.Pm.get(0, m) * Pme(m, e) / Pe(e);
    }

    public static double Pek(int k, int e) {
        return Tables.Pk.get(0, k) * Pke(k, e) / Pe(e);
    }

    public static double Pke(int k, int e) {
        double sum = 0;
        for (int m = 0; m < Tables.MESSAGE_COUNT; ++m) {
            sum += Tables.Pm.get(0, m) * fi(m, k, e);
        }
        return sum;
    }
}
