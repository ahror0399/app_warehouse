package uz.developers.appwarehouse.entity.warehouseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.developers.appwarehouse.entity.extendesClasses.AbsClass;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Client extends AbsClass {

    @Column(nullable = false,unique = true)
    private String phoneNumber;

}
