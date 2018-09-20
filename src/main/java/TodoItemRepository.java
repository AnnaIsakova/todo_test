import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TodoItemRepository implements RestRepository<TodoItem> {

    public TodoItem save(TodoItem item, String url) throws UnirestException {
        HttpResponse<TodoItem> response = Unirest.post(url)
                .header("Content-Type", "application/json")
                .body(item)
                .asObject(TodoItem.class);
        return response.getBody();
    }

    public List<TodoItem> getAll(String url) throws UnirestException {
        HttpResponse<TodoItem[]> response = Unirest
                .get(url)
                .asObject(TodoItem[].class);
        return new ArrayList<TodoItem>(Arrays.asList(response.getBody()));
    }

    public TodoItem updateItem(TodoItem item, String url) throws UnirestException {
        HttpResponse<TodoItem> response = Unirest.put(url)
                .header("Content-Type", "application/json")
                .body(item)
                .asObject(TodoItem.class);
        return response.getBody();
    }

    public void deleteItem(long id, String url) throws UnirestException {
        HttpResponse<TodoItem> getResponse = Unirest
                .get(url)
                .asObject(TodoItem.class);
        TodoItem itemToUpdate = getResponse.getBody();
        itemToUpdate.setStatus(Status.COMPLETED);
        Unirest.put(url)
            .header("Content-Type", "application/json")
            .body(itemToUpdate)
            .asObject(TodoItem.class);
    }
}
