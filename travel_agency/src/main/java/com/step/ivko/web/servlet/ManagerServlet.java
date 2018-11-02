package com.step.ivko.web.servlet;

import com.step.ivko.service.ManagerService;
import com.step.ivko.service.ManagerServiceImpl;
import com.step.ivko.web.dto.ManagerCreateDto;
import com.step.ivko.web.dto.ManagerViewDto;
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

@WebServlet(name = "managerController", urlPatterns = "/managers")
public class ManagerServlet extends HttpServlet {
    private static final long serialVersionUID = 6552775805019173305L;
    private ManagerService managerService = new ManagerServiceImpl();

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
            ManagerViewDto manager = managerService.getManagerById(id);
            session.setAttribute("manager", manager);
            resp.sendRedirect("/manager-profile.jsp");
        } else {
            showAllManagers(req, resp, session);
        }
    }

    private void showAllManagers(HttpServletRequest req,
                                 HttpServletResponse resp, HttpSession session) throws ServletException, IOException {
        HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(req) {
            @Override
            public String getMethod() {
                return "GET";
            }
        };
        List<ManagerViewDto> managers = managerService.getAllManagers();
        session.setAttribute("managers", managers);
        RequestDispatcher requestDispatcher = wrapper.getRequestDispatcher("/show.jsp");
        requestDispatcher.forward(wrapper, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ManagerCreateDto managerCreateDto = extractManagerFromRequest(req, "");
        managerService.registerManager(managerCreateDto);
        showAllManagers(req, resp, req.getSession(true));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("saveList") != null) {
            updateManagers(req);
        } else {
            ManagerCreateDto managerCreateDto = extractManagerFromRequest(req, "");
            managerService.updateManager(managerCreateDto);
        }
        showAllManagers(req, resp, req.getSession(true));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ManagerCreateDto managerCreateDto = extractManagerFromRequest(req, "");
        managerService.deleteManager(managerCreateDto);
        showAllManagers(req, resp, req.getSession(true));
    }

    private void updateManagers(HttpServletRequest req) {
        List<ManagerViewDto> allManagers = managerService.getAllManagers();
        Predicate<Pair<ManagerViewDto, ManagerCreateDto>> isManagerChanged = getIsChangedPredicate();
        List<ManagerCreateDto> changedManagers = allManagers
                .stream()
                .map(manager -> new ImmutablePair<>(manager, extractManagerFromRequest(req, "_" + manager.getId())))
                .filter(isManagerChanged::test)
                .map(ImmutablePair::getRight)
                .collect(Collectors.toList());
        managerService.updateManagers(changedManagers);
    }

    private Predicate<Pair<ManagerViewDto, ManagerCreateDto>> getIsChangedPredicate() {
        return (pair) -> {
            ManagerViewDto origin = pair.getLeft();
            ManagerCreateDto income = pair.getRight();
            return !(origin.getName().equals(income.getName())
                    && origin.getSurname().equals(income.getSurname())
                    && origin.getAge().equals(income.getAge())
                    && origin.getEmail().equals(income.getEmail()));
        };
    }

    private ManagerCreateDto extractManagerFromRequest(HttpServletRequest req, String suffix) {
        String idParam = req.getParameter("id" + suffix);
        String password = req.getParameter("password" + suffix);
        String name = req.getParameter("name" + suffix);
        String surname = req.getParameter("surname" + suffix);
        String ageParam = req.getParameter("age" + suffix);
        String email = req.getParameter("email" + suffix);
        String isManagerParam = req.getParameter("isManager" + suffix);
        String isBlockedParam = req.getParameter("isBlocked" + suffix);
        Integer id = null;
        if (StringUtils.isNotEmpty(idParam)) {
            id = Integer.valueOf(idParam);
        }
        Integer age = null;
        if (StringUtils.isNotEmpty(ageParam)) {
            age = Integer.valueOf(ageParam);
        }
        Boolean isManager = null;
        if (StringUtils.isNotEmpty(isManagerParam)) {
            isManager = Boolean.valueOf(isManagerParam);
        }
        Boolean isBlocked = null;
        if (StringUtils.isNotEmpty(isBlockedParam)) {
            isBlocked = Boolean.valueOf(isBlockedParam);
        }
        return new ManagerCreateDto(id, name, surname, age, email, password, isManager, isBlocked);
    }
}