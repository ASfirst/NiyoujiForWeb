package com.jeramtough.niyouji.dao.mapper;

import com.jeramtough.niyouji.bean.travelnote.Travelnote;
import com.jeramtough.niyouji.bean.travelnote.TravelnotePage;
import com.jeramtough.niyouji.dao.db.DatabaseProperty;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author 11718
 */
@Mapper
@Repository
public interface TravelnoteMapper
{
	@Insert("INSERT INTO " + DatabaseProperty.TABLE_NAME_1 + " VALUES(" + "#{travelnoteId}," +
			"#{performerId}," + "#{createTime}," + "#{coverType}," + "#{coverResourceUrl}," +
			"#{travelnoteTitle}," + "#{attentionsCount})")
	void insertTravelnote(Travelnote travelnote);
	
	@Select("SELECT COUNT(*) FROM " + DatabaseProperty.TABLE_NAME_1)
	int getTravelnotesTotality();
	
	@Select("SELECT travelnote_id FROM " + DatabaseProperty.TABLE_NAME_1 +
			" ORDER BY travelnote_id DESC LIMIT 1;")
	int getLastTravelnoteId();
}
