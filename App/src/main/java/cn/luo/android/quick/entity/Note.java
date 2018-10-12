package cn.luo.android.quick.entity;

import java.util.Date;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * @author Hurston
 * @version V1.0.0
 * @description
 * @createdTime 2018/9/28 17:10
 * @note:
 */
@Entity
public class Note {
    @Id public long id;
    public String content;
    public Date createdTime;
    public Date modifiedTime;

    public Note() {
    }

    public Note(Note source) {
        Note target = new Note();
        target.content = source.content;
        target.createdTime = source.createdTime;
        target.modifiedTime = source.modifiedTime;
    }
}
