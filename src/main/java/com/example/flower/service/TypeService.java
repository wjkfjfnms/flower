package com.example.flower.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.flower.dto.AddTypeDTO;
import com.example.flower.po.Type;
import com.example.flower.vo.PagePara;
import com.example.flower.vo.RE;
import org.springframework.web.multipart.MultipartFile;


public interface TypeService extends IService<Type> {

    RE addType(AddTypeDTO addTypeDTO, MultipartFile file);

    RE deleteType(Long id);

    RE selectAllType(PagePara pagePara);

    RE updateType(Type type, MultipartFile file);

    RE getType();
}
