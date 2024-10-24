package com.example.pdfgenerator.service;

import com.example.pdfgenerator.model.PdfRequest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PdfServiceTest {

    @Mock
    private TemplateEngine templateEngine;

    @InjectMocks
    private PdfService pdfService;

    @Test
    void testGeneratePdf() throws IOException {

        PdfRequest pdfRequest = new PdfRequest();
        pdfRequest.setSeller("XYZ Pvt. Ltd.");
        pdfRequest.setSellerGstin("29AABBCCDD121ZD");
        pdfRequest.setSellerAddress("New Delhi, India");
        pdfRequest.setBuyer("Vedant Computers");
        pdfRequest.setBuyerGstin("29AABBCCDD131ZD");
        pdfRequest.setBuyerAddress("New Delhi, India");
        pdfRequest.setItems(Collections.singletonList(new PdfRequest.Item() {{
            setName("Product 1");
            setQuantity("12 Nos");
            setRate(123.00);
            setAmount(1476.00);
        }}));

        when(templateEngine.process("pdf-template", new Context())).thenReturn("<html>...</html>");

        String fileName = pdfService.generatePdf(pdfRequest);
        File generatedFile = new File("./generated-pdfs/" + fileName);
        
        assertTrue(generatedFile.exists(), "The PDF file should be created.");
        
        if (generatedFile.exists()) {
            Files.delete(generatedFile.toPath());
        }
    }
}
