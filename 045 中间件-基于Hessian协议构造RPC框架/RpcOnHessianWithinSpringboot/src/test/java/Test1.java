import org.junit.*;
public class Test1 {

	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis()); // 毫秒
		//System.out.println(getmicTime());
		System.out.println("********");
		System.out.println(System.currentTimeMillis()); // 毫秒
		//System.out.println(getmicTime());
	}

	/**
	 * @return返回微秒
	 */
    @Test
	public void  getmicTime() {
		Long cutime = System.currentTimeMillis() * 1000; // 微秒
		Long nanoTime = System.nanoTime(); // 纳秒
		System.out.println(nanoTime);
		//return cutime + (nanoTime - nanoTime / 1000000 * 1000000) / 1000;
	}

}
