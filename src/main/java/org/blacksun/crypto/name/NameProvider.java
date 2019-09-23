package org.blacksun.crypto.name;

public interface NameProvider {
    String getTableName();

    String getRowName(int index);

    String getColumnName(int index);
}
