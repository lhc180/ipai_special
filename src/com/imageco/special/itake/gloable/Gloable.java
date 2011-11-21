package com.imageco.special.itake.gloable;

/**
 * Created by IntelliJ IDEA.
 * User: OYQX
 * Date: 11-11-4
 * Time: 上午11:21
 */
public class Gloable {
    private String direction;//手势方向

    private Boolean newHistory=Boolean.TRUE;//历史是否新开

    private Boolean needUpdate;//是否升级
    private String downPath;//下载地址
    private Boolean isShow;//是否提示
    private String apkRemark;//更新内容
    private String apkVersion;//版本号

    public Boolean getNeedUpdate() {
        return needUpdate;
    }

    public void setNeedUpdate(Boolean needUpdate) {
        this.needUpdate = needUpdate;
    }

    private static Gloable INSTANCE = null;

    public static Gloable getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Gloable();
        }
        return INSTANCE;
    }

    public String getDownPath() {
        return downPath;
    }

    public void setDownPath(String downPath) {
        this.downPath = downPath;
    }

    public Boolean getShow() {
        return isShow;
    }

    public void setShow(Boolean show) {
        isShow = show;
    }

    public String getApkRemark()
    {
        return apkRemark;
    }

    public void setApkRemark(String apkRemark)
    {
        this.apkRemark = apkRemark;
    }

    public String getApkVersion()
    {
        return apkVersion;
    }

    public void setApkVersion(String apkVersion)
    {
        this.apkVersion = apkVersion;
    }

    public String getDirection()
    {
        return direction;
    }

    public void setDirection(String direction)
    {
        this.direction = direction;
    }

    public Boolean getNewHistory()
    {
        return newHistory;
    }

    public void setNewHistory(Boolean newHistory)
    {
        this.newHistory = newHistory;
    }
}
