/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sorena.write.business.external.account;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sorena.write.business.internal.AccountBusiness;
import com.sorena.write.model.entity.AccountEntity;
import com.sorena.write.model.entity.DeviceEntity;
import com.sorena.write.model.service.RegisterModel;
import com.sorena.write.utility.UtilMethod;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sorena
 */
@WebServlet(name = "RegisterService", urlPatterns = {"/Register"})
public class RegisterService extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        AccountBusiness business = new AccountBusiness();
        AccountEntity entity = new AccountEntity();
        DeviceEntity deviceEntity = new DeviceEntity();
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        entity.setUsername(request.getParameter("username"));
        entity.setPassword(request.getParameter("password"));
        entity.setFirstName(request.getParameter("firstName"));
        entity.setLastName(request.getParameter("lastName"));
        entity.setEmail(request.getParameter("email"));
        entity.setMobileNumber(request.getParameter("mobileNumber"));
        entity.setRegisterDate(new Date());
        entity.setToken(UtilMethod.generateUUID());
        entity.setMacAddress(request.getParameter("macAddress"));

        RegisterModel model = new RegisterModel();
        try {
            model = business.register(entity, model);
        } catch (Exception ex) {
        } finally {
            response.getWriter().println(gson.toJson(model));
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
