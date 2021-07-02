package com.jeanpandolfi.documentoservice.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MinioProperties {

    private String url;
    private String accessKey;
    private String secretKey;
    private String bucketName;

}
