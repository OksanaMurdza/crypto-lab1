package org.blacksun.crypto;

import org.blacksun.crypto.data.DataProvider;
import org.blacksun.crypto.formatter.DataFormatter;
import org.blacksun.crypto.name.NameProvider;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.Objects;

public class SimpleTableModel<T> extends AbstractTableModel {
    private final NameProvider name;
    private final int rows;
    private final int columns;
    private final DataProvider<T> provider;
    private final DataFormatter<T> formatter;
    private final boolean editable;

    public SimpleTableModel(NameProvider name, DataProvider<T> provider) {
        this(name, provider, false);
    }

    public SimpleTableModel(NameProvider name, DataProvider<T> provider, boolean editable) {
        this(name, provider, editable, Objects::toString);
    }

    public SimpleTableModel(NameProvider name, DataProvider<T> provider, boolean editable, DataFormatter<T> formatter) {
        this.name = name;
        this.rows = provider.getRows() + 1;
        this.columns = provider.getColumns() + 1;
        this.provider = provider;
        this.editable = editable;
        this.formatter = formatter;
    }

    public int getRowCount() {
        return rows;
    }

    public int getColumnCount() {
        return columns;
    }

    public String getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex == 0 && columnIndex == 0)
            return name.getTableName();
        if (rowIndex == 0)
            return name.getColumnName(columnIndex);
        if (columnIndex == 0)
            return name.getRowName(rowIndex);
        return formatter.format(provider.get(rowIndex - 1, columnIndex - 1));
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (rowIndex == 0 || columnIndex == 0)
            return false;
        return editable;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        try {
            provider.set(rowIndex - 1, columnIndex - 1, (String) value);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
