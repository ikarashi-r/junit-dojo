package junit.dojo.mockito;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MockitoTest {

	private List mockedList;

	@BeforeEach
	void setUp() {
		// mock creation
		mockedList = mock(List.class);
	}

	@Test
	void verifyのテスト() {
		mockedList.add("one");
		mockedList.clear();

		verify(mockedList).add("one");
		verify(mockedList).add("two"); //この処理は通っていないのでここがエラーになる
		verify(mockedList).clear();
	}

	@Test
	void whenのテスト() {
		when(mockedList.get(0)).thenReturn("first");
		when(mockedList.get(1)).thenReturn("one");

		assertThat(mockedList.get(0), is("first"));
		assertThat(mockedList.get(1), is("one"));
		assertThat(mockedList.get(99), is(nullValue()));//値を設定していないのでnull
	}

	@SuppressWarnings("boxing")
	@Test
	void spyのテスト() {
		List list = new LinkedList();
		List spy = Mockito.spy(list);

		when(spy.size()).thenReturn(100);

		spy.add("one");
		spy.add("two");

		assertThat(spy.get(0), is("one"));
		assertThat(spy.size(), is(100));
	}

	@Test
	void anyIntのテスト() {
		when(mockedList.get(anyInt())).thenReturn("NG");
		when(mockedList.get(10)).thenReturn("OK");

		//10以外は”NG”を返す
		assertThat(mockedList.get(1), is("NG"));
		assertThat(mockedList.get(9), is("NG"));
		assertThat(mockedList.get(10), is("OK"));
		assertThat(mockedList.get(100), is("NG"));
	}
}
