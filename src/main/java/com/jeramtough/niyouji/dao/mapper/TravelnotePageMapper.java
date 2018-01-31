package com.jeramtough.niyouji.dao.mapper;

import com.jeramtough.niyouji.bean.travelnote.TravelnotePage;
import com.jeramtough.niyouji.dao.db.DatabaseProperty;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator
 * on 2017  August 21 Monday 12:50.
 *
 * @author 11718
 */
@Mapper
@Repository
public interface TravelnotePageMapper
{
	
	@Insert("INSERT INTO " + DatabaseProperty.TABLE_NAME_2 + " VALUES(" + "#{pageId}," +
			"#{travelnoteId}," + "#{createTime}," + "#{pageType}," + "#{themePosition}," +
			"#{resourceUrl}," + "#{textContent}," + "#{backgroundMusicPath})")
	void insertTravelnotePage(TravelnotePage travelnotePage);
	
	@Select("SELECT COUNT(*) FROM " + DatabaseProperty.TABLE_NAME_2)
	int getTravelnotePagesTotality();
	
	@Select("SELECT page_id FROM " + DatabaseProperty.TABLE_NAME_2 +
			" ORDER BY page_id DESC LIMIT 1;")
	int getLastPageId();
}
