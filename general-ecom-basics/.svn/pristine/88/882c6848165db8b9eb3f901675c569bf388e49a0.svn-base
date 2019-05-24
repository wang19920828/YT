package org.fh.general.ecom.basics.service.impl;

import org.fh.general.ecom.basics.model.StudentMongo;
import org.fh.general.ecom.basics.service.StudentMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("studentMongoService")
public class StudentMongoServiceImpl implements StudentMongoService {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 保存一个Student信息到student集合里
     *
     * @param stu
     */
    public void save(StudentMongo stu) {
        try {
            mongoTemplate.save(stu, "student");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    /**
     * 查询所有的Student信息
     *
     * @return
     */
    public List<StudentMongo> findAll() {
        return mongoTemplate.findAll(StudentMongo.class, "student");
    }

    /**
     * 通过Student.name查询document
     *
     * @param name
     * @return
     */
    public List<StudentMongo> findByName(String name) {
        List<StudentMongo> list = null;
        try {
            Query query = new Query(Criteria.where("name").is(name));
            list = mongoTemplate.find(query, StudentMongo.class, "student");
        } catch (Exception e) {
            // TODO: handle exception
        }
        return list;
    }

    /**
     * 通过Student.StudentScore.des查询document
     *
     * @param des
     * @return
     */
    public List<StudentMongo> findByStudentScoreDes(String des) {
        List<StudentMongo> list = null;
        try {
            Query query = new Query(Criteria.where("studentScore.des").is(des));
//                query.with(new Sort(Sort.Direction.DESC,   "createTime")); //排序

            list = mongoTemplate.find(query, StudentMongo.class, "student");
        } catch (Exception e) {
            // TODO: handle exception
        }
        return list;
    }


    /**
     * 通过Student.name和Student.des查询document
     *
     * @param name
     * @param des
     * @return
     */
    public List<StudentMongo> searchByNameAndDes(String name, String des) {
        List<StudentMongo> list = null;
        try {
            Query query = new Query(Criteria.where("name").is(name).and("des").is(des));
            list = mongoTemplate.find(query, StudentMongo.class, "student");
        } catch (Exception e) {
            // TODO: handle exception
        }
        return list;
    }

    /**
     * 通过Student.name和Student.Student.des查询document
     *
     * @param name
     * @param des
     * @return
     */
    public List<StudentMongo> searchByNameAndStudentScoreDes(String name, String des) {
        List<StudentMongo> list = null;
        try {
            Query query = new Query(Criteria.where("name").is(name).and("studentScore.des").is(des));
            list = mongoTemplate.find(query, StudentMongo.class, "student");
        } catch (Exception e) {
            // TODO: handle exception
        }
        return list;
    }

    /**
     * 更新
     *
     * @param stu
     */
    public void update(StudentMongo stu) {
        try {
            Query query = new Query(Criteria.where("name").is(stu.getName()));
            Update update = new Update().set("des", stu.getDes()).set("studentScore.des", stu.getStudentScoreMongo().getDes());
            //更新查询返回结果集的第一条
            mongoTemplate.updateFirst(query, update, StudentMongo.class, "student");
            //更新查询返回结果集的所有
            // mongoTemplate.updateMulti(query,update,Student.class);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    /**
     * 删除
     *
     * @param name
     */
    public void remove(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        mongoTemplate.remove(query, StudentMongo.class, "student");
    }
}
