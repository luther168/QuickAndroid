package cn.luo.android.quick.entity;

/**
 * AUTHOR:       Luo
 * VERSION:      V1.0
 * DESCRIPTION:  description
 * CREATE TIME:        2018/5/31 10:39
 * NOTE:
 */
public class Signal {
    public static final int TYPE_SUCCESS = 1;
    public static final int TYPE_FAILED = -1;
    private int type;
    private String message;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
