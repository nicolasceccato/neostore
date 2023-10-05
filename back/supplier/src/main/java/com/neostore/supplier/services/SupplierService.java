package com.neostore.supplier.services;

import com.neostore.supplier.entities.Supplier;
import com.neostore.supplier.repositories.SupplierRepositoryImpl;
import com.neostore.supplier.services.exceptions.ResourceNotFoundException;

import java.util.List;

public class SupplierService {
    private SupplierRepositoryImpl supplierRepository;

    public SupplierService(SupplierRepositoryImpl supplierRepository) {
        this.supplierRepository = supplierRepository;

    }

    public Supplier addSupplier(Supplier supplierRequest) {
        if (supplierRequest == null) {
            throw new IllegalArgumentException("Objeto supplierRequest não pode ser nulo.");
        }

        String email = supplierRequest.getEmail();
        String cnpj = supplierRequest.getCnpj();

        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Email inválido!");
        }
        if (!isValidCnpj(cnpj)) {
            throw new IllegalArgumentException("CNPJ inválido!");
        }

        return supplierRepository.addSupplier(supplierRequest);
    }

    protected static boolean isValidCnpj(String cnpj) {
        // Verifica se o CNPJ nao eh nulo
        if (cnpj == null) {
            return false;
        }

        // Remove caracteres não numéricos
        cnpj = cnpj.replaceAll("[^0-9]", "");

        // Verifica se o CNPJ tem 14 dígitos
        if (cnpj.length() != 14)
            return false;

        // Verifica se todos os dígitos são iguais (caso especial que não é um CNPJ válido)
        if (cnpj.matches("(\\d)\\1*"))
            return false;

        int[] digitos = new int[14];
        for (int i = 0; i < 14; i++) {
            digitos[i] = Character.getNumericValue(cnpj.charAt(i));
        }

        // Calcula o primeiro dígito verificador
        int soma = 0;
        int peso = 2;
        for (int i = 11; i >= 0; i--) {
            soma += digitos[i] * peso;
            peso++;
            if (peso == 10)
                peso = 2;
        }
        int resto = soma % 11;
        int digito1 = (resto < 2) ? 0 : (11 - resto);

        // Verifica o primeiro dígito verificador
        if (digitos[12] != digito1)
            return false;

        // Calcula o segundo dígito verificador
        soma = 0;
        peso = 2;
        for (int i = 12; i >= 0; i--) {
            soma += digitos[i] * peso;
            peso++;
            if (peso == 10)
                peso = 2;
        }
        resto = soma % 11;
        int digito2 = (resto < 2) ? 0 : (11 - resto);

        // Verifica o segundo dígito verificador
        return (digitos[13] == digito2);
    }


    protected static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        // Expressão regular para validar o formato do e-mail
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }

    public Supplier getSupplier(String cnpj) {
        Supplier supplier = null;
        try {
            supplier = supplierRepository.getSupplier(cnpj);
        } catch (Exception e) {
            throw new ResourceNotFoundException(cnpj);
        }
        return supplier;
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.getAllSuppliers();
    }

    public Supplier editSupplier(String cnpj, Supplier updatedSupplier) {
        if (updatedSupplier == null) {
            throw new IllegalArgumentException("Objeto updatedSupplier não pode ser nulo.");
        }
        return supplierRepository.editSupplier(cnpj, updatedSupplier);
    }

    public void deleteSupplier(String cnpj) {
        supplierRepository.deleteSupplier(cnpj);
    }

    public String doSomething() {
        return "deu certo";
    }
}
