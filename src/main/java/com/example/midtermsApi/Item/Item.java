package com.example.midtermsApi.Item;

import com.example.midtermsApi.Customer.Customer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "item")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

// ====================================================================================================================================================================================== \\

public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String product_name;
    @NotBlank
    private String manufacturer_name;
    @NotBlank
    private int price;


    public Item() {
    }


    public Item(@NotBlank String product_name, @NotBlank String manufacturer_name, @NotBlank int price) {
        this.product_name = product_name;
        this.manufacturer_name = manufacturer_name;
        this.price = price;
    }


    public Item(@NotBlank String product_name, @NotBlank String manufacturer_name, @NotBlank Integer price) {
        this.product_name = product_name;
        this.manufacturer_name = manufacturer_name;
        this.price = price;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String item_name) {
        this.product_name = item_name;
    }



    public String getManufacturer_name() {
        return manufacturer_name;
    }

    public void setManufacturer_name(String category_name) {
        this.manufacturer_name = category_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item= (Item) o;
        return
                Objects.equals(product_name, item.product_name) &&
                        Objects.equals(manufacturer_name, item.manufacturer_name) &&
                        Objects.equals(price, item.price);
    }



    @Override
    public int hashCode() {
        return Objects.hash(id, product_name, manufacturer_name, price);
    }

    @Override
    public String toString() {
        return " " +
                "Customer" +
                " " +
                "id=" + id +
                ", item_name='" + product_name + '\'' +
                ", category='" + manufacturer_name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerid", nullable = false)
    private Customer customer;

}