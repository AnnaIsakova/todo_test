import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TodoItem {
    private String objectId;
    private String description;
    private Status status;

    public TodoItem(String description, Status status) {
        this.description = description;
        this.status = status;
    }
}
