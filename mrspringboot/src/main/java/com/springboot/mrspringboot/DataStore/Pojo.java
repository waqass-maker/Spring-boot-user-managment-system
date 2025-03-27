package com.springboot.mrspringboot.DataStore;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "Pojo")
@Data
@NoArgsConstructor
public class Pojo {
    @Id
    private ObjectId id;
    private String title;
    private String des;
    private LocalDateTime date;

   }
