package club.licho.base.service.impl;

import com.ingcore.common.service.CommonService;

import org.springframework.beans.factory.annotation.Autowired;

import club.licho.base.service.SystemService;

public class SystemServiceImpl implements SystemService {
    @Autowired
    private CommonService commonService;
}
