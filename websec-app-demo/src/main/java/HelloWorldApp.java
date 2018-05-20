import java.io.IOException;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class HelloWorldApp extends HttpServlet
{
    public static void main( String[] args ) throws Exception
    {
        Server server = new Server(10103);
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);

        handler.addServletWithMapping(HelloWorldApp.class, "/*");

        System.out.println("Just about to run server.start()");
        server.start();
        System.out.println("Just about to run server.join()");
        server.join();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet called");
        System.out.println("URL is " + request.getRequestURL());
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        out.println("<p>This is the respionse for a GET to " + request.getRequestURL());
        out.println("<form method=post>Here is a form to fill out:<br>");
        out.println("input 'foo': <input type=text name=foo><br>");
        out.println("input 'bar': <input type=text name=bar><br>");
        out.println("<input type=submit value='submit form'>");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost called");
        System.out.println("URL is " + request.getRequestURL());
        System.out.println("Parameters are " + request.getParameterMap());
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        out.println("<p>This is the response for a POST to " + request.getRequestURL());
    }
}
