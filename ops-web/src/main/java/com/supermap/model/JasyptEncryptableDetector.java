package com.supermap.model;

import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyDetector;
import lombok.Data;

/**
 * 自定义密文前后的关键词
 */
@Data
public class JasyptEncryptableDetector implements EncryptablePropertyDetector {
    /**
     * 密文前缀
     */
    private final static String PREFIX = "OPTENC(";
    /**
     * 密文后缀
     */
    private final static String SUFFIX = ")";

    @Override
    public boolean isEncrypted(String property) {
        if (property == null) {
            return false;
        } else {
            String trimmedValue = property.trim();
            return trimmedValue.startsWith(PREFIX) && trimmedValue.endsWith(SUFFIX);
        }
    }

    @Override
    public String unwrapEncryptedValue(String property) {
        return property.substring(PREFIX.length(), property.length() - SUFFIX.length());
    }

    /**
     * 返回格式化的加密值
     */
    public String wrapEncryptedValue(String unwrapdEncryptedValue){
        if(isEncrypted(unwrapdEncryptedValue)){
            return unwrapdEncryptedValue;
        }else {
            return PREFIX + unwrapdEncryptedValue + SUFFIX;
        }
    }

}