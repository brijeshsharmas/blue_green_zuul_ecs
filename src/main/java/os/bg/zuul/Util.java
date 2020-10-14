/**
 * @author Brijesh Sharma
 * Copyright (c) 2020, Brijesh Sharma 
 * All rights reserved. 
 * This source code is licensed under the MIT license found in the LICENSE file in the root directory of this source tree. 
 */
package os.bg.zuul;

/**
 * @author Brijesh Sharma
 *
 */
public class Util {

	public static void sleep(long millisecond) { 
		try {Thread.sleep(millisecond); }catch(InterruptedException exp) {
			System.out.println("Thread [" + Thread.currentThread().getName() + "] Interuptted Exception. Exception [" + exp.getMessage() + "]");
		}
	}

}
