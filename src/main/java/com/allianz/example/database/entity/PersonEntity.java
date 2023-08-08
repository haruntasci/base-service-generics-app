package com.allianz.example.database.entity;

import com.allianz.example.util.dbutil.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table
@AttributeOverride(
        name = "uuid",
        column = @Column(
                name = "person_uuid"
        )
)
@Data
public class PersonEntity extends BaseEntity {

    @Column(unique = true,length = 1250)
    private String name;

    @Column
    private String surname;

    @Column
    private int birthYear;

    @Column
    private String tc;


    @OneToMany(targetEntity = AddressEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(referencedColumnName = "id",name = "person_id")
    private List<AddressEntity> addressEntityList;


}
