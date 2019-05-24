package org.fh.general.ecom.basics.model;

import lombok.Data;

@Data
public class StudentMongo {
      private String name;
      private String sex;
      private Integer age;
      private String des;
      private StudentScoreMongo studentScoreMongo;
}
