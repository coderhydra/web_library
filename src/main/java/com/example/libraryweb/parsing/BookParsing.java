package com.example.libraryweb.parsing;

import com.example.libraryweb.vo.BookVO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/* 인터파크 도서 openapi parsing
 * 검색어를 파라미터로 받아서 검색결과를 리스트로 리턴한다*/
public class BookParsing {
    //검색
    public List<BookVO> search(String keyWord) {
        // 인증키 (개인이 받아와야함)
        String key = "E4382798261957A5F3B708916542BA8E5AF936F427FC7D8C7826F22C11C7FDEC";
        // 검색 결과 초기화
        String result = "";
        // 문자 인코딩
        String encodeKey;
        //debug
        try {
            encodeKey = URLEncoder.encode(keyWord, "UTF-8");
            /*인기 100 url*/
            URL url = new URL("http://book.interpark.com/api/bestSeller.api?key="
                    + key + "&categoryId=100&output=json");
            /*도서 검색 url*/
            url = new URL(
                    "https://book.interpark.com/api/search.api?key="
                            + key
                            + "&query="
                            + encodeKey
                            + "&maxResults=99"
                            + "&output=json"
            );
            BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = bf.readLine();
            //System.err.println("json 응답="+result);
            // json 문자열 스플릿
            JSONParser parser = new JSONParser();
            JSONObject bookData = (JSONObject) parser.parse(result);
            //System.err.println(bookData.toJSONString());
            JSONArray arr = (JSONArray) bookData.get("item");
            //System.err.println(arr.toJSONString());
            //return할 리스트 객체 초기화
            List<BookVO> list = new ArrayList<>();
            for (int i = 0; i < arr.size(); i++) {
                JSONObject tmp = (JSONObject) arr.get(i);//인덱스 번호로 접근해서 가져온다.
                String isbn = (String) tmp.get("isbn");
                String title = (String) tmp.get("title");
                String imageUrl = (String) tmp.get("coverLargeUrl");
                String author = (String) tmp.get("author");
                String translator = (String) tmp.get("translator");
                String publisher = (String) tmp.get("publisher");
                String pubDate = (String) tmp.get("pubDate");
                String description = (String) tmp.get("description");
                Long reviewCount = (Long) tmp.get("reviewCount");
                Object reviewRank = (Object) tmp.get("customerReviewRank");
                //book list add
                BookVO book = new BookVO();
                book.setIsbn(isbn);
                book.setTitle(title);
                book.setAuthor(author);
                book.setTranslator(translator);
                book.setImageUrl(imageUrl);
                book.setPublisher(publisher);
                book.setPubDate(pubDate);
                book.setDescription(description);
                book.setReviewCount(reviewCount);
                book.setReviewRank(reviewRank);
                list.add(book);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //isbn 검색
//isbn 검색
    public BookVO searchIsbn(String isbn) {
        String key = "E4382798261957A5F3B708916542BA8E5AF936F427FC7D8C7826F22C11C7FDEC";
        String result = "";
        try {
            URL url = new URL(
                    "https://book.interpark.com/api/search.api?key="
                            + key
                            + "&query="
                            + isbn
                            + "&queryType=isbn&output=json"
            );
            BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = bf.readLine();
            //System.err.println("json 응답="+result);
            // json 문자열 스플릿
            JSONParser parser = new JSONParser();
            JSONObject bookData = (JSONObject) parser.parse(result);
            JSONArray arr = (JSONArray) bookData.get("item");
            JSONObject tmp = (JSONObject) arr.get(0);
            String title = (String) tmp.get("title");
            String imageUrl = (String) tmp.get("coverLargeUrl");
            String author = (String) tmp.get("author");
            String translator = (String) tmp.get("translator");
            String publisher = (String) tmp.get("publisher");
            String pubDate = (String) tmp.get("pubDate");
            String description = (String) tmp.get("description");
            Long reviewCount = (Long) tmp.get("reviewCount");
            Object reviewRank = (Object) tmp.get("customerReviewRank");
            BookVO book = new BookVO();
            book.setIsbn(isbn);
            book.setTitle(title);
            book.setAuthor(author);
            book.setTranslator(translator);
            book.setImageUrl(imageUrl);
            book.setPublisher(publisher);
            book.setPubDate(pubDate);
            book.setDescription(description);
            book.setReviewCount(reviewCount);
            book.setReviewRank(reviewRank);
            return book;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<BookVO> hot() {
        // 인증키 (개인이 받아와야함)
        String key = "E4382798261957A5F3B708916542BA8E5AF936F427FC7D8C7826F22C11C7FDEC";
        // 검색 결과 초기화
        String result = "";
        // 문자 인코딩
        String encodeKey;
        //debug
        try {
            /*인기 100 url*/
            URL url = new URL("http://book.interpark.com/api/bestSeller.api?key="
                    + key + "&categoryId=100&output=json");
            BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = bf.readLine();
            //System.err.println("json 응답="+result);
            // json 문자열 스플릿
            JSONParser parser = new JSONParser();
            JSONObject bookData = (JSONObject) parser.parse(result);
            //System.err.println(bookData.toJSONString());
            JSONArray arr = (JSONArray) bookData.get("item");
            //System.err.println(arr.toJSONString());
            //return할 리스트 객체 초기화
            List<BookVO> list = new ArrayList<>();
            for (int i = 0; i < arr.size(); i++) {
                JSONObject tmp = (JSONObject) arr.get(i);//인덱스 번호로 접근해서 가져온다.
                String isbn = (String) tmp.get("isbn");
                String title = (String) tmp.get("title");
                String imageUrl = (String) tmp.get("coverLargeUrl");
                String author = (String) tmp.get("author");
                String translator = (String) tmp.get("translator");
                String publisher = (String) tmp.get("publisher");
                String pubDate = (String) tmp.get("pubDate");
                String description = (String) tmp.get("description");
                Long reviewCount = (Long) tmp.get("reviewCount");
                Object reviewRank = (Object) tmp.get("customerReviewRank");
                //book list add
                BookVO book = new BookVO();
                book.setIsbn(isbn);
                book.setTitle(title);
                book.setAuthor(author);
                book.setTranslator(translator);
                book.setImageUrl(imageUrl);
                book.setPublisher(publisher);
                book.setPubDate(pubDate);
                book.setDescription(description);
                book.setReviewCount(reviewCount);
                book.setReviewRank(reviewRank);
                list.add(book);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}//end C
