package com.hongkkongblog.web.admin.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminFileDao
{
	@Autowired
	private SqlSession sqlSession;

	public int fileInsert(Map<String, Object> tempMap)
	{
		// TODO Auto-generated method stub
		return sqlSession.update("file.insert", tempMap);
	}
	
//	public int fileInsert(HashMap<String,Object> map)
//	{
//		// TODO Auto-generated method stub
//		return sqlSession.update("crudFile.fileInsert", map);
//	}


}
