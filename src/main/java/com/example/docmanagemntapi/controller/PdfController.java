package com.example.docmanagemntapi.controller;

import com.example.docmanagemntapi.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;

@RestController
public class PdfController {

    @Autowired
    public FileStorageService fileStorageService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            fileStorageService.uploadPdfFile(file);
            return ResponseEntity.ok("File uploaded successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/view/{fileName}")
    public ResponseEntity<byte[]> viewPdf(@PathVariable String fileName) {
        try {
            fileStorageService.viewPdf(fileName);
            // Load the PDF document from the specified file path
            String filePath = "C:/temp/" + fileName;
            PDDocument document = PDDocument.load(new File(filePath));

            // Convert the PDF document into a byte array
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            document.save(baos);
            document.close();
            fileStorageService.viewPdf(fileName);
            // Set the appropriate headers for the response
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData(fileName, fileName);
            return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
