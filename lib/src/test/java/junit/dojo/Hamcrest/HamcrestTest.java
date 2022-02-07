package junit.dojo.Hamcrest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.either;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("boxing")
public class HamcrestTest {

	@Nested
	class EqualTo {
		@Test
		void _equalTo_成功() {
			String expected = "abc";
			String actual = "abc";
			assertThat(actual, equalTo(expected));
		}

		@Test
		void _equalTo_失敗() {
			String expected = "abc";
			String actual = "abcd";
			assertThat(actual, equalTo(expected));
		}
	}

	@Nested
	class Is {
		@Test
		void _is_成功() {
			String expected = "abc";
			String actual = "abc";
			assertThat(actual, is(expected));
		}

		@Test
		void _is_失敗() {
			String expected = "abc";
			String actual = "abcd";
			assertThat(actual, is(expected));
		}
	}

	@Nested
	class Either {
		@Test
		void _either_成功1() {
			String actual = "fizz";
			assertThat(actual, is(either(equalTo("fizz")).or(equalTo("buzz"))));
		}

		@Test
		void _either_成功2() {
			String actual = "buzz";
			assertThat(actual, is(either(equalTo("fizz")).or(equalTo("buzz"))));
		}

		@Test
		void _either_失敗() {
			String actual = "fizzbuzz";
			assertThat(actual, is(either(equalTo("fizz")).or(equalTo("buzz"))));
		}
	}

	@Nested
	class Than {
		@Test
		void _greaterThan_成功1() {
			int actual = 11;
			assertThat(actual, is(greaterThan(10)));
		}

		@Test
		void _greaterThan_失敗1() {
			int actual = 10;
			assertThat(actual, is(greaterThan(10)));
		}

		@Test
		void _greaterThan_失敗2() {
			int actual = 9;
			assertThat(actual, is(greaterThan(10)));
		}

		@Test
		void _lessThan_成功1() {
			int actual = 9;
			assertThat(actual, is(lessThan(10)));
		}

		@Test
		void _lessThan_失敗1() {
			int actual = 10;
			assertThat(actual, is(lessThan(10)));
		}

		@Test
		void _lessThan_失敗2() {
			int actual = 11;
			assertThat(actual, is(lessThan(10)));
		}
	}

	@Nested
	class Both {
		@Test
		void _both_成功1() {
			int actual = 1;
			assertThat(actual, is(both(greaterThan(0)).and(lessThan(100))));
		}

		@Test
		void _both_成功2() {
			int actual = 99;
			assertThat(actual, is(both(greaterThan(0)).and(lessThan(100))));
		}

		@Test
		void _both_失敗1() {
			int actual = 0;
			assertThat(actual, is(both(greaterThan(0)).and(lessThan(100))));
		}

		@Test
		void _both_失敗2() {
			int actual = 100;
			assertThat(actual, is(both(greaterThan(0)).and(lessThan(100))));
		}
	}

	@Nested
	class Not {
		@Test
		void _not_成功() {
			int actual = 1;
			assertThat(actual, is(not(equalTo(0))));
		}

		@Test
		void _not_失敗() {
			int actual = 0;
			assertThat(actual, is(not(equalTo(0))));
		}
	}

	@Nested
	class StartsWith {
		@Test
		void _startsWith_成功() {
			String actual = "abcdef";
			assertThat(actual, is(startsWith("abc")));
		}

		@Test
		void _startsWith_失敗() {
			String actual = "xyzabc";
			assertThat(actual, is(startsWith("abc")));
		}
	}

	@Nested
	class EndsWith {
		@Test
		void _endsWith_成功() {
			String actual = "xyzabc";
			assertThat(actual, is(endsWith("abc")));
		}

		@Test
		void _endsWith_失敗() {
			String actual = "abcdef";
			assertThat(actual, is(endsWith("abc")));
		}
	}

	@Nested
	class ContainsString {
		@Test
		void _containsString_成功1() {
			String actual = "abcdef";
			assertThat(actual, is(containsString("abc")));
		}

		@Test
		void _containsString_成功2() {
			String actual = "xyzabc";
			assertThat(actual, is(containsString("abc")));
		}

		@Test
		void _containsString_失敗() {
			String actual = "defxyz";
			assertThat(actual, is(containsString("abc")));
		}
	}

}
