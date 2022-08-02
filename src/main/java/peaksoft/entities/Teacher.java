package peaksoft.entities;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "teachers")
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teacher_sequence")
    @SequenceGenerator(name = "teacher_sequence", sequenceName = "teacher_sequence", allocationSize = 1)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    private String email;
    @Column(name = "last_name")
    private String lastName;

    @OneToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    private Course course;
    @Transient
    private Long courseId;

}
