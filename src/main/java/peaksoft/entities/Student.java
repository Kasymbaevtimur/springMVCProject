package peaksoft.entities;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "students")
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    private String email;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "study_format")

    @Enumerated
    private StudyFormat studyFormat;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
    @Transient
    private Long groupId;
}
