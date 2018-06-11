package estructuraList;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import estructuraList.*;
import excepciones.*;

public interface Table {


	public int size();

	public boolean isEmpty();

	public void removeAll();

	public boolean equals(Object o);

	public boolean getRef();

	public String toString();

	public void addField(String name, String type) throws DuplicateFieldException;

	public void removeField(String name) throws PrimaryKeyException, NoSuchElementException, RecordErrorException;

	public void addRecord(List<Object> r) throws PrimaryKeyException, TypeErrorException, RecordErrorException;

	public void removeRecord(Object o);

	public Table searchRecords(String name, Comparator func, Object value) throws RecordErrorException;

	public String getName();

	public String getKeyName();

	public String getKeyType();

	public List<Field> getFields();

}
