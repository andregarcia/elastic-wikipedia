package br.curso.elastic.model.local;

public class WikipediaIdRow {

    private int index;
    private String id;
    private String title;

    public WikipediaIdRow(int index, String id, String title) {
        this.index = index;
        this.id = id;
        this.title = title;
    }

    public int getIndex() {
        return index;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
