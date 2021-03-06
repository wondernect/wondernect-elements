package com.wondernect.elements.rdb.base.service;

import com.wondernect.elements.rdb.base.model.BaseLongModel;
import com.wondernect.elements.rdb.response.BaseLongResponseDTO;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: BaseLongService
 * Author: chenxun
 * Date: 2020-06-26 08:24
 * Description:
 */
public abstract class BaseLongService<RES_DTO extends BaseLongResponseDTO, T extends BaseLongModel> extends BaseIDService<RES_DTO, T, Long> {

}
