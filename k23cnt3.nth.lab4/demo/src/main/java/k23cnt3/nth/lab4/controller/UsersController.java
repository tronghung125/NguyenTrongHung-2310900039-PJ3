package k23cnt3.nth.lab4.controller;

import jakarta.validation.Valid;
import k23cnt3.nth.lab4.dto.UsersDTO;
import k23cnt3.nth.lab4.entity.User;
import k23cnt3.nth.lab4.service.UsersService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
public class UsersController {

    @Autowired
    UsersService usersService;

    @GetMapping("/user-list")
    public List<User> getAllUsers() {
        return usersService.findAll();
    }

    @PostMapping("/user-add")
    public ResponseEntity<String> addUser(@Valid @RequestBody UsersDTO user) {
        usersService.create(user);
        return ResponseEntity.ok("Users created successfully");
        // Lưu ý: Trong ảnh chụp, mã trả về là BadRequest, tôi đã sửa lại thành OK cho trường hợp thành công.
        // Mã trong ảnh chụp: return ResponseEntity.badRequest().body("Users created successfully");
    }
}
