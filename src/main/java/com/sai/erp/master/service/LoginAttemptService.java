package com.sai.erp.master.service;

import com.sai.erp.master.dao.SsVehStockLoginDao;
import com.sai.erp.master.entity.SsVehStockLogin;
import java.time.Duration;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginAttemptService {

    private static final int MAX_FAILED_ATTEMPTS = 5;
    private static final long LOCK_TIME_DURATION_MINUTES = 10;

    @Autowired
    private SsVehStockLoginDao loginRepo;

    public boolean isLocked(SsVehStockLogin user) {
        if (user.getLockTime() == null) return false;

        LocalDateTime unlockTime = user.getLockTime().plusMinutes(LOCK_TIME_DURATION_MINUTES);
        if (LocalDateTime.now().isAfter(unlockTime)) {
            user.setLockTime(null);
            user.setFailedAttempts(0);
            loginRepo.save(user);
            return false;
        }
        return true;
    }

    public long getRemainingLockSeconds(SsVehStockLogin user) {
        if (user.getLockTime() == null) return 0;

        LocalDateTime unlockTime = user.getLockTime().plusMinutes(LOCK_TIME_DURATION_MINUTES);
        Duration remaining = Duration.between(LocalDateTime.now(), unlockTime);
        return Math.max(0, remaining.getSeconds());
    }

    public int getRemainingAttempts(SsVehStockLogin user) {
        int failed = user.getFailedAttempts() != null ? user.getFailedAttempts() : 0;
        return Math.max(0, MAX_FAILED_ATTEMPTS - failed);
    }

    public void loginFailed(SsVehStockLogin user) {
        int newAttempts = user.getFailedAttempts() != null ? user.getFailedAttempts() + 1 : 1;
        user.setFailedAttempts(newAttempts);
        if (newAttempts >= MAX_FAILED_ATTEMPTS) {
            user.setLockTime(LocalDateTime.now());
        }
        loginRepo.save(user);
    }

    public void loginSucceeded(SsVehStockLogin user) {
        user.setFailedAttempts(0);
        user.setLockTime(null);
        loginRepo.save(user);
    }
}
