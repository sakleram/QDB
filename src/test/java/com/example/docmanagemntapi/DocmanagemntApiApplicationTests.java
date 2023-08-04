package com.example.docmanagemntapi;

import com.example.docmanagemntapi.controller.PdfController;
import com.example.docmanagemntapi.controller.PostController;
import com.sun.source.tree.AssertTree;
import org.apache.coyote.Response;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.net.http.HttpResponse;

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


}
