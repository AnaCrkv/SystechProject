package com.methodie.prvizadatak.business.database;
import  com.methodie.prvizadatak.business.database.enums.EngineType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table (schema ="public",name="car" )
@Getter
@Setter
public class Car extends BaseData{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rec_id")
    private Long id;

    @Column
    private String manufacturer;

    @Column
    private LocalDate production_date;

    @Column
    private Integer engine;

    @Enumerated(EnumType.ORDINAL)
    @Column
    private EngineType engine_type;

    @Column
    private String kilometers;

    @ManyToOne
    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "car_customer"))
    //@Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private Customer customer;

    public boolean update(Car car){
        boolean update = false;

        if(!Objects.equals(this.manufacturer, car.manufacturer)){this.manufacturer = car.manufacturer; update = true;}
        if(!Objects.equals(this.production_date, car.production_date)){this.production_date = car.production_date; update = true;}
        if(!Objects.equals(this.engine, car.engine)){this.engine = car.engine; update = true;}
        if(!Objects.equals(this.engine_type, car.engine_type)){this.engine_type = car.engine_type; update = true;}
        if(!Objects.equals(this.kilometers, car.kilometers)){this.kilometers = car.kilometers; update = true;}
        if(!Objects.equals(this.customer, car.customer)){this.customer = car.customer; update = true;}

        return update;
    }


}