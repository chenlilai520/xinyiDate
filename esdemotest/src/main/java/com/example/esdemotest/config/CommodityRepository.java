package com.example.esdemotest.config;

import com.example.esdemotest.entity.Commodity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author chenlilai
 * @title: CommodityRepository
 * @projectName xinyiDate
 * @description:
 * @date 2019/5/2514:29
 */
@Repository
public interface CommodityRepository extends ElasticsearchRepository<Commodity, String> {





}
