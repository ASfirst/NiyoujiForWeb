package com.jeramtough.niyouji.business;

import com.jeramtough.niyouji.bean.user.NiyoujiUser;
import com.jeramtough.niyouji.dao.mapper.NiyoujiUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 11718
 */
@Service
public class UserService implements UserBusiness
{
	private NiyoujiUserMapper niyoujiUserMapper;
	
	@Autowired
	public UserService(NiyoujiUserMapper niyoujiUserMapper)
	{
		this.niyoujiUserMapper = niyoujiUserMapper;
	}
	
	@Override
	public NiyoujiUser getNiyoujiUser(String userId)
	{
		NiyoujiUser niyoujiUser = new NiyoujiUser();
		niyoujiUser.setNickname(niyoujiUserMapper.getUserNickname(userId));
		niyoujiUser.setUserId(userId);
		niyoujiUser.setSurfaceImageUrl(niyoujiUserMapper.getSurfaceImageUrl(userId));
		return niyoujiUser;
	}
}
