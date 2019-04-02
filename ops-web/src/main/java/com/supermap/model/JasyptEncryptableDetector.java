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
    private final String prefix = "OPTENC(";
    /**
     * 密文后缀
     */
    private final String suffix = ")";

    @Override
    public boolean isEncrypted(String property) {
        if (property == null) {
            return false;
        } else {
            String trimmedValue = property.trim();
            return trimmedValue.startsWith(this.prefix) && trimmedValue.endsWith(this.suffix);
        }
    }

    @Override
    public String unwrapEncryptedValue(String property) {
        return property.substring(this.prefix.length(), property.length() - this.suffix.length());
    }

    /**
     * 返回格式化的加密值
     */
    public String wrapEncryptedValue(String unwrapdEncryptedValue){
        if(isEncrypted(unwrapdEncryptedValue)){
            return unwrapdEncryptedValue;
        }else {
            return this.prefix + unwrapdEncryptedValue + this.suffix;
        }
    }

}