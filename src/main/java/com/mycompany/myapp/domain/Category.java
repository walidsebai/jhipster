package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.mycompany.myapp.domain.enumeration.CategoryStatus;

/**
 * A Category.
 */
@Entity
@Table(name = "category")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CategoryStatus status;

    @ManyToOne
    @JsonIgnoreProperties(value = "categories", allowSetters = true)
    private Category parent;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "category_product",
               joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
    private Set<Product> products = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public Category description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public Category sortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
        return this;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public CategoryStatus getStatus() {
        return status;
    }

    public Category status(CategoryStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(CategoryStatus status) {
        this.status = status;
    }

    public Category getParent() {
        return parent;
    }

    public Category parent(Category category) {
        this.parent = category;
        return this;
    }

    public void setParent(Category category) {
        this.parent = category;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public Category products(Set<Product> products) {
        this.products = products;
        return this;
    }

    public Category addProduct(Product product) {
        this.products.add(product);
        product.getCategories().add(this);
        return this;
    }

    public Category removeProduct(Product product) {
        this.products.remove(product);
        product.getCategories().remove(this);
        return this;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Category)) {
            return false;
        }
        return id != null && id.equals(((Category) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Category{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", sortOrder=" + getSortOrder() +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
