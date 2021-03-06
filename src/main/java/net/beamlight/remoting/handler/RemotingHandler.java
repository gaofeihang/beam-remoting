package net.beamlight.remoting.handler;

import net.beamlight.remoting.BeamPacket;
import net.beamlight.remoting.Protocol;
import net.beamlight.remoting.RemotingResponse;
import net.beamlight.remoting.ResponseFuture;
import net.beamlight.remoting.util.PacketUtils;

/**
 * Created on Mar 11, 2015
 * 
 * 
 * @author gaofeihang
 * @since 1.0.0
 */
public class RemotingHandler {
    
    public static BeamPacket buildResponsePacket(BeamPacket reqPacket, Object response) {
        return PacketUtils.encodeResponse(response, reqPacket.getId(), Protocol.PACKET_RESPONSE, reqPacket.getCodec());
    }
    
    public static void handleResponse(BeamPacket packet) {
        RemotingResponse response = new RemotingResponse(packet.getId(), packet);
        ResponseFuture.receiveResponse(response);
    }

}
