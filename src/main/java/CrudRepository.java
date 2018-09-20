import java.util.List;

public interface CrudRepository<T> {

    T save(T item) throws Exception;
    List<T> getAll() throws Exception;
    T updateItem(T item) throws Exception;
    void deleteItem(String id) throws Exception;
}
