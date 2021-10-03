package com.ling.entry;

import lombok.Data;

import java.util.List;

/**
 * @author zhangling
 * @date 2021/10/3 9:34 上午
 */
@Data
public class Menu {
    private Integer menuId;
    private String url;
    // 表示访问该资源所需要的角色
    private List<Role> roles;

}
