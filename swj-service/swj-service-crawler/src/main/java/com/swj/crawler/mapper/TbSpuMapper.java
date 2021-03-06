package com.swj.crawler.mapper;

import com.swj.crawler.model.TbSpu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Bean;

import java.util.List;

public interface TbSpuMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table crawler..tb_spu
     *
     * @mbg.generated Tue Feb 04 21:54:06 CST 2020
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table crawler..tb_spu
     *
     * @mbg.generated Tue Feb 04 21:54:06 CST 2020
     */
    int insert(TbSpu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table crawler..tb_spu
     *
     * @mbg.generated Tue Feb 04 21:54:06 CST 2020
     */
    TbSpu selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table crawler..tb_spu
     *
     * @mbg.generated Tue Feb 04 21:54:06 CST 2020
     */
    List<TbSpu> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table crawler..tb_spu
     *
     * @mbg.generated Tue Feb 04 21:54:06 CST 2020
     */
    int updateByPrimaryKey(TbSpu record);
}