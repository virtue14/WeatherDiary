package diary.weather;


import diary.weather.repository.JdbcMemoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import diary.weather.domain.Memo;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
//@Transactional
class JdbcMemoRepositoryTest {
    @Autowired
    JdbcMemoRepository jdbcMemoRepository;

    @Test
    void insertMemoTest() {

        // given
        Memo newMemo = new Memo(2, "insertMemoTest");

        // when
        jdbcMemoRepository.save(newMemo);

        // then
        Optional<Memo> result = jdbcMemoRepository.findById(2);
        assertEquals(result.get().getText(), "insertMemoTest");
    }

    @Test
    void fineAllMemoTest() {
        List<Memo> memoList = jdbcMemoRepository.findAll();
        memoList.forEach(System.out::println);
        assertNotNull(memoList);
    }
}