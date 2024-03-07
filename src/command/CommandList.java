package command;

import java.util.ArrayList;

import javax.swing.JTextArea;

import observer.Observer;


public class CommandList   {
	private ArrayList<Command> commands = new ArrayList<Command>();
	private ArrayList<Command> undo = new ArrayList<Command>();
	
	private String commandHistory="";
	
	private JTextArea commandListener;
	
	private int position;
	
	public void undo() {
		commands.get(position).unexecute();
		undo.add(commands.get(position));
		commandHistory+= "[Undo];"+commands.get(position)+"\n"; 
		if(commandListener!=null)
		logCommands();
		position--;
		
	}
	
	public void redo() {
		position++;
		commands.get(position).execute();
		undo.remove(commands.get(position));
		commandHistory+= "[Redo];"+commands.get(position)+"\n"; 
		if(commandListener!=null)
			logCommands();
	}
	
	public void newCommand(boolean check) {
		if(check) {
			commands.removeAll(undo);
			undo.clear();
		}
		position=commands.size()-1;
	}
	
	public void add(Command command){
		commands.add(command);
		commandHistory+= "[New Command];"+command+"\n"; 
		if(commandListener!=null)
			logCommands();
	}
	
	
	public ArrayList<Command> getCommands() {
		return commands;
	}
	public void setCommands(ArrayList<Command> commands) {
		this.commands = commands;
	}
	public ArrayList<Command> getUndo() {
		return undo;
	}
	public void setUndo(ArrayList<Command> undo) {
		this.undo = undo;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}

	public JTextArea getCommandListener() {
		return commandListener;
	}

	public void setCommandListener(JTextArea commandListener) {
		this.commandListener = commandListener;
	}

	public void logCommands(){
		if(this.commandHistory!=null)
		this.commandListener.setText(commandHistory);
	}

	public String getCommandHistory() {
		return commandHistory;
	}

	public void setCommandHistory(String commandHistory) {
		this.commandHistory = commandHistory;
	}
	
	
}
