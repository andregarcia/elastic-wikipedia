package br.curso.elastic.response;

/**
 * Created by andregarcia on 31/05/17.
 */
public class SingleItemResponse<T> {

    private T item;

    public SingleItemResponse(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }
}
