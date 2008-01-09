package org.dimagi.view;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;

import org.celllife.clforms.Controller;
import org.celllife.clforms.MVCComponent;
import org.celllife.clforms.api.Prompt;
import org.celllife.clforms.api.ResponseEvent;
import org.celllife.clforms.view.FormView;
import org.celllife.clforms.view.IPrompter;

import org.dimagi.chatscreen.ChatScreenForm;


public class ChatPromptScreen extends MVCComponent implements IPrompter {

	private Controller controller;
	private static Command prevCommand = new Command("Prev", Command.ITEM, 3);
	private static Command nextCommand = new Command("Next", Command.ITEM, 3);
	private static Command exitCommand = new Command("Exit", Command.EXIT, 3);
	private static Displayable screen = null;
	private ChatScreenForm chatScreenForm = new ChatScreenForm();
	private int screenIndex;
	private int totalScreens;
	private Prompt p;
	
	
	public ChatPromptScreen() {
		screen = chatScreenForm;
		screen.addCommand(nextCommand);
		screen.addCommand(prevCommand);
		screen.addCommand(exitCommand);
	}

	public Displayable getScreen() {
		return screen;
	}
	
	public void commandAction(Command command, Displayable s) {
  	
		try {
		    if (command == exitCommand){
				controller.processEvent(new ResponseEvent(ResponseEvent.EXIT, -1));
			} else if (command == nextCommand) {
				chatScreenForm.goToNextPrompt();
			} else if (command == prevCommand) {
				chatScreenForm.goToPreviousPrompt();
			}			 
		} catch (Exception e) {
			Alert a = new Alert("error.screen" + " 2"); //$NON-NLS-1$
			a.setString(e.getMessage());
			a.setTimeout(Alert.FOREVER);
			display.setCurrent(a);
		}
	}

	// Initialize. If a data member is not backed by RMS, make sure
	// it is uninitialized (null) before you put in values.
	protected void initModel() throws Exception {
		
	}

	protected void createView() {}

	protected void updateView() throws Exception {}
	

  	public void registerController(Controller controller) {
  		this.controller = controller;		
	}

	public void showPrompt(Prompt prompt) {
		this.p = prompt;
		showScreen();
	}

	public void showPrompt(Prompt prompt, int screenIndex, int totalScreens) {
		this.p = prompt;
		this.screenIndex = screenIndex;
		this.totalScreens = totalScreens;
		showScreen();
	}
	
}
