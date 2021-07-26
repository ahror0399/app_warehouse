package uz.developers.appwarehouse.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class CategoryDto {
    @Column(unique = true)
    private String  name;
    private Long category_id;
}
