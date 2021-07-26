package uz.developers.appwarehouse.entity.warehouseEntity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.developers.appwarehouse.entity.extendesClasses.AbsClass;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)

@Data
@Entity
public class Warehouse extends AbsClass {

}
