package br.ucb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LivingDaysServlet
 */
@WebServlet("/LivingDaysServlet")
public class LivingDaysServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LivingDaysServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String birthdayDateHTML = request.getParameter("birthday_date");
		
		try {
			GregorianCalendar birthdayDate = getDateInstance(birthdayDateHTML);
			
			out.println("Voce ja viveu " + this.calculateAliveDays(birthdayDate)/1000/60/60/24 + " dias.");
		} catch (ParseException e) {
			out.println("O valor de data enviado não corresponde ao esperado.");
			
		}
		
	}
	
	private GregorianCalendar getDateInstance(String birthdayDateString) throws ParseException{
		
		String[] splitDate = birthdayDateString.split("-");
		
		GregorianCalendar dateForReturn = new GregorianCalendar(Integer.parseInt(splitDate[0]), Integer.parseInt(splitDate[1])-1, Integer.parseInt(splitDate[2]));
		
		return dateForReturn;
	}
	
	private long calculateAliveDays(GregorianCalendar birthdayDate){
		GregorianCalendar todayDate = new GregorianCalendar();
		GregorianCalendar todayDate2 = new GregorianCalendar();
		
		todayDate2.add(Calendar.DAY_OF_YEAR, 1);
		
		System.out.println(birthdayDate.getTimeInMillis());
		System.out.println(todayDate.getTimeInMillis());
		return (todayDate.getTimeInMillis() - birthdayDate.getTimeInMillis());
	}

}
