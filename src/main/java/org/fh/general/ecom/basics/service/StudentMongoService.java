package org.fh.general.ecom.basics.service;

import org.fh.general.ecom.basics.model.StudentMongo;

import java.util.List;

public interface StudentMongoService {

    /**
     * 保存一个Student信息到student集合里
     *
     * @param stu
     */
    void save(StudentMongo stu);

    List<StudentMongo> findAll();

    List<StudentMongo> findByName(String name);

    List<StudentMongo> findByStudentScoreDes(String des);

    /**
     * 通过Student.name和Student.des查询document
     *
     * @param name
     * @param des
     * @return
     */
    List<StudentMongo> searchByNameAndDes(String name, String des);

    /**
     * 通过Student.name和Student.Student.des查询document
     *
     * @param name
     * @param des
     * @return
     */
    List<StudentMongo> searchByNameAndStudentScoreDes(String name, String des);

    /**
     * 更新
     *
     * @param stu
     */
    void update(StudentMongo stu);

    /**
     * 删除
     *
     * @param name
     */
    void remove(String name);


}
