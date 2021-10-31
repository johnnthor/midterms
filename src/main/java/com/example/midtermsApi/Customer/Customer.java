package com.example.midtermsApi.Customer;


import com.example.midtermsApi.Item.Item;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// ====================================================================================================================================================================================== \\

@Entity
@Table(name = "customer")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Customer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String email;

    @NotBlank
    private String name;

    @NotNull
    private String password;


    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "customer")
    private Set<Item> items = new HashSet<>();



    public Customer() {
    }

    public Customer(@NotBlank String email, @NotBlank String username, @NotBlank String password) {
        this.email = email;
        this.name = username;
        this.password = password;
    }

    public Customer(@NotBlank String username, @NotBlank String password) {
        this.name = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String username) {
        this.name = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer= (Customer) o;
        return
                Objects.equals(name, customer.name) &&
                        Objects.equals(password, customer.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, name, password);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}
