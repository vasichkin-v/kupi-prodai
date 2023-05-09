package com.example.kupiprodai.controllers;

import com.example.kupiprodai.models.Product;
import com.example.kupiprodai.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;

import static org.springframework.util.StringUtils.hasText;

@Controller
@RequiredArgsConstructor // Ломбок. Создаст конструктор с нужными инжектами
@Slf4j
public class ProductController {
    private final ProductService productService;

    @GetMapping("/")
    public String welcome(Principal principal, Model model){
        model.addAttribute("user", productService.getCurrentUser(principal));
        return "welcome"; // Шаблон welcome.ftlh (файл в дир templates лежит, для отображения представления)
    }

    @GetMapping("/products")
    public String products(@RequestParam(name = "title", required = false) String title, Principal principal, Model model){
        model.addAttribute("products", hasText(title) ? productService.findByTitle(title) : productService.listProducts());
        model.addAttribute("user", productService.getCurrentUser(principal));
        return "products";
    }

    @PostMapping("/product/create")
    public String create(@RequestParam(name = "img") MultipartFile[] img, Product product, Principal principal, Model model) throws IOException {
        // TODO почему вываливается ошиба ниже, если inputУ с картинками задачть name = images
        // Field error in object 'product' on field 'images': rejected value [org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@4f73b9d7]; codes [typeMismatch.product.images,typeMismatch.images,typeMismatch.java.util.List,typeMismatch]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [product.images,images]; arguments []; default message [images]]; default message [Failed to convert property value of type 'org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile' to required type 'java.util.List' for property 'images'; nested exception is java.lang.IllegalStateException: Cannot convert value of type 'org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile' to required type 'com.example.kupiprodai.models.Image' for property 'images[0]': no matching editors or conversion strategy found]
        productService.saveProduct(principal, product, img);
        model.addAttribute("user", productService.getCurrentUser(principal));
        return "redirect:/products";
    }

    @GetMapping("/product/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("product", productService.editProduct(id));
        return "/product-edit";
    }

    @PostMapping("/product/update")
    public String update(Product product){
        productService.update(product);
        return "redirect:/product/view/" + product.getId();
    }

    @GetMapping("/product/view/{id}")
    public String more(Model model, @PathVariable Long id){
        model.addAttribute("product", productService.getProductById(id));
        return "/product-card";
    }

    @GetMapping("/product/remove/{id}") // В HTTP есть только GET и POST, по этому, для http запросов мб придется заменить на get
    public String delete(@PathVariable Long id){
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
