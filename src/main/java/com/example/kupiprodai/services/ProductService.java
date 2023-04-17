package com.example.kupiprodai.services;

import com.example.kupiprodai.models.Image;
import com.example.kupiprodai.models.Product;
import com.example.kupiprodai.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private static final int MAX_COUNT_IMG = 3;
    private static final int NUM_PREVIEW_IMG = 0;
    private final ProductRepository productRepository;

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

    public void saveProduct(Product product, MultipartFile[] images) throws IOException {
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

        log.debug("Try to save product [{}]", product);
        productRepository.save(product);
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
        final Product entity = productRepository.getReferenceById(product.getId());
        entity.setId(product.getId());
        entity.setTitle(product.getTitle());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice());
        entity.setCity(product.getCity());
        entity.setAuthor(product.getAuthor());
        productRepository.save(entity);
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
