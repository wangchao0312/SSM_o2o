package com.imooc.o2o.service;

import java.io.IOException;
import java.util.List;

import com.imooc.o2o.entity.HeadLine;

public interface HeadLineService {

	/**
	 * 根据传入的条件返回指定的头条列表,只根据这一个条件headLineCondition.enableStatus来查询
	 */
	List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException;
}
