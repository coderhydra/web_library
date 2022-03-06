package com.example.libraryweb.dao;

import com.example.libraryweb.mapper.RecordMapper;
import com.example.libraryweb.vo.RecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecordDAO {

    @Autowired
    private RecordMapper mapper;

    public int insertRent(RecordVO record) {
        mapper.insertStatus(record);
        return mapper.insertRecord(record);
    }

    public int insertReserve(RecordVO record) {
        return mapper.insertReserve(record);
    }

    public int reserveNo(RecordVO record) {
        Integer result = mapper.reserveNo(record);
        if (result == null) return 0;
        return result;
    }

    public int insertDelivery(RecordVO record) {
        return mapper.insertDelivery(record);
    }

    public RecordVO status(RecordVO isbn) {
        List<RecordVO> list = mapper.getRentStatusByIsbn(isbn);
        RecordVO record = null;
        if (list.size() == 0) return null;
        record = list.get(0);
        return record;
    }

    public List<RecordVO> getRecordsById(String uid) {
        return mapper.getRecordsById(uid);
    }

    public List<RecordVO> getReserveListById(String uid) {
        return mapper.getReserveListById(uid);
    }


}
