package com.greenfox.tomdreidel.chatapp.service;

import com.greenfox.tomdreidel.chatapp.model.LogEntry;
import com.greenfox.tomdreidel.chatapp.repository.LogRepository;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {

  @Autowired
  LogRepository logRepository;

  public void addLog(HttpServletRequest request, HttpServletResponse response) {
  //    Log to database
    String paramBuilder = "";
    Enumeration<String> parameterNames = request.getParameterNames();

    while (parameterNames.hasMoreElements()) {
      String tempName = parameterNames.nextElement();
      String tempValue = request.getParameterValues(tempName)[0];
      paramBuilder += tempName + "=" + tempValue + "; ";
    }

    LogEntry logResult = new LogEntry((response.getStatus() == 200 ? "INFO" : "ERROR"), request.getServletPath(), request.getMethod(), request.getRemoteAddr(), paramBuilder);
    logRepository.save(logResult);

  //    Log to console
    System.out.println((char)27 + "[" + (logResult.getLogLevel().equals("ERROR") ? "31" : "34") + "m" + logResult.consoleLog() + (char)27 + "[0m");
    System.out.println(response.getStatus());
  }

  public List<LogEntry> listAllLogs() {
    return logRepository.recentLogFirst();
  }

}
