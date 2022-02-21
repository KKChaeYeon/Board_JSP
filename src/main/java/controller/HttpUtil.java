package controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HttpUtil {
	
	public static void Forward(HttpServletRequest req, HttpServletResponse resp, String url){
		try{
			req.getRequestDispatcher(url).forward(req, resp);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}
