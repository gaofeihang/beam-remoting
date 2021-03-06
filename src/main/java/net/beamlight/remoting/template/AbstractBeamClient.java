package net.beamlight.remoting.template;

import java.util.concurrent.TimeUnit;

import net.beamlight.remoting.BeamClient;
import net.beamlight.remoting.BeamPacket;
import net.beamlight.remoting.RemotingResponse;
import net.beamlight.remoting.ResponseFuture;
import net.beamlight.remoting.exception.RemotingException;

/**
 * Created on Mar 11, 2015
 * 
 * @author gaofeihang
 * @since 1.0.0
 */
public abstract class AbstractBeamClient extends DefaultBeamEndpoint implements BeamClient {
    
    protected String host;
    protected int port;
    
    public AbstractBeamClient(String host, int port) {
        this.host = host;
        this.port = port;
    }
    
    @Override
    public void send(BeamPacket packet) throws RemotingException {
        doWrite(packet);
    }

    @Override
    public BeamPacket sendAndGet(final BeamPacket packet) throws RemotingException {
        ResponseFuture responseFuture = new ResponseFuture(packet.getId());
        
        doWrite(packet);
        
        try {
            RemotingResponse response = responseFuture.get(packet.getTimeout(), TimeUnit.MILLISECONDS);
            return response.getModel();
        } catch (Exception e) {
            LOG.error("Receive response timeout! request: {}", packet);
        }
        return null;
    }
    
    protected abstract void doWrite(BeamPacket packet);

}
