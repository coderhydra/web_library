package com.example.libraryweb.dao;

import com.example.libraryweb.parsing.BookParsing;
import com.example.libraryweb.vo.BookVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDAO {

    private BookParsing bp = new BookParsing();

    public List<BookVO> search(String keyWord) {
        return bp.search(keyWord);
    }

    public Object searchIsbn(String isbn) {
        return bp.searchIsbn(isbn);
    }

    public List<BookVO> getSearch(String keyWord) {
        return bp.search(keyWord);
    }

    public List<BookVO> getHot() {
        return bp.hot();
    }
}
