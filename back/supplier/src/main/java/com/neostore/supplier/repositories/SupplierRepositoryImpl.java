package com.neostore.supplier.repositories;

import com.neostore.supplier.entities.Supplier;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class SupplierRepositoryImpl implements SupplierRepository {

    private EntityManagerFactory emf;
    private EntityManager em;

    public SupplierRepositoryImpl() {
        emf = Persistence.createEntityManagerFactory("supplier-jpa");
        em = emf.createEntityManager();
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return em.createQuery("SELECT s FROM Supplier s", Supplier.class)
                .getResultList();
    }

    @Override
    public Supplier getSupplier(String cnpj) {
        return em.createQuery("SELECT s FROM Supplier s WHERE s.cnpj = :cnpj", Supplier.class)
                .setParameter("cnpj", cnpj)
                .getSingleResult();
    }

    @Override
    public Supplier addSupplier(Supplier supplier) {
        em.getTransaction().begin();
        em.persist(supplier);
        em.getTransaction().commit();
        return supplier;
    }

    @Override
    public Supplier editSupplier(String cnpj, Supplier updatedSupplier) {
        Supplier existingSupplier = em.find(Supplier.class, cnpj);
        if (existingSupplier != null) {
            em.getTransaction().begin();
            existingSupplier.setName(updatedSupplier.getName());
            existingSupplier.setEmail(updatedSupplier.getEmail());
            existingSupplier.setDescription(updatedSupplier.getDescription());
            em.getTransaction().commit();
        }
        return existingSupplier;
    }

    @Override
    public void deleteSupplier(String cnpj) {
        Supplier supplier = em.find(Supplier.class, cnpj);
        if (supplier != null) {
            em.getTransaction().begin();
            em.remove(supplier);
            em.getTransaction().commit();
        }
    }

    public void close() {
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }
}
