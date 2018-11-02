package com.step.ivko.web.servlet;

import com.step.ivko.service.TourService;
import com.step.ivko.service.TourServiceImpl;
import com.step.ivko.web.dto.TourCreateDto;
import com.step.ivko.web.dto.TourViewDto;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@WebServlet(name = "tourController", urlPatterns = "/tours")
public class TourServlet extends HttpServlet {
    private static final long serialVersionUID = -4512120085379871606L;
    private TourService tourService = new TourServiceImpl();

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
            TourViewDto tourViewDto = tourService.getTourById(id);
            session.setAttribute("tour", tourViewDto);
            resp.sendRedirect("/manager-profile.jsp");
        } else {
            showAllTours(req, resp, session);
        }
    }

    private void showAllTours(HttpServletRequest req,
                              HttpServletResponse resp, HttpSession session) throws ServletException, IOException {
        HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(req) {
            @Override
            public String getMethod() {
                return "GET";
            }
        };
        List<TourViewDto> tours = tourService.getAllTours();
        session.setAttribute("tours", tours);
        RequestDispatcher requestDispatcher = wrapper.getRequestDispatcher("/show.jsp");
        requestDispatcher.forward(wrapper, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TourCreateDto tourCreateDto = extractTourFromRequest(req, "");
        tourService.addTour(tourCreateDto);
        showAllTours(req, resp, req.getSession(true));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("saveList") != null) {
            updateTours(req);
        } else {
            TourCreateDto tourCreateDto = extractTourFromRequest(req, "");
            tourService.updateTour(tourCreateDto);
        }
        showAllTours(req, resp, req.getSession(true));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TourCreateDto tourCreateDto = extractTourFromRequest(req, "");
        tourService.deleteTour(tourCreateDto);
        showAllTours(req, resp, req.getSession(true));
    }

    private void updateTours(HttpServletRequest req) {
        List<TourViewDto> allTours = tourService.getAllTours();
        Predicate<Pair<TourViewDto, TourCreateDto>> isTourChanged = getIsChangedPredicate();
        List<TourCreateDto> changedManagers = allTours
                .stream()
                .map(tour -> new ImmutablePair<>(tour, extractTourFromRequest(req, "_" + tour.getId())))
                .filter(isTourChanged::test)
                .map(ImmutablePair::getRight)
                .collect(Collectors.toList());
        tourService.updateTours(changedManagers);
    }

    private Predicate<Pair<TourViewDto, TourCreateDto>> getIsChangedPredicate() {
        return (pair) -> {
            TourViewDto origin = pair.getLeft();
            TourCreateDto income = pair.getRight();
            return !(origin.getType().equals(income.getType())
                    && origin.getPrice().equals(income.getPrice())
                    && origin.getPeopleCount().equals(income.getPeopleCount())
                    && origin.getHotelType().equals(income.getHotelType())
                    && origin.getCountry().equals(income.getCountry())
                    && origin.getDateDeparture().equals(income.getDateDeparture())
                    && origin.getDateArrival().equals(income.getDateArrival())
                    && origin.getDiscount().equals(income.getDiscount())
                    && origin.isRegistered() == (income.isRegistered())
                    && origin.isHot() == (income.isHot())
                    && origin.isPaidFor() == (income.isPaidFor())
                    && origin.isCanceled() == (income.isCanceled()));
        };
    }

    private TourCreateDto extractTourFromRequest(HttpServletRequest req, String suffix) {
        String idParam = req.getParameter("id" + suffix);
        String typeParam = req.getParameter("type" + suffix);
        String priceParam = req.getParameter("price" + suffix);
        String peopleCountParam = req.getParameter("peopleCount" + suffix);
        String hotelTypeParam = req.getParameter("hotelType" + suffix);
        String countryParam = req.getParameter("country" + suffix);
        String dateDepartureParam = req.getParameter("dateDeparture" + suffix);
        String dateArrivalParam = req.getParameter("dateArrival" + suffix);
        String discountParam = req.getParameter("discount" + suffix);
        String isRegisteredParam = req.getParameter("isRegistered" + suffix);
        String isHotParam = req.getParameter("isHot" + suffix);
        String isPaidForParam = req.getParameter("isPaidFor" + suffix);
        String isCanceledParam = req.getParameter("isCanceled" + suffix);
        Integer id = null;
        if (StringUtils.isNotEmpty(idParam)) {
            id = Integer.valueOf(idParam);
        }
        Integer peopleCount = null;
        if (StringUtils.isNotEmpty(peopleCountParam)) {
            peopleCount = Integer.valueOf(peopleCountParam);
        }
        Double price = null;
        if (StringUtils.isNoneEmpty(priceParam)) {
            price = Double.valueOf(priceParam);
        }
        Double discount = null;
        if (StringUtils.isNoneEmpty(discountParam)) {
            discount = Double.valueOf(discountParam);
        }
        Date dateDeparture = null;
        Date dateArrival = null;
        try {
            if (StringUtils.isNoneEmpty(dateDepartureParam)) {
                DateFormat df = new SimpleDateFormat("dateDepartureParam");
                dateDeparture = df.parse(dateDepartureParam);
            }
            if (StringUtils.isNoneEmpty(dateArrivalParam)) {
                DateFormat df = new SimpleDateFormat("dateArrivalParam");
                dateArrival = df.parse(dateArrivalParam);
            }
        }catch (ParseException e){
            System.out.println(e.getMessage());
        }
        Boolean isRegistered = null;
        if (StringUtils.isNotEmpty(isRegisteredParam)) {
            isRegistered = Boolean.valueOf(isRegisteredParam);
        }
        Boolean isHot = null;
        if (StringUtils.isNotEmpty(isHotParam)) {
            isHot = Boolean.valueOf(isHotParam);
        }
        Boolean isPaidFor = null;
        if (StringUtils.isNotEmpty(isPaidForParam)) {
            isPaidFor = Boolean.valueOf(isPaidForParam);
        }
        Boolean isCanceled = null;
        if (StringUtils.isNotEmpty(isCanceledParam)) {
            isCanceled = Boolean.valueOf(isCanceledParam);
        }
        return new TourCreateDto(
                id,
                typeParam,
                price,
                peopleCount,
                hotelTypeParam,
                countryParam,
                dateDeparture,
                dateArrival,
                discount,
                isRegistered,
                isHot,
                isPaidFor,
                isCanceled);
    }
}