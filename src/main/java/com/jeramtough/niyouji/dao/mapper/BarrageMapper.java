package com.jeramtough.niyouji.dao.mapper;

import com.jeramtough.niyouji.bean.travelnote.Barrage;
import com.jeramtough.niyouji.dao.db.DatabaseProperty;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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
}
