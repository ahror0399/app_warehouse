package uz.developers.appwarehouse.results;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class Result {

    private  String message;
    private  boolean success;
    private Object object;

    public Result(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
