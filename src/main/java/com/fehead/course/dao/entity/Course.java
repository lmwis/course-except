package com.fehead.course.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lmwis
 * @description:
 * @date 2019-09-02 12:17
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@TableName("user_course")
@Data
public class Course extends NoCourse{

    @TableId(type = IdType.AUTO)
    private long id;

    private String weeksText;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getWeeksText() {
        return weeksText;
    }

    public void setWeeksText(String weeksText) {
        this.weeksText = weeksText;
    }
}
