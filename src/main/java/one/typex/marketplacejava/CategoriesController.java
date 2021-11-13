package one.typex.marketplacejava;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class CategoriesController {

    final CategoriesRepository categoriesRepository;
    final ProductsRepository productsRepository;

    public CategoriesController(CategoriesRepository categoriesRepository, ProductsRepository productsRepository) {
        this.categoriesRepository = categoriesRepository;
        this.productsRepository = productsRepository;
    }

    @GetMapping("/api/v1/categories")
    public List<Category> getCategories() {
        return categoriesRepository.findAll();
    }

    @GetMapping("/api/v1/products")
    public List<Product> getProducts() {
        return productsRepository.findAll();
    }

    @Transactional
    @PostMapping("/api/v1/products")
    public void loadProducts(@RequestBody List<Product> products) {
        productsRepository.saveAll(products);
    }

}
