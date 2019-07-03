package cn.luo.android.quick.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Hurston
 * @version V1.0.0
 * @description
 * @createdTime 2018/9/29 9:35
 * @note:
 */
public class DatabaseObj {

    private String name;
    private String insertTime;
    private String updateTime;
    private String queryTime;
    private String deleteTime;

    public DatabaseObj(String insertTime, String updateTime, String queryTime, String deleteTime) {
        this("", insertTime, updateTime, queryTime, deleteTime);
    }

    public DatabaseObj(String name, String insertTime, String updateTime, String queryTime, String deleteTime) {
        this.name = name;
        this.insertTime = insertTime;
        this.updateTime = updateTime;
        this.queryTime = queryTime;
        this.deleteTime = deleteTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(String queryTime) {
        this.queryTime = queryTime;
    }

    public String getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(String deleteTime) {
        this.deleteTime = deleteTime;
    }

    public void setInsertTime(long insertTime) {
        this.insertTime = String.valueOf(insertTime + "ms");
    }

    public List<String> toStringList() {
        List<String> list = new ArrayList<>();
        list.add(name);
        list.add(insertTime);
        list.add(updateTime);
        list.add(queryTime);
        list.add(deleteTime);
        return list;
    }
}
