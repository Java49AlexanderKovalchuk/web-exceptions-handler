package telran.exeptions;

public class NotFoundExeption extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public NotFoundExeption(String message) {
		super(message);
	}
}
