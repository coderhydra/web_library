package com.example.libraryweb.cont;

import com.example.libraryweb.svc.BookSVC;
import com.example.libraryweb.svc.UserSVC;
import com.example.libraryweb.vo.BookVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/library")
public class BookCont {

    @Autowired
    private BookSVC bookSvc;

    @Autowired
    private UserSVC uSvc;

    @GetMapping("bookSearch")
    public String bookSerch() {
        return "/book/bookSearch";
    }

    @GetMapping("bookSearch/{keyWord}")
    public String bookSearch(@PathVariable("keyWord") String keyWord,
                             Model m) {
        m.addAttribute("bookList", bookSvc.search(keyWord));
        //페이지헬퍼 연결
        return "/book/bookSearchList";
    }

    @GetMapping("/hotbook")
    public String bookSearch(Model m) {
        m.addAttribute("bookList", bookSvc.getHot());
        //페이지헬퍼 연결
        return "/book/bookSearchList";
    }

    @GetMapping("/bookDetail/{isbn}")
    public String bookDetail(@PathVariable("isbn") String isbn,
                             Model m) {
        m.addAttribute("book", bookSvc.searchIsbn(isbn));
        return "/book/bookDetail";
    }

    @GetMapping("/cart")
    public String showCart(Model m) {
        m.addAttribute("list", bookSvc.showCart());
        return "/book/showCart";
    }

    @PostMapping("/cart/tocart")
    @ResponseBody
    public String toCart(BookVO book) {
        return String.format("{\"ok\":%b}", bookSvc.toCart(book));
    }

    @GetMapping("/cart/delete/{isbn}")
    public String deleteBook(@PathVariable("isbn") String isbn, Model model) {
        bookSvc.delete(isbn);
        return "redirect:/library/cart";
    }

    @GetMapping("/cartempty")
    public String cartEmpty() {
        bookSvc.cartEmpty();
        return "redirect:/library/cart";
    }

    @PostMapping("/cart/rent/{isbn}")
    @ResponseBody
    public String rent(@PathVariable("isbn") String isbn) {
        String res = String.format("{\"msg\":\"%s\"}", bookSvc.rent(isbn));
        return res;
    }

    @PostMapping("/cart/reserve/{isbn}")
    @ResponseBody
    public String reserve(@PathVariable("isbn") String isbn) {
        return String.format("{\"msg\":\"%s\"}", bookSvc.reserve(isbn));
    }

    @PostMapping("/cart/delivery/{isbn}")
    @ResponseBody
    public String delivery(@PathVariable("isbn") String isbn) {
        return String.format("{\"msg\":\"%s\"}", bookSvc.delivery(isbn));
    }

    @GetMapping("/myShelf")
    public String myShelf(Model m) {
        m.addAttribute("reserveList", bookSvc.reserveList());
        m.addAttribute("rentedList", bookSvc.rentedList());
        m.addAttribute("rentingList", bookSvc.rentingList());
        return "thymeleaf/myShelf";
    }
}