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
package org.restfulwhois.rdap.client.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.security.KeyStore;
import java.security.KeyStoreException;

import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.junit.Test;
import org.restfulwhois.rdap.client.exception.RdapClientException;

public class SSLUtilTest {
    String path = "src/test/java/org/restfulwhois/rdap/client/util/test.keystore";
    
    @Test
    public void test_loadKeyStore() throws KeyStoreException{
        KeyStore keyStore;
        try {
            keyStore = SSLUtil.loadKeyStore(path, "123456");
        } catch (RdapClientException e) {
            keyStore = null;
        }
        assertEquals("test", keyStore.aliases().nextElement());
    }
    
    @Test
    public void test_getTrustManager() throws KeyStoreException{
        TrustManager[] tm;
        try {
            
            tm = SSLUtil.getTrustManager(SSLUtil.loadKeyStore(path, "123456"));
        } catch (RdapClientException e) {
            tm = null;
        }
        assertNotNull(tm);
    }
    
    @Test
    public void test_getSSLSocketFactory() throws KeyStoreException{
        SSLSocketFactory factory;
        try {
            
            factory = SSLUtil.getSSLSocketFactory(SSLUtil.getTrustManager(SSLUtil.loadKeyStore(path, "123456")));
        } catch (RdapClientException e) {
            factory = null;
        }
        assertNotNull(factory);
    }
    
}
