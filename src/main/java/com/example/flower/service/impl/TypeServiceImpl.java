package com.example.flower.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.flower.dao.TypeMapper;
import com.example.flower.dao.UsersMapper;
import com.example.flower.dto.AddTypeDTO;
import com.example.flower.po.Type;
import com.example.flower.po.Users;
import com.example.flower.service.TypeService;
import com.example.flower.service.UploadImageService;
import com.example.flower.util.PageResultS;
import com.example.flower.vo.PagePara;
import com.example.flower.vo.RE;
import com.example.flower.vo.userVO;
import com.sun.xml.internal.bind.v2.runtime.reflect.ListIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
@Transactional
public class TypeServiceImpl implements TypeService {

    @Autowired
    TypeMapper typeMapper;

    @Autowired
    UploadImageService uploadImageService;

    @Override
    public RE addType(AddTypeDTO addTypeDTO, MultipartFile file){
        if (addTypeDTO != null){
            Type type = new Type();
            type.setTypec(addTypeDTO.getTypec());
            type.setTypename(addTypeDTO.getTypename());
            type.setTypeimage(uploadImageService.upload(file).get("name"));
            int re=typeMapper.insert(type);
            if (re != 0){
                return RE.ok();
            }else {
                return RE.error();
            }
        }else {
            return RE.error().message("参数为空！");
        }
    }

    @Override
    public RE deleteType(Long id) {
        if (id != null){
            int re = typeMapper.deleteByPrimaryKey(id);
            if (re != 0){
                return RE.ok();
            }else {
                return RE.error().message("该类别不存在或已删除");
            }
        }else {
            return RE.error().message("参数为空！");
        }

    }

    @Override
    public RE selectAllType(PagePara pagePara) {
        // 创建 Page 对象，指定当前页和每页显示数量
        Page<PagePara> page = new Page<>(pagePara.getNowPage() == null ? 1 : pagePara.getNowPage(), pagePara.getOnePageCount() == null ? 3 : pagePara.getOnePageCount());
        IPage<Type> queryResult =typeMapper.selectAllType(page, pagePara);
        // 根据查询结果构建 PagePara 对象，包括当前页、每页数量、总记录数和总页数
        PagePara pageResult = new PagePara(queryResult.getCurrent(), queryResult.getSize(), queryResult.getTotal(), queryResult.getPages());
        // 构建 PageResultS 对象，设置查询结果列表和分页信息
        PageResultS<Type> result = new PageResultS<>();
        result.setList(queryResult.getRecords());
        result.setPage(pageResult);
        if (result != null){
            return RE.ok().data("TypeList",result);
        }else {
            return RE.error();
        }

    }

    @Override
    public RE updateType(Type type, MultipartFile file) {
        if (type != null){
            type.setTypeimage(uploadImageService.upload(file).get("name"));
            int re = typeMapper.updateByPrimaryKeySelective(type);
            if (re != 0){
                return RE.ok();
            }else {
                return RE.error();
            }
        }else {
            return RE.error().message("参数为空！");
        }
    }

    @Override
    public RE getType() {
        List<Type> typeList = typeMapper.getType();
        if (typeList != null){
            return RE.ok().data("result",typeList);
        }
        return RE.error().message("没有数据！");
    }

    @Override
    public boolean saveBatch(Collection<Type> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<Type> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<Type> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(Type entity) {
        return false;
    }

    @Override
    public Type getOne(Wrapper<Type> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<Type> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<Type> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<Type> getBaseMapper() {
        return null;
    }
}
