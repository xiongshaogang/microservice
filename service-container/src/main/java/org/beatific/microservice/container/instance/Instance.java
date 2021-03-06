package org.beatific.microservice.container.instance;

import java.io.Serializable;
import java.net.URI;
import java.util.Map;

import org.beatific.microservice.container.service.Service;

import com.netflix.appinfo.InstanceInfo;

import lombok.Data;

@Data
public class Instance implements Serializable {

	private static final long serialVersionUID = 2908442016683011196L;
	private String instanceId;
	private int pid = -1;
	private String host;
	private int port;
	private boolean secure;
	private URI uri;
	private Map<String, String> metadata;
	private String serviceName;
	
	private Service service;
	
	public Instance() {}
	
	public Instance(InstanceInfo info) {
		
		host = info.getHostName();
		instanceId = info.getInstanceId();
		metadata = info.getMetadata();
		secure = false;
		port = info.getPort();
		serviceName = info.getAppName().toLowerCase();
		String scheme = isSecure() ? "https" : "http";
		String url = String.format("%s://%s:%s", scheme, getHost(), getPort());
		uri = URI.create(url);
		
	}
}
