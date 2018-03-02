/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.luchm.log;

import java.util.Properties;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author luchm
 */
public class LogConfig {
    public LogConfig() {
         Properties p = new Properties();
         p.setProperty("log4j.rootLogger", "DEBUG, FILE ");
         p.setProperty("log4j.appender.FILE", "org.apache.log4j.RollingFileAppender");
         p.setProperty("log4j.appender.FILE.File", "src/vng/luchm/log/logs/log.bat");
         p.setProperty("log4j.appender.FILE.ImmediateFlush", "true");
         p.setProperty("log4j.appender.FILE.Threshold", "debug" );
         p.setProperty("log4j.appender.FILE.Append", "true");
         p.setProperty("log4j.appender.FILE.MaxFileSize", "1024KB");
         p.setProperty("log4j.appender.FILE.MaxBackupIndex", "2");
         p.setProperty("log4j.appender.FILE.layout", "org.apache.log4j.PatternLayout");
         p.setProperty("log4j.appender.FILE.layout.conversionPattern", "%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n");
         PropertyConfigurator.configure(p);
    }
}