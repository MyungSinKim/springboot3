package my.examples.webexam.component;

import my.examples.webexam.dto.Board;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

// BeanNameViewResolver
@Component("csvView")
public class ReportCsvView implements View {

    @Override
    public String getContentType() {
        return "text/csv";
    }

    @Override
    public void render(Map<String, ?> map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        PrintWriter out = httpServletResponse.getWriter();
        List<Board> boards = (List<Board>)map.get("boards");

        for(Board board : boards){
            out.println(board.getReadCount() + "," + board.getTitle() + "," + board.getName());
        }

        out.close();
    }

}