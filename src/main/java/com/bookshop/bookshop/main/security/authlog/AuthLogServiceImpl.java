package com.bookshop.bookshop.main.security.authlog;

import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthLogServiceImpl implements AuthLogService {

    @Autowired
    AuthLogRepository authLogRepository;


    @Override
    public void createAuthlog(AuthLog authLog) {

        authLogRepository.save(authLog);
    }

    @Override
    public void createAuthlogByParam(String username, String password, boolean isSuccess, Date date) {
        AuthLog authLog = new AuthLog();
        authLog.setUsername(username);
        authLog.setPassword(password);
        authLog.setSuccess(isSuccess);
        authLog.setCreated(date);

        authLogRepository.save(authLog);
    }
}
