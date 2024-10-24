package com.example.pdfgenerator.controller;

import com.example.pdfgenerator.model.PdfRequest;
import com.example.pdfgenerator.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/pdf")
public class PdfController {

    @Autowired
    private PdfService pdfService;

    @PostMapping("/generate")
    public ResponseEntity<String> generatePdf(@RequestBody PdfRequest pdfRequest) {
        try {
            String fileName = pdfService.generatePdf(pdfRequest);
            return ResponseEntity.ok(fileName);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error generating PDF: " + e.getMessage());
        }
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<?> downloadPdf(@PathVariable String fileName) {
        File file = new File("./generated-pdfs/" + fileName);
        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(file);
    }
}
