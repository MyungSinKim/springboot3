package soundsystem;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class UI {
    private BufferedReader br;
    public UI(){
        br = new BufferedReader(new InputStreamReader(System.in));
    }
    public int main(){
        System.out.println("1. 입력");
        System.out.println("2. 조회");
        System.out.println("3. 종료");
        return getInt();
    }

    public Student input(){
        System.out.println("이름 : ");
        String name = getLine();
        System.out.println("국어 : ");
        int kor = getInt();
        System.out.println("영어 : ");
        int eng = getInt();
        System.out.println("수학 : ");
        int math = getInt();
        Student student = new Student();
        student.setName(name);
        student.setKor(kor);
        student.setEng(eng);
        student.setMath(math);
        return student;
    }

    public void list(List<Student> studentList){
        for(Student student :studentList){
            System.out.println(student);
        }
    }

    public void end(){
        System.out.println("종료하시겠습니까? (y/n)");
        String str = getLine();
        if("y".equals(str)){
            System.exit(0);
        }
    }

    private String getLine(){
        try {
            return br.readLine();
        }catch(Exception ex){
            return null;
        }
    }

    private int getInt(){
        return Integer.parseInt(getLine());
    }
}
