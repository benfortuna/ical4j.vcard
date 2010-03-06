/**
 * Copyright (c) 2010, Ben Fortuna
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  o Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *
 *  o Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 *  o Neither the name of Ben Fortuna nor the names of any other contributors
 * may be used to endorse or promote products derived from this software
 * without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.fortuna.ical4j.vcard.property;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;

import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.util.Strings;
import net.fortuna.ical4j.vcard.Group;
import net.fortuna.ical4j.vcard.Parameter;
import net.fortuna.ical4j.vcard.Property;
import net.fortuna.ical4j.vcard.PropertyFactory;

/**
 * MEMBER property.
 * 
 * $Id$
 *
 * Created on 21/09/2008
 *
 * @author Ben
 *
 */
public final class Member extends Property {

    public static final PropertyFactory<Member> FACTORY = new Factory();

    private static final long serialVersionUID = 6622845049765958916L;
    
    private final URI uri;
    
    /**
     * @param uri a URI that represents a member
     */
    public Member(URI uri) {
        super(Id.MEMBER);
        this.uri = uri;
    }
    
    /**
     * Factory constructor.
     * @param params property parameters
     * @param value string representation of a property value
     * @throws URISyntaxException where the specified value is an invalid URI
     */
    public Member(List<Parameter> params, String value) throws URISyntaxException {
        super(Id.MEMBER, params);
        this.uri = new URI(value);
    }
    
    /**
     * @return the uri
     */
    public URI getUri() {
        return uri;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getValue() {
        return Strings.valueOf(uri);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate() throws ValidationException {
        for (Parameter param : getParameters()) {
            assertPidParameter(param);
        }
    }

    private static class Factory implements PropertyFactory<Member> {

        /**
         * {@inheritDoc}
         */
        public Member createProperty(final List<Parameter> params, final String value) throws URISyntaxException {
            return new Member(params, value);
        }

        /**
         * {@inheritDoc}
         */
        public Member createProperty(final Group group, final List<Parameter> params, final String value)
                throws URISyntaxException, ParseException {
            // TODO Auto-generated method stub
            return null;
        }
    }
}
