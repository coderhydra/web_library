package com.example.libraryweb.mapper;

import com.example.libraryweb.vo.BoardVO;
import com.example.libraryweb.vo.CommentVO;
import com.example.libraryweb.vo.UploadVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    int insertBoard(BoardVO b);

    int addAndGetKey(BoardVO b);

    BoardVO getBoardById(int id);

    List<BoardVO> getBoardList(String section);

    int updateBoard(BoardVO b);

    //int deleteBoard(int id);
    BoardVO findWithoutId(BoardVO b);

    int deleteBoard(BoardVO b);

    int insertComment(CommentVO c);

    List<CommentVO> getCommentList(int parent_id);

    int deleteComment(int id);

    int updateComment(CommentVO c);

    //조회수 카운트
    int boardCnt(int id);

    //검색기능
    List<BoardVO> getSearchTitle(BoardVO key);

    List<BoardVO> getSearchBoth(BoardVO key);

    List<BoardVO> getSearchContent(BoardVO key);

    List<BoardVO> getSearchWriter(BoardVO key);

    int UploadAndGetKey(UploadVO uploadVO);

    List<UploadVO> getUploadImageUrl(int id);

    CommentVO getCommentById(int id);
}
