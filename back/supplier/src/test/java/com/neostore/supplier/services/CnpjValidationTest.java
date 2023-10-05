package com.neostore.supplier.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CnpjValidationTest {
    @Test
    public void testValidCnpj() {
        assertTrue(SupplierService.isValidCnpj("19.616.974/0001-22"));
        assertTrue(SupplierService.isValidCnpj("19616974000122"));
    }

    @Test
    public void testInvalidCnpj() {
        assertFalse(SupplierService.isValidCnpj("12.345.678/0001-91"));
        assertFalse(SupplierService.isValidCnpj("12345678000191"));
        assertFalse(SupplierService.isValidCnpj("12345"));
        assertFalse(SupplierService.isValidCnpj(""));
        assertFalse(SupplierService.isValidCnpj(null));
    }
}

