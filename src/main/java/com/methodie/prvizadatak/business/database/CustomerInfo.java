package com.methodie.prvizadatak.business.database;
import  com.methodie.prvizadatak.business.database.enums.Active;
import lombok.Getter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table (schema ="public",name="customer_info" )
@Getter
@Setter
public class CustomerInfo extends BaseData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="rec_id")
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="activation")
    private Active activation;

    @Column(name="amount_of_money")
    private Integer amount;

    @OneToOne
    @JoinColumn(name="customer_id",foreignKey = @ForeignKey(name="customer_info_customer"))
    private Customer customer;

    public boolean update(CustomerInfo customerInfo){
        boolean update = false;

        if(!Objects.equals(this.activation, customerInfo.activation)){this.activation = customerInfo.activation; update = true;}
        if(!Objects.equals(this.amount, customerInfo.amount)){this.amount = customerInfo.amount; update = true;}
        if(!Objects.equals(this.customer, customerInfo.customer)){this.customer = customerInfo.customer; update = true;}

        return update;
    }
}
