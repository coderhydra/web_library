package com.example.libraryweb.svc;

import com.example.libraryweb.dao.UserDAO;
import com.example.libraryweb.util.Cart;
import com.example.libraryweb.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserSVC {

    @Autowired
    private HttpSession session;

    @Autowired
    private UserDAO dao;

    @Autowired
    public UserVO user;

    @Autowired
    public UserSVC(@Qualifier("UserDAO") UserDAO dao) {
        this.dao = dao;
    }

    public boolean login(String uid, String pwd) {
        user = dao.select(uid);
        if (user.getUid().equals(uid) && user.getPwd().equals(pwd)) {
            //로그인 성공시 세션에 아이디 저장
            session.setAttribute("uid", uid);
            session.setAttribute("grade", user.getGrade());
            // 장바구니 없으면 장바구니 추가
            if (session.getAttribute("cart") == null) {
                session.setAttribute("cart", new Cart());
                System.err.println("유저서비스에서 카트주기");
            }
            return true;
        }
        return false;
    }

    public boolean signup(UserVO u) {
        return dao.insert(u);
    }

    public String getGrade() {
        String grade = (String) session.getAttribute("grade");
        if (grade == null) return "";
        return grade;
    }

    public String getUid() {
        return (String) session.getAttribute("uid");
    }

    public boolean isLogin() {
        return (session.getAttribute("uid") != null);
    }

    public boolean logOut() {
        session.removeAttribute("uid");
        return true;
    }
}
