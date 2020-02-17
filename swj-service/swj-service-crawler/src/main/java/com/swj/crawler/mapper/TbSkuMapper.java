package com.swj.crawler.mapper;

import com.swj.crawler.model.TbSku;
import java.util.List;

public interface TbSkuMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table crawler..tb_sku
     *
     * @mbg.generated Thu Feb 13 21:55:22 CST 2020
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table crawler..tb_sku
     *
     * @mbg.generated Thu Feb 13 21:55:22 CST 2020
     */
    int insert(TbSku record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table crawler..tb_sku
     *
     * @mbg.generated Thu Feb 13 21:55:22 CST 2020
     */
    TbSku selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table crawler..tb_sku
     *
     * @mbg.generated Thu Feb 13 21:55:22 CST 2020
     */
    List<TbSku> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table crawler..tb_sku
     *
     * @mbg.generated Thu Feb 13 21:55:22 CST 2020
     */
    int updateByPrimaryKey(TbSku record);
}