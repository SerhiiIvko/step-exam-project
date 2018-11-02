package com.step.ivko.web.servlet;

import com.step.ivko.service.CustomerService;
import com.step.ivko.service.CustomerServiceImpl;
import com.step.ivko.web.dto.CustomerCreateDto;
import com.step.ivko.web.dto.CustomerViewDto;
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

@WebServlet(name = "customerController", urlPatterns = "/customers")
public class CustomerServlet extends HttpServlet {
    private static final long serialVersionUID = -1621472048555267188L;
    private CustomerService customerService = new CustomerServiceImpl();

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
            CustomerViewDto customerViewDto = customerService.getCustomerById(id);
            session.setAttribute("customer", customerViewDto);
            resp.sendRedirect("/manager-profile.jsp");
        } else {
            showAllCustomers(req, resp, session);
        }
    }

    private void showAllCustomers(HttpServletRequest req,
                               HttpServletResponse resp, HttpSession session) throws ServletException, IOException {
        HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(req) {
            @Override
            public String getMethod() {
                return "GET";
            }
        };
        List<CustomerViewDto> customers = customerService.getAllCustomers();
        session.setAttribute("customers", customers);
        RequestDispatcher requestDispatcher = wrapper.getRequestDispatcher("/show.jsp");
        requestDispatcher.forward(wrapper, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerCreateDto customerCreateDto = extractCustomerFromRequest(req, "");
        customerService.registerCustomer(customerCreateDto);
        showAllCustomers(req, resp, req.getSession(true));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("saveList") != null) {
            updateCustomers(req);
        } else {
            CustomerCreateDto customerCreateDto = extractCustomerFromRequest(req, "");
            customerService.updateCustomer(customerCreateDto);
        }
        showAllCustomers(req, resp, req.getSession(true));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerCreateDto customerCreateDto = extractCustomerFromRequest(req, "");
        customerService.deleteCustomer(customerCreateDto);
        showAllCustomers(req, resp, req.getSession(true));
    }

    private void updateCustomers(HttpServletRequest req) {
        List<CustomerViewDto> allCustomers = customerService.getAllCustomers();
        Predicate<Pair<CustomerViewDto, CustomerCreateDto>> isCustomerChanged = getIsChangedPredicate();
        List<CustomerCreateDto> changedCustomers = allCustomers
                .stream()
                .map(customer -> new ImmutablePair<>(customer, extractCustomerFromRequest(req, "_" + customer.getId())))
                .filter(isCustomerChanged::test)
                .map(ImmutablePair::getRight)
                .collect(Collectors.toList());
        customerService.updateCustomers(changedCustomers);
    }

    private Predicate<Pair<CustomerViewDto, CustomerCreateDto>> getIsChangedPredicate() {
        return (pair) -> {
            CustomerViewDto origin = pair.getLeft();
            CustomerCreateDto income = pair.getRight();
            return !(origin.getName().equals(income.getName())
                    && origin.getSurname().equals(income.getSurname())
                    && origin.getAge().equals(income.getAge())
                    && origin.getEmail().equals(income.getEmail())
                    && origin.getBlocked().equals(income.getIsBlocked()));
        };
    }

    private CustomerCreateDto extractCustomerFromRequest(HttpServletRequest req, String suffix) {
        String idParam = req.getParameter("id" + suffix);
        String password = req.getParameter("password" + suffix);
        String name = req.getParameter("name" + suffix);
        String surname = req.getParameter("surname" + suffix);
        String ageParam = req.getParameter("age" + suffix);
        String email = req.getParameter("email" + suffix);
        String isBlockedParam = req.getParameter("isBlocked" + suffix);
        Integer id = null;
        if (StringUtils.isNotEmpty(idParam)) {
            id = Integer.valueOf(idParam);
        }
        Integer age = null;
        if (StringUtils.isNotEmpty(ageParam)) {
            age = Integer.valueOf(ageParam);
        }
        Boolean isBlocked = null;
        if (StringUtils.isNotEmpty(isBlockedParam)) {
            isBlocked = Boolean.valueOf(isBlockedParam);
        }
        return new CustomerCreateDto(id, name, surname, age, email, password, isBlocked);
    }
}