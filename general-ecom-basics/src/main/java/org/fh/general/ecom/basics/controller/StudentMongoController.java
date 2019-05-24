package org.fh.general.ecom.basics.controller;

import org.fh.general.ecom.basics.model.StudentMongo;
import org.fh.general.ecom.basics.model.StudentScoreMongo;
import org.fh.general.ecom.basics.service.StudentMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/mongo")
public class StudentMongoController {

    @Autowired
    private StudentMongoService studentMongoService;



    /**
     * 向mongondb添加一个document对象
     */
    @RequestMapping("/add")
    public void add() {
        StudentMongo student=new StudentMongo();
        student.setName("hzb");
        student.setSex("man");
        student.setAge(31);
        student.setDes("hzb_father");
        StudentScoreMongo score=new StudentScoreMongo();
        score.setChinese("88");
        score.setEnglish("93");
        score.setDes("hzb_child");
        student.setStudentScoreMongo(score);

        StudentMongo student1=new StudentMongo();
        student1.setName("xiaweihu");
        student1.setSex("man");
        student1.setAge(31);
        student1.setDes("xiaweihu_father");
        StudentScoreMongo score1=new StudentScoreMongo();
        score1.setChinese("66");
        score1.setEnglish("54");
        score1.setDes("xiaweihu_child");
        student1.setStudentScoreMongo(score1);

        StudentMongo student2=new StudentMongo();
        student2.setName("hzb");
        student2.setSex("man");
        student2.setAge(31);
        student2.setDes("hzb_father");
        StudentScoreMongo score2=new StudentScoreMongo();
        score2.setChinese("77");
        score2.setEnglish("99");
        score2.setDes("hzb_child2");
        student2.setStudentScoreMongo(score2);
        studentMongoService.save(student);
        studentMongoService.save(student1);
        studentMongoService.save(student2);
    }

    /**
     * 查询mongodb当中的所有document
     * @return
     */
    @RequestMapping("/findAll")
    public List<StudentMongo> findAll() {
        List<StudentMongo> list= studentMongoService.findAll();
        return list;
    }
    /**
     * 通过名字查询document
     * @return
     */
    @GetMapping("/findByName")
    public List<StudentMongo> findByName() {
        List<StudentMongo> student=studentMongoService.findByName("hzb");
        return student;
    }



    /**
     * 通过Student.Student.des查询document
     * @param des
     * @return
     */
    @RequestMapping("/findByStudentScoreDes")
    public List<StudentMongo> findByStudentScore_Des(String des){
        List<StudentMongo> student=studentMongoService.findByStudentScoreDes(des);
        return student;
    }

    /**
     * 通过Student.name和Student.des查询document
     * @param des
     * @return
     */
    @RequestMapping("/findByNameAndDes")
    public List<StudentMongo> findByNameAndDes(String des){
        List<StudentMongo> student=studentMongoService.searchByNameAndDes("hzb","hzb_father");
        return student;
    }

    /**
     * 通过Student.name和Student.Student.des查询document
     * @param des
     * @return
     */
    @RequestMapping("/findByNameAndStudentScoreDes")
    public List<StudentMongo> findByNameAndStudentScoreDes(String des){
        List<StudentMongo> student=studentMongoService.searchByNameAndStudentScoreDes("hzb","hzb_child2");
        return student;
    }

    /**
     * 更新document
     */
    @RequestMapping("/updateByName")
    public void ubdateByName(){
        List<StudentMongo> students=studentMongoService.findByName("xiaweihu");
        StudentMongo student=students.get(0);
        student.setDes("aaaaaaaaaaaaaaaaaaaa");
        student.getStudentScoreMongo().setDes("bbbbbbbbbbbbbbbbbbbbb");
        studentMongoService.update(student);
    }

    /**
     * 删除document
     */
    @RequestMapping("/deleteByName")
    public void deleteByName(){
        studentMongoService.remove("xiaweihu");
    }

}