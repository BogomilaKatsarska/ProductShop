package bg.softuni.productshop.data.entities;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public BaseEntity() {
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {}
}
