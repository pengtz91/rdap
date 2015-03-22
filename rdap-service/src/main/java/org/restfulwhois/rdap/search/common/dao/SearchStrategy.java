/*
 * Copyright (c) 2012 - 2015, Internet Corporation for Assigned Names and
 * Numbers (ICANN) and China Internet Network Information Center (CNNIC)
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *  this list of conditions and the following disclaimer in the documentation
 *  and/or other materials provided with the distribution.
 * * Neither the name of the ICANN, CNNIC nor the names of its contributors may
 *  be used to endorse or promote products derived from this software without
 *  specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL ICANN OR CNNIC BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH
 * DAMAGE.
 */
package org.restfulwhois.rdap.search.common.dao;

import java.util.List;

import org.restfulwhois.rdap.common.model.base.BaseModel;
import org.restfulwhois.rdap.common.support.QueryParam;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 
 * search strategy.
 * @param <T>
 *      object derived from BaseModel.
 * @author jiashuo
 */
public interface SearchStrategy<T extends BaseModel> {

    /**
     * check if support queryParam.
     * 
     * @param queryParam
     *            queryParam.
     * @return boolean
     */
    boolean support(QueryParam queryParam);

    /**
     * searchCount.
     * 
     * @param queryParam
     *            queryParam.
     * @param jdbcTemplate
     *            jdbcTemplate.
     * @return search count.
     */
    Long searchCount(QueryParam queryParam, JdbcTemplate jdbcTemplate);

    /**
     * search.
     * 
     * @param queryParam
     *            queryParam
     * @param jdbcTemplate
     *            jdbcTemplate.
     * @return list.
     */
    List<T> search(QueryParam queryParam, JdbcTemplate jdbcTemplate);

}