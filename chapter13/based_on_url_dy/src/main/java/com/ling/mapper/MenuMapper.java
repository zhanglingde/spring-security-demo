package com.ling.mapper;

import com.ling.entry.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {

    List<Menu> selectAllMenu();
}
