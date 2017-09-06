package br.curso.elastic.model.local;


public class LinkedFrom {

    private String docId;

    private String linkedFromDocId;

    private Integer count;

    public LinkedFrom(String docId, String linkedFromDocId, Integer count) {
        this.docId = docId;
        this.linkedFromDocId = linkedFromDocId;
        this.count = count;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getLinkedFromDocId() {
        return linkedFromDocId;
    }

    public void setLinkedFromDocId(String linkedFromDocId) {
        this.linkedFromDocId = linkedFromDocId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
