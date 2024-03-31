package com.example.flower.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.flower.constant.RedisConstant;
import com.example.flower.dao.RealinfoMapper;
import com.example.flower.dao.UsersMapper;
import com.example.flower.dto.*;
import com.example.flower.enums.HttpStatusEnum;
import com.example.flower.po.AddUserPO;
import com.example.flower.po.Realinfo;
import com.example.flower.po.Users;
import com.example.flower.service.UsersService;
import com.example.flower.util.PageResultS;
import com.example.flower.util.StringUtil;
import com.example.flower.util.TokenUtils;
import com.example.flower.vo.OneUserVO;
import com.example.flower.vo.PagePara;
import com.example.flower.vo.RE;
import com.example.flower.vo.userVO;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service
@Transactional
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService{


    @Resource
    private TokenUtils tokenUtils;

    @Resource
    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RealinfoMapper realinfoMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return usersMapper.deleteByPrimaryKey(id);
    }

    @Override
    public RE logoutByPrimaryKey(Integer id) {
        if (id == null){
            return RE.error().message("参数不能为空！");
        }else {
//            在users表删除
            int re = usersMapper.logoutByPrimaryKey(id);
            if (re != 0){
//                在realinfo表查询有无该用户的认证信息，如果有则删除
                int ree = realinfoMapper.deleteByUserId(Long.valueOf(id));
                System.out.println(ree);
                return RE.ok().message("注销成功！");
            }else {
                return RE.error().message("该账户注销失败或者已注销！");
            }
        }

    }

    @Override
    public RE findOneUser(Long id) {
        OneUserVO oneUserVO = usersMapper.findOneUser(id);
        if (oneUserVO != null){
            return RE.ok().data("result",oneUserVO);
        }
        return RE.error();
    }

    /**
     * 注册
     * @param registerDTO (邮箱、密码、验证码、身份)
     * @return
     */
    @Override
    public RE insert(RegisterDTO registerDTO) {

        if (registerDTO == null) {
            return RE.error(HttpStatusEnum.PARAM_ILLEGAL);
        }
        // 获取参数
        String email = registerDTO.getEmail();
        String password = registerDTO.getPassword();
        String code = registerDTO.getCode();
        String role = registerDTO.getRole();

        if (StringUtils.isAnyBlank(email, password, code)) {
            // 非空
            return RE.error(HttpStatusEnum.PARAM_ILLEGAL);
        }else if (!StringUtil.checkEmail(email)) {
            // 邮箱格式校验
            return RE.error(HttpStatusEnum.EMAIL_ERROR);
        }else if (!StringUtil.checkPassword(password) || code.length() != 6) {
            // 密码格式和验证码长度校验
            return RE.error(HttpStatusEnum.PARAM_ILLEGAL);
        }

        // 构造查询条件对象
        QueryWrapper<Users> wrapper = new QueryWrapper<>();
        wrapper.select("id");
        wrapper.eq("email", email);
        wrapper.last("limit 1");

        // 查询用户，是否存在
        if (this.baseMapper.selectOne(wrapper) != null) {
            return RE.error(HttpStatusEnum.EMAIL_ALREADY_EXIST);
        }

        // 获取正确的验证码
        String rightCode = redisTemplate.opsForValue().get(RedisConstant.EMAIL + email);
        if (!code.equals(rightCode)) {
            // 验证码比对
            return RE.error(HttpStatusEnum.CODE_ERROR);
        }

        // 删除验证码
        redisTemplate.delete(RedisConstant.EMAIL + email);

        // 注册用户
        Users users = new Users();
        // 获取加密盐
        String salt = StringUtil.randomEncryptedSalt();
        // 邮箱
        users.setEmail(email);
        // 密码加密（原明文密码 + 随机加密盐） md5加密
        users.setPassword(DigestUtils.md5Hex(password + salt));
        // 加密盐
        users.setSalt(salt);
        // 身份
        users.setRole(role);

        registerDTO.setPassword(DigestUtils.md5Hex(password + salt));
        registerDTO.setSalt(salt);

        // 插入数据
        return usersMapper.insert(registerDTO) == 0 ? RE.error(HttpStatusEnum.UNKNOWN_ERROR) : RE.ok();
    }

    /**
     * 账号密码登录
     * @param passwordLoginDTO (邮箱和密码)
     * @return
     */
    @Override
    public RE passwordLogin(PasswordLoginDTO passwordLoginDTO) {
        if (passwordLoginDTO == null) {
            return RE.error(HttpStatusEnum.PARAM_ILLEGAL);
        }

        // 获取参数
        String email = passwordLoginDTO.getEmail();
        String password = passwordLoginDTO.getPassword();

        if (StringUtils.isAnyBlank(email, password)) {
            // 非空
            return RE.error(HttpStatusEnum.PARAM_ILLEGAL);
        }else if (!StringUtil.checkEmail(email)) {
            // 邮箱格式校验
            return RE.error(HttpStatusEnum.EMAIL_ERROR);
        }else if (!StringUtil.checkPassword(password)) {
            // 密码格式
            return RE.error(HttpStatusEnum.PASSWORD_ERROR);
        }

        // 构件条件对象 select salt from user where email = #{email} limit 1
        QueryWrapper<Users> wrapper = new QueryWrapper<>();
        wrapper.select("salt");
        wrapper.eq("email", email);
        wrapper.last("limit 1");

        // 查询结果
        Users users = this.baseMapper.selectOne(wrapper);
        if (users == null) {
            // 用户不存在
            return RE.error(HttpStatusEnum.USER_NOT_EXIST);
        }

        // 获取加密盐
        String salt = users.getSalt();
        // 重新设置条件 select id,email,password,role from users where email = #{email} and password #{password} limit 1
        QueryWrapper<Users> wrapper2 = new QueryWrapper<>();
        wrapper2.select("id","email","password","role");
        wrapper2.eq("email", email);
        wrapper2.eq("password", DigestUtils.md5Hex(password + salt));
        wrapper2.last("limit 1");
        // 查询用户
        users = this.baseMapper.selectOne(wrapper2);
        System.out.println(users);
//        Users users1= new Users();
//        userVO uv= usersMapper.selectByEmail(email);
//        users1.setEmail(uv.getEmail());
//        users1.setRole(uv.getRole());
//        System.out.println(users1);
        return users == null ? RE.error(HttpStatusEnum.PASSWORD_ERROR) : RE.ok().data("token",tokenUtils.createToken(users));
    }

    /**
     * 验证码登录
     * @param codeLoginDTO (邮箱和验证码)
     * @return
     */
    @Override
    public RE codeLogin(CodeLoginDTO codeLoginDTO) {
        if (codeLoginDTO == null) {
            return RE.error(HttpStatusEnum.PARAM_ILLEGAL);
        }

        // 获取参数
        String email = codeLoginDTO.getEmail();
        String code = codeLoginDTO.getCode();

        if (StringUtils.isAnyBlank(email)) {
            // 非空
            return RE.error(HttpStatusEnum.PARAM_ILLEGAL);
        }else if (!StringUtil.checkEmail(email)) {
            // 邮箱格式校验
            return RE.error(HttpStatusEnum.EMAIL_ERROR);
        }

        // 构件条件对象 select id from users where email = #{email} limit 1
        QueryWrapper<Users> wrapper = new QueryWrapper<>();
        wrapper.select("id","email","role");
        wrapper.eq("email", email);
        wrapper.last("limit 1");

        // 查询结果
        Users users = this.baseMapper.selectOne(wrapper);
        if (users == null) {
            // 用户不存在
            return RE.error(HttpStatusEnum.USER_NOT_EXIST);
        }

        // 获取正确的验证码
        String rightCode = redisTemplate.opsForValue().get(RedisConstant.EMAIL + email);
        if (!code.equals(rightCode)) {
            // 验证码比对
            return RE.error(HttpStatusEnum.CODE_ERROR);
        }

        // 删除验证码
        redisTemplate.delete(RedisConstant.EMAIL + email);

        return RE.ok().data("token",tokenUtils.createToken(users));
    }

    /**
     *
     * @param getEmailCodeDTO (邮箱、密码、验证码)
     * @return
     */
    @Override
    public RE findPassword(GetEmailCodeDTO getEmailCodeDTO) {
        if (getEmailCodeDTO == null) {
            return RE.error(HttpStatusEnum.PARAM_ILLEGAL);
        }

        // 获取参数
        String email = getEmailCodeDTO.getEmail();
        String password = getEmailCodeDTO.getPassword();
        String code = getEmailCodeDTO.getCode();

        if (StringUtils.isAnyBlank(email, password, code)) {
            // 非空
            return RE.error(HttpStatusEnum.PARAM_ILLEGAL);
        }else if (!StringUtil.checkEmail(email)) {
            // 邮箱格式校验
            return RE.error(HttpStatusEnum.EMAIL_ERROR);
        }else if (!StringUtil.checkPassword(password) || code.length() != 6) {
            // 密码格式和验证码长度校验
            return RE.error(HttpStatusEnum.PARAM_ILLEGAL);
        }

        // 构造查询条件对象
        QueryWrapper<Users> wrapper = new QueryWrapper<>();
        wrapper.select("id", "salt");
        wrapper.eq("email", email);
        wrapper.last("limit 1");

        // 查询用户，是否存在
        Users user = this.baseMapper.selectOne(wrapper);
        if (user == null) {
            return RE.error(HttpStatusEnum.USER_NOT_EXIST);
        }

        // 获取正确的验证码
        String rightCode = redisTemplate.opsForValue().get(RedisConstant.EMAIL + email);
        if (!code.equals(rightCode)) {
            // 验证码比对
            return RE.error(HttpStatusEnum.CODE_ERROR);
        }

        // 删除验证码
        redisTemplate.delete(RedisConstant.EMAIL + email);

        // 修改密码
        Users user1 = new Users();
        user1.setId(user.getId());
        user1.setPassword(DigestUtils.md5Hex(password + user.getSalt()));

        // 修改
        return this.baseMapper.updateById(user1) == 0 ? RE.error(HttpStatusEnum.UNKNOWN_ERROR) : RE.ok();
    }

    @Override
    public RE selectAllUser(String name, PagePara pagePara) {
        if (name != null && !name.equals("")){
//            查询某个人
            userVO uservo= usersMapper.selectByname(name);
           if (uservo!=null){
               return RE.ok().data("uservo",uservo);
           }else {
               return RE.error().message("数据库没有该用户的信息");
           }
        }else {
//            查询全部
            // 创建 Page 对象，指定当前页和每页显示数量
            Page<PagePara> page = new Page<>(pagePara.getNowPage() == null ? 1 : pagePara.getNowPage(), pagePara.getOnePageCount() == null ? 3 : pagePara.getOnePageCount());
            IPage<userVO> queryResult =usersMapper.selectAllUser(page, pagePara);
            // 根据查询结果构建 PagePara 对象，包括当前页、每页数量、总记录数和总页数
            PagePara pageResult = new PagePara(queryResult.getCurrent(), queryResult.getSize(), queryResult.getTotal(), queryResult.getPages());
            // 构建 PageResultS 对象，设置查询结果列表和分页信息
            PageResultS<userVO> result = new PageResultS<>();
            result.setList(queryResult.getRecords());
            result.setPage(pageResult);
            if (result !=null){
                return RE.ok().data("usersList",result);
            }else {
                return RE.error().message("查询失败，查询不到符合条件的员工信息");
            }
        }
    }

    @Override
    public RE addUser(AddUserDTO addUserDTO) {
//        获取参数
        AddUserPO addUserPO=new AddUserPO();
        addUserPO.setEmail(addUserDTO.getEmail());
        addUserPO.setPhone(addUserDTO.getPhone());
        addUserPO.setSex(addUserDTO.getSex());
        addUserPO.setRole(addUserDTO.getRole());

        if (StringUtils.isAnyBlank(addUserPO.getEmail(), addUserDTO.getPassword())) {
            // 非空
            return RE.error(HttpStatusEnum.PARAM_ILLEGAL);
        }else if (!StringUtil.checkEmail(addUserPO.getEmail())) {
            // 邮箱格式校验
            return RE.error(HttpStatusEnum.EMAIL_ERROR);
        }else if (!StringUtil.checkPassword(addUserDTO.getPassword())) {
            // 密码格式和验证码长度校验
            return RE.error(HttpStatusEnum.PARAM_ILLEGAL);
        }
        // 获取加密盐
        String salt = StringUtil.randomEncryptedSalt();
        // 密码加密（原明文密码 + 随机加密盐） md5加密
        addUserPO.setPassword(DigestUtils.md5Hex(addUserDTO.getPassword() + salt));
        addUserPO.setSalt(salt);
        addUserPO.setState(0);
//            在users表添加用户,返回主键
        int id=usersMapper.addUser(addUserPO);
        if (id != 0){
            //        获取参数
            Realinfo realinfo = new Realinfo();
            realinfo.setUserid(addUserPO.getId());
            realinfo.setRealname(addUserDTO.getRealname());
            realinfo.setIdentitycardnum(addUserDTO.getIdentitycardnum());

            //        在realinfo表添加用户信息，返回主键
            int realinfoId = realinfoMapper.AddUser(realinfo);
            if (realinfoId != 0){
                OneUserVO users = usersMapper.findOneUser(addUserPO.getId());
                return RE.ok().data("result",users);
            }else {
                return RE.error().message("realinfo表添加失败！");
            }
        }else {
            return RE.error().message("添加员工失败！");
        }
    }

    @Override
    public Users selectByPrimaryKey(Long id) {
        return usersMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Users record) {
        return usersMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public RE updateByPrimaryKey(UsersUpdateVO usersUpdateVO) {
       int re= usersMapper.updateByPrimaryKey(usersUpdateVO);
        if (re != 0){
            Users update = usersMapper.selectByPrimaryKey(usersUpdateVO.getId());
            return RE.ok().data("result",update);
        }else {
            return RE.error();
        }

    }

}
