package k23cnt3.nth.lab8.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book_author")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NthBookAuthor {

    @EmbeddedId
    NthBookAuthorId id;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "bookid")
    NthBook book;

    @ManyToOne
    @MapsId("authorId")
    @JoinColumn(name = "authorid")
    NthAuthor author;

    @Column(name = "is_editor")
    Boolean isEditor;
}
