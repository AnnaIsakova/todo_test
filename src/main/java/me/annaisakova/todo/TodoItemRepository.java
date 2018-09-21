import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TodoItemRepository implements CrudRepository<TodoItem> {

    public TodoItem save(TodoItem item) throws UnirestException {
        HttpResponse<TodoItem> response = Unirest.post(UrlBuilder.getBasicUrl())
                .header("Content-Type", "application/json")
                .body(item)
                .asObject(TodoItem.class);
        return response.getBody();
    }

    public List<TodoItem> getAll() throws UnirestException {
        HttpResponse<TodoItem[]> response = Unirest
                .get(UrlBuilder.getBasicUrl())
                .asObject(TodoItem[].class);
        return new ArrayList<TodoItem>(Arrays.asList(response.getBody()));
    }

    public TodoItem updateItem(TodoItem item) throws UnirestException {
        HttpResponse<TodoItem> response = Unirest.put(UrlBuilder.getUrlWithId(item.getObjectId()))
                .header("Content-Type", "application/json")
                .body(item)
                .asObject(TodoItem.class);
        return response.getBody();
    }

    public void deleteItem(String id) throws UnirestException {
        HttpResponse<TodoItem> getResponse = Unirest
                .get(UrlBuilder.getUrlWithId(id))
                .asObject(TodoItem.class);
        TodoItem itemToUpdate = getResponse.getBody();
        itemToUpdate.setStatus(Status.COMPLETED);
        Unirest.put(UrlBuilder.getUrlWithId(id))
            .header("Content-Type", "application/json")
            .body(itemToUpdate)
            .asObject(TodoItem.class);
    }
}
