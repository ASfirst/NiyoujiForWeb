package com.jeramtough.niyouji.dao.mapper;

import com.jeramtough.niyouji.dao.db.RandlDatabaseProperty;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author 11718
 */
@Mapper
@Repository
public interface PrimaryUserMapper
{
	@Select("SELECT nickname FROM " + RandlDatabaseProperty.TABLE_NAME_2 +
			" WHERE user_id = #{userId}")
	String getUserNickname(@Param("userId") String userId);
}
