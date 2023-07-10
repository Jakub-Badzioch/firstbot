package com.armiabotow.firstbot.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {

    @Bean
    public AmazonS3 amazonS3() {
        return AmazonS3ClientBuilder.standard()
                .withCredentials(
                        new AWSStaticCredentialsProvider(
                                new BasicAWSCredentials("AKIATCGHOYBWOTWIF24J", "oQgpBFjGBA91shtST/51w8GTYIL872iTir9pWLIO")
                        )
                )
                .withRegion(Regions.US_EAST_1).build();
    }

    // todo usuwam role na 3 i sprawdzam czy pobralo az ogarne ktora to byla rola
}
