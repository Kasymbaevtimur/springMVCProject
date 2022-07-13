package peaksoft.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "groups")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(name = "group_name")
    private String groupName;
    @Column(name = "date_of_start")
    private String dateOfStart;
    @Column(name = "date_of_finish")
    private String dateOfFinish;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
//    @JoinTable(name="group_course",
//               joinColumns = @JoinColumn(name="group_id"),
//                inverseJoinColumns = @JoinColumn(name="course_id"))
//    private  List<Course> courses;
//
//
//    @OneToMany(fetch = FetchType.EAGER,cascade ={CascadeType.MERGE,CascadeType.REMOVE},mappedBy = "course")
//
//    private List<Student>students;
//

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinTable(name = "course_group",
            joinColumns = @JoinColumn(name = "groups_id"),
            inverseJoinColumns = @JoinColumn(name = "courses_id"))
    List<Course> courses;

    @OneToMany(cascade = CascadeType.ALL)
    List<Student> students;

    @Transient
    private Long courseId;
}
