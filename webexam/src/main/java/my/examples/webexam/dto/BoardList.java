package my.examples.webexam.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BoardList {

    @JacksonXmlElementWrapper(localName = "Boards")
    @JacksonXmlProperty(localName = "Board")
    private List<Board> boards;
}
