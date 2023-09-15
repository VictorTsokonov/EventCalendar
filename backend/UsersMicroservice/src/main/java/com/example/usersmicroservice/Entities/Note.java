package com.example.usersmicroservice.Entities;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import lombok.Data;

import java.util.Date;

@Data
public class Note {

    @DynamoDBAttribute(attributeName = "eventId")
    private String eventId;

    @DynamoDBAttribute(attributeName = "text")
    private String text;

    // You might also want to include a timestamp or other metadata here

    @DynamoDBAttribute(attributeName = "timestamp")
    private Date timestamp;
}
