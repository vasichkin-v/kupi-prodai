package com.example.kupiprodai.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
//@Data // Почему-то вызывает рекурсию из-за связи между сущьностями (OneToMany), из-за методов toString, equals, hashCode
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    @Column(name="title")
    private String title;
    @Column(name="description", columnDefinition = "text")
    private String description;
    @Column(name="price")
    private int price;
    @Column(name="city")
    private String city;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User user;
    @OneToMany(cascade = CascadeType.ALL, // При удалении товара, удалим все связанные с ним картинки
        fetch = FetchType.LAZY, // При загрузке товара, не грузим картинки
        mappedBy = "product" // С каким полем мапить (речь про поле в моделе images)
    )
    private List<Image> images = new ArrayList<>();
    @Column(name = "preview_image_id", columnDefinition = "varchar(36)")
    private String previewImageId;
    private LocalDateTime dateOfCreated;

    @PrePersist
    private void init(){
        dateOfCreated = LocalDateTime.now();
    }

    public void addImage(Image image) {
        image.setProduct(this);
        images.add(image);
    }

    public String toStringWithImgCount() {
        return "Product {" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", city='" + city + '\'' +
                ", author='" + user.getUsername() + '\'' +
                ", count_images=" + images.size() +
                ", previewImageId=" + previewImageId +
                ", dateOfCreated=" + dateOfCreated +
                '}';
    }
}
