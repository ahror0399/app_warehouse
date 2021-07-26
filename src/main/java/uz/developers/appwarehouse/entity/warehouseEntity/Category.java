package uz.developers.appwarehouse.entity.warehouseEntity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.developers.appwarehouse.entity.extendesClasses.AbsClass;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)

@Data
@Entity
@Table(
        uniqueConstraints=
        @UniqueConstraint(columnNames={"name", "category_id"}))
public class Category extends AbsClass {
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
