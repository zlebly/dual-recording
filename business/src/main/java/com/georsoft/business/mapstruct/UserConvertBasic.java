package com.georsoft.business.mapstruct;

import com.georsoft.business.entity.VO.UserOptionsVO;
import com.georsoft.common.core.domain.entity.UsrUsers;

//@Mapper(componentModel = "spring")
public class UserConvertBasic {
//    UserConvertBasic INSTANCE = Mappers.getMapper(UserConvertBasic.class);

    public static UserOptionsVO toUserOptionsVO(UsrUsers data){
        if ( data == null ) {
            return null;
        }

        UserOptionsVO userOptionsVO = new UserOptionsVO();
        userOptionsVO.setUserName(data.getUserName());
        userOptionsVO.setUserId(data.getId());
        return userOptionsVO;
    }
}
