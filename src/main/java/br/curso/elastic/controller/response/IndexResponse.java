package br.curso.elastic.controller.response;


public class IndexResponse {

    private Boolean success;

    private Integer docCount;

    public IndexResponse(Boolean success, Integer docCount) {
        this.success = success;
        this.docCount = docCount;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getDocCount() {
        return docCount;
    }

    public void setDocCount(Integer docCount) {
        this.docCount = docCount;
    }

}
