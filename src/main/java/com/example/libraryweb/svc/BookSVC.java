package com.example.libraryweb.svc;

import com.example.libraryweb.dao.BookDAO;
import com.example.libraryweb.dao.RecordDAO;
import com.example.libraryweb.util.Cart;
import com.example.libraryweb.vo.BookVO;
import com.example.libraryweb.vo.RecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookSVC {

    @Autowired
    private HttpSession session;

    @Autowired
    private BookDAO book;

    @Autowired
    private RecordDAO recordDao;

    @Autowired
    private Cart cart;

    public List<BookVO> search(String keyWord) {
        return book.search(keyWord);
    }

    public List<BookVO> getSearch(String keyWord) {
        return book.getSearch(keyWord);
    }

    public Object searchIsbn(String isbn) {
        return book.searchIsbn(isbn);
    }

    public List<BookVO> getHot() {
        return book.getHot();
    }

    public boolean toCart(BookVO book) {
        if (session.getAttribute("cart") == null) {
            session.setAttribute("cart", cart);
            System.err.println("서비스에서 카트주기");
        }
        Cart cart = (Cart) session.getAttribute("cart");
        return cart.add(book);
    }

    public List<RecordVO> showCart() {
        Cart cart = (Cart) session.getAttribute("cart");
//	카트 리스트는 대출상태 확인이 필요하다..
//리스트를 받아서 db에서 대출/예약 정보를 추가 한다.
        List<RecordVO> newList = new ArrayList<>();
        List<BookVO> list = new ArrayList<>();
        list = cart.getList();
        for (int i = 0; i < list.size(); i++) {
            BookVO book = new BookVO();
            book = list.get(i);
            RecordVO castRecord = new RecordVO();
            // booklist 에 대출현황을 포함함 recordVO로 가공 반환하다.
            castRecord.setIsbn(book.getIsbn());
            castRecord.setTitle(book.getTitle());
            castRecord.setAuthor(book.getAuthor());
            castRecord.setPublisher(book.getPublisher());
            castRecord.setImageUrl(book.getImageUrl());
            if (status(castRecord)) {
                castRecord.setReturn_time("대출가능");
            } else {
                castRecord.setReturn_time("대출중");
            }
            castRecord.setReserve(reserveNo(book.getIsbn()));
//		castRecord.setIsbn(book.getIsbn());
//		castRecord.setRent_time(db)
//		castRecord.setReturn_time(db);
//		castRecord.setReserve(db);
            newList.add(castRecord);
        }
        return newList;
    }

    public boolean delete(String isbn) {
        Cart cart = (Cart) session.getAttribute("cart");
        return cart.delete(isbn);
    }

    public boolean cartEmpty() {
        Cart cart = (Cart) session.getAttribute("cart");
        return cart.cartEmpty();

    }

    public String rent(String isbn) {
        String uid = (String) session.getAttribute("uid");
        if (uid == null) return "로그인한 사용자만 사용가능한 기능입니다";
        if (!(rentable(uid))) return "이미 3개이상의 도서를 대출중입니다.(대출가능 도서는 3개입니다.)";
        Cart cart = (Cart) session.getAttribute("cart");
        List<BookVO> list = cart.getList();
        RecordVO record = new RecordVO();
        for (int i = 0; i < cart.getList().size(); i++) {
            record.setUid(uid);
            record.setIsbn(list.get(i).getIsbn());
            record.setImageUrl(list.get(i).getImageUrl());
            record.setAuthor(list.get(i).getAuthor());
            record.setTitle(list.get(i).getTitle());
            record.setPublisher(list.get(i).getPublisher());
        }
        //대출확인 로직 디비에서 응답하면 대출중이다.
        if (status(record)) {
            if (recordDao.insertRent(record) > 0) {
                return "대출되었습니다";
            }
            return "대출에 실패하였습니다";
        }
        return "대출중인 도서입니다";
    }

    public String reserve(String isbn) {
        String uid = (String) session.getAttribute("uid");
        System.err.println("서비스 아이디=" + uid);
        if (uid == null) {
            System.err.println("서비스 아이디=" + uid);
            return "로그인한 사용자만 사용가능한 기능입니다";
        }
        Cart cart = (Cart) session.getAttribute("cart");
        List<BookVO> list = cart.getList();
        RecordVO record = new RecordVO();
        for (int i = 0; i < list.size(); i++) {
            if (isbn.equals(list.get(i).getIsbn())) {
                record.setUid(uid);
                record.setIsbn(list.get(i).getIsbn());
                record.setImageUrl(list.get(i).getImageUrl());
                record.setAuthor(list.get(i).getAuthor());
                record.setTitle(list.get(i).getTitle());
                record.setPublisher(list.get(i).getPublisher());
            }
        }
        int no = recordDao.insertReserve(record);
        if (no > 0) {
            return Integer.toString(reserveNo(isbn));
        }
        return null;
    }

    public int reserveNo(String isbn) {
        RecordVO record = new RecordVO();
        record.setIsbn(isbn);
        return recordDao.reserveNo(record);
    }

    public String delivery(String isbn) {
        String uid = (String) session.getAttribute("uid");
        if (uid == null) return "로그인한 사용자만 사용가능한 기능입니다";
        if (!(rentable(uid))) return "이미 3개이상의 도서를 대출중입니다.(대출가능 도서는 3개입니다.)";
        Cart cart = (Cart) session.getAttribute("cart");
        List<BookVO> list = cart.getList();
        RecordVO record = new RecordVO();
        for (int i = 0; i < cart.getList().size(); i++) {
            if (isbn.equals(list.get(i).getIsbn())) {
                record.setUid(uid);
                record.setIsbn(list.get(i).getIsbn());
                record.setImageUrl(list.get(i).getImageUrl());
                record.setAuthor(list.get(i).getAuthor());
                record.setTitle(list.get(i).getTitle());
                record.setPublisher(list.get(i).getPublisher());
            }
        }
        //대출확인 로직 디비에서 응답하면 대출중이다.
        if (status(record)) {
            int no = recordDao.insertDelivery(record);
            if (no > 0) {
                return "배달 신청되었습니다.";
            }
            return "배달 신청에 실패했습니다.";
        }
        return "대출중인 도서입니다.";
    }

    //대출확인메소드 recordVO를 넣으면 대출확인
    public boolean status(RecordVO record) {
        if (recordDao.status(record) == null) {
            return true;
        }
        return false;
    }

    //사용자의 대출현황을 파악하여 대출가능 도서가 3개를 넘지 않는지 확인한다.
    public boolean rentable(String uid) {
        List<RecordVO> list = recordDao.getRecordsById(uid);
        int token = 0;
        for (int i = 0; i < list.size(); i++) {
            RecordVO record = list.get(i);
            if ((record.getRent_time() != null) && (record.getReturn_time() == null)) {
                token += 1;
            }
        }
        if (token < 3) return true;
        return false;
    }

    //이용자의 아이디를 세션에서추출 예약 목록을 보여준다.
    public List<RecordVO> reserveList() {
        String uid = (String) session.getAttribute("uid");
        return recordDao.getReserveListById(uid);
    }

    public List<RecordVO> rentedList() {
        String uid = (String) session.getAttribute("uid");
        return recordDao.getRecordsById(uid);
    }

    public List<RecordVO> rentingList() {
        List<RecordVO> recordList = rentedList();
        List<RecordVO> newList = new ArrayList<>();
        for (int i = 0; i < recordList.size(); i++) {
            RecordVO record = recordList.get(i);
            if (record.getReturn_time() == null) {
                newList.add(record);
            }
        }
        return newList;
    }

}