/**
 * @author Brijesh Sharma
 * Copyright (c) 2020, Brijesh Sharma 
 * All rights reserved. 
 * This source code is licensed under the MIT license found in the LICENSE file in the root directory of this source tree. 
 */
package os.bg.zuul;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClientBuilder;
import com.amazonaws.services.simplesystemsmanagement.model.GetParametersByPathRequest;
import com.amazonaws.services.simplesystemsmanagement.model.GetParametersByPathResult;
import com.amazonaws.services.simplesystemsmanagement.model.Parameter;

/**
 * @author Brijesh Sharma
 *
 */

@Service
public class SSMConfig {
	
	@Value("${ssm.wait.interval}")
	private int ssmWaitInterval;
	@Value("${ssm.root.path}")
	private String ssmParamRootPath;
	
	
	private Map<String, String> mapSSMParams = new ConcurrentHashMap<String, String>();
	
	private AWSSimpleSystemsManagement ssmClient = null;
	/**
	 * 
	 */
	public SSMConfig() {
		Thread thread = new Thread("Deamon-SSMConfig") { public void run() {poll();} };
		thread.start();
	}

	/*********************************************Builder Methods*****************************************************************/
	public String getSSMParamValue(String key) { return mapSSMParams.get(key);}
	
	/*********************************************Builder Methods*****************************************************************/
	private AWSSimpleSystemsManagement getSSMClient() {
		if(ssmClient != null) return ssmClient;
		return ssmClient = AWSSimpleSystemsManagementClientBuilder.standard().build();
	}
	
	/*********************************************SSM Polling*****************************************************************/
	private void poll() {
		while(true) {	
			refreshParams();
			Util.sleep(ssmWaitInterval);
		}
	}
	private void refreshParams() { 
		GetParametersByPathRequest request = new GetParametersByPathRequest().withPath(ssmParamRootPath);
		GetParametersByPathResult results = getSSMClient().getParametersByPath(request);
		List<Parameter> listParams = results.getParameters();
		for(Parameter param: listParams) mapSSMParams.put(param.getName().substring(ssmParamRootPath.length()), param.getValue());
	}
}
