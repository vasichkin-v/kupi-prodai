package com.example.kupiprodai.services;

import com.example.kupiprodai.models.Image;
import com.example.kupiprodai.models.Product;
import com.example.kupiprodai.models.User;
import com.example.kupiprodai.repositories.ProductRepository;
import com.example.kupiprodai.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private static final int MAX_COUNT_IMG = 3;
    private static final int NUM_PREVIEW_IMG = 0;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    {
        // Это блок не статической инициализации (срабатывает при вызове любого конструктора).
//        products.add(new Product(++ID, "Жена соседа", "Беспонятия кто это, но прикольно же продать ее, во он удевится", 100, "New Adygea", "Любимый сосед"));
//        products.add(new Product(++ID,"Носки", "Продаю носки, бес пробега по Германии", 1_000_000, "New Adygea", "Любимый сосед"));
    }

    public List<Product> listProducts(){
        return productRepository.findAll();
    }

    public List<Product> findByTitle(String title){
        return productRepository.findByTitle(title);
    }

    public void saveProduct(Principal principal, Product product, MultipartFile[] images) throws IOException {
        if (images != null) {
            for (int i = 0; i < images.length; i++) {
                if (images[i] != null && images[i].getSize() != 0) {
                    if (i >= MAX_COUNT_IMG) break;
                    final Image image = toImageEntity(images[i], i == NUM_PREVIEW_IMG);
                    product.addImage(image);
                    if (i == NUM_PREVIEW_IMG) {
                        product.setPreviewImageId(image.getId());
                    }
                }
            }
        }

        product.setUser(getCurrentUser(principal));
        log.debug("Try to save product [{}]", product);
        productRepository.save(product);
    }

    /**
     * Получение текущего пользователся
     * @param principal
     * @return User
     */
    public User getCurrentUser(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    public static Image toImageEntity(MultipartFile image, boolean isFirstImg) throws IOException {
        final Image img = new Image();
        img.setName(image.getName());
        img.setOriginalFileName(image.getOriginalFilename());
        img.setSize(image.getSize());
        img.setContentType(image.getContentType());
        img.setPreviewImage(isFirstImg);
        img.setBytes(image.getBytes());
        return img;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
        log.info("Deleted product with id = {}", id);
    }
    
    public void update(Product product){
        final Product p = productRepository.getReferenceById(product.getId());
        if(product.getTitle() != null) p.setTitle(product.getTitle());
        if(product.getDescription() != null) p.setDescription(product.getDescription());
        p.setPrice(product.getPrice());
        if(product.getCity() != null) p.setCity(product.getCity());

        productRepository.save(p);
    }

    public Product editProduct(@PathVariable Long id){
        return productRepository.findById(id).orElseGet(Product::new);
    }

    public Product getProductById(Long id) {
        final Optional<Product> byId = productRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        throw new RuntimeException("Товар с ID " + id + " отсутствует");
    }
}
