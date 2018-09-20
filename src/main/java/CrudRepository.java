import java.util.List;

public interface CrudRepository<T> {

    void save(T item, String url);
    List<T> getAll(String url);
    void updateItem(T item, String url);
    void deleteItem(long id, String url);
}
