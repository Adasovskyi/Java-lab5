package task3;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class CipherFilterReader extends FilterReader {
    private final char key;

    public CipherFilterReader(Reader in, char key) {
        super(in);
        this.key = key;
    }

    @Override
    public int read() throws IOException {
        int c = super.read();
        if (c == -1) {
            return -1;
        }
        return c - key;
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        int numRead = super.read(cbuf, off, len);
        if (numRead == -1) {
            return -1;
        }
        for (int i = off; i < off + numRead; i++) {
            cbuf[i] = (char) (cbuf[i] - key);
        }
        return numRead;
    }
}