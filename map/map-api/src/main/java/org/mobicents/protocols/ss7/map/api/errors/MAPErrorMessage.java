/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.mobicents.protocols.ss7.map.api.errors;

import org.mobicents.protocols.ss7.map.api.MAPException;
import org.mobicents.protocols.ss7.tcap.asn.comp.Parameter;

/**
 * Base class of MAP ReturnError messages
 * 
 * @author sergey vetyutnev
 * 
 */
public interface MAPErrorMessage {

	public Long getErrorCode();

	public Parameter[] encodeParameters() throws MAPException;

	public void decodeParameters( Parameter[] p ) throws MAPException;

	
	public Boolean isEmParameterless();

	public Boolean isEmExtensionContainer();

	public Boolean isEmFacilityNotSup();

	public Boolean isEmSMDeliveryFailure();

	public Boolean isEmSystemFailure();

	
	public MAPErrorMessageParameterless getEmParameterless();

	public MAPErrorMessageExtensionContainer getEmExtensionContainer();

	public MAPErrorMessageFacilityNotSup getEmFacilityNotSup();

	public MAPErrorMessageSMDeliveryFailure getEmSMDeliveryFailure();

	public MAPErrorMessageSystemFailure getEmSystemFailure();

}