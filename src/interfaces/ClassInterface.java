package interfaces;

import java.util.List;

public interface ClassInterface {
	
	abstract public void menu(Boolean isAdmin);
	abstract public void add();
	abstract public void view();
	abstract public void search();
	abstract public void edit(List<String> recordsToEdit);
	abstract public void delete(List<String> recordsToDelete);
	
}
