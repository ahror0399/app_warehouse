package uz.developers.appwarehouse.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductDto {

    private String name;
    private Integer category_id;
    private List<Integer> attechment_id;
    private Long measurement_id;

}
