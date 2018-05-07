package somepack;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;

public class Util {

    public static String readInputText(HttpServletRequest request) {
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null){
                jb.append(line);
            }
        } catch (Exception e) {

            return "error";
        }
        return jb.toString();
    }

}
