package com.qhit.le.gr3b.xzn.hbt01.dao;

import java.util.List;

import com.qhit.le.gr3b.xzn.hbt01.bean.User;

/**
 * @author �����
 *TODO
 *2017��12��11�� ����9:22:43
 */
public interface BaseDao {
	//���
	  public void add(Object obj);
		//ɾ��
	  public void del(Object obj);
		//�޸�
	  public void upd(Object obj);
		//��ѯ
	  public List<User> all(String fromObject);
}
