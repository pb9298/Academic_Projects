package edu.advanceST.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.advanceST.data.FacilityDAO;
import edu.advanceST.data.ReservationDao;
import edu.advanceST.model.Reservation;
import edu.advanceST.model.ReservationError;

@WebServlet(urlPatterns = { "/availableFacilityReport", "/requestReservation", "/reservations", "/viewReservation",
		"/decision", "/modifyReservation" })
public class ReservatoinController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (request.getServletPath().equalsIgnoreCase("/modifyReservation")) {
			String message = request.getParameter("message");
			request.setAttribute("type", "modify");
			request.setAttribute("name", request.getParameter("name"));
			request.setAttribute("date", request.getParameter("date"));
			request.setAttribute("reservationId", request.getParameter("reservationId"));
			request.setAttribute("facilityNames", new FacilityDAO().getAllFacilityName());
			getServletContext().getRequestDispatcher("/requestReservation.jsp").forward(request, response);
		} else if (request.getServletPath().equalsIgnoreCase("/requestReservation")) {
			String message = request.getParameter("message");
			request.setAttribute("marNumber", request.getParameter("marNumber"));
			request.setAttribute("name", request.getParameter("name"));
			request.setAttribute("date", request.getParameter("date"));
			request.setAttribute("facilityNames", new FacilityDAO().getAllFacilityName());
			request.setAttribute("type", "create");
			getServletContext().getRequestDispatcher("/requestReservation.jsp").forward(request, response);
		} else if (request.getServletPath().equalsIgnoreCase("/reservations")) {
			request.setAttribute("reservations",
					new ReservationDao().getAllReservationRequests(session.getAttribute("username").toString()));
			getServletContext().getRequestDispatcher("/reservations.jsp").forward(request, response);
		} else if (request.getServletPath().equalsIgnoreCase("/viewReservation")) {
			request.setAttribute("reservation",
					new ReservationDao().getReservationById(Integer.valueOf(request.getParameter("reservationId"))));
			request.setAttribute("marNumber",
					new ReservationDao().getMarNumbers(Integer.valueOf(request.getParameter("reservationId"))));
			getServletContext().getRequestDispatcher("/viewReservation.jsp").forward(request, response);
		} else {
			postDecisionReservation(request, response);
			getServletContext().getRequestDispatcher("/viewReservation.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getServletPath().equalsIgnoreCase("/requestReservation")) {
			boolean isReserved = postRequestReservation(request, response);
			if (isReserved) {
				request.setAttribute("message", "Reservation request successful");
			} else {
				request.setAttribute("message", "Reservation request failed");
			}
			request.setAttribute("type", "create");
			request.setAttribute("marNumber", request.getParameter("marNumber"));
			request.setAttribute("name", request.getParameter("facilityName"));
			request.setAttribute("date", request.getParameter("date"));
			request.setAttribute("facilityNames", new FacilityDAO().getAllFacilityName());
			getServletContext().getRequestDispatcher("/requestReservation.jsp").forward(request, response);
		} else {
			boolean isReserved = postModifyReservation(request, response);
			if (isReserved) {
				request.setAttribute("message", "Reservation request successful");
			} else {
				request.setAttribute("message", "Reservation request failed");
			}
			request.setAttribute("type", "modify");
			request.setAttribute("marNumber", request.getParameter("marNumber"));
			request.setAttribute("name", request.getParameter("facilityName"));
			request.setAttribute("date", request.getParameter("date"));
			request.setAttribute("reservationId", request.getParameter("reservationId").toString());
			request.setAttribute("facilityNames", new FacilityDAO().getAllFacilityName());
			getServletContext().getRequestDispatcher("/requestReservation.jsp").forward(request, response);
		}
	}

	private Boolean postModifyReservation(HttpServletRequest request, HttpServletResponse response) {
		int reservationId = Integer.valueOf(request.getParameter("reservationId").toString());
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String status = request.getParameter("status");
		String facilityName = request.getParameter("facilityName");
		String sDate = request.getParameter("date");
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
		} catch (ParseException e) {
		}
		ReservationError error = Reservation.validateReservation(date, startTime, endTime, facilityName);
		if (error.getDateError().isEmpty() && error.getStartTimeError().isEmpty()
				&& error.getEndTimeError().isEmpty()) {
			return new ReservationDao().modifyReservation(
					new Reservation(reservationId, null, null, (Date) date, startTime, endTime, status));
			
		} else {
			request.setAttribute("error", error);
			return false;
		}

	}

	private void postDecisionReservation(HttpServletRequest request, HttpServletResponse response) {
		Integer marNumber = null;
		marNumber = Integer.valueOf(request.getParameter("marNumber"));
		int reservationId = Integer.valueOf(request.getParameter("reservationId"));
		String status = request.getParameter("status");
		new ReservationDao().decisionReservation(reservationId, status, marNumber);

	}

	private Boolean postRequestReservation(HttpServletRequest request, HttpServletResponse response) {
		String marNumber = request.getParameter("marNumber");
		String facilityName = request.getParameter("facilityName");
		String username = request.getParameter("username");
		Date date = null;
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String sDate = request.getParameter("date");
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
		} catch (ParseException e) {
		}
		ReservationError error = Reservation.validateReservation(date, startTime, endTime, facilityName);
		if (error.getDateError().isEmpty() && error.getStartTimeError().isEmpty()
				&& error.getEndTimeError().isEmpty()) {
			return new ReservationDao().requestReservation(
					new Reservation(null, facilityName, username, date, startTime, endTime, "pending"), marNumber);
		} else {
			request.setAttribute("error", error);
			return false;
		}
	}
}
