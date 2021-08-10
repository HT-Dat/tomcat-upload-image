/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author FAMILY
 */
@WebServlet(name = "multiPartServlet", urlPatterns = {"/multiPartServlet"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class multiPartServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        //Get current path of this web app
        /*Careful, this path might not look like your MeoMeo location because 
        they are not actually deploy to MeoMeo's webapps folder
        They actually use the built version which locate in /build 
        I know, sound weird right? Have no idea why KhanhKute didn't talk about this before
        But it doesn't affect much as long as we deploy them manually 
        using war file into MeoMeo webapps folder*/
        String MeoMeoPath = getServletContext().getRealPath("");
        out.println("Current path of this web application: " + MeoMeoPath);
        String partOfPath[] = MeoMeoPath.split("\\\\");
        String uploadPath = "";
        //Simple String process
        //Only get the part before the last two backslash because we don't want 
        //to save it into webapp which will be get deleted every time we redeploy
        for (int i = 0; i < partOfPath.length - 2; i++) {
            uploadPath += (partOfPath[i] + File.separator);
        }
        uploadPath += ("images" + File.separator);
        out.println("</br>Path we save img: " + uploadPath);
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        for (Part part : request.getParts()) {
            int i;
            for (i = part.getHeader("content-disposition").length() - 1; i > 0; i--) {
                if (part.getHeader("content-disposition").charAt(i) == '=') {
                    break;
                }
            }
            String rawFileName
                    = part.getHeader("content-disposition").substring(i + 2, part.getHeader("content-disposition").length() - 1);
            String partOfFileName[] = rawFileName.split("\\\\");
            int indexOfLastPartFileName = partOfFileName.length - 1;
            String fileName = partOfFileName[indexOfLastPartFileName];
            out.println("</br> File name: " + fileName);
            part.write(uploadPath + File.separator + fileName);
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
