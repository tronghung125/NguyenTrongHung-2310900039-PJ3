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
@Table(name = "book")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NthBook {

    @Id
    @Column(name = "id")
    Long id;

    String code;
    String name;
    String description;

    @Column(name = "imgUrl")
    String imgUrl;

    Integer quantity;
    Double price;

    @Column(name = "isActive")
    Boolean isActive;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    List<NthBookAuthor> bookAuthors = new ArrayList<>();
}
