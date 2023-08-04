package com.example.docmanagemntapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

@Component
public class PostService {

    @Value("${api.url}")
    private String apiUrl;

    //public RestTemplate restTemplate;

    private final RestTemplate restTemplate = new RestTemplate();


    /*@Autowired
    @Qualifier(value = "RestTemplate")*/
   /*@Qualifier(value = "RestTemplate")
    private RestTemplate restTemplate; */

    /*public PostService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }*/

    public void createPostWithDocument(String postId, String documentFileName) {
        try {
            // Read the uploaded document file as a byte array
            String filePath = "C:/temp/" + documentFileName;
            byte[] documentData = Files.readAllBytes(Paths.get(filePath));

            // Make a POST request to the third-party API to create a new post
            String postUrl = apiUrl + "/posts";
            HttpHeaders postHeaders = new HttpHeaders();
            postHeaders.setContentType(MediaType.APPLICATION_JSON);
            // Set other necessary headers or parameters for the POST request
            HttpEntity<String> postEntity = new HttpEntity<>("{\"postId\":\"" + postId + "\", \"document\":\"" + documentData + "\"}", postHeaders);
            ResponseEntity<String> postResponse = restTemplate.exchange(postUrl, HttpMethod.POST, postEntity, String.class);
            if (postResponse.getStatusCode() == HttpStatus.CREATED) {
                System.out.println("Post created!");
            } else {
                System.out.println("Error creating post. Status code: " + postResponse.getStatusCodeValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String viewPost(String postId) {
        String responseBody = "";
        // Make a GET request to the third-party API to view a post
        String postUrl = apiUrl + "/posts/" + postId;
        ResponseEntity<String> getResponse = restTemplate.getForEntity(postUrl, String.class);
        if (getResponse.getStatusCode() == HttpStatus.OK) {
            // Process the response body and display or use the post data as needed
            responseBody = getResponse.getBody();
            System.out.println("Post data: " + responseBody);
        } else {
            System.out.println("Error retrieving post. Status code: " + getResponse.getStatusCodeValue());
        }
        return responseBody;
    }

    public void postCommentsWithDocument(String documentFileName) {
        try {
            // Read the uploaded document file as a byte array
            String filePath = "C:/temp/" + documentFileName;
            byte[] documentData = Files.readAllBytes(Paths.get(filePath));

            // Make a POST request to the third-party API to create a new post
            String postUrl = apiUrl + "/posts";
            HttpHeaders postHeaders = new HttpHeaders();
            postHeaders.setContentType(MediaType.APPLICATION_JSON);
            // Set other necessary headers or parameters for the POST request
            HttpEntity<String> postEntity = new HttpEntity<>("{\"document\":\"" + documentData + "\"}", postHeaders);
            ResponseEntity<String> postResponse = restTemplate.exchange(postUrl, HttpMethod.POST, postEntity, String.class);

            if (postResponse.getStatusCode() == HttpStatus.CREATED) {
                System.out.println("Post created!");
            } else {
                System.out.println("Error creating post. Status code: " + postResponse.getStatusCodeValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String createComments(@RequestBody String documentFileName) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(documentFileName, headers);
        //HttpEntity<String> entity = new HttpEntity<String>(documentFileName,headers);
        return restTemplate.exchange(
                "https://jsonplaceholder.typicode.com", HttpMethod.POST, entity, String.class).getBody();
    }

}
