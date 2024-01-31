package com.wup.utils;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

/**
 * @ClassName : SHA256  //类名
 * @Description :   //描述
 * @Author : brainwu //作者
 * @Date: 2023/12/13  22:49
 */
public class SHA256 {
    public static String encode(String content) {
        Digester sha256 = new Digester(DigestAlgorithm.SHA256);
        return sha256.digestHex(content, CharsetUtil.CHARSET_UTF_8);
    }
}
