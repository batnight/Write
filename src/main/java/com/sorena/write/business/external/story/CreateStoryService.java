/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sorena.write.business.external.story;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sorena.write.business.internal.StoryBusiness;
import com.sorena.write.model.entity.SessionEntity;
import com.sorena.write.model.entity.StoryEntity;
import com.sorena.write.model.service.CreateStoryModel;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sorena
 */
@WebServlet(name = "CreateStoryService", urlPatterns = {"/CreateStory"})
public class CreateStoryService extends HttpServlet {

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
        
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        CreateStoryModel model = new CreateStoryModel();
        StoryEntity storyEntity = new StoryEntity();
        SessionEntity sessionEntity = new SessionEntity();
        StoryBusiness business = new StoryBusiness();
        
        String token = request.getParameter("token");
        String macAddress = request.getParameter("macAddress");
        String image = request.getParameter("image");
        
        storyEntity.setStoryName(request.getParameter("storyName"));
        storyEntity.setSummary(request.getParameter("summary"));
        storyEntity.setNumberAuthors(Integer.parseInt(request.getParameter("numberAuthors")));
        
        sessionEntity.setSessionName(request.getParameter("sessionName"));
        sessionEntity.setSessionText(request.getParameter("sessionContent"));
        
        try {
            business.createStory(model, storyEntity, sessionEntity, token, macAddress, image);
        } catch (Exception ex) {
            model.setMessage("داستان خراب شد");
            model.setMessageType("ERROR");
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
