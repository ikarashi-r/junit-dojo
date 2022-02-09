package junit.dojo.fibonacci;

public class Fibo {

	//フィボナッチ数列：1つ前と2つ前の数字を足していく　最初は0と1

	public int fibo(int idx) {
		if (idx == 0) {
			return 0;
		}
		if (idx == 1) {
			return 1;
		}

		return fibo(idx - 1) + fibo(idx - 2);
	}

	/*記録：最初に書いていた配列使うコード（実行できる範囲が決まっているのが問題）
	 * public int fibo(int idx) {
			int[] Fibo = new int[15];
			Fibo[0] = 0;
			Fibo[1] = 1;

			for(int i = 2; i <= idx; i++) {
				Fibo[i] = Fibo[i - 1] + Fibo[i - 2];
			}
			return Fibo[idx];
		}
	*/

}
