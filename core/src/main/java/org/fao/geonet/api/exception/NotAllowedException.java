/*
 * Copyright (C) 2001-2016 Food and Agriculture Organization of the
 * United Nations (FAO-UN), United Nations World Food Programme (WFP)
 * and United Nations Environment Programme (UNEP)
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301, USA
 *
 * Contact: Jeroen Ticheler - FAO - Viale delle Terme di Caracalla 2,
 * Rome - Italy. email: geonetwork@osgeo.org
 */
package org.fao.geonet.api.exception;


import java.util.Locale;

import org.fao.geonet.exceptions.LocalizedRuntimeException;

public class NotAllowedException extends LocalizedRuntimeException {

    /**
     * Constructs a new NotAllowedException exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public NotAllowedException() {
    }

    /**
     * Constructs a new NotAllowedException exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public NotAllowedException(String message) {
        super(message);
    }

    /**
     * Constructs a new NotAllowedException exception with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this runtime exception's detail message.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method).  (A <tt>null</tt> value is
     *                permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     */
    public NotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new NotAllowedException exception with the specified cause and a
     * detail message of <tt>(cause==null ? null : cause.toString())</tt>
     * (which typically contains the class and detail message of
     * <tt>cause</tt>).  This constructor is useful for runtime exceptions
     * that are little more than wrappers for other throwables.
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method).  (A <tt>null</tt> value is
     *              permitted, and indicates that the cause is nonexistent or
     *              unknown.)
     */
    public NotAllowedException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new runtime exception with the specified detail
     * message, cause, suppression enabled or disabled, and writable
     * stack trace enabled or disabled.
     *
     * @param message            the detail message.
     * @param cause              the cause.  (A {@code null} value is permitted,
     *                           and indicates that the cause is nonexistent or unknown.)
     * @param enableSuppression  whether or not suppression is enabled
     *                           or disabled
     * @param writableStackTrace whether or not the stack trace should
     *                           be writable
     * @since 1.7
     */
    public NotAllowedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    protected String getResourceBundleBeanQualifier() {
        return "apiMessages";
    }

    @Override
    public NotAllowedException withMessageKey(String messageKey) {
        super.withMessageKey(messageKey);
        return this;
    }

    @Override
    public NotAllowedException withMessageKey(String messageKey, Object[] messageKeyArgs) {
        super.withMessageKey(messageKey, messageKeyArgs);
        return this;
    }

    @Override
    public NotAllowedException withDescriptionKey(String descriptionKey) {
        super.withDescriptionKey(descriptionKey);
        return this;
    }

    @Override
    public NotAllowedException withDescriptionKey(String descriptionKey, Object[] descriptionKeyArgs) {
        super.withDescriptionKey(descriptionKey, descriptionKeyArgs);
        return this;
    }

    @Override
    public NotAllowedException withLocale(Locale locale) {
        super.withLocale(locale);
        return this;
    }
}
