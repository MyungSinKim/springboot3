package my.examples.webexam.controller;

import my.examples.webexam.dto.Board;
import my.examples.webexam.dto.BoardList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/boards")
public class BoardApiController {
    // /api/boards/100
    @GetMapping(path = "/{id}")
    public ResponseEntity<Board> getBoard(@PathVariable(name = "id") long id){
        Board board = Board.builder().name("kim").content("content2").title("title2").build();
        board.setId(id);

        return new ResponseEntity<>(board, HttpStatus.OK);
    }

//    @GetMapping
//    public List<Board> getBoards(){
//        Board board2 = Board.builder().name("kim").content("content2").title("title2").build();
//        List<Board> list = Arrays.asList(new Board("title1", "kim", "content1"), board2, new Board("title3", "kang", "content3"));
//
//        return list;
//    }

    // http://localhost:8080/api/boards?format=xml
    // http://localhost:8080/api/boards?format=json
    @GetMapping
    public BoardList getBoards(){
        Board board2 = Board.builder().name("kim").content("content2").title("title2").build();
        List<Board> list = Arrays.asList(new Board("title1", "kim", "content1"), board2, new Board("title3", "kang", "content3"));

        return new BoardList(list);
    }


}
