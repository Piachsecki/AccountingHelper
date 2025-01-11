package com.piasecki.exception;

public class InvoiceNotFoundException extends RuntimeException{
    public InvoiceNotFoundException(String invoiceNumber) {
        super("Invoice not found for invoice number: " + invoiceNumber);
    }
}
