package com.example.libraryweb.svc;

import com.example.libraryweb.dao.BoardDAO;
import com.example.libraryweb.vo.BoardVO;
import com.example.libraryweb.vo.CommentVO;
import com.example.libraryweb.vo.UploadVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class BoardSVC {
    @Autowired
    private BoardDAO dao;

    @Autowired
    HttpSession session;

    public int addAndGetKey(BoardVO b) {
        String writer = (String) session.getAttribute("uid");
        b.setWriter(writer);
        int res = dao.addAndGetKey(b);
        System.err.println("결과 +" + res);
        System.err.println("서비스 들록키 작성자" + writer);
        if (writer == null) return 2;
        return 1;
    }

    public BoardVO getBoardById(int id) {
        return dao.getBoardById(id);
    }

    public List<BoardVO> getBoardList(String section) {
        return dao.getBoardList(section);
    }

    public PageInfo<BoardVO> getListByPage(int pg, int pageSize, String section) {
        PageInfo<BoardVO> pgInfo = dao.getBoardListPage(pg, pageSize, section);
        //현재페이지에서 -5 +5 해서 최대 11개만 브라우저에 표시한다!
        return pgInfo;
    }

    public boolean updateBoard(BoardVO b) {
        return dao.updateBoard(b);
    }

    public boolean deleteBoard(int id) {
        BoardVO b = new BoardVO();
        b.setId(id);
        return dao.deleteBoard(b);
    }

    //로그인 세션에서 uid로 작성자 변경 섹션 게시판종류
    public int insertComment(CommentVO c) {
        c.setSection("free");
        String writer = (String) session.getAttribute("uid");
        c.setWriter(writer);
        if (writer == null) return 2;
        if (dao.insertComment(c)) return 1;
        return 0;
    }

    public List<CommentVO> getCommentList(int parent_id) {
        return dao.getCommentList(parent_id);
    }

    public boolean deleteComment(int id) {
        return dao.deleteComment(id);
    }

    public boolean updateComment(CommentVO c) {
        return dao.updateComment(c);
    }

    public int boardCnt(int id) {
        return dao.boardCnt(id);
    }

    /*검색기능*/
    public PageInfo<BoardVO> getSearchListByPage(int pg, int i, String section, String cmd, String word) {
        return dao.getSearchListByPage(pg, i, section, cmd, word);
    }

    public int upload(int pid, String filePath) {
        UploadVO uploadVO = new UploadVO();
        //uploadVO.setUid((String) session.getAttribute("uid"));
        uploadVO.setPid(pid);
        uploadVO.setUid("test");
        uploadVO.setImageUrl(filePath);
        return dao.upload(uploadVO);
    }

    public int boardWrite(String section, String title, String content) {
        BoardVO b = new BoardVO();
        b.setTitle(title);
        b.setSection(section);
        b.setContent(content);
        String writer = (String) session.getAttribute("uid");
        b.setWriter(writer);
        return dao.addAndGetKey(b);
    }

    public List<UploadVO> getUploadImageUrl(int id) {
        return dao.getUploadImageUrl(id);
    }

    public CommentVO getCommentById(int id) {
        return dao.getCommentById(id);
    }

}
