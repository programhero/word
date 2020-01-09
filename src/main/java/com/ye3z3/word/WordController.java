package com.ye3z3.word;

import com.alibaba.excel.EasyExcel;
import com.ye3z3.word.Word;
import com.ye3z3.word.WordDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/word")
public class WordController {
    @Autowired
    private WordDAO wordDAO;
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page> index(Word w, @RequestParam(name = "page",defaultValue = "1", required = false) int page,
                                      @RequestParam(name = "size",defaultValue = "10", required = false) int size) {
        page = page -1;
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues().withMatcher("wordd", matcher1 -> matcher1.contains());
        Example<Word> of = Example.of(w, matcher);
        Page<Word> all = wordDAO.findAll(of, PageRequest.of(page, size));
        return ResponseEntity.ok(all);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/export")
    public void export(Word w,HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应下载的文件名不对。这个时候 请别使用swagger 他会有影响
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode( "word", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues().withMatcher("wordd", matcher1 -> matcher1.contains());
        Example<Word> of = Example.of(w, matcher);
        List<Word> all = wordDAO.findAll(of);
        EasyExcel.write(response.getOutputStream(), Word.class).sheet("word").doWrite(all);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody Word word) {
        if (word.getId() != null) {
            word.setLastUpdatedTime(new Date());
            word.setFrequency(word.getFrequency() +1);
        } else {
            word.setCreatedTime(new Date());
            word.setLastUpdatedTime(new Date());
        }
        wordDAO.save(word);
        return ResponseEntity.ok().body(null);
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public ResponseEntity delete(@PathVariable("id")Integer id) {
        wordDAO.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @RequestMapping("/search")
    public ResponseEntity search(@RequestParam String word) {
        List<Word> byWordd = wordDAO.findByWordd(word, Sort.by(Sort.Direction.DESC, "lastUpdatedTime"));
        if (byWordd.size() > 0) {
            return ResponseEntity.ok(byWordd.get(0));
        }
        return ResponseEntity.noContent().build();
    }

}
