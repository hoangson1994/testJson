package somepack;

import com.google.gson.Gson;
import com.googlecode.objectify.ObjectifyService;
import entity.JsonData;
import entity.JsonDataGetList;
import entity.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class TestObjectify extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        if(req.getPathInfo() != null){
            // get by id
            long id = Long.parseLong(req.getPathInfo().replace("/", ""));
            Student st = (Student) ofy().load().type(Student.class).id(id).now();
            JsonData jsonData = new JsonData();
            try {
                resp.getWriter().print(new Gson().toJson(jsonData.getInstance(st)));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }else {
            // get list
            List<Student> students = (List<Student>) ofy().load().type(Student.class).list();

            JsonDataGetList jsonDataGetList = new JsonDataGetList();
            JsonData jsonData = new JsonData();
            try {
                jsonDataGetList.setData(jsonData.getListInstance(students));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            resp.getWriter().print(new Gson().toJson(jsonDataGetList));
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonPost = Util.readInputText(req);
        JsonData<Student> result = new Gson().fromJson(jsonPost, JsonData.class);

        String name = (String) result.getAttributes().get("name");
        String rollnumber = (String) result.getAttributes().get("rollnumber");
        Student st = new Student(name, rollnumber);
        ofy().save().entity(st).now();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String idStr = req.getPathInfo().replace("/", "");
        long id = Long.parseLong(idStr);
        Student st = (Student) ofy().load().type(Student.class).id(id).now();
        st.setStatus(0);

        ofy().save().entity(st).now();
        JsonData jsonData = new JsonData();
        try {
            resp.getWriter().print(new Gson().toJson(jsonData.getInstance(st)));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String idStr = req.getPathInfo().replace("/", "");
        long id = Long.parseLong(idStr);
        Student st = (Student) ofy().load().type(Student.class).id(id).now();
        if (st != null) {
            String jsonPost = Util.readInputText(req);

            JsonData<Student> result = new Gson().fromJson(jsonPost, JsonData.class);

            String name = (String) result.getAttributes().get("name");
            String rollnumber = (String) result.getAttributes().get("rollnumber");
            Student stUpdate = new Student(id, name, rollnumber);
            ofy().save().entity(stUpdate).now();
        }
    }
}
