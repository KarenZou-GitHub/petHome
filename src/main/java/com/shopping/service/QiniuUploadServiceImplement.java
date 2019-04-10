package com.shopping.service;

import com.qiniu.util.Auth;
import org.springframework.stereotype.Service;

@Service
public class QiniuUploadServiceImplement implements QiniuUploadService {
    // 空间密钥
    String ACCESS_KEY = "bAp4aQFzDzrH3hzpbZ_ySsiLjlZ8z2lc14PJBN3E";
    String SECRET_KEY = "QMvUW5lMSlbB0xvs4rLHZqg6PJtG3kGfdaw2ywBP";
    // 空间名
    String bucketname = "pet-shopping";
    // 密钥配置
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

    // 简单上传，使用默认策略，只需要设置上传的空间名就可以了
    @Override
    public String getUpToken() {
        return auth.uploadToken(bucketname);
    }
}
