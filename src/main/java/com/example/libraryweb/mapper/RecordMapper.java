package com.example.libraryweb.mapper;

import com.example.libraryweb.vo.RecordVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecordMapper {

    int insertRecord(RecordVO r);

    int insertReserve(RecordVO r);

    Integer reserveNo(RecordVO r);

    int insertDelivery(RecordVO record);

    List<RecordVO> getRentStatusByIsbn(RecordVO vo);

    int insertStatus(RecordVO record);

    List<RecordVO> getRecordsById(String uid);

    List<RecordVO> getReserveListById(String uid);
}
