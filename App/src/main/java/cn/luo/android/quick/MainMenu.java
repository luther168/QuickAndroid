package cn.luo.android.quick;

/**
 * @author Luo
 * @version V1.0
 * @description description
 * @createTime 2019/6/29 0:28
 * @note
 */
public class MainMenu {

    private String title;
    private String path;

    public MainMenu(String title, String path) {
        this.title = title;
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
