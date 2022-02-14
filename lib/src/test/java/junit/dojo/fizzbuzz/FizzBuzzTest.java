package junit.dojo.fizzbuzz;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FizzBuzzTest {

	FizzBuzz fizzBuzz = new FizzBuzz();

	@BeforeEach
	void setUp() {
		fizzBuzz = new FizzBuzz();
	}

	@Test
	void 引数が3と5と15以外の時は引数をそのままStringで返す() {
		assertThat(fizzBuzz.convert(1), is("1"));
		assertThat(fizzBuzz.convert(2), is("2"));
		assertThat(fizzBuzz.convert(7), is("7"));
	}

	@Test
	void 引数が15の倍数の時はFizzBuzzを返す() {
		assertThat(fizzBuzz.convert(15), is("FizzBuzz"));
		assertThat(fizzBuzz.convert(30), is("FizzBuzz"));
		assertThat(fizzBuzz.convert(45), is("FizzBuzz"));
	}

	@Test
	void 引数が3の倍数の時はFizzを返す() {
		assertThat(fizzBuzz.convert(3), is("Fizz"));
		assertThat(fizzBuzz.convert(6), is("Fizz"));
		assertThat(fizzBuzz.convert(9), is("Fizz"));
	}

	@Test
	void 引数が5の倍数の時はBuzzを返す() {
		assertThat(fizzBuzz.convert(5), is("Buzz"));
		assertThat(fizzBuzz.convert(10), is("Buzz"));
		assertThat(fizzBuzz.convert(20), is("Buzz"));
	}

}
