package day1;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * hamcrest(assertThat)を使ったテストに書き換える
 */

class StringConverterTest2 {

	StringConverter converter = new StringConverter();
	StringConverter.Validator validator = new StringConverter.Validator();

	@BeforeEach
	void setUp() {
		converter = new StringConverter();
		validator = new StringConverter.Validator();
	}

	@Nested
	class 複合テスト {

		@Test
		void 全種類含む場合() {
			assertThat(converter.sanitizeName("abc  xyz \n123$456%#789"), is("abc-xyz-123_456__789"));
		}
	}

	@Nested
	class アルファベットと数字は置換しない {

		@Test
		void 引数がabcxyzの場合はabcxyzを返す() {
			assertThat(converter.sanitizeName("abcxyz"), is("abcxyz"));
		}

		@Test
		void 引数が0123456789の場合0123456789を返す() {

			assertThat(converter.sanitizeName("0123456789"), is("0123456789"));
		}
	}

	@Nested
	class 空白をまとめてハイフンにする {
		@Test
		void 引数が空白の場合にハイフンを返す() {
			assertThat(converter.sanitizeName(" "), is("-"));
		}

		@Test
		void 引数が連続した空白の場合にまとめてハイフンを返す() {
			assertThat(converter.sanitizeName("  "), is("-"));
		}

		@Test
		void 引数が改行の場合にハイフンを返す() {
			assertThat(converter.sanitizeName("\n"), is("-"));
			assertThat(converter.sanitizeName("\r"), is("-"));
		}

		@Test
		void 空白と改行が連続する場合にまとめてハイフンを返す() {
			assertThat(converter.sanitizeName(" \n"), is("-"));
			assertThat(converter.sanitizeName("\r\n"), is("-"));
		}

	}

	@Nested
	class 大文字を小文字にする {
		@Test
		void すべて大文字の場合すべて小文字に変換する() {
			assertThat(converter.sanitizeName("ABC"), is("abc"));
		}

		@Test
		void 大文字と小文字が混ざっている場合大文字部分だけ小文字に変換する() {
			assertThat(converter.sanitizeName("XyZ"), is("xyz"));
		}
	}

	@Nested
	class アルファベットと数字以外をアンダースコアに変換する {
		@Test
		void 記号をアンダースコアに変換する() {
			assertThat(converter.sanitizeName("$"), is("_"));
		}

		@Test
		void 記号を1文字ずつアンダースコアに変換する() {
			assertThat(converter.sanitizeName("$@#"), is("___"));
		}
	}

	@Nested
	class 例外処理 {

		@Test
		void 引数nullがきたらnullPointer() {
			assertThrows(NullPointerException.class, () -> {
				validator.validate(null);
			}, "");
		}

		@Test
		void 空文字がきたらillegalArgumentException() {
			assertThrows(IllegalArgumentException.class, () -> {
				validator.validate("");
			}, "");
		}
	}

}
