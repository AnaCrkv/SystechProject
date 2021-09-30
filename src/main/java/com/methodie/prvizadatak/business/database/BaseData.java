package com.methodie.prvizadatak.business.database;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="rec_id")
    protected Long id;
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (Hibernate.getClass(this) != Hibernate.getClass(obj))
            return false;
        BaseData other = (BaseData) obj;
        if (this.getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!this.getId().equals(other.getId()))
            return false;
        return true;
    }
}
