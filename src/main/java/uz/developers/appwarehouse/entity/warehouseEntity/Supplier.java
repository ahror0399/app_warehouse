package uz.developers.appwarehouse.entity.warehouseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.developers.appwarehouse.entity.extendesClasses.AbsClass;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Supplier extends AbsClass {

    @Column(unique = true,nullable = false)
    private String phoneNumber;


}
