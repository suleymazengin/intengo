package com.intengo.intengo.domain;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@Entity
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    private String fileName;
    private String path;
    private Long size;
    private String type;

    @Lob
    private byte[] data;


}
