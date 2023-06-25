package org.tech.mybatis.entities;
import lombok.Data;

@Data
public class Street {

    private long id;

    private String name;

    private int postcode;
    public Street() {
        
    }
    public Street(long id, String name, int postcode) {
        this.id = id;
        this.name = name;
        this.postcode = postcode;
    }
}
