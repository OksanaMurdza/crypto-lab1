package org.blacksun.crypto.name;

import org.blacksun.crypto.data.DoubleData;

public class SumNameProvider extends TableNameProvider {
    private final DoubleData data;

    public SumNameProvider(String tableName, String rowName, String columnName, DoubleData data) {
        super(tableName, rowName, columnName);
        this.data = data;
    }

    @Override
    public String getRowName(int row) {
        double sum = 0;
        for (int column = 0; column < data.getColumns(); ++column) {
            sum += data.get(row - 1, column);
        }
        return super.getRowName(row) + " " + sum;
    }
}
