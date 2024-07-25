package com.georsoft.business.entity.VO;

import lombok.Data;

@Data
public class UserOptionsVO {
    /** 用户ID */
    private Long userId;

    /** 用户账号 */
    private String loginName;

    /** 用户账号 */
    private String userName;
}
