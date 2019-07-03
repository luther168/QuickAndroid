package cn.luo.android.quick.entity;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * @author Hurston
 * @version V1.0.0
 * @description
 * @createdTime 2018/9/28 17:08
 * @note:
 */
@Entity
public class Tag {
    @Id public long id;
    public String name;
}
