package soundsystem;

import java.util.ArrayList;
import java.util.List;

public class Control {
    UI ui;
    List<Student> studentList = new ArrayList<>();

    public void setUI(UI ui){
        this.ui = ui;
    }

    public void control(){
        while(true){
            int value = ui.main();
            switch(value){
                case 1 :
                    Student student = ui.input();
                    studentList.add(student);
                    break;
                case 2 :
                    ui.list(studentList);
                    break;
                case 3 :
                    ui.end();
            }
        }
    } // control
}
