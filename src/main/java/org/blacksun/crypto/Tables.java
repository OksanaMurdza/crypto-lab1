package org.blacksun.crypto;

import org.blacksun.crypto.data.DoubleData;
import org.blacksun.crypto.data.IntData;
import org.blacksun.crypto.filler.RandomFiller;
import org.blacksun.crypto.name.NameProvider;
import org.blacksun.crypto.name.SumNameProvider;
import org.blacksun.crypto.name.TableNameProvider;

import javax.swing.table.AbstractTableModel;

class Tables {
    static final int MESSAGE_COUNT = 8;
    static final int KEY_COUNT = 8;
    static final int RESULT_COUNT = 8;

    // crypto function
    static final IntData E;
    static final AbstractTableModel EModel;
    // initial message probability
    static final DoubleData Pm;
    static final AbstractTableModel PmModel;
    // initial key probability
    static final DoubleData Pk;
    static final AbstractTableModel PkModel;
    // post message probability
    static final DoubleData Pem;
    static final AbstractTableModel PemModel;
    // post key probability
    static final DoubleData Pek;
    static final AbstractTableModel PekModel;
    // diff between Pm(e) and P(m)
    static final DoubleData PmeDiff;
    static final AbstractTableModel PmeDiffModel;
    // Pe(k)/P(m)
    static final DoubleData Pekm;
    static final AbstractTableModel PekmModel;

    static {
        {
            E = new IntData(MESSAGE_COUNT, KEY_COUNT);
            NameProvider provider = new TableNameProvider("E", "K", "M");
            EModel = new SimpleTableModel<>(provider, E, true);
        }
        {
            Pm = new DoubleData(1, MESSAGE_COUNT);
            NameProvider provider = new SumNameProvider("P(m)", "P", "M", Pm);
            Pm.fillRow(0, new RandomFiller(MESSAGE_COUNT));
            PmModel = new SimpleTableModel<>(provider, Pm, true);
        }
        {
            Pk = new DoubleData(1, KEY_COUNT);
            NameProvider provider = new SumNameProvider("P(k)", "P", "K", Pk);
            Pk.fillRow(0, new RandomFiller(KEY_COUNT));
            PkModel = new SimpleTableModel<>(provider, Pk, true);
        }
        {
            Pem = new DoubleData(RESULT_COUNT, MESSAGE_COUNT);
            NameProvider provider = new TableNameProvider("Pm(e)", "E", "M");
            PemModel = new SimpleTableModel<>(provider, Pem);
        }
        {
            Pek = new DoubleData(RESULT_COUNT, KEY_COUNT);
            NameProvider provider = new TableNameProvider("Pk(e)", "E", "K");
            PekModel = new SimpleTableModel<>(provider, Pek);
        }
        {
            PmeDiff = new DoubleData(RESULT_COUNT, MESSAGE_COUNT);
            NameProvider provider = new TableNameProvider("Pm(e) - P(m)", "E", "M");
            PmeDiffModel = new SimpleTableModel<>(provider, PmeDiff);
        }
        {
            Pekm = new DoubleData(RESULT_COUNT, KEY_COUNT);
            NameProvider provider = new TableNameProvider("Pe(k)/P(m)", "K", "E");
            PekmModel = new SimpleTableModel<>(provider, Pekm);
        }
        fill();
    }

    static void fill() {
        Pem.fill((e, m) -> Functions.Pem(m, e));
        Pek.fill((e, k) -> Functions.Pek(k, e));
        PmeDiff.fill((e, m) -> Pem.get(e, m) - Pm.get(0, m));
        Pekm.fill((k, e) -> {
            double value = Pek.get(e, k) / Pm.get(0, getM(k, e));
            System.out.println(String.format("K=%d, E=%d, M=%d. Pk(e)=%.2f, P(m)=%.2f, value=%.2f",
                    k, e, getM(k, e), Pek.get(e, k), Pm.get(0, getM(k, e)), value));
            return value;
        });
        PemModel.fireTableDataChanged();
        PekModel.fireTableDataChanged();
        PmeDiffModel.fireTableDataChanged();
        PekmModel.fireTableDataChanged();
    }

    private static int getM(int k, int e) {
        for (int m = 0; m < MESSAGE_COUNT; ++m) {
            if (E.get(k, m) == e) {
                return m;
            }
        }
        System.out.println("ERROR");
        return 0;//throw new IllegalStateException(String.format("No message Mi found that Mi * K%d = E%d", k + 1, e + 1));
    }
}
