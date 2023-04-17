package com.example.kupiprodai.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "images")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO) // Заменил Long на UUID
    @Column(name="id", columnDefinition = "varchar(36)")
    private String id = String.valueOf(UUID.randomUUID());
    @Column(name="name")
    private String name;
    @Column(name="original_file_name")
    private String originalFileName;
    @Column(name="size")
    private Long size;
    @Column(name="content_type")
    private String contentType;
    @Column(name="is_preview_image")
    private boolean isPreviewImage;
    @Lob
//    @Column(columnDefinition = "LONGBLOB") // аналог @Lob
    private  byte[] bytes;
    @ManyToOne(
            cascade = CascadeType.REFRESH, // наверно изменится только при обновлении
            fetch = FetchType.EAGER // При получении картинки, сразу будет подгружет и товар
    )
//    @Column(name = "product")
    @JoinColumn(name = "products_id")
    private Product product;
}
