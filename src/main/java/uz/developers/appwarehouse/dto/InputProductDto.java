package uz.developers.appwarehouse.dto;


import lombok.Data;

import java.util.Date;

@Data
public class InputProductDto {
    private  Long product_id;
    private double amount;
    private  double price;

    private Date expire_date;
    private Long input_id;

}
