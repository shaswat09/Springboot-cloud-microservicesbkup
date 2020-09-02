package com.example.demo.organization.event.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
// Multiple output and input
public interface CustomSourceChannels {

	@Output("outboundOrga")
	MessageChannel outboundOrg();
}
