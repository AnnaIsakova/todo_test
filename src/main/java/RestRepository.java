import com.mashape.unirest.http.HttpResponse;

import java.util.List;

public interface RestRepository<T> {

    T save(T item, String url) throws Exception;
    List<T> getAll(String url) throws Exception;
    T updateItem(T item, String url) throws Exception;
    void deleteItem(long id, String url) throws Exception;
}
