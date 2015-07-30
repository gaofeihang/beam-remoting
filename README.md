## Beam Remoting
A common data exchanging framework based on Netty.

### Requirements

Beam common framework needs to be installed.

See https://github.com/gaofeihang/beam-framework

### Installation

    git clone https://github.com/gaofeihang/beam-remoting.git
    cd beam-remoting
    mvn clean install -Dmaven.test.skip

### Maven Dependency

	<dependency>
	    <groupId>net.beamlight</groupId>
	    <artifactId>beam-remoting</artifactId>
	    <version>1.0.0-SNAPSHOT</version>
	</dependency>
	
### API

#### Server

    BeamServer server = new NettyBeamServer(8080);
    server.setHandler(new PacketHandlerAdapter() {
        @Override
        protected Object handleRequest(BeamPacket packet) {
            BeamRequest request = PacketUtils.decode(packet, BeamRequest.class);
            return new BeamResponse("resp to: " + request.getService());
        }
    });
    server.start();
	
#### Client

    BeamPacket respPacket = client.sendAndGet(
            PacketUtils.encodeRequest(new BeamRequest("test-service"), Codec.MSGPACK));
    if (respPacket != null) {
        BeamResponse response = PacketUtils.decode(respPacket, BeamResponse.class);
        System.out.println(response);
    }
