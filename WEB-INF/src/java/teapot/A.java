/**
 *
 */
package teapot;

import java.util.HashMap;
import java.util.Map;

/**
 * array:98,map:58
array:58,map:70
array:36,map:59
array:36,map:57
array:36,map:60
array:36,map:60
array:39,map:58
array:35,map:60
array:36,map:57
array:346,map:506
array:355,map:485
array:338,map:468

 * @author dubenju
 *
 */
public class A {

    public static int MAX_CNT = 100;
    public static int MAX_LOOP_CNT = 100000;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		A proc = new A();
		proc.init();
		long st1 = System.currentTimeMillis();
		for (int i = 0; i < MAX_LOOP_CNT; i ++) {
		    proc.testArray();
		}
		long st2 = System.currentTimeMillis();
		for (int i = 0; i < MAX_LOOP_CNT; i ++) {
            proc.testMap();
        }
		long st3 = System.currentTimeMillis();
		System.out.println("array:" + (st2 - st1) + ",map:" + (st3 - st2) );
	}

	private ICallBack[] arry = null;
	private Map<Integer, ICallBack> map = null;
	public void init() {
	    arry = new ICallBack[MAX_CNT];
	    map = new HashMap<Integer, ICallBack>();
	    for (int i = 0; i < MAX_CNT; i ++) {
	        ICallBack cb = null;
	        int j = i % 10;
	        if (j == 0) { cb = new CallBack000(); }
	        if (j == 1) { cb = new CallBack001(); }
	        if (j == 2) { cb = new CallBack002(); }
	        if (j == 3) { cb = new CallBack003(); }
	        if (j == 4) { cb = new CallBack004(); }
	        if (j == 5) { cb = new CallBack005(); }
	        if (j == 6) { cb = new CallBack006(); }
	        if (j == 7) { cb = new CallBack007(); }
	        if (j == 8) { cb = new CallBack008(); }
	        if (j == 9) { cb = new CallBack009(); }
	        arry[i] = cb;
	        map.put(i, cb);
	    }
	}

	public void testArray() {
	    for (int i = 0; i < MAX_CNT; i ++) {
	        arry[i].callback();
	    }
	}

	public void testMap() {
        for (int i = 0; i < MAX_CNT; i ++) {
            map.get(i).callback();
        }
	}
}
