package com.gresmer.school.crud.gresmerschoolcrud.controllers;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/s3")
public class sthreeController {

    String key;
    String secret;
    AWSCredentials credentials;
    AmazonS3 s3;


    @Autowired
    public sthreeController(Environment env) {
        key = env.getProperty("aws_user_key");
        secret = env.getProperty("aws_user_secret_key");
        System.out.println("KEY ::::::::::::::: " + key);
        System.out.println("secret ::::::::::::::: " + secret);


        if (key != null && secret != null) {
            credentials = new BasicAWSCredentials(key, secret);

            s3 = AmazonS3ClientBuilder
                        .standard()
                        .withCredentials(new AWSStaticCredentialsProvider(credentials))
                        .withRegion(Regions.US_EAST_1)
                        .build();
        }
    }


    @PostMapping("/create/{name}")
    ResponseEntity<String> createBucket(@PathVariable("name") String name) {
        if (s3 == null) return ResponseEntity.badRequest().body("No credentials");

        if (s3.doesBucketExist(name)) {
            return ResponseEntity.badRequest().body("A bucket with that name (" + name + ") already exists.");
        }

        try {
            s3.createBucket(name);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.unprocessableEntity().body("Something went wrong when creating that bucket.");
        }


        return ResponseEntity.accepted().body("Your bucket has been created! " + name);
    }
}
