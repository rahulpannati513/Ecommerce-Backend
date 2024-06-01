package org.rahul.ecommercebackend.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class CategoryProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 50)
    private  String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_category_id")
    private  CategoryProduct parentCategory;

    private int level;

    public CategoryProduct() {
    }

    public CategoryProduct(Long id, String name, CategoryProduct parentCategory, int level) {

        this.id = id;
        this.name = name;
        this.parentCategory = parentCategory;
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull @Size(max = 50) String getName() {
        return name;
    }

    public void setName(@NotNull @Size(max = 50) String name) {
        this.name = name;
    }

    public CategoryProduct getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(CategoryProduct parentCategory) {
        this.parentCategory = parentCategory;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
