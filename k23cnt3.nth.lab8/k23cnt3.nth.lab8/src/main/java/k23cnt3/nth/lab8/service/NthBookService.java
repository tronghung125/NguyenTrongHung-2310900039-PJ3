package k23cnt3.nth.lab8.service;

import k23cnt3.nth.lab8.entity.NthBook;
import k23cnt3.nth.lab8.repository.NthBookRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NthBookService {

    private final NthBookRepository nthBookRepository;

    public NthBookService(NthBookRepository nthBookRepository) {
        this.nthBookRepository = nthBookRepository;
    }

    public List<NthBook> getAllBooks() {
        return nthBookRepository.findAll();
    }

    public NthBook getBookById(Long id) {
        return nthBookRepository.findById(id).orElse(null);
    }

    public NthBook saveBook(NthBook book) {
        return nthBookRepository.save(book);
    }

    public void deleteBook(Long id) {
        nthBookRepository.deleteById(id);
    }
}
