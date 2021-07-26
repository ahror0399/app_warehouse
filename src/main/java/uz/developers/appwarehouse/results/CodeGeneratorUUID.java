package uz.developers.appwarehouse.results;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
@Component
public class CodeGeneratorUUID {
    private String code;

public String  getCode(){

    UUID uuid = UUID.randomUUID();
    code = uuid.toString();
    return code;
}
}
