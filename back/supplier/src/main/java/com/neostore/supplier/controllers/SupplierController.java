package com.neostore.supplier.controllers;

import com.neostore.supplier.entities.Supplier;
import com.neostore.supplier.repositories.SupplierRepositoryImpl;
import com.neostore.supplier.services.SupplierService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@WebServlet("/supplier")
public class SupplierController extends HttpServlet {
    private SupplierService supplierService;
    private ObjectMapper objectMapper = new ObjectMapper();

    public SupplierController() {
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.supplierService = new SupplierService(new SupplierRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        String cnpjParam = request.getParameter("cnpj");
        if (cnpjParam != null && !cnpjParam.isEmpty()) {
            Supplier supplier = supplierService.getSupplier(cnpjParam);
            if (supplier != null) {
                objectMapper.writeValue(response.getWriter(), supplier);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().println("Fornecedor não encontrado");
            }
        } else {
            List<Supplier> suppliers = supplierService.getAllSuppliers();
            objectMapper.writeValue(response.getWriter(), suppliers);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        InputStream requestBody = request.getInputStream();
        Supplier supplierRequest = null;
        try {
            if (objectMapper != null) {
                supplierRequest = objectMapper.readValue(requestBody, Supplier.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (supplierRequest != null) {
            Supplier newSupplier = supplierService.addSupplier(supplierRequest);
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_CREATED);
            objectMapper.writeValue(response.getWriter(), newSupplier);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Dados do fornecedor inválidos");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cnpjParam = request.getParameter("cnpj");
        if (cnpjParam != null && !cnpjParam.isEmpty()) {
            InputStream requestBody = request.getInputStream();
            Supplier updatedSupplier = objectMapper.readValue(requestBody, Supplier.class);
            Supplier updated = supplierService.editSupplier(cnpjParam, updatedSupplier);
            if (updated != null) {
                response.setContentType("application/json");
                objectMapper.writeValue(response.getWriter(), updated);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().println("Fornecedor não encontrado");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("CNPJ do fornecedor não fornecido na URL");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cnpjParam = request.getParameter("cnpj");
        if (cnpjParam != null && !cnpjParam.isEmpty()) {
            supplierService.deleteSupplier(cnpjParam);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("CNPJ do fornecedor não fornecido na URL");
        }
    }
}