package com.example.flower.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.flower.dto.*;
import com.example.flower.po.AddUserPO;
import com.example.flower.po.Users;
import com.example.flower.vo.PagePara;
import com.example.flower.vo.RE;

public interface UsersService extends IService<Users>{

    /**
     * 注册
     * @param registerDTO (邮箱、密码、验证码、身份)
     * @return 主键id
     */
    RE insert(RegisterDTO registerDTO);

    /**
     * 密码登录
     * @param passwordLoginDTO (邮箱和密码)
     * @return
     */
    RE passwordLogin(PasswordLoginDTO passwordLoginDTO);

    /**
     * 验证码登录
     * @param codeLoginDTO (邮箱和密码)
     * @return
     */
    RE codeLogin(CodeLoginDTO codeLoginDTO);

    /**
     * 找回密码
     * @param getEmailCodeDTO (邮箱、密码、验证码)
     * @return
     */
    RE findPassword(GetEmailCodeDTO getEmailCodeDTO);

    /**
     * 查询全部员工信息
     * @return
     */
    RE selectAllUser(String name,PagePara pagePara);

    /**
     * 添加员工
     * @param addUserDTO
     * @return
     */
    RE addUser(AddUserDTO addUserDTO);

    Users selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Users record);

    RE updateByPrimaryKey(UsersUpdateVO usersUpdateVO);

    int deleteByPrimaryKey(Long id);

    RE logoutByPrimaryKey(Integer id);

    RE findOneUser(Long id);

}
