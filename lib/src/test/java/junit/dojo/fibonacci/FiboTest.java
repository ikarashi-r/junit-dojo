package junit.dojo.fibonacci;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class FiboTest extends Fibo {

	@SuppressWarnings("boxing")
	@ParameterizedTest
	@CsvSource({
			"1,1",
			"2,1",
			"3,2",
			"4,3",
			"5,5",
			"6,8",
			"7,13",
			"8,21",
			"9,34",
			"10,55",
			"11,89",
			"12,144",
			"20,6765",
			"30,832040" })
	void indexを指定すると数列から該当する数値を返す(int arg, int expected) {
		Fibo fibonacci = new Fibo();
		assertThat(fibonacci.fibo(arg), is(expected));
	}

}
