package my.examples.webexam.controller;

import my.examples.webexam.dto.Board;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/boards")
public class BoardController {

    @GetMapping
    public String list(HttpSession session, ModelMap modelMap){
        Board board2 = Board.builder().name("kim").content("content2").title("title2").build();
        List<Board> list = Arrays.asList(new Board("title1", "kim", "content1"), board2, new Board("title3", "kang", "content3"));

        session.setAttribute("sessionValue", "hello session");
        modelMap.addAttribute("test", "test data\ntest data1\ntest data1\ntest data1\ntest data1\ntest data1\ntest data1\n");
        modelMap.addAttribute("count", 100);
        modelMap.addAttribute("list", list);
        return "list";
    }

    @GetMapping("/download")
    public void download(HttpServletResponse response){
        Board board2 = Board.builder().name("kim").content("content2").title("title2").build();
        List<Board> list = Arrays.asList(new Board("title1", "kim", "content1"), board2, new Board("title3", "kang", "content3"));

        // 메모리에 100개의 행을 유지합니다. 행의 수가 넘으면 디스크에 적습니다.
        SXSSFWorkbook wb = new SXSSFWorkbook(100);
        Sheet sheet = wb.createSheet();

        try {
            int i = 0;
            for(Board board: list) {
                Row row = sheet.createRow(i++);
                Cell cell = null;
                cell = row.createCell(0);
                cell.setCellValue(board.getReadCount() + "");
                cell = row.createCell(1);
                cell.setCellValue(board.getTitle());
                cell = row.createCell(2);
                cell.setCellValue(board.getName());
            }

            response.setHeader("Set-Cookie", "fileDownload=true; path=/");
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"boards.xlsx\""));
            wb.write(response.getOutputStream());


        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            wb.dispose(); // 임시 파일 제거
            try { wb.close(); } catch(Exception ignore) {}
        }
    }


    // /boards?format=csv 요청은 text/csv
    @GetMapping(params = "format=csv")
    public String getBoardsToXls(Model model){
        Board board2 = Board.builder().name("kim").content("content2").title("title2").build();
        List<Board> list = Arrays.asList(new Board("title1", "kim", "content1"), board2, new Board("title3", "kang", "content3"));

        model.addAttribute("boards", list);
        return "csvView";
    }

}
