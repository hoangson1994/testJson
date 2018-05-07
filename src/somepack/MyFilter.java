package somepack;

import com.googlecode.objectify.ObjectifyService;
import entity.Student;

import javax.servlet.*;
import java.io.IOException;

public class MyFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        ObjectifyService.register(Student.class);

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
