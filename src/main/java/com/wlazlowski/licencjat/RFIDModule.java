package com.wlazlowski.licencjat;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;

//@Document(collection = "rfidmodule")
public class RFIDModule {

    @Id
    private String id;

    private String uid;
    private String timestamp;
    private String place;
    private Long time;

    public String getTimestamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.toString();
    }

    public String getId() {
        return id;
    }

    public String getUid() {
        return uid;
    }

    public String getPlace() {
        return place;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Long getTime() {

        return time;
    }
}
