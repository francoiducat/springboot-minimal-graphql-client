package com.ducatillon.graphqlclient.util;

import io.micrometer.core.instrument.util.IOUtils;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class GraphqlSchemaReaderUtil {

  public static String getSchemaFromFile(final String schemaName) {
    final InputStream inputStream = GraphqlSchemaReaderUtil.class.getClassLoader()
        .getResourceAsStream("graphql/" + schemaName + ".graphql");
    return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
  }
}
