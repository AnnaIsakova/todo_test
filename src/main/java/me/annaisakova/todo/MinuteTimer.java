import com.backendless.servercode.annotation.BackendlessTimer;
import com.backendless.servercode.extension.TimerExtender;
import com.mashape.unirest.http.Unirest;

import java.util.List;

@BackendlessTimer("{'startDate':1537478569,'frequency':{'schedule':'custom','repeat':{'every':60}},'timername':'Minute'}")
public class MinuteTimer extends TimerExtender {
    CrudRepository<TodoItem> repository = new TodoItemRepository();
    @Override
    public void execute() {
        try {
            List<TodoItem> items = repository.getAll();
            items.forEach(item->{
                if(item.getStatus().equals(Status.COMPLETED)){
                    this.sendDeleteRequest(item.getObjectId());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendDeleteRequest(String itemId){
        Unirest.delete(UrlBuilder.getUrlWithId(itemId));
    }
}
