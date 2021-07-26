package uz.developers.appwarehouse.entity.warehouseEntity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import net.bytebuddy.agent.builder.AgentBuilder;
import uz.developers.appwarehouse.entity.extendesClasses.AbsClass;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(
        uniqueConstraints=
        @UniqueConstraint(columnNames={"name", "category_id"}))
public class Product extends AbsClass {

    @ManyToOne (optional = false)
    @JoinColumn(name = "category_id")
    private  Category category;

    @OneToMany
    private List<Attechment> attechments;

    @Column(unique = true,nullable = false )
    private  String  code;

    @ManyToOne(optional = false)
    private  Measurement measurement;





}
