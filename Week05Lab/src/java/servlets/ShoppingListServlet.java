/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;

/**
 *
 * @author 818396
 */
public class ShoppingListServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        HttpSession session = request.getSession();
        String action = (String) request.getParameter("action");
        String name = (String) session.getAttribute("username");
        
        
        if(action == null) {
            if(name == null) {
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request,response); 
            }else {
                ArrayList<String> items = (ArrayList<String>) session.getAttribute("items");
                request.setAttribute("name", name);
                request.setAttribute("items", items);

                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request,response); 
            }
        }else {
             if(action.equals("logout")) {
//                System.out.println("try to logout");
//                System.out.println(action);
                session.invalidate();
                session = request.getSession();

                response.sendRedirect("/Week05Lab/shoppingList");
            } 
        }
    }   
        
 @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
        HttpSession session = request.getSession();
        String action = (String) request.getParameter("action");
        
        System.out.println(action);
        
        if(action.equals("registerName")) {
            
            String username = (String) request.getParameter("name");
            ArrayList<String> itemsList = new ArrayList<>();
            
            session.setAttribute("username", username);
            session.setAttribute("itemsList", itemsList);
            
            request.setAttribute("name", username);
            request.setAttribute("itemsList", itemsList);
            
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request,response); 
        }
        else if(action.equals("delete")) {
            
            String item = (String) request.getParameter("item");
            ArrayList<String> listUpdated = (ArrayList<String>) session.getAttribute("itemList");
            
            for(int i=0; i<listUpdated.size(); i++) {
                
                if(listUpdated.get(i).equals(item)) {
                    listUpdated.remove(i);
                }
                
            }
          session.setAttribute("itemList", listUpdated);
          String username = (String) session.getAttribute("username");
          
          request.setAttribute("itemList", listUpdated);
          request.setAttribute("name", username);
          
          getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request,response); 
        }   
        else if(action.equals("add")) {
            
            String item = (String) request.getParameter("addItem");
            ArrayList<String> listUpdated;
            
            if(session.getAttribute("itemList") == null) {
                listUpdated = new ArrayList<String>();
            }
            else {
                listUpdated = (ArrayList<String>) session.getAttribute("itemList");
            }
            
          listUpdated.add(item);
          session.setAttribute("itemList", listUpdated);
          String username = (String) session.getAttribute("username");
          
          request.setAttribute("itemList", listUpdated);
          request.setAttribute("name", username);
          
          getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request,response); 
            
        }
     }
}