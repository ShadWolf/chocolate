package org.asalas.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by donaldsmallidge on 2/19/17.
 */
@MappedSuperclass
public class AbstractDomainClass implements DomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }


}
