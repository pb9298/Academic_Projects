package edu.advanceST.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.advanceST.data.FacilityDAO;
import edu.advanceST.data.LoginDAO;
import edu.advanceST.data.MarDAO;
import edu.advanceST.model.AssignMar;
import edu.advanceST.model.AssignMarError;
import edu.advanceST.model.Mar;
import edu.advanceST.model.MarError;

@WebServlet(urlPatterns = { "/viewMar", "/createMAR", "/assignMar", "/searchMar", "/searchAssgMar", "/listMars" })
public class MarController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (request.getServletPath().equalsIgnoreCase("/searchMar")) {
			getServletContext().getRequestDispatcher("/searchMar.jsp").forward(request, response);
		} else if (request.getServletPath().equalsIgnoreCase("/searchAssgMar")) {
			List<Mar> mars = new MarDAO().getMars(session.getAttribute("username").toString());
			request.setAttribute("mars", mars);
			getServletContext().getRequestDispatcher("/searchAssgMar.jsp").forward(request, response);
		} else if (request.getServletPath().equalsIgnoreCase("/assignMar")) {
			request.setAttribute("repairers", new LoginDAO().getAllRepairers());
			request.setAttribute("marNumber", request.getParameter("marNumber"));
			getServletContext().getRequestDispatcher("/assignMar.jsp").forward(request, response);
		} else if (request.getServletPath().equalsIgnoreCase("/createMAR")) {
			getServletContext().getRequestDispatcher("/createMAR.jsp").forward(request, response);
		} else {
			Mar mar = new MarDAO().getMarByNumber(Long.valueOf(request.getParameter("marNumber")));
			request.setAttribute("mar", mar);
			AssignMar assignMar = new MarDAO().getAssignMarByNumber(Long.valueOf(request.getParameter("marNumber")));
			request.setAttribute("assignMar", assignMar);
			getServletContext().getRequestDispatcher("/viewMar.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (request.getServletPath().equalsIgnoreCase("/searchMar")) {
			postSearchMar(request, response);
			getServletContext().getRequestDispatcher("/searchMar.jsp").forward(request, response);
		} else if (request.getServletPath().equalsIgnoreCase("/assignMar")) {
			postAssignMar(request, response);
		} else {
			postCreateMar(request, response);
		}
	}

	private void postSearchMar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String assigned = request.getParameter("assigned");
		java.sql.Date assignedDate = null;
		try {
			assignedDate = new java.sql.Date(
					new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("assignedDate")).getTime());
		} catch (ParseException e) {
			System.out.println();
		}
		List<Mar> mars = new MarDAO().searchMars(assigned, assignedDate);
		request.setAttribute("mars", mars);
	}

	private void postCreateMar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Object object = getMARForm(request);
		if (object instanceof Mar) {
			Mar mar = (Mar) object;
			new MarDAO().addMARdetails(mar);
			getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
		} else {
			request.setAttribute("error", (MarError) object);
			getServletContext().getRequestDispatcher("/createMAR.jsp").forward(request, response);
		}
	}

	private Object getMARForm(HttpServletRequest request) {
		String facilityType = request.getParameter("facilityType");
		String name = request.getParameter("name");
		String urgency = request.getParameter("urgency");
		String description = request.getParameter("description");
		String reportedBy = request.getParameter("reportedBy");
		MarError error = Mar.validate(name, description);
		if (error.getNameError().isEmpty() && error.getDescriptionError().isEmpty()) {
			return new Mar(null, facilityType, name, urgency, description, reportedBy, new Date(), false);
		} else {
			return error;
		}
	}

	private void postAssignMar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Object object = getAssignMar(request);
		if (object instanceof AssignMar) {
			new MarDAO().addAssignMar((AssignMar) object);
			getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
		} else {
			request.setAttribute("error", (AssignMarError) object);
			request.setAttribute("repairers", new LoginDAO().getAllRepairers());
			request.setAttribute("marNumber", request.getParameter("marNumber"));
			getServletContext().getRequestDispatcher("/assignMar.jsp").forward(request, response);
		}
	}

	private Object getAssignMar(HttpServletRequest request) {
		long marNumber = Long.valueOf(request.getParameter("marNumber"));
		String assignedTo = request.getParameter("assignedTo");
		String etr = request.getParameter("etr");
		Date assignedDate = null;
		try {
			assignedDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("assignedDate"));
		} catch (ParseException e) {
		}
		AssignMarError error = AssignMar.validate(assignedTo, assignedDate);
		if (error.getAssignedDateError().isEmpty()) {
			return new AssignMar(marNumber, assignedTo, assignedDate, etr);
		} else {
			return error;
		}
	}
}
