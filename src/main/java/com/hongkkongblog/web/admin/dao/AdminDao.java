package com.hongkkongblog.web.admin.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDao
{
	@Autowired
	private SqlSession sqlSession;
}
