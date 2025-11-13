import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class Test1 {
	ArthOps obj=new ArthOps();
	@Test
	public void testAdd() {
		
		int i = 2;
		int j = 3;
	
		assertEquals(obj.add(i, j),5);
	}
	
	@Test
	public void testMultiply() {
		
		int i = 2;
		int j = 3;
		assertEquals(obj.multiply(i, j),6);
	}
	
	@Test
	public void testValidateName() {
		
	String custname ="James";
	
		assertTrue(obj.validateName(custname));
	}

	
	@Test
	public void testName() {
		
		String name = null;
		
		assertNotNull(name);
		
		
	}

	@Test
	public void testValidation() {
		
		boolean  status = true;
		
		assertTrue(status);
		
		
	}
}