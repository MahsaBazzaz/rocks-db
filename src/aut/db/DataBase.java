package aut.db;

import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DataBase {
    static {
        RocksDB.loadLibrary();
    }

    private RocksDB db;

    public DataBase() {
        try (final Options options = new Options().setCreateIfMissing(true)) {
            String[] arr;
            File file = new File("./American Stock Exchange 20200206_Names_ClosedVal.csv");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            db = RocksDB.open(options, "./data");
            while ((st = br.readLine()) != null) {
                arr = st.split(",");
                byte[] a = arr[0].getBytes();
                byte[] b = arr[1].getBytes();
                final byte[] value = db.get(a);
                if (value == null) {
                    db.put(a, b);
                }
            }
        } catch (RocksDBException | IOException e) {
            System.out.println("failed");
        }
    }

    public void create(String key, String value) {
        try {
            final byte[] isAlreadyThere = db.get(key.getBytes());
            if (isAlreadyThere == null) {
                System.out.println("true");
                db.put(key.getBytes(), value.getBytes());
            } else {
                System.out.println("false");
            }
        } catch (RocksDBException e) {
            System.out.println("creating failed");
        }
    }

    public void fetch(String key) {
        try {
            final byte[] isAlreadyThere = db.get(key.getBytes());
            if (isAlreadyThere != null) {
                System.out.println("true");
                System.out.println(new String(isAlreadyThere));
            } else {
                System.out.println("false");
            }
        } catch (RocksDBException e) {
            System.out.println("fetching failed");
        }
    }

    public void update(String key, String updateValue) {
        try {
            final byte[] isAlreadyThere = db.get(key.getBytes());
            if (isAlreadyThere != null) {
                System.out.println("true");
                db.put(key.getBytes(), updateValue.getBytes());
            } else {
                System.out.println("false");
            }
        } catch (RocksDBException e) {
            System.out.println("updating failed");
        }
    }

    public void delete(String key) {
        try {
            final byte[] isAlreadyThere = db.get(key.getBytes());
            if (isAlreadyThere != null) {
                System.out.println("true");
                db.delete(key.getBytes());
            } else {
                System.out.println("false");
            }
        } catch (RocksDBException e) {
            System.out.println("false");
        }
    }
}
