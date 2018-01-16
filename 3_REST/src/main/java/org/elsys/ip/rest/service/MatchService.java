package org.elsys.ip.rest.service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.elsys.ip.rest.model.MatchInfo;
import org.elsys.ip.rest.repository.MatchRepository;

import javax.ws.rs.core.MultivaluedMap;
import java.io.*;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public class MatchService {

  private MatchRepository matchRepository = new MatchRepository();
  private static AtomicLong idCount = new AtomicLong();

  public List<MatchInfo> getTestList() {
    return matchRepository.getTestList();
  }

  public MatchInfo getTestById(Integer id) {
    return matchRepository.getTestById(id).orElse(null);
  }

  public List<MatchInfo> getByMultipleIds(Set<Integer> ids) {
    return matchRepository.findByIds(ids);
  }

  public List<MatchInfo> getByMultipleFields(MultivaluedMap<String,String> fieldsToValues) {
    return matchRepository.findBySearchFields(fieldsToValues);
  }

  public MatchInfo saveTest(MatchInfo test) {

    test.setId((int) idCount.incrementAndGet());
    return matchRepository.saveTest(test);
  }

  public MatchInfo updateTest(Integer id, MatchInfo test) {
    return matchRepository.updateTest(id, test);
  }

  public void deleteTest(Integer id) {
    matchRepository.deleteTest(id);
  }

  public void writeCSVFile(String csvFileName, List<MatchInfo> list) throws Exception {
    CsvMapper mapper = new CsvMapper();
    CsvSchema schema = mapper.schemaFor(MatchInfo.class);
    schema = schema.withColumnSeparator('\t');

    // output writer
    ObjectWriter myObjectWriter = mapper.writer(schema);
    File tempFile = new File("C:\\data.csv");
    FileOutputStream tempFileOutputStream = new FileOutputStream(tempFile);
    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(tempFileOutputStream, 1024);
    OutputStreamWriter writerOutputStream = new OutputStreamWriter(bufferedOutputStream, "UTF-8");

    myObjectWriter.writeValue(writerOutputStream, this.getTestList());
  }

  public void readCSVFile(String csvFileName, List<MatchInfo> list) throws Exception {

    ClassLoader classLoader = getClass().getClassLoader();
    File file = new File(classLoader.getResource("C:\\data.csv").getFile());

    // configure the schema we want to read
    CsvMapper mapper = new CsvMapper();
    CsvSchema schema = mapper.schemaFor(MatchInfo.class);


    // configure the reader on what bean to read and how we want to write
    // that bean
    ObjectReader oReader = mapper.reader(MatchInfo.class).with(schema);

    // read from file
    try (Reader reader = new FileReader(file)) {
      MappingIterator<MatchInfo> mi = oReader.readValues(reader);
      while (mi.hasNext()) {
        System.out.println(mi.next());
      }
    }
  }

  public void addList(List<MatchInfo> matches) {

    matchRepository.addList(matches);
  }
}
