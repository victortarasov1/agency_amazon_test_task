package agency.amazon.test.batch.reader;

public interface Reader {
    <T> T readData(String path, Class<T> clazz);

    String getType();
}
