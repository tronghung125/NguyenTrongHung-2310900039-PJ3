package k23cnt3.nth.lab8.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "author")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NthAuthor {

    @Id
    @Column(name = "id")
    Long id;

    String code;
    String name;
    String description;

    @Column(name = "imgUrl")
    String imgUrl;

    String email;
    String phone;
    String address;

    @Column(name = "isActive")
    Boolean isActive;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    List<NthBookAuthor> bookAuthors = new ArrayList<>();
}
