package com.example.libraryweb.cont;


import com.example.libraryweb.svc.BoardSVC;
import com.example.libraryweb.svc.UserSVC;
import com.example.libraryweb.vo.BoardVO;
import com.example.libraryweb.vo.CommentVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("library")
public class BoardCont {

    @Autowired
    private BoardSVC svc;
    @Autowired
    private UserSVC uSvc;

    /*board create*/
    @GetMapping("/board/regiB4Check")
    @ResponseBody
    public String regiCheck() {
        String grade = uSvc.getGrade();
        System.err.println("grade=" + grade);
        String msg = "";
        if (!(grade.equals(""))) {
            msg = "match";
        } else if (grade.equals("")) {
            msg = "로그인하지 않으셨습니다.";
            System.err.println(msg);
        }
        return String.format("{\"msg\":\"%s\"}", msg);
    }

    @GetMapping("/board/adminCheck")
    @ResponseBody
    public String postRegi() {
        //String grade  = uSvc.getGrade();
        String grade = null;
        String msg = "";
        if (grade.equals("master")) {
            msg = "match";
        } else if (grade == null) {
            msg = "로그인하지 않으셨습니다.";
        } else {
            msg = "운영진만 사용할수 있는 기능입니다.";
        }
        return String.format("{\"msg\":\"%s\"}", msg);
    }

    @GetMapping("/board/{section}/regi/{page}")
    public String getRegi(
            @PathVariable("section") String section,
            @PathVariable("page") String page,
            Model m) {
        if (!(uSvc.isLogin())) return "redirect:/library/login";
        m.addAttribute("section", section);
        m.addAttribute("curPage", page);
        if (section.equals("photo")) return "/board/photoBoardRegi";
        return "/board/boardRegi";
    }

    @PostMapping("/board/regi")
    @ResponseBody
    public String postRegi(BoardVO b) {
        int saved = svc.addAndGetKey(b);
        return String.format("{\"saved\":%d}", saved);
    }

    /*board list*/
    @RequestMapping("/board/{section}")
    public String getBoard(
            @PathVariable("section") String section,
            Model m) {
        if (section.equals("notice")) return "redirect:/library/board/notice/list/page/1";
        if (section.equals("free")) return "redirect:/library/board/free/list/page/1";
        if (section.equals("FAQ")) return "redirect:/library/board/FAQ/list/page/1";
        if (section.equals("photo")) return "redirect:/library/board/photo/list/page/1";
        return "redirect:/library/login";
    }

    /*board list pageInfo*/
    @GetMapping("/board/{section}/list/page/{pgNum}")
    public String getListByPage(@PathVariable("pgNum") String page,
                                @PathVariable("section") String section,
                                Model model) {
        int pg = 0;
        if (page.equals("undefined")) pg = 1;
        else pg = Integer.parseInt(page);
        PageInfo<BoardVO> pgInfo = svc.getListByPage(pg, 10, section);
        model.addAttribute("pageInfo", pgInfo);
        model.addAttribute("page", pgInfo.getPageNum());
        model.addAttribute("section", section);
        if (section.equals("notice")) return "/board/noticeBoard";
        if (section.equals("free")) return "/board/freeBoard";
        if (section.equals("FAQ")) return "/board/FAQBoard";
        if (section.equals("photo")) return "/board/photoBoard";
        return "redirect:/library/login";
    }

    /*board read*/
    @GetMapping("/board/{section}/detail/{page}/{id}")
    public String detail(
            @PathVariable("section") String section,
            @PathVariable("page") int pg,
            @PathVariable("id") int id,
            Model m) {
        svc.boardCnt(id);
        m.addAttribute("detail", svc.getBoardById(id));
        m.addAttribute("section", section);
        m.addAttribute("page", pg);
        m.addAttribute("comments", svc.getCommentList(id));
        m.addAttribute("imgList", svc.getUploadImageUrl(id));
        return "/board/boardDetail";
    }

