package com.greenfox.tomdreidel.chatapp.service;

import com.greenfox.tomdreidel.chatapp.model.LogEntry;
import com.greenfox.tomdreidel.chatapp.repository.LogRepository;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {

  @Autowired
  LogRepository logRepository;

  public void addLog(HttpServletRequest request, String status) {
    String paramBuilder = "";
    Enumeration<String> parameterNames = request.getParameterNames();
    while (parameterNames.hasMoreElements()) {
      String tempName = parameterNames.nextElement();
      String tempValue = request.getParameterValues(tempName)[0];
      paramBuilder += tempName + "=" + tempValue + "; ";
    }

    String chatAppLoglevel = System.getenv("CHAT_APP_LOGLEVEL");
    LogEntry logResult = new LogEntry(chatAppLoglevel, request.getServletPath(), request.getMethod(), request.getRemoteAddr(), paramBuilder);

    if (status.equals("ERROR") || chatAppLoglevel.equals("INFO")) {
      logRepository.save(logResult);
      System.out.println((char)27 + "[" + (logResult.getLogLevel().equals("ERROR") ? "31" : "34") + "m" + logResult.consoleLog() + (char)27 + "[0m");
    }
  }

  public List<LogEntry> listAllLogs() {
    return logRepository.recentLogFirst();
  }

}
