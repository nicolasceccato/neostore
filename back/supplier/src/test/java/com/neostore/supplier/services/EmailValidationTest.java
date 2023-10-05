package com.neostore.supplier.services;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailValidationTest {

    @Test
    public void testValidEmail() {
        assertTrue(SupplierService.isValidEmail("test@example.com"));
        assertTrue(SupplierService.isValidEmail("test.user@domain.com"));
        assertTrue(SupplierService.isValidEmail("testuser123@test.domain.com"));
    }

    @Test
    public void testInvalidEmail() {
        assertFalse(SupplierService.isValidEmail("invalid-email@"));
        assertFalse(SupplierService.isValidEmail("invalid-email"));
        assertFalse(SupplierService.isValidEmail("test@domain"));
        assertFalse(SupplierService.isValidEmail(""));
        assertFalse(SupplierService.isValidEmail(null));
    }
}