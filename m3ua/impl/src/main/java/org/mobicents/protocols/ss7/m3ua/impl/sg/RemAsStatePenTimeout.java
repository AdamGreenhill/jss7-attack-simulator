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
package org.mobicents.protocols.ss7.m3ua.impl.sg;

import javolution.util.FastList;

import org.apache.log4j.Logger;
import org.mobicents.protocols.ss7.m3ua.impl.Asp;
import org.mobicents.protocols.ss7.m3ua.impl.AspState;
import org.mobicents.protocols.ss7.m3ua.impl.TransitionState;
import org.mobicents.protocols.ss7.m3ua.impl.fsm.FSM;
import org.mobicents.protocols.ss7.m3ua.impl.fsm.State;
import org.mobicents.protocols.ss7.m3ua.impl.fsm.StateEventHandler;
import org.mobicents.protocols.ss7.m3ua.impl.fsm.UnknownTransitionException;
import org.mobicents.protocols.ss7.m3ua.message.MessageClass;
import org.mobicents.protocols.ss7.m3ua.message.MessageType;
import org.mobicents.protocols.ss7.m3ua.message.mgmt.Notify;
import org.mobicents.protocols.ss7.m3ua.parameter.Status;

public class RemAsStatePenTimeout implements StateEventHandler {

    private RemAsImpl as;
    private FSM fsm;
    private static final Logger logger = Logger.getLogger(RemAsStatePenTimeout.class);

    boolean inactive = false;

    public RemAsStatePenTimeout(RemAsImpl as, FSM fsm) {
        this.as = as;
        this.fsm = fsm;
    }

    public void onEvent(State state) {
        this.inactive = false;
        // check if there are any ASP's who are INACTIVE, transition to
        // INACTIVE else DOWN
        for (FastList.Node<Asp> n = this.as.getAspList().head(), end = this.as.getAspList().tail(); (n = n
                .getNext()) != end;) {
            RemAspImpl remAspImpl = (RemAspImpl) n.getValue();
            if (remAspImpl.getState() == AspState.INACTIVE) {
                try {

                    if (!this.inactive) {
                        this.fsm.signal(TransitionState.AS_INACTIVE);
                        inactive = true;
                    }
                    Notify msg = createNotify(remAspImpl);
                    remAspImpl.getAspFactory().write(msg);
                } catch (UnknownTransitionException e) {
                    logger.error(String.format("Error while translating Rem AS to INACTIVE. %s", this.fsm.toString()),
                            e);
                }

            }// if (remAspImpl.getState() == AspState.INACTIVE)
        }// for

        if (!this.inactive) {
            // else transition to DOWN
            try {
                this.fsm.signal(TransitionState.AS_DOWN);
                inactive = true;
            } catch (UnknownTransitionException e) {
                logger.error(String.format("Error while translating Rem AS to DOWN. %s", this.fsm.toString()), e);
            }
        }
    }

    private Notify createNotify(RemAspImpl remAsp) {
        Notify msg = (Notify) this.as.getM3UAProvider().getMessageFactory().createMessage(MessageClass.MANAGEMENT,
                MessageType.NOTIFY);

        Status status = this.as.getM3UAProvider().getParameterFactory().createStatus(Status.STATUS_AS_State_Change,
                Status.INFO_AS_INACTIVE);
        msg.setStatus(status);

        if (remAsp.getASPIdentifier() != null) {
            msg.setASPIdentifier(remAsp.getASPIdentifier());
        }

        msg.setRoutingContext(this.as.getRoutingContext());

        return msg;
    }

}
