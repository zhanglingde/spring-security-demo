package com.ling.service;

import com.ling.entry.Menu;
import com.ling.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangling
 * @date 2021/10/3 10:08 上午
 */
@Service
public class MenuService {

    @Autowired
    MenuMapper menuMapper;

    public List<Menu> selectAllMenu() {
        return menuMapper.selectAllMenu();
    }
}
