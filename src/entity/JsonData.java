package entity;



import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonData<T> {

    private String type;
    private Object id;
    private HashMap<String, Object> attributes;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public HashMap<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(HashMap<String, Object> attributes) {
        this.attributes = attributes;
    }

    public JsonData getInstance(T obj) throws IllegalAccessException {
        JsonData data = new JsonData();
        HashMap<String, Object> mapAtt = new HashMap<>();

        data.setType(obj.getClass().getSimpleName());

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);

            if (field.isAnnotationPresent(com.googlecode.objectify.annotation.Id.class)) {
                data.setId(field.get(obj));
            } else {
                mapAtt.put(field.getName(), field.get(obj));
            }

        }
        data.setAttributes(mapAtt);
        return data;
    }

    public List<JsonData> getListInstance(List<T> lobj) throws IllegalAccessException {
        List<JsonData> DataGets = new ArrayList<>();
        for (T obj: lobj) {
            JsonData data = new JsonData();
            HashMap<String, Object> mapAtt = new HashMap<>();

            data.setType(obj.getClass().getSimpleName());

            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);

                if (field.isAnnotationPresent(com.googlecode.objectify.annotation.Id.class)) {
                    data.setId(field.get(obj));
                } else {
                    mapAtt.put(field.getName(), field.get(obj));
                }

            }
            data.setAttributes(mapAtt);
            DataGets.add(data);
        }

        return DataGets;
    }
}
