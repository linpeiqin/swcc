package cn.zf.swc.swcc.setinfo.util;

import cn.zf.swc.swcc.setinfo.vo.SetStatus;

import java.util.List;

public class Singleton {
    private static class SingletonHolder {
        private static Singleton instance = new Singleton();
    }
    private Singleton() {
    }
    public static Singleton getInstance() {
        return SingletonHolder.instance;
    }
    private static List<SetStatus> setStatusList;
    public static List<SetStatus> getSetStatusList() {
        return  setStatusList;
    }

    public static void setSetStatusList(List<SetStatus> setStatusList) {
        Singleton.setStatusList = setStatusList;
    }
}
