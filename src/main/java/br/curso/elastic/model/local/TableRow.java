package br.curso.elastic.model.local;

/**
 * Created by andregarcia on 26/08/17.
 */
public class TableRow {

    private String key;

    private String value;

    public TableRow(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "TableRow{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
