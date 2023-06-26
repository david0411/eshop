package fsse2305.eshop.data.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "category")
public class CategoryEntity {
    @Id
    private Integer catId;
    @ManyToOne
    @JoinColumn(name = "u_cat_id")
    private CategoryEntity categoryEntity;
    @Column(nullable = false)
    private String catName;

    public CategoryEntity() {
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}
