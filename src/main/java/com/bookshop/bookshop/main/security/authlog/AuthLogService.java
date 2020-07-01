package com.bookshop.bookshop.main.security.authlog;

import java.util.Date;

public interface AuthLogService {

    void createAuthlog(AuthLog authLog);
    void createAuthlogByParam(String username, String password,boolean isSuccess,  Date date);

}
