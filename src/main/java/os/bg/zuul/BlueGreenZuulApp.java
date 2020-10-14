/**
 * @author Brijesh Sharma
 * Copyright (c) 2020, Brijesh Sharma 
 * All rights reserved. 
 * This source code is licensed under the MIT license found in the LICENSE file in the root directory of this source tree. 
 */
package os.bg.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author Brijesh Sharma
 * Zuul application, which polls AWS Cloud Map (Service Registry) for discovering deployed microservices and their container configuration (IP/Port) and pass it back to
 * Zuul Ribbon load balancer
 *
 */


@SpringBootApplication(scanBasePackages="os.bg.*")
@EnableZuulProxy

public class BlueGreenZuulApp {

	/**
	 * 
	 */
	public BlueGreenZuulApp() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) { SpringApplication.run(BlueGreenZuulApp.class, args);	}

}
