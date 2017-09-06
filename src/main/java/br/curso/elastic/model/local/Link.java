package br.curso.elastic.model.local;


public class Link {

    private String text;

    private Integer count;

    public Link(String text) {
        this.text = text;
        count = 0;
    }

    public String getText() {
        return text;
    }

    public Integer getCount() {
        return count;
    }

    public void incrementCount(){
        count += 1;
    }

    @Override
    public String toString() {
        return "Link{" +
                "text='" + text + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Link)) return false;

        Link link = (Link) o;

        return text.equals(link.text);

    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }
}
