package com.methodie.prvizadatak.business.database;

import  com.methodie.prvizadatak.business.database.enums.Type;
import lombok.Getter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table (schema ="public",name="insurance" )
@Getter
@Setter
public class Insurance extends BaseData{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="rec_id")
    private Long id;

    @Column(name="start_date")
    private LocalDate startDate;

    @Column(name="end_date")
    private LocalDate endDate;

    @Enumerated(EnumType.ORDINAL)
    @Column
    private Type type;

    @Column
    private Integer price;

    @ManyToOne//(cascade=CascadeType.ALL)
    @JoinColumn(name="car_id",foreignKey = @ForeignKey(name="insurance_car"))
    private Car car;

    public boolean update(Insurance insurance){
        boolean update = false;

        if(!Objects.equals(this.startDate, insurance.startDate)){this.startDate = insurance.startDate; update = true;}
        if(!Objects.equals(this.endDate, insurance.endDate)){this.endDate = insurance.endDate; update = true;}
        if(!Objects.equals(this.type, insurance.type)){this.type = insurance.type; update = true;}
        if(!Objects.equals(this.price, insurance.price)){this.price = insurance.price; update = true;}
        if(!Objects.equals(this.car, insurance.car)){this.car = insurance.car; update = true;}

        return update;
    }
}
