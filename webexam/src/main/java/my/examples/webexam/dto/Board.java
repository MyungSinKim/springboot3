package my.examples.webexam.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@JacksonXmlRootElement(localName  = "BoardData")
@Data
public class Board {
    private Long id;
    private String title;
    private String name;
    private String content;
    private LocalDateTime createDate;
    private int readCount;

    @Builder
    public Board(String title, String name, String content) {
        this.title = title;
        this.name = name;
        this.content = content;
        this.createDate = LocalDateTime.now();
        this.readCount = 0;
    }
}
