package com.georsoft.business.mapstruct;

import com.georsoft.business.entity.VO.UserOptionsVO;
import com.georsoft.common.core.domain.entity.SysUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

//@Mapper(componentModel = "spring")
public class UserConvertBasic {
//    UserConvertBasic INSTANCE = Mappers.getMapper(UserConvertBasic.class);

    public static UserOptionsVO toUserOptionsVO(SysUser data){
        if ( data == null ) {
            return null;
        }

        UserOptionsVO userOptionsVO = new UserOptionsVO();
        userOptionsVO.setUserName(data.getUserName());
        userOptionsVO.setUserId(data.getUserId());
        return userOptionsVO;
    }
}
