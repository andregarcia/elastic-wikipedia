package br.curso.elastic.response;


public class DeleteIndexResponse {

    private Boolean success;

    public DeleteIndexResponse(Boolean success) {
        this.success = success;
    }

    public Boolean getSuccess() {
        return success;
    }

}
