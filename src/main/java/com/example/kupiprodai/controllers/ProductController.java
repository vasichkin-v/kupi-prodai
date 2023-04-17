package com.example.kupiprodai.controllers;

import com.example.kupiprodai.models.Product;
import com.example.kupiprodai.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor // Ломбок. Создаст конструктор с нужными инжектоми
@Slf4j
public class ProductController {
    private final ProductService productService;


    @GetMapping("/")
    public String welcome(){
        return "welcome"; // Шаблон welcome.ftlh (файл в дир templates лежит, для отображения представления)
    }

    @GetMapping("/products")
    public String products(@RequestParam(name = "title", required = false)String title, Model model){
        model.addAttribute("products", StringUtils.hasText(title) ?
                productService.findByTitle(title) : productService.listProducts());
        return "products";
    }

    @PostMapping("/product/create")
    public String create(@RequestParam("img") MultipartFile[] img, Product product) throws IOException {
        productService.saveProduct(product, img);
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

    @GetMapping("/product/remove/{id}")
    public String delete(@PathVariable Long id){
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
