package edu.advanceST.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.advanceST.data.FacilityDAO;
import edu.advanceST.data.ReservationDao;
import edu.advanceST.model.FacilityDetails;
import edu.advanceST.model.FacilityDetailsError;

@WebServlet(urlPatterns = { "/addFacility", "/searchFacility", "/viewFacility" })
public class FacilityController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getServletPath().equalsIgnoreCase("/addFacility")) {
			getServletContext().getRequestDispatcher("/addfacility.jsp").forward(request, response);
		} else if (request.getServletPath().equalsIgnoreCase("/viewFacility")) {
			FacilityDetails facility = new FacilityDAO()
					.getSpecificFacilitiy(String.valueOf(request.getParameter("name")));
			request.setAttribute("facility", facility);
			getServletContext().getRequestDispatcher("/viewfacility.jsp").forward(request, response);
		} else {
			getServletContext().getRequestDispatcher("/searchFacility.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getServletPath().equalsIgnoreCase("/addFacility")) {
			postAddfacility(request, response);
		} else {
			postSearchFacility(request, response);
		}
	}

	private void postAddfacility(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object object = getFacilityForm(request);
		if (object instanceof FacilityDetailsError) {
			request.setAttribute("error", (FacilityDetailsError) object);
		} else {
			FacilityDetails facilityDetails = (FacilityDetails) object;
			if (new FacilityDAO().checkFacility(facilityDetails.getName())) {
				request.setAttribute("message", "Facility already exists");
			} else {
				request.setAttribute("message", "Facility added successfully");
				new FacilityDAO().addFacilitydetails((FacilityDetails) object);
			}
		}
		getServletContext().getRequestDispatcher("/addfacility.jsp").forward(request, response);
	}

	private void postSearchFacility(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String facilityType = request.getParameter("facilityType");
		String duration = request.getParameter("duration");
		String venue = request.getParameter("venue");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		ArrayList<FacilityDetails> facDet = new ArrayList<FacilityDetails>();
		String sDate = request.getParameter("date");
		Date date = null;
		try {
			date = new SimpleDateFormat("YYYY-MM-DD").parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		FacilityDetailsError error = FacilityDetails.validate(date, startTime, endTime);
		if (!error.getDateError().isEmpty() || !error.getEndTimeError().isEmpty()) {
			request.setAttribute("error", error);
		} else {
			ArrayList<FacilityDetails> temp = new FacilityDAO().searchFacility(facilityType, duration, venue);
			for (FacilityDetails facility : temp) {
				TreeMap<Double, Boolean> report = new ReservationDao().getAvailableFacilityReport(facility.getName(),
						date);
				double sTime = Integer.valueOf(startTime);
				double eTime = Integer.valueOf(endTime);
				while (sTime < eTime) {
					sTime += 0.5;
				}
				facDet.add(facility);
			}
			request.setAttribute("facDet", facDet);
		}
		getServletContext().getRequestDispatcher("/searchFacility.jsp").forward(request, response);
	}

	private Object getFacilityForm(HttpServletRequest request) {

		String facilityType = request.getParameter("facilityType");
		String name = request.getParameter("name");
		String interval = request.getParameter("interval");
		String duration = request.getParameter("duration");
		String venue = request.getParameter("venue");
		FacilityDetailsError error = FacilityDetails.validate(name);
		if (error.getNameError().isEmpty()) {
			return new FacilityDetails(facilityType, name, interval, duration, venue);
		} else {
			return error;
		}
	}

}
