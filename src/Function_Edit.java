
public class Function_Edit  {
	
	NoteBook notebook;
	
	public Function_Edit(NoteBook notebook) {
		this.notebook = notebook;
		
		}
	public void undo() {
		notebook.um.undo();
		
	}
	public void redo() {
		notebook.um.redo();
		
	}
		
	

}
