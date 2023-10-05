package com.neostore.supplier.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Supplier {
    @Id
    private String cnpj;
    private String name;
    private String email;
    private String description;

    public Supplier() {
    }

    public Supplier(String cnpj, String name, String email, String description) {
        this.cnpj = cnpj;
        this.name = name;
        this.email = email;
        this.description = description;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
