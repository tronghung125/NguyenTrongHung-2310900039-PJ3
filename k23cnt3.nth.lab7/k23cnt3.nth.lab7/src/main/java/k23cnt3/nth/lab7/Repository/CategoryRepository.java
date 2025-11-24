package k23cnt3.nth.lab7.Repository;

import k23cnt3.nth.lab7.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends
        JpaRepository<Category, Long> {

}
