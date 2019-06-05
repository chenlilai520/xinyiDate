package com.example.esdemotest.service.impl;

import com.example.esdemotest.config.CommodityRepository;
import com.example.esdemotest.entity.Commodity;
import com.example.esdemotest.service.CommodityService;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenlilai
 * @title: CommodityServiceImpl
 * @projectName xinyiDate
 * @description:
 * @date 2019/5/2514:31
 */
@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private CommodityRepository commodityRepository;

    @Autowired
    private TransportClient transportClient;

    @Override
    public long count() {
        return commodityRepository.count();
    }

    @Override
    public Commodity save(Commodity commodity) {
        return commodityRepository.save(commodity);
    }

    @Override
    public void delete(Commodity commodity) {
        commodityRepository.delete(commodity);
//        commodityRepository.deleteById(commodity.getSkuId());
    }

    @Override
    public Iterable<Commodity> getAll() {
        return commodityRepository.findAll();
    }

    @Override
    public List<Commodity> getByName(String name) {
        List<Commodity> list = new ArrayList<>();
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("name", name);
        Iterable<Commodity> iterable = commodityRepository.search(matchQueryBuilder);
        iterable.forEach(e->list.add(e));
        return list;
    }

    @Override
    public Page<Commodity> pageQuery(Integer pageNo, Integer pageSize, String kw) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchPhraseQuery("name", kw))
                .withPageable(PageRequest.of(pageNo, pageSize))
                .build().addSort(new Sort("desc"));
        return commodityRepository.search(searchQuery);
    }


}
