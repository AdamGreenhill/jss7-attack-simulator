/*
 * JBoss, Home of Professional Open Source
 * Copyright ${year}, Red Hat, Inc. and individual contributors
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
package org.mobicents.protocols.ss7.m3ua.impl.as;

import org.apache.log4j.Logger;
import org.mobicents.protocols.ss7.m3ua.impl.fsm.FSM;
import org.mobicents.protocols.ss7.m3ua.impl.fsm.State;
import org.mobicents.protocols.ss7.m3ua.impl.fsm.TransitionHandler;
import org.mobicents.protocols.ss7.m3ua.message.MessageClass;
import org.mobicents.protocols.ss7.m3ua.message.MessageType;
import org.mobicents.protocols.ss7.m3ua.message.asptm.ASPActive;
import org.mobicents.protocols.ss7.m3ua.parameter.RoutingContext;

public class AspTransInactToActSnt implements TransitionHandler {

    private AspImpl asp;
    private FSM fsm;
    private static final Logger logger = Logger.getLogger(AspTransInactToActSnt.class);

    public AspTransInactToActSnt(AspImpl asp, FSM fsm) {
        this.asp = asp;
        this.fsm = fsm;
    }

    public boolean process(State state) {

//        try {
//            long[] rcs = (long[]) this.fsm.getAttribute(AspImpl.ATTRIBUTE_ROUTING_CONTEXTS);
//            RoutingContext rc = this.asp.m3UAProvider.getParameterFactory().createRoutingContext(rcs);
//            
//            ASPActive aspUp = (ASPActive) this.asp.m3UAProvider.getMessageFactory().createMessage(
//                    MessageClass.ASP_TRAFFIC_MAINTENANCE, MessageType.ASP_ACTIVE);
//            aspUp.setRoutingContext(rc);
//
//            this.asp.write(aspUp);
//
//            return true;
//        } catch (Exception e) {
//            logger.error(String.format("Error while sending ASPUp message. %s", this.fsm.toString()), e);
//        }

        // If any error return false
        return false;
    }

}
