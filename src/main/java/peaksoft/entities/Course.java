package peaksoft.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
public class Course {
    @Id
    @SequenceGenerator(
            name = "company_sequence",
            sequenceName = "company_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "company_sequence"
    )
    private Long id;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "duration_month")
    private String durationMonth;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "companies_id")
    private Company company;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.MERGE,CascadeType.REMOVE})
    @JoinTable(name = "course_group",
            joinColumns = @JoinColumn(name = "courses_id"),
            inverseJoinColumns = @JoinColumn(name = "groups_id"))
    private List<Group> groups;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.MERGE,CascadeType.REMOVE}, mappedBy = "course")
    private Teacher teacher;

    @Transient
    private Long companyId;

}