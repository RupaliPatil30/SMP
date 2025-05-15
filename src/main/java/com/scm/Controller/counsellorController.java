package com.scm.Controller;


import com.scm.Binding.DashboardResponse;
import com.scm.Entity.Counsellor;
import com.scm.Service.CounsellorService;
import com.scm.Service.EnquiryService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;


@Controller

public class counsellorController {

    @Autowired
    private CounsellorService counsellorService;
    @GetMapping("/logout")
    public String logout(HttpServletRequest req, Model model) {
        HttpSession session = req.getSession(false);
        session.invalidate();
        return "redirect:/";
    }


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("counsellor", new Counsellor());
        return "loginView";
    }

    @PostMapping("/login")
    public String handleLogin(Counsellor c, HttpServletRequest req, Model model) {
        Counsellor obj = counsellorService.loginCheck(c.getEmail(), c.getPwd());
        if (obj == null) {
            model.addAttribute("errMsg", "Invalid credentials");
            return "loginView";
        }
        HttpSession session =req.getSession(true);
         session.setAttribute("CID",obj.getCid());
        return "redirect:dashboard";
    }


//    @GetMapping("/dashboard")
//    public String BuildDashboardMethod(HttpServletRequest req ,Model model) {
//        HttpSession session=req.getSession(false);
//        Long IntegerCid = (Long) session.getAttribute("cid");
//        //Integer cid = IntegerCid.intValue();
//        int cid = Optional.ofNullable(IntegerCid).map(Long::intValue).orElse(0);
//
//        DashboardResponse dashboardInfo=counsellorService.getDashboardInfo(cid);
//        model.addAttribute("dashboard", dashboardInfo);
//        return "DashBoardView";
//        //Long cid = (Long) session.getAttribute("CID");
    //}
@GetMapping("/dashboard")
public String buildDashboard(HttpServletRequest req, Model model) {

    HttpSession session = req.getSession(false);
    Object obj = session.getAttribute("CID");
    Integer cid = (Integer) obj;

    DashboardResponse dashboardInfo = counsellorService.getDashboardInfo(cid);

    model.addAttribute("dashboard", dashboardInfo);

    return "DashboardView";

}


    @GetMapping("/register")
    public String regPage(Model model) {
        model.addAttribute("counsellor", new Counsellor());
        return "RegisterView";
    }

    @PostMapping("/register")
    public String handleRegistration(Counsellor c, Model model) {
        String msg = counsellorService.saveCounsellor(c);
        model.addAttribute("msg", msg);
        return "registerView";
    }

    @GetMapping("/forgot-pwd")
    public String recoverPwdPage(Model model) {
        model.addAttribute("counsellor", new Counsellor());
        return "forgetPwdView";
    }

    @GetMapping("/recover-pwd")
    public String recoverPwd(@RequestParam String email, Model model) {
        boolean status = counsellorService.recoverPwd(email);
        if (status) {
            model.addAttribute("smsg", "pwd send to your email");

        } else{ model.addAttribute("errmsg", "Invalid email");
        }
        return "forgetPwdView";
    }
    @PostMapping("/recover-pwd")
    public String handlePasswordRecovery(@RequestParam("email") String email, Model model) {
        // handle recovery logic
        return "forgetPwdView";
    }


}









