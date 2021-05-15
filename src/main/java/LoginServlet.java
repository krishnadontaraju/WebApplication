import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;


@WebServlet(

        description = "Login to Nirvana",
        urlPatterns = {"/LoginServlet"}
)
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Calling the input given by the user
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");

        Pattern userNamePattern = Pattern.compile("^[A-Z][a-z]{3,}");

        //Checking if the userName given is according to the pattern
        if (user.matches(String.valueOf(userNamePattern))) {
            request.setAttribute("user", user);
            //Forwarding to the successful login jsp file
            request.getRequestDispatcher("LoginSuccess.jsp").forward(request, response);
        } else {//Rejecting entry if user name is not according to the pattern
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Wrong Username Pattern</font>");
            dispatcher.include(request, response);
        }
    }
}
