package com.supermap.aide;

/**
 * 运行时常量
 *
 * @author leishiguang
 * @date 2019/04/05
 */
public class RuntimeConst {
    public enum Profiles {
        /*定义了可用的环境配置*/
        dev("开发环境");

        public final String value;

        Profiles(String chinese) {
            this.value = chinese;
        }

        /*Profiles valueof(String name) {
            Profiles[] profilesList = Profiles.values();
            for (Profiles profile : profilesList) {
                if (profile.name().equals(name)) {
                    return profile;
                }
            }
            return null;
        }*/
    }

}
