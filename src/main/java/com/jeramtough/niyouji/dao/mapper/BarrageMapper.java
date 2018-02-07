package com.jeramtough.niyouji.dao.mapper;

import com.jeramtough.niyouji.bean.travelnote.Barrage;
import com.jeramtough.niyouji.dao.db.DatabaseProperty;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author 11718
 */
@Mapper
@Repository
public interface BarrageMapper
{
	@Insert("INSERT INTO " + DatabaseProperty.TABLE_NAME_3 + " VALUES(" + "#{barrageId}," +
			"#{pageId}," + "#{travelnoteId}," + "#{nickname}," + "#{isPerformers}," +
			"#{content}," + "#{createTime})")
	void insertBarrage(Barrage barrage);
	
	@Select("SELECT COUNT(*) FROM " + DatabaseProperty.TABLE_NAME_3 + " WHERE " +
			"travelnote_id=#{travelnoteId}")
	int getBarrageCountByTravelnoteId(String travelnoteId);
	
	@Select("SELECT * FROM " + DatabaseProperty.TABLE_NAME_3 + " WHERE " +
			"travelnote_id=#{travelnoteId} AND page_id=#{pageId}")
	ArrayList<Barrage> getBarragesByTravelnoteIdAndPageId(
			@Param("travelnoteId") String travelnoteId, @Param("pageId") String pageId);
}
