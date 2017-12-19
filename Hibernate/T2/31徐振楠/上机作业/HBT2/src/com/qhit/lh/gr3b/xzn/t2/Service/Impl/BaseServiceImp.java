package com.qhit.lh.gr3b.xzn.t2.Service.Impl;

import java.util.List;

import com.qhit.lh.gr3b.xzn.t2.Service.BaseService;
import com.qhit.lh.gr3b.xzn.t2.dao.BaseDao;
import com.qhit.lh.gr3b.xzn.t2.dao.Imp.BaseDaoImp;

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
	public List<Object> all(String fromObject) {
		// TODO Auto-generated method stub
		return bas.all(fromObject);
	}

	@Override
	public Object getObjectById(Object obj, int id) {
		// TODO Auto-generated method stub
		return bas.getObjectById(obj, id);
	}


}
