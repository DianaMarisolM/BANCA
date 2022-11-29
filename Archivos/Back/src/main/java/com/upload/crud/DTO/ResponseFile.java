package com.upload.crud.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseFile {
    private String name;
    private String url;
    private String type;
    private long size;
    public ResponseFile(String url) {
        this.url = url;
    }

}
