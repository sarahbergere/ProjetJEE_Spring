package marketplace.ProjetJ2EE_SpringBoot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/account")
public class AccountController {

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public String processRequest(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            Object roleAttribute = session.getAttribute("role");

            if (roleAttribute != null) {
                String role = (String) roleAttribute;

                if ("client".equals(role)) {
                    return "forward:/client";
                } else if ("admin".equals(role)) {
                    return "forward:/admin";
                } else {
                    return "redirect:/login";
                }
            } else {
                return "redirect:/login";
            }
        } else {
            return "redirect:/login";
        }
    }
}
