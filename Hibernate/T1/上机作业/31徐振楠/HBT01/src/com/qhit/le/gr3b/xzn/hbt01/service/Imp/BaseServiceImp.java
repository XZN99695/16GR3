package com.qhit.le.gr3b.xzn.hbt01.service.Imp;

import java.util.List;

import com.qhit.le.gr3b.xzn.hbt01.bean.User;
import com.qhit.le.gr3b.xzn.hbt01.dao.BaseDao;
import com.qhit.le.gr3b.xzn.hbt01.dao.Imp.BaseDaoImp;
import com.qhit.le.gr3b.xzn.hbt01.service.BaseService;

public class BaseServiceImp implements BaseService {
 private BaseDao bas = new BaseDaoImp();
	@Override
	public void add(Object obj) {
		// TODO Auto-generated method stub
		bas.add(obj);
	}

	@Override
	public void del(Object obj) {
		// TODO Auto-generated method stub
		bas.del(obj);
	}

	@Override
	public void upd(Object obj) {
		// TODO Auto-generated method stub
		bas.upd(obj);
	}

	@Override
	public List<User> all(String fromObject) {
		// TODO Auto-generated method stub
		return bas.all(fromObject);
	}

}