    /*board edit 수정창에서 뒤로버튼 눌렀을때 보던 페이지로 돌아가기 위한 메소드*/
    @GetMapping("/board/{section}/edit/{page}/{id}")
    public String getEdit(@PathVariable("id") int id,
                          @PathVariable("page") int pg,
                          @PathVariable("section") String section,
                          Model m) {
        m.addAttribute("detail", svc.getBoardById(id));
        m.addAttribute("section", section);
        m.addAttribute("page", pg);
        return "/board/boardEdit";
    }

    /*이용자와 글쓴이가 동일한지 확인*/
    @GetMapping("/board/writerCheck/{id}")
    @ResponseBody
    public String boardWriterCheck(@PathVariable("id") int id) {
        String grade = uSvc.getGrade();
        if (grade.equals("master"))
            return String.format("{\"msg\":\"%s\"}", "master");
        String uid = uSvc.getUid();
        String writer = svc.getBoardById(id).getWriter();
        String msg = "";
        if (uid == null) {
            msg = "로그인하지 않으셨습니다.";
        } else {
            if (uid.equals(writer)) {
                msg = "match";
            } else {
                msg = "작성자만 사용할 있는 기능입니다.";
            }
        }
        return String.format("{\"msg\":\"%s\"}", msg);
    }

    /*board edit*/
    @PostMapping("/board/edit")
    @ResponseBody
    public boolean postEdit(BoardVO b) {
        return svc.updateBoard(b);
    }

    /*board delete*/
    @GetMapping("/board/delete/{id}")
    @ResponseBody
    public String getDelete(@PathVariable("id") int id) {
        boolean ok = svc.deleteBoard(id);
        return String.format("{\"ok\":%b}", ok);
    }

    /*board comment create*/
    @PostMapping("/board/comment")
    @ResponseBody
    public int postComment(
            CommentVO c) {
        return svc.insertComment(c);
    }

    /*이용자와 글쓴이가 동일한지 확인*/
    @GetMapping("/comment/writerCheck/{id}")
    @ResponseBody
    public String commentWriterCheck(@PathVariable("id") int id) {
        String uid = uSvc.getUid();
        String writer = svc.getCommentById(id).getWriter();
        String msg = "";
        if (uSvc.getGrade().equals("master"))
            return String.format("{\"msg\":\"%s\"}", "match");
        if (uid == null) {
            msg = "로그인하지 않으셨습니다.";
        } else {
            if (uid.equals(writer)) {
                msg = "match";
            } else {
                msg = "작성자만 사용할 있는 기능입니다.";
            }
        }
        return String.format("{\"msg\":\"%s\"}", msg);
    }

    /*board comment edit*/
    @PostMapping("/board/commentEdit")
    @ResponseBody
    public boolean postEdit(CommentVO c) {
        return svc.updateComment(c);
    }

    /*board delete*/
    @GetMapping("/board/{section}/comentDelete/{page}/{pid}/{id}")
    public String commentDel(
            @PathVariable("section") String section,
            @PathVariable("page") int page,
            @PathVariable("pid") int pid,
            @PathVariable("id") int id
    ) {
        svc.deleteComment(id);
        return "redirect:/library/board/" + section + "/detail/" + page + "/" + pid;
    }

    /*검색후 페이지연결 기본 페이지는 뷰에서 1로줌 검색종류와 검색어 받아옴*/
    @GetMapping("/board/search/{section}/{page}/{cmd}/{word}")
    public String getSearch(
            @PathVariable("section") String section,
            @PathVariable("page") int pg,
            @PathVariable("cmd") String cmd,
            @PathVariable("word") String word,
            Model m
    ) {
        PageInfo<BoardVO> pgInfo = svc.getSearchListByPage(pg, 10, section, cmd, word);
        m.addAttribute("pageInfo", pgInfo);
        m.addAttribute("page", pgInfo.getPageNum());
        return "/board/freeBoard";
    }

    //검색결과 boolean
    @PostMapping("/board/search")
    @ResponseBody
    public String postSearch(
            @RequestParam("section") String section,
            @RequestParam("cmd") String cmd,
            @RequestParam("word") String word
    ) {
        PageInfo<BoardVO> pgInfo = svc.getSearchListByPage(1, 10, section, cmd, word);
        return String.format("{\"ok\":%b}", pgInfo.hasContent());
    }
}