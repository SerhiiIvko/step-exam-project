package com.step.ivko.web.servlet;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "errorPageServlet", urlPatterns = "/error")
public class ErrorPageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        processError(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        processError(request, response);
    }

    private void processError(HttpServletRequest request,
                              HttpServletResponse response) throws IOException, ServletException {
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
        if (servletName == null) {
            servletName = "Unknown";
        }
        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }

        List<Pair<String, String>> pairs = new ArrayList<>();
        String title;
        if (statusCode == 500) {
            title = "Exception Details";
            pairs.add(new ImmutablePair<>("Status Code", String.valueOf(statusCode)));
            pairs.add(new ImmutablePair<>("Servlet Name", servletName));
            pairs.add(new ImmutablePair<>("Exception Name", throwable.getClass().getName()));
            pairs.add(new ImmutablePair<>("Requested URI", requestUri));
            pairs.add(new ImmutablePair<>("Exception Message", throwable.getMessage()));
        } else {
            title = "Error Details";
            pairs.add(new ImmutablePair<>("Status Code", String.valueOf(statusCode)));
            pairs.add(new ImmutablePair<>("Requested URI", requestUri));
        }
        HttpSession session = request.getSession(true);
        session.setAttribute("title", title);
        session.setAttribute("pairs", pairs);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/requestDetails.jsp");
        requestDispatcher.forward(request, response);
    }
}