package com.supermap.util;

import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;

/**
 * 创建新文件的一些
 *
 * @author leishiguang
 * @date 2019/04/03
 */
@Slf4j
public class CreateFileUtil {

    /**
     * 创建文件，如果目录不存在会自动创建目录
     *
     * @param destFileName 路径+文件名
     * @return 是否成功
     */
    public static boolean createFile(String destFileName) {
        File file = new File(destFileName);
        if (file.exists()) {
            log.error("创建单个文件" + destFileName + "失败，目标文件已存在！");
            return false;
        }
        if (destFileName.endsWith(File.separator)) {
            log.error("创建单个文件" + destFileName + "失败，目标文件不能为目录！");
            return false;
        }
        //判断目标文件所在的目录是否存在
        if (!file.getParentFile().exists()) {
            //如果目标文件所在的目录不存在，则创建父目录
            log.debug("目标文件所在目录不存在，准备创建它！");
            if (!file.getParentFile().mkdirs()) {
                log.error("创建目标文件所在目录失败！");
                return false;
            }
        }
        //创建目标文件
        try {
            if (file.createNewFile()) {
                log.debug("创建单个文件" + destFileName + "成功！");
                return true;
            } else {
                log.error("创建单个文件" + destFileName + "失败！");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error("创建单个文件" + destFileName + "失败！" + e.getMessage());
            return false;
        }
    }


    /**
     * 创建路径
     *
     * @param destDirName 路径名称
     * @return 是否成功
     */
    public static boolean createDir(String destDirName) {
        File dir = new File(destDirName);
        if (dir.exists()) {
            log.error("创建目录" + destDirName + "失败，目标目录已经存在");
            return false;
        }
        if (!destDirName.endsWith(File.separator)) {
            destDirName = destDirName + File.separator;
        }
        //创建目录
        if (dir.mkdirs()) {
            log.debug("创建目录" + destDirName + "成功！");
            return true;
        } else {
            log.error("创建目录" + destDirName + "失败！");
            return false;
        }
    }


    /**
     * 创建临时文件
     *
     * @param prefix  前缀
     * @param suffix  格式
     * @param dirName 目录
     * @return 创建的文件路径与名称
     */
    public static String createTempFile(String prefix, String suffix, String dirName) {
        File tempFile;
        if (dirName == null) {
            try {
                //在默认文件夹下创建临时文件
                tempFile = File.createTempFile(prefix, suffix);
                //返回临时文件的路径
                return tempFile.getCanonicalPath();
            } catch (IOException e) {
                e.printStackTrace();
                log.error("创建临时文件失败！" + e.getMessage());
                return null;
            }
        } else {
            File dir = new File(dirName);
            //如果临时文件所在目录不存在，首先创建
            if (!dir.exists()) {
                if (!CreateFileUtil.createDir(dirName)) {
                    log.error("创建临时文件失败，不能创建临时文件所在的目录！");
                    return null;
                }
            }
            try {
                //在指定目录下创建临时文件
                tempFile = File.createTempFile(prefix, suffix, dir);
                return tempFile.getCanonicalPath();
            } catch (IOException e) {
                e.printStackTrace();
                log.error("创建临时文件失败！" + e.getMessage());
                return null;
            }
        }
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     *
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     * If a deletion fails, the method stops attempting to
     * delete and returns "false".
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            if (children != null) {
                for (String child : children) {
                    boolean success = deleteDir(new File(dir, child));
                    if (!success) {
                        return false;
                    }
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

}
