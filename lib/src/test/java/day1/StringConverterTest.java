package day1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class StringConverterTest {

	StringConverter converter = new StringConverter();

	@BeforeEach
	void setUp() {
		converter = new StringConverter();
	}

	@Nested
	class 複合テスト {

		@Test
		void 全種類含む場合() {
			assertEquals("abc-xyz-123_456__789", converter.sanitizeName("abc  xyz \n123$456%#789"));
		}
	}

	@Nested
	class アルファベットと数字は置換しない {

		@Test
		void 引数がabcxyzの場合はabcxyzを返す() {
			assertEquals("abcxyz", converter.sanitizeName("abcxyz"));
		}

		@Test
		void 引数が0123456789の場合0123456789を返す() {

			assertEquals("0123456789", converter.sanitizeName("0123456789"));
		}
	}

	@Nested
	class 空白をまとめてハイフンにする {
		@Test
		void 引数が空白の場合にハイフンを返す() {
			assertEquals("-", converter.sanitizeName(" "));
		}

		@Test
		void 引数が連続した空白の場合にまとめてハイフンを返す() {
			assertEquals("-", converter.sanitizeName("  "));
		}

		@Test
		void 引数が改行の場合にハイフンを返す() {
			assertEquals("-", converter.sanitizeName("\n"));
			assertEquals("-", converter.sanitizeName("\r"));
		}

		@Test
		void 空白と改行が連続する場合にまとめてハイフンを返す() {
			assertEquals("-", converter.sanitizeName(" \n"));
			assertEquals("-", converter.sanitizeName("\r\n"));
		}

	}

	@Nested
	class 大文字を小文字にする {
		@Test
		void すべて大文字の場合すべて小文字に変換する() {
			assertEquals("abc", converter.sanitizeName("ABC"));
		}

		@Test
		void 大文字と小文字が混ざっている場合大文字部分だけ小文字に変換する() {
			assertEquals("xyz", converter.sanitizeName("XyZ"));
		}
	}

	@Nested
	class アルファベットと数字以外をアンダースコアに変換する {
		@Test
		void 記号をアンダースコアに変換する() {
			assertEquals("_", converter.sanitizeName("$"));
		}

		@Test
		void 記号を1文字ずつアンダースコアに変換する() {
			assertEquals("___", converter.sanitizeName("$@#"));
		}
	}

}
