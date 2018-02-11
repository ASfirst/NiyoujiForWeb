package com.jeramtough.niyouji.dao.mapper;

import com.jeramtough.niyouji.bean.travelnote.Appraise;
import com.jeramtough.niyouji.dao.db.DatabaseProperty;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author 11718
 */
@Mapper
@Repository
public interface AppraiseMapper
{
	@Insert("INSERT INTO " + DatabaseProperty.TABLE_NAME_4 + " VALUES(" + "#{appraiseId}," +
			"#{travelnoteId}," + "#{nickname}," + "#{content}," + "#{createTime})")
	void insertAppraise(Appraise appraise);
	
	@Select("SELECT COUNT(*) FROM " + DatabaseProperty.TABLE_NAME_4 +
			" WHERE travelnote_id=#{travelnoteId}")
	int getAppraisesCountByTravelnoteId(String travelnoteId);
	
	@Select("SELECT * FROM " + DatabaseProperty.TABLE_NAME_4 +
			" WHERE travelnote_id=#{travelnoteId} ORDER BY create_time")
	ArrayList<Appraise> getAppraisesByTravelnoteId(String travelnoteId);
	
}
