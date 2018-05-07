package entity;

import java.util.List;

public class JsonDataGetList<T> {
    private List<T> data;

    public JsonDataGetList() {
    }

    public JsonDataGetList(List<T> data) {
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
