package com.search.hh.entities;

import com.search.hh.entities.embedded.AreaEntity;
import com.search.hh.entities.embedded.ContactEntity;
import com.search.hh.entities.embedded.EmployerEntity;
import com.search.hh.entities.embedded.SalaryEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PositionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    @Size(max = 200)
    private String title;

    @Embedded
    private AreaEntity area;

    @Embedded
    private SalaryEntity salary;

    @Column(name = "is_open")
    private boolean isOpen;

    @Embedded
    private EmployerEntity employer;

    @Column(name = "published")
    private Date published;

    @Column(name = "requirement")
    @Size(max = 10000)
    private String requirement;

    @Column(name = "responsibility")
    @Size(max = 10000)
    private String responsibility;

    @Embedded
    private ContactEntity contact;
}
