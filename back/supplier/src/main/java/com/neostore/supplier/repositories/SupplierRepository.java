package com.neostore.supplier.repositories;

import com.neostore.supplier.entities.Supplier;

import java.util.List;

public interface SupplierRepository {
    List<Supplier> getAllSuppliers();

    Supplier getSupplier(String cnpj);

    Supplier addSupplier(Supplier supplier);

    Supplier editSupplier(String cnpj, Supplier supplier);

    void deleteSupplier(String cnpj);
}
