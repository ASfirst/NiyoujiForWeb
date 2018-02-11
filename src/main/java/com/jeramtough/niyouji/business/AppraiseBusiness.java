package com.jeramtough.niyouji.business;

import com.jeramtough.niyouji.bean.travelnote.Appraise;

/**
 * @author 11718
 */
public interface AppraiseBusiness
{
	void addAppraise(Appraise appraise);
	
	int getAppraisesCount(String travelnoteId);
}
