package br.curso.elastic.model.local;


import com.google.common.collect.Lists;

import java.util.List;

public class TableInfo {

    private String name;
    private List<TableRow> rows;

    public TableInfo(String name) {
        this.name = name;
        this.rows = Lists.newArrayList();
    }

    public TableInfo(){
        this.rows = Lists.newArrayList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TableRow> getRows() {
        return rows;
    }

    public void addRow(TableRow row){
        this.rows.add(row);
    }

    @Override
    public String toString() {
        return "TableInfo{" +
                "name='" + name + '\'' +
                ", rows=" + rows +
                '}';
    }
}
