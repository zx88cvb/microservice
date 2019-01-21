package com.angel.user.dto;

import com.angel.thrift.user.UserInfo;
import com.angel.user.common.DTOConvert;
import com.google.common.base.Converter;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * @author JingXiang Bi
 * @date 2019/1/21
 */
@Data
@Accessors(chain = true)    // 链式调用
public class UserDTO implements Serializable {
    private int id; // required
    private String username; // required
    private String password; // required
    private String realName; // required
    private String email; // required
    private String mobile;

    public UserInfo converToUser() {
        UserInputDTOConvert userInputDTOConvert = new UserInputDTOConvert();
        UserInfo convert = userInputDTOConvert.convert(this);
        return convert;
    }

    public UserDTO converFor(UserInfo userInfo) {
        UserInputDTOConvert userInputDTOConvert = new UserInputDTOConvert();
        UserDTO convert = userInputDTOConvert.reverse().convert(userInfo);
        return convert;
    }

    private static class UserInputDTOConvert extends Converter<UserDTO, UserInfo> {
        /*@Override
        public UserInfo convert(UserDTO userDTO) {
            UserInfo userInfo = new UserInfo();
            BeanUtils.copyProperties(userDTO,userInfo);
            return userInfo;
        }*/

        @Override
        protected UserInfo doForward(UserDTO userDTO) {
            UserInfo userInfo = new UserInfo();
            BeanUtils.copyProperties(userDTO,userInfo);
            return userInfo;
        }

        @Override
        protected UserDTO doBackward(UserInfo userInfo) {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(userInfo,userDTO);
            return userDTO;
        }
    }
}
