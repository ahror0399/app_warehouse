package uz.developers.appwarehouse.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

public class UserDto {
    private  String firstName;
    private String lastName;
    private String phoneNumber;
    private ArrayList<Integer> warehouse_id;



}
