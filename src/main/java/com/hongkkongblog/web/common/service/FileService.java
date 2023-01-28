package com.hongkkongblog.web.common.service;

import java.util.HashMap;

import org.springframework.ui.Model;

public interface FileService
{

	int insert(HashMap<String, String> rs_PARAM, Model model);

}
