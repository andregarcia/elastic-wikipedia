package br.curso.elastic.model.local;


public class Link {


    private String text;

    public Link(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Link{" +
                "text='" + text + '\'' +
                '}';
    }
}
