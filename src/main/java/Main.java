import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        setUnirestObjectMapper();
        UrlBuilder.readProperties();
        CrudRepository<TodoItem> repository = new TodoItemRepository();
        TodoItem item1 = new TodoItem("first", Status.NEW);
        TodoItem item2 = new TodoItem("second", Status.NEW);
        TodoItem item3 = new TodoItem("third", Status.NEW);

        try {
            TodoItem result1 = repository.save(item1);
            System.out.println("item1 was saved with data: " + result1);
            TodoItem result2 = repository.save(item2);
            System.out.println("item2 was saved with data: " + result2);
            TodoItem result3 = repository.save(item3);
            System.out.println("item3 was saved with data: " + result3);
            List<TodoItem> items = repository.getAll();
            System.out.println("Get all items:");
            items.forEach(item -> System.out.print(item+"\n"));
            result1.setDescription("updated first item");
            TodoItem updated1 = repository.updateItem(result1);
            System.out.println("item1 was updated: " + updated1);
            System.out.println("Deleting item3");
            repository.deleteItem(result3.getObjectId());
            System.out.println("Get all items after deleting:");
            items = repository.getAll();
            items.forEach(item -> System.out.print(item+"\n"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setUnirestObjectMapper() {
        Unirest.setObjectMapper(new ObjectMapper() {
            com.fasterxml.jackson.databind.ObjectMapper mapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public String writeValue(Object value) {
                try {
                    return mapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                return null;
            }

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return mapper.readValue(value, valueType);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }
}
