package k23cnt3.nth.lab7.Controller;

import k23cnt3.nth.lab7.entity.Product;
import k23cnt3.nth.lab7.Service.ProductService;
import k23cnt3.nth.lab7.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;

public class ProductController{

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    // Hiển thị danh sách sản phẩm
    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "product/product-list";
    }

    // Form tạo mới sản phẩm
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "product/product-form";
    }

    // Form chỉnh sửa sản phẩm
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findById(id).orElse(new Product()));
        model.addAttribute("categories", categoryService.getAllCategories());
        return "product/product-form";
    }

    // Lưu sản phẩm mới
    @PostMapping("/save")
    public String saveProduct(@ModelAttribute Product product,
                              @RequestParam("imageFile") MultipartFile imageFile) throws IOException {

        handleImageUpload(product, imageFile);
        productService.saveProduct(product);
        return "redirect:/products";
    }

    // Cập nhật sản phẩm
    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id,
                                @ModelAttribute Product product,
                                @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        product.setId(id);
        handleImageUpload(product, imageFile);
        productService.saveProduct(product);
        return "redirect:/products";
    }

    // Xóa sản phẩm
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    // --- Xử lý upload ảnh ---
    private void handleImageUpload(Product product, MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
            String uploadDir = "src/main/resources/static/images/";

            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (var inputStream = imageFile.getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            }

            product.setImageUrl("images/" + fileName);
        }
    }
}
