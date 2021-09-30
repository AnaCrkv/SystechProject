package com.methodie.prvizadatak.business.database;

import  com.methodie.prvizadatak.business.database.enums.EGender;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(schema="public",name="customer")
@Getter
@Setter
public class Customer extends BaseData{

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name="rec_id")
 private Long id;

    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private LocalDate date;

    @Enumerated(EnumType.ORDINAL)
    @Column
    private EGender gender;

    public boolean update(Customer customer){
        boolean update = false;

        if(!Objects.equals(this.name, customer.name)){this.name = customer.name; update = true;}
        if(!Objects.equals(this.surname, customer.surname)){this.surname = customer.surname; update = true;}
        if(!Objects.equals(this.date, customer.date)){this.date = customer.date; update = true;}
        if(!Objects.equals(this.gender, customer.gender)){this.gender = customer.gender; update = true;}
        if(!Objects.equals(this.gender, customer.gender)){this.gender = customer.gender; update = true;}
        return update;
    }

    public String getFullName(){
        return this.name + " " + this.surname;
    }

}
