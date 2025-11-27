package k23cnt3.nth.lab8.service;

import k23cnt3.nth.lab8.entity.NthAuthor;
import k23cnt3.nth.lab8.repository.NthAuthorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NthAuthorService {

    private final NthAuthorRepository nthAuthorRepository;

    public NthAuthorService(NthAuthorRepository nthAuthorRepository) {
        this.nthAuthorRepository = nthAuthorRepository;
    }

    public List<NthAuthor> getAllAuthors() {
        return nthAuthorRepository.findAll();
    }

    public NthAuthor getAuthorById(Long id) {
        return nthAuthorRepository.findById(id).orElse(null);
    }

    public NthAuthor saveAuthor(NthAuthor author) {
        return nthAuthorRepository.save(author);
    }

    public void deleteAuthor(Long id) {
        nthAuthorRepository.deleteById(id);
    }
}
