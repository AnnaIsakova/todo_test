import java.util.List;

public interface CrudRepository<T> {

    void save(T item);
    List<T> getAll();
    void updateItem(T item);
    void deleteItem(long id);
}
