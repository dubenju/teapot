package org.teapot.jdbc;
class TestGetter extends Thread {
    static String[] keys = {
            "system",
            "category",
            "id"
        };

	private int id;
    public TestGetter(int n) {
        id = n;
    }
    /* *
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
    	long a = System.currentTimeMillis();
        long sn = -2;
        try {
            sn = JSerialNumber.updateSerialNumber(keys);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Thread" + id + ":sn=" + sn + "耗时：" + (System.currentTimeMillis() - a));
    }

}

public class TestJN {

    public static void main(String[] args) throws Exception {
    	long a1 = System.currentTimeMillis();
    	JSerialNumber.init();
    	System.out.println("JSerialNumber.init();耗时：" + (System.currentTimeMillis() - a1));

        String[] keys = {
            "system",
            "category",
            "id"
        };
        // long sn = JSerialNumber.createSerialNumber(keys, JSerialNumber.MIN_VALUE, 1L, JSerialNumber.MAX_VALUE);
        long a = System.currentTimeMillis();
        long sn = JSerialNumber.updateSerialNumber(keys);
        System.out.println("TestJN:sn=" + sn + "耗时：" + (System.currentTimeMillis() - a));

//        TestGetter[] gs = new TestGetter[100];
//        for (int i = 0; i < 100; i ++) {
//            gs[i] = new TestGetter(i);
//        }
//        for (TestGetter g : gs) {
//            g.start();
//        }
    }

}
