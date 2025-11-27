package k23cnt3.nth.lab8.repository;

import k23cnt3.nth.lab8.entity.NthBookAuthor;
import k23cnt3.nth.lab8.entity.NthBookAuthorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NthBookAuthorRepository extends JpaRepository<NthBookAuthor, NthBookAuthorId> {
}
