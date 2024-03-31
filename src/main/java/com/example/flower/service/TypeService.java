package com.example.flower.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.flower.dto.AddTypeDTO;
import com.example.flower.po.Type;
import com.example.flower.vo.PagePara;
import com.example.flower.vo.RE;


public interface TypeService extends IService<Type> {

    RE addType(AddTypeDTO addTypeDTO);

    RE deleteType(Long id);

    RE selectAllType(PagePara pagePara);

    RE updateType(Type type);

    RE getType();
}
