package br.curso.elastic.response;


public class IndexRange {

    private int startIndex;

    private int endIndex;

    public IndexRange(int startIndex, int endIndex) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }


    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }
}
