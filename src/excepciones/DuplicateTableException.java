package excepciones;

public class DuplicateTableException extends Exception {

	private static final long serialVersionUID = 1L;

	public DuplicateTableException(String msg) {
        super(msg);
    }
}
