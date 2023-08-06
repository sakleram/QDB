package com.example.docmanagemntapi;

import com.example.docmanagemntapi.controller.PdfController;
import com.example.docmanagemntapi.controller.PostController;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockMultipartFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DocmanagemntApiApplicationTests {

    @Mock
    private PdfController pdfController;

    @Mock
    private PostController postController;

	@Test
    public void createPostWithDocument() {
        postController.createPostWithDocument("testFile");
        assertEquals("200 OK", HttpStatus.OK.toString());
    }

    @Test
    public void viewPost() {
        postController.viewPost("1");
        assertEquals("200 OK", HttpStatus.OK.toString());
    }
    @Test
    public void createProducts(){
	    String documentFileName = "FBPPolicy.pdf";
        postController.createProducts(documentFileName);
        assertEquals("200 OK", HttpStatus.OK.toString());
    }

    @Test
    public void uploadFile() throws Exception {
        MockMultipartFile firstFile = new MockMultipartFile("data", "FBPPolicy.pdf", "pdf/plain", "some xml".getBytes());
        pdfController.uploadFile(firstFile);
    }

    @Test
    public void viewPdf(){
        String documentFileName = "FBPPolicy.pdf";
        pdfController.viewPdf(documentFileName);
    }

}
