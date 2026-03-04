package com.interview.domain.com.interview.pr;

import java.util.HashMap;
import java.util.Map;

public class Test03 {

    private final StringBuilder loginAuditLog = new StringBuilder();

    private final Map<String, Integer> userLogins = new HashMap<>();

    public void recordLogin(String userId) {
        // 3. Atomicity Issue: Check-then-act
        Integer count = userLogins.get(userId);
        userLogins.put(userId, (count == null) ? 1 : count + 1);

        loginAuditLog.append("User: ")
                .append(userId)
                .append(" logged in at ")
                .append(System.currentTimeMillis())
                .append("\n");
    }

    public String getFullAudit() {
        return loginAuditLog.toString();
    }
}