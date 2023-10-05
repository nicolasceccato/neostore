package com.neostore.supplier.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Object id) {
        super("Supplier not found! CNPJ " + id);
    }
}
