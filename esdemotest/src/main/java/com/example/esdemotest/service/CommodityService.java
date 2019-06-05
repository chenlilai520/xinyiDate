package com.example.esdemotest.service;

import com.example.esdemotest.entity.Commodity;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author chenlilai
 * @title: CommodityService
 * @projectName xinyiDate
 * @description:
 * @date 2019/5/2514:30
 */
public interface CommodityService {

    long count();

    Commodity save(Commodity commodity);

    void delete(Commodity commodity);

    Iterable<Commodity> getAll();

    List<Commodity> getByName(String name);

    Page<Commodity> pageQuery(Integer pageNo, Integer pageSize, String kw);

}
