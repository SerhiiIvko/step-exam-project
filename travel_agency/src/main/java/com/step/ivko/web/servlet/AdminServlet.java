package com.step.ivko.web.servlet;
import com.step.ivko.service.AdminService;
import com.step.ivko.service.AdminServiceImpl;
import com.step.ivko.web.dto.AdminCreateDto;
import com.step.ivko.web.dto.AdminViewDto;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@WebServlet(name = "adminController", urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = -1892055116962900304L;
    private AdminService adminService = new AdminServiceImpl();

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int status = resp.getStatus();
        List<Pair<String, String>> pairs = new ArrayList<>();
        pairs.add(new ImmutablePair<>("Status", String.valueOf(status)));
        Enumeration headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = req.getHeader(key);
            pairs.add(new ImmutablePair<>(key, value));
        }
        HttpSession session = req.getSession(true);
        session.setAttribute("title", "Status");
        session.setAttribute("pairs", pairs);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/requestDetails.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id;
        HttpSession session = req.getSession(true);
        String idParam = req.getParameter("id");
        if (StringUtils.isNotEmpty(idParam)) {
            id = Integer.valueOf(idParam);
            AdminViewDto adminViewDto = adminService.getAdminById(id);
            session.setAttribute("admin", adminViewDto);
            resp.sendRedirect("/admin-profile.jsp");
        } else {
            showAllAdmins(req, resp, session);
        }
    }

    private void showAllAdmins(HttpServletRequest req,
                                 HttpServletResponse resp, HttpSession session) throws ServletException, IOException {
        HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(req) {
            @Override
            public String getMethod() {
                return "GET";
            }
        };
        List<AdminViewDto> admins = adminService.getAllAdmins();
        session.setAttribute("admins", admins);
        RequestDispatcher requestDispatcher = wrapper.getRequestDispatcher("/show.jsp");
        requestDispatcher.forward(wrapper, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdminCreateDto adminCreateDto = extractAdminFromRequest(req, "");
        adminService.registerAdmin(adminCreateDto);
        showAllAdmins(req, resp, req.getSession(true));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("saveList") != null) {
            updateManagers(req);
        } else {
            AdminCreateDto adminCreateDto = extractAdminFromRequest(req, "");
            adminService.updateAdmin(adminCreateDto);
        }
        showAllAdmins(req, resp, req.getSession(true));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdminCreateDto adminCreateDto = extractAdminFromRequest(req, "");
        adminService.deleteAdmin(adminCreateDto);
        showAllAdmins(req, resp, req.getSession(true));
    }

    private void updateManagers(HttpServletRequest req) {
        List<AdminViewDto> allAdmins = adminService.getAllAdmins();
        Predicate<Pair<AdminViewDto, AdminCreateDto>> isAdminChanged = getIsChangedPredicate();
        List<AdminCreateDto> changedAdmins = allAdmins
                .stream()
                .map(manager -> new ImmutablePair<>(manager, extractAdminFromRequest(req, "_" + manager.getId())))
                .filter(isAdminChanged::test)
                .map(ImmutablePair::getRight)
                .collect(Collectors.toList());
        adminService.updateAdmins(changedAdmins);
    }

    private Predicate<Pair<AdminViewDto, AdminCreateDto>> getIsChangedPredicate() {
        return (pair) -> {
            AdminViewDto origin = pair.getLeft();
            AdminCreateDto income = pair.getRight();
            return !(origin.getName().equals(income.getName())
                    && origin.getSurname().equals(income.getSurname())
                    && origin.getAge().equals(income.getAge())
                    && origin.getEmail().equals(income.getEmail())
                    && origin.getAdmin().equals(income.getAdmin()));
        };
    }

    private AdminCreateDto extractAdminFromRequest(HttpServletRequest req, String suffix) {
        String idParam = req.getParameter("id" + suffix);
        String password = req.getParameter("password" + suffix);
        String name = req.getParameter("name" + suffix);
        String surname = req.getParameter("surname" + suffix);
        String ageParam = req.getParameter("age" + suffix);
        String email = req.getParameter("email" + suffix);
        String isAdminParam = req.getParameter("isAdmin" + suffix);
        Integer id = null;
        if (StringUtils.isNotEmpty(idParam)) {
            id = Integer.valueOf(idParam);
        }
        Integer age = null;
        if (StringUtils.isNotEmpty(ageParam)) {
            age = Integer.valueOf(ageParam);
        }
        Boolean isAdmin = null;
        if (StringUtils.isNotEmpty(isAdminParam)) {
            isAdmin = Boolean.valueOf(isAdminParam);
        }
        return new AdminCreateDto(id, name, surname, age, email, password, isAdmin);
    }
}