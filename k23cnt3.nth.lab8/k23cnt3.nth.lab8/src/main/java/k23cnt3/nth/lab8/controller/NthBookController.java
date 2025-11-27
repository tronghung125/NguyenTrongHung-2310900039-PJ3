package k23cnt3.nth.lab8.controller;

import k23cnt3.nth.lab8.entity.*;
import k23cnt3.nth.lab8.repository.NthBookAuthorRepository;
import k23cnt3.nth.lab8.service.NthAuthorService;
import k23cnt3.nth.lab8.service.NthBookService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/books")
public class NthBookController {

    private final NthBookService bookService;
    private final NthAuthorService authorService;
    private final NthBookAuthorRepository bookAuthorRepository;

    public NthBookController(
            NthBookService bookService,
            NthAuthorService authorService,
            NthBookAuthorRepository bookAuthorRepository
    ) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.bookAuthorRepository = bookAuthorRepository;
    }

    // ================================
    // LIST
    // ================================
    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "book/list";
    }

    // ================================
    // CREATE FORM
    // ================================
    @GetMapping("/new")
    public String createBookForm(Model model) {
        model.addAttribute("book", new NthBook());
        model.addAttribute("authors", authorService.getAllAuthors());
        return "book/form";
    }

    // ================================
    // EDIT FORM
    // ================================
    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable Long id, Model model) {
        NthBook book = bookService.getBookById(id);

        if (book == null) return "redirect:/books";

        List<NthAuthor> allAuthors = authorService.getAllAuthors();

        model.addAttribute("book", book);
        model.addAttribute("authors", allAuthors);

        return "book/form";
    }

    // ================================
    // SAVE (CREATE + UPDATE)
    // ================================
    @PostMapping("/save")
    public String saveBook(
            @ModelAttribute("book") NthBook book,
            @RequestParam(value = "authorIds", required = false) List<Long> authorIds,
            @RequestParam("imageFile") MultipartFile imageFile
    ) throws IOException {

        // ----- Upload ảnh -----
        if (!imageFile.isEmpty()) {
            String uploadDir = "uploads/book/";
            File uploadPath = new File(uploadDir);

            if (!uploadPath.exists()) uploadPath.mkdirs();

            String filePath = uploadDir + imageFile.getOriginalFilename();
            imageFile.transferTo(new File(filePath));

            book.setImgUrl("/" + filePath);
        }

        // ----- Lưu sách -----
        bookService.saveBook(book);

        // ----- Xóa quan hệ cũ -----
        bookAuthorRepository.deleteAll(
                bookAuthorRepository.findAll().stream()
                        .filter(ba -> ba.getBook().getId().equals(book.getId()))
                        .toList()
        );

        // ----- Tạo quan hệ mới -----
        if (authorIds != null) {
            for (Long authorId : authorIds) {
                NthAuthor author = authorService.getAuthorById(authorId);

                NthBookAuthorId baId = new NthBookAuthorId(book.getId(), authorId);

                NthBookAuthor ba = new NthBookAuthor();
                ba.setId(baId);
                ba.setBook(book);
                ba.setAuthor(author);
                ba.setIsEditor(false);

                bookAuthorRepository.save(ba);
            }
        }

        return "redirect:/books";
    }

    // ================================
    // DELETE
    // ================================
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {

        // Xóa quan hệ book_author trước
        bookAuthorRepository.deleteAll(
                bookAuthorRepository.findAll().stream()
                        .filter(ba -> ba.getBook().getId().equals(id))
                        .toList()
        );

        bookService.deleteBook(id);
        return "redirect:/books";
    }
}
