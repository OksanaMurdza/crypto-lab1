package org.blacksun.crypto.name;

public class TableNameProvider implements NameProvider {
    private final String tableName;
    private final String rowName;
    private final String columnName;

    public TableNameProvider(String tableName, String rowName, String columnName) {
        this.tableName = tableName;
        this.rowName = rowName;
        this.columnName = columnName;
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public String getRowName(int index) {
        return rowName + index;
    }

    @Override
    public String getColumnName(int index) {
        return columnName + index;
    }
}
