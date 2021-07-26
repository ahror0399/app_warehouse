package uz.developers.appwarehouse.dto;


import lombok.Data;

import java.util.Date;

@Data
public class OutputProductDto {
    private  Long product_id;
    private double amount;
    private  double price;

    private Long output_id;

}
