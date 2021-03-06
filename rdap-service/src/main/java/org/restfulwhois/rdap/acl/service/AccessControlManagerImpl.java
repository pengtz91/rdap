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
package org.restfulwhois.rdap.acl.service;

import org.restfulwhois.rdap.acl.bean.Principal;
import org.restfulwhois.rdap.acl.bean.SecureObject;
import org.restfulwhois.rdap.acl.dao.AclDao;
import org.restfulwhois.rdap.common.model.base.BaseModel;
import org.restfulwhois.rdap.common.service.AccessControlManager;
import org.restfulwhois.rdap.common.support.PrincipalHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;



/**
 * AccessControlManager implementation.
 * 
 * Requirement from  
 * http://tools.ietf.org/html/draft-ietf-weirds-rdap-sec-06#section-3.2 .
 * 
 * Provide authorization to user and its access object, 
 * according to Access Control List.
 * 
 * @author jiashuo
 * 
 */
@Service
public class AccessControlManagerImpl implements AccessControlManager {
    /**
     * logger.
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(AccessControlManagerImpl.class);    
    
    /**
     * acl dao.
     */
    @Autowired
    private AclDao aclDao;
    
    /**
     * Is a model permitted for querying.
     * 
     * @param object
     *            param for object to be queried.
     * @return boolean.
     */   
    @Override
    public boolean hasPermission(BaseModel object) {
        Assert.notNull(object);
        Assert.notNull(object.getId());
        Assert.notNull(object.getObjectType());
        
        LOGGER.debug("hasPermission:" + object);
        Principal principal = PrincipalHolder.getPrincipal();
        SecureObject secureObject = new SecureObject(object.getId(), object
                .getObjectType().getName());
        return aclDao.hasEntry(principal, secureObject);
    }  
}
