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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "position")
public class PositionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "is_favorite")
    private boolean isFavorite = false;
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

    @Column(name = "description", length = 10000)
    private String description;
    @Embedded
    private ContactEntity contact;

    @Column(name = "headhunter_id")
    private int headHunterId;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "positions")
    private List<SkillEntity> skills = new ArrayList<>();

    @Column(name="experience")
    private String experience;
}
