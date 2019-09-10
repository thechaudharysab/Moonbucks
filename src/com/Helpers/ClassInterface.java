package com.Helpers;

import java.util.List;

public interface ClassInterface {
	
	abstract public void menu(Boolean isAdmin);
	abstract public void add();
	abstract public void view();
	abstract public void search();
	public void edit(List<String> recordsToEdit);
	public void delete(List<String> recordsToDelete);
	
}
